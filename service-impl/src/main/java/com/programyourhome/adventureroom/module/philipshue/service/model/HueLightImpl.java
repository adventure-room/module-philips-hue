package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.philips.lighting.hue.sdk.utilities.PHUtilities;
import com.philips.lighting.hue.sdk.utilities.impl.Color;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLight.PHLightColorMode;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;
import com.programyourhome.adventureroom.module.philipshue.model.service.ColorMode;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLight;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLightState;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLightType;
import com.programyourhome.adventureroom.module.philipshue.service.util.ValueUtil;

/**
 * For more details on the meaning of the properties, see the UpdateLightStateBuilder class.
 */
public class HueLightImpl implements HueLight {

    private final int id;
    private final String name;
    private final HueLightType type;
    private HueLightStateImpl state;

    public HueLightImpl(final PHLight phLight) {
        this.id = Integer.parseInt(phLight.getIdentifier());
        this.name = phLight.getName();
        this.type = HueLightType.fromModelAbbreviation(phLight.getModelNumber().substring(0, 3));
        this.on = phLight.getLastKnownLightState().isOn();
        this.dim = phLight.getLastKnownLightState().getBrightness() == null ? null
                : ValueUtil.brightnessToBasisPoints(phLight.getLastKnownLightState().getBrightness());
        this.colorMode = this.fromPhColorMode(phLight.getLastKnownLightState().getColorMode());
        if (this.colorMode != ColorMode.NONE) {
            this.hue = phLight.getLastKnownLightState().getHue() == null ? null : ValueUtil.hueToBasisPoints(phLight.getLastKnownLightState().getHue());
            this.saturation = phLight.getLastKnownLightState().getSaturation() == null ? null
                    : ValueUtil.saturationToBasisPoints(phLight.getLastKnownLightState().getSaturation());
            this.colorTemperature = phLight.getLastKnownLightState().getCt() == null ? null
                    : ValueUtil.colorTemperatureToBasisPoints(phLight.getLastKnownLightState().getCt());
            if (phLight.getLastKnownLightState().getX() != null && phLight.getLastKnownLightState().getY() != null) {
                final float[] points = new float[2];
                points[0] = phLight.getLastKnownLightState().getX();
                points[1] = phLight.getLastKnownLightState().getY();
                int philipsHueColor = PHUtilities.colorFromXY(points, phLight.getModelNumber());
                this.colorRgb = new ColorRgb(Color.red(philipsHueColor), Color.green(philipsHueColor), Color.blue(philipsHueColor));
            }
        }
    }

    private ColorMode fromPhColorMode(PHLightColorMode phColorMode) {
        ColorMode colorMode = ColorMode.NONE;
        if (phColorMode == PHLightColorMode.COLORMODE_XY) {
            colorMode = ColorMode.RGB;
        } else if (phColorMode == PHLightColorMode.COLORMODE_HUE_SATURATION) {
            colorMode = ColorMode.HUE_SATURATION;
        } else if (phColorMode == PHLightColorMode.COLORMODE_CT) {
            colorMode = ColorMode.TEMPERATURE;
        }
        return colorMode;
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
    public HueLightType getType() {
        return this.type;
    }

    @Override
    public HueLightState getState() {
        return this.state;
    }

}
