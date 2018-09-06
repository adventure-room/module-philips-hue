package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.philips.lighting.hue.sdk.utilities.PHUtilities;
import com.philips.lighting.hue.sdk.utilities.impl.Color;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLight.PHLightColorMode;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;
import com.programyourhome.adventureroom.module.philipshue.service.util.ValueUtil;

/**
 * For more details on the meaning of the properties, see the UpdateLightStateBuilder class.
 */
public class HueLightStateImpl implements HueLight {

    private int id;
    private String name;
    private HueLightType type;
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

    public static Builder builder(int lightId) {
        return new Builder(lightId);
    }

    public static class Builder {
        private final HueLightStateImpl hueLight;

        public Builder(int lightId) {
            this.hueLight = new HueLightStateImpl();
            this.hueLight.id = lightId;
            this.hueLight.colorMode = ColorMode.NONE;
        }

        public Builder name(String name) {
            this.hueLight.name = name;
            return this;
        }

        public Builder type(HueLightType type) {
            if (type == HueLightType.LIVING_WHITES_PLUG) {
                throw new IllegalArgumentException("Light cannot be of type plug");
            }
            this.hueLight.type = type;
            return this;
        }

        public Builder on() {
            this.hueLight.on = true;
            return this;
        }

        public Builder off() {
            this.hueLight.on = false;
            return this;
        }

        public Builder setOn(boolean on) {
            this.hueLight.on = on;
            return this;
        }

        public Builder dim(int dim) {
            this.hueLight.on = true;
            this.hueLight.dim = dim;
            return this;
        }

        public Builder colorRgb(ColorRgb colorRgb) {
            this.hueLight.on = true;
            this.hueLight.colorMode = ColorMode.RGB;
            this.hueLight.colorRgb = colorRgb;
            return this;
        }

        public Builder hueSaturation(int hue, int saturation) {
            this.hueLight.on = true;
            this.hueLight.colorMode = ColorMode.HUE_SATURATION;
            this.hueLight.hue = hue;
            this.hueLight.saturation = saturation;
            return this;
        }

        public Builder colorTemperature(int colorTemperature) {
            this.hueLight.on = true;
            this.hueLight.colorMode = ColorMode.TEMPERATURE;
            this.hueLight.colorTemperature = colorTemperature;
            return this;
        }

        public HueLightStateImpl build() {
            return this.hueLight;
        }
    }

}
