package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.philips.lighting.hue.sdk.utilities.PHUtilities;
import com.philips.lighting.model.PHLight;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLight;
import com.programyourhome.adventureroom.module.philipshue.service.model.LightType;
import com.programyourhome.adventureroom.module.philipshue.service.util.ValueUtil;

public class HueLightImpl implements HueLight {

    private final int id;
    private final String name;
    private final LightType type;
    private final boolean on;
    private final Integer dim;
    private final Integer hue;
    private final Integer saturation;
    private final Integer colorTemperature;
    private ColorRGBImpl colorRGB;

    public HueLightImpl(final PHLight phLight) {
        this.id = Integer.parseInt(phLight.getIdentifier());
        this.name = phLight.getName();
        this.type = LightType.fromModelAbbreviation(phLight.getModelNumber().substring(0, 3));
        this.on = phLight.getLastKnownLightState().isOn();
        this.dim = phLight.getLastKnownLightState().getBrightness() == null ? null
                : ValueUtil.brightnessToBasisPoints(phLight.getLastKnownLightState().getBrightness());
        this.hue = phLight.getLastKnownLightState().getHue() == null ? null : ValueUtil.hueToBasisPoints(phLight.getLastKnownLightState().getHue());
        this.saturation = phLight.getLastKnownLightState().getSaturation() == null ? null
                : ValueUtil.saturationToBasisPoints(phLight.getLastKnownLightState().getSaturation());
        this.colorTemperature = phLight.getLastKnownLightState().getCt() == null ? null
                : ValueUtil.colorTemperatureToBasisPoints(phLight.getLastKnownLightState().getCt());
        if (phLight.getLastKnownLightState().getX() != null && phLight.getLastKnownLightState().getY() != null) {
            final float[] points = new float[2];
            points[0] = phLight.getLastKnownLightState().getX();
            points[1] = phLight.getLastKnownLightState().getY();
            this.colorRGB = new ColorRGBImpl(PHUtilities.colorFromXY(points, phLight.getModelNumber()));
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LightType getType() {
        return this.type;
    }

    @Override
    public boolean isOn() {
        return this.on;
    }

    @Override
    public Integer getDim() {
        return this.dim;
    }

    @Override
    public Integer getHue() {
        return this.hue;
    }

    @Override
    public Integer getSaturation() {
        return this.saturation;
    }

    @Override
    public Integer getColorTemperature() {
        return this.colorTemperature;
    }

    @Override
    public ColorRGBImpl getColorRGB() {
        return this.colorRGB;
    }

}
