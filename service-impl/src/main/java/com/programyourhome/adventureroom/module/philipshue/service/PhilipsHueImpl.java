package com.programyourhome.adventureroom.module.philipshue.service;

import java.util.Collection;
import java.util.stream.Collectors;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.adventureroom.module.philipshue.service.model.ColorRGB;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLight;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLightImpl;
import com.programyourhome.adventureroom.module.philipshue.service.model.LightType;
import com.programyourhome.adventureroom.module.philipshue.service.model.Mood;
import com.programyourhome.adventureroom.module.philipshue.service.model.SmartPlug;
import com.programyourhome.adventureroom.module.philipshue.service.model.SmartPlugImpl;

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

        this.sdk.setAppName("Adventure Roome");
        this.sdk.setDeviceName("Adventure Roome- Philips Hue Module");
        this.sdk.getNotificationManager().registerSDKListener(this.sdkListener);

        this.sdk.connect(this.accessPoint);
    }

    @Override
    public boolean isConnectedToBridge() {
        return this.sdk.isAccessPointConnected(this.accessPoint);
    }

    @Override
    public void disconnectFromBridge() {
        this.sdk.disconnect(this.getBridge());
        this.sdk.destroySDK();
    }

    @Override
    public Collection<HueLight> getLights() {
        return this.getAllLights().stream()
                .filter(light -> light.getType() != LightType.LIVING_WHITES_PLUG)
                .collect(Collectors.toList());
    }

    @Override
    public HueLight getLight(final int lightId) {
        return new HueLightImpl(this.getPHLight(lightId));
    }

    @Override
    public Collection<SmartPlug> getPlugs() {
        return this.getAllLights().stream()
                .filter(light -> light.getType() != LightType.LIVING_WHITES_PLUG)
                .map(SmartPlugImpl::new)
                .collect(Collectors.toList());
    }

    @Override
    public void turnOnLight(final int lightId) {
        this.switchLight(lightId, true);
    }

    @Override
    public void turnOffLight(final int lightId) {
        this.switchLight(lightId, false);
    }

    @Override
    public void turnOnPlug(final int plugId) {
        this.switchLight(plugId, true);
    }

    @Override
    public void turnOffPlug(final int plugId) {
        this.switchLight(plugId, false);
    }

    private PHLightStateBuilder createBuilder(final int lightId) {
        return new PHLightStateBuilder(this.getPHLight(lightId));
    }

    private void applyNewState(final PHLightStateBuilder builder) {
        this.getBridge().updateLightState(builder.getPHLight(), builder.build(), this.sdkListener);
    }

    @Override
    public void dim(final int lightId, final int dimBasisPoints) {
        this.applyNewState(this.createBuilder(lightId)
                .dim(dimBasisPoints));
    }

    @Override
    public void setColorRGB(final int lightId, final ColorRGB color) {
        this.applyNewState(this.createBuilder(lightId)
                .colorRGB(color));
    }

    @Override
    public void setMood(final int lightId, final Mood mood) {
        this.applyNewState(this.createBuilder(lightId)
                .mood(mood));
    }

    @Override
    public void setColorXY(final int lightId, final float x, final float y) {
        this.applyNewState(this.createBuilder(lightId)
                .colorXY(x, y));
    }

    @Override
    public void setColorHueSaturation(final int lightId, final int hueBasisPoints, final int saturationBasisPoints) {
        this.applyNewState(this.createBuilder(lightId)
                .colorHueSaturation(hueBasisPoints, saturationBasisPoints));
    }

    @Override
    public void setColorTemperature(final int lightId, final int temperatureBasisPoints) {
        this.applyNewState(this.createBuilder(lightId)
                .colorTemperature(temperatureBasisPoints));
    }

    @Override
    public void dimToColorRGB(final int lightId, final int dimBasisPoints, final ColorRGB color) {
        this.applyNewState(this.createBuilder(lightId)
                .dim(dimBasisPoints)
                .colorRGB(color));
    }

    @Override
    public void dimToColorXY(final int lightId, final int dimBasisPoints, final float x, final float y) {
        this.applyNewState(this.createBuilder(lightId)
                .dim(dimBasisPoints)
                .colorXY(x, y));
    }

    @Override
    public void dimToColorHueSaturation(final int lightId, final int dimBasisPoints, final int hueBasisPoints, final int saturationBasisPoints) {
        this.applyNewState(this.createBuilder(lightId)
                .dim(dimBasisPoints)
                .colorHueSaturation(hueBasisPoints, saturationBasisPoints));
    }

    @Override
    public void dimToColorTemperature(final int lightId, final int dimBasisPoints, final int temperatureBasisPoints) {
        this.applyNewState(this.createBuilder(lightId)
                .dim(dimBasisPoints)
                .colorTemperature(temperatureBasisPoints));
    }

    @Override
    public void dimToMood(final int lightId, final int dimBasisPoints, final Mood mood) {
        this.applyNewState(this.createBuilder(lightId)
                .dim(dimBasisPoints)
                .mood(mood));
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

    private Collection<HueLight> getAllLights() {
        return this.getCache().getAllLights().stream()
                .map(HueLightImpl::new)
                .collect(Collectors.toList());
    }

    private void switchLight(final int lightId, final boolean on) {
        this.applyNewState(new PHLightStateBuilder(this.getPHLight(lightId), on));
    }

}
