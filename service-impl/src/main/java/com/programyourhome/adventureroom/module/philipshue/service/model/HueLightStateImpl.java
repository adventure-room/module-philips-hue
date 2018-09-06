package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.philips.lighting.hue.sdk.utilities.PHUtilities;
import com.philips.lighting.hue.sdk.utilities.impl.Color;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLight.PHLightColorMode;
import com.philips.lighting.model.PHLightState;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;
import com.programyourhome.adventureroom.module.philipshue.model.service.ColorMode;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLightState;
import com.programyourhome.adventureroom.module.philipshue.service.util.ValueUtil;

/**
 * For more details on the meaning of the properties, see the UpdateLightStateBuilder class.
 */
public class HueLightStateImpl implements HueLightState {

    private boolean on;
    private Integer dim;
    private ColorMode colorMode;
    private Integer hue;
    private Integer saturation;
    private Integer colorTemperature;
    private ColorRgb colorRgb;

    private HueLightStateImpl() {
    }

    public HueLightStateImpl(final PHLight phLight) {
        PHLightState phLightState = phLight.getLastKnownLightState();
        this.on = phLightState.isOn();
        this.dim = phLightState.getBrightness() == null ? null
                : ValueUtil.brightnessToBasisPoints(phLightState.getBrightness());
        this.colorMode = this.fromPhColorMode(phLightState.getColorMode());
        if (this.colorMode != ColorMode.NONE) {
            this.hue = phLightState.getHue() == null ? null : ValueUtil.hueToBasisPoints(phLightState.getHue());
            this.saturation = phLightState.getSaturation() == null ? null
                    : ValueUtil.saturationToBasisPoints(phLightState.getSaturation());
            this.colorTemperature = phLightState.getCt() == null ? null
                    : ValueUtil.colorTemperatureToBasisPoints(phLightState.getCt());
            if (phLightState.getX() != null && phLightState.getY() != null) {
                final float[] points = new float[2];
                points[0] = phLightState.getX();
                points[1] = phLightState.getY();
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
    public boolean isOn() {
        return this.on;
    }

    @Override
    public Integer getDim() {
        return this.dim;
    }

    @Override
    public ColorMode getColorMode() {
        return this.colorMode;
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
    public ColorRgb getColorRgb() {
        return this.colorRgb;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final HueLightStateImpl hueLightState;

        public Builder() {
            this.hueLightState = new HueLightStateImpl();
            this.hueLightState.colorMode = ColorMode.NONE;
        }

        public Builder on() {
            this.hueLightState.on = true;
            return this;
        }

        public Builder off() {
            this.hueLightState.on = false;
            return this;
        }

        public Builder setOn(boolean on) {
            this.hueLightState.on = on;
            return this;
        }

        public Builder dim(int dim) {
            this.hueLightState.on = true;
            this.hueLightState.dim = dim;
            return this;
        }

        public Builder colorRgb(ColorRgb colorRgb) {
            this.hueLightState.on = true;
            this.hueLightState.colorMode = ColorMode.RGB;
            this.hueLightState.colorRgb = colorRgb;
            return this;
        }

        public Builder hueSaturation(int hue, int saturation) {
            this.hueLightState.on = true;
            this.hueLightState.colorMode = ColorMode.HUE_SATURATION;
            this.hueLightState.hue = hue;
            this.hueLightState.saturation = saturation;
            return this;
        }

        public Builder colorTemperature(int colorTemperature) {
            this.hueLightState.on = true;
            this.hueLightState.colorMode = ColorMode.TEMPERATURE;
            this.hueLightState.colorTemperature = colorTemperature;
            return this;
        }

        public HueLightStateImpl build() {
            return this.hueLightState;
        }
    }

}
