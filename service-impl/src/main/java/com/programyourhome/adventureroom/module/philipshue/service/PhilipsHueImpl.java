package com.programyourhome.adventureroom.module.philipshue.service;

import static com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilderImpl.NO_VALUE;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.programyourhome.adventureroom.module.philipshue.service.model.ColorMode;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLight;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLightImpl;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLightType;
import com.programyourhome.adventureroom.module.philipshue.service.model.SmartPlug;
import com.programyourhome.adventureroom.module.philipshue.service.model.SmartPlugImpl;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightState;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilder;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilderImpl;

import one.util.streamex.StreamEx;

public class PhilipsHueImpl implements PhilipsHue {

    private PHHueSDK sdk;
    private PHAccessPoint accessPoint;
    private SDKListener sdkListener;

    @Override
    public void connectToBridge(String host, String username) {
        this.sdk = PHHueSDK.getInstance();
        this.accessPoint = new PHAccessPoint();
        this.sdkListener = new SDKListener();

        this.accessPoint.setIpAddress(host);
        this.accessPoint.setUsername(username);

        this.sdk.setAppName("Adventure Room");
        this.sdk.setDeviceName("Adventure Room- Philips Hue Module");
        this.sdk.getNotificationManager().registerSDKListener(this.sdkListener);

        this.sdk.connect(this.accessPoint);
    }

    @Override
    public boolean isConnectedToBridge() {
        return this.sdk.isAccessPointConnected(this.accessPoint);
    }

    @Override
    public void disconnectFromBridge() {
        if (this.isConnectedToBridge()) {
            this.sdk.disconnect(this.getBridge());
        }
        this.sdk.destroySDK();
    }

    @Override
    public Collection<HueLight> getLights() {
        return this.getAllHueLights().stream()
                .filter(light -> light.getType() != HueLightType.LIVING_WHITES_PLUG)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HueLight> getLight(final int lightId) {
        return StreamEx.of(this.getLights())
                .filter(light -> light.getId() == lightId)
                .findFirst();
    }

    @Override
    public Collection<SmartPlug> getPlugs() {
        return this.getAllHueLights().stream()
                .filter(light -> light.getType() == HueLightType.LIVING_WHITES_PLUG)
                .map(SmartPlugImpl::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SmartPlug> getPlug(int plugId) {
        return StreamEx.of(this.getPlugs())
                .filter(plug -> plug.getId() == plugId)
                .findFirst();
    }

    private void switchLight(final int lightId, final boolean on) {
        this.applyNewState(new PHLightStateBuilder(this.getPHLight(lightId), on));
    }

    @Override
    public void turnOnPlug(final int plugId) {
        this.switchLight(plugId, true);
    }

    @Override
    public void turnOffPlug(final int plugId) {
        this.switchLight(plugId, false);
    }

    @Override
    public UpdateLightStateBuilder lightStateBuilder(int lightId) {
        return new UpdateLightStateBuilderImpl(lightId);
    }

    private PHLightStateBuilder createPhBuilder(int lightId) {
        return new PHLightStateBuilder(this.getPHLight(lightId));
    }

    private void applyNewState(final PHLightStateBuilder builder) {
        this.getBridge().updateLightState(builder.getPHLight(), builder.build(), this.sdkListener);
    }

    @Override
    public void updateLightState(UpdateLightStateBuilder hueLightStateBuilder) {
        UpdateLightState updateLightState = hueLightStateBuilder.build();
        PHLightStateBuilder phBuilder;
        HueLight newState = updateLightState.getNewState();
        if (!newState.isOn()) {
            phBuilder = new PHLightStateBuilder(this.getPHLight(newState.getId()), false);
        } else {
            phBuilder = this.createPhBuilder(newState.getId());
            if (newState.getDim() != NO_VALUE) {
                phBuilder.dim(newState.getDim());
            }
            if (newState.getColorMode() == ColorMode.RGB) {
                phBuilder.colorRGB(newState.getColorRgb());
            }
            if (newState.getColorMode() == ColorMode.HUE_SATURATION) {
                phBuilder.colorHueSaturation(newState.getHue(), newState.getSaturation());
            }
            if (newState.getColorMode() == ColorMode.TEMPERATURE) {
                phBuilder.colorTemperature(newState.getColorTemperature());
            }
            if (updateLightState.getTransitionTime() != NO_VALUE) {
                phBuilder.transitionTime(updateLightState.getTransitionTime());
            }
        }
        this.applyNewState(phBuilder);
    }

    private PHBridge getBridge() {
        return this.sdk.getSelectedBridge();
    }

    private PHBridgeResourcesCache getCache() {
        return this.getBridge().getResourceCache();
    }

    private PHLight getPHLight(final int lightId) {
        return this.getCache().getAllLights().stream()
                .filter(phLight -> phLight.getIdentifier().equals(Integer.toString(lightId)))
                .findFirst()
                .get();
    }

    private Collection<? extends HueLight> getAllHueLights() {
        return StreamEx.of(this.getCache().getAllLights())
                .map(HueLightImpl::new)
                .toList();
    }

}
