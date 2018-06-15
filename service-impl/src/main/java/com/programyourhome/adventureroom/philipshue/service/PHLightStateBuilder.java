package com.programyourhome.adventureroom.philipshue.service;

import java.awt.Color;

import com.philips.lighting.hue.sdk.utilities.PHUtilities;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLight.PHLightColorMode;
import com.programyourhome.adventureroom.philipshue.service.model.Mood;
import com.programyourhome.adventureroom.philipshue.service.util.ValueUtil;
import com.philips.lighting.model.PHLightState;

public class PHLightStateBuilder {

    private final PHLight phLight;
    private final PHLightState phLightState;

    public PHLightStateBuilder(final PHLight phLight) {
        this(phLight, true);
    }

    public PHLightStateBuilder(final PHLight phLight, final boolean on) {
        this.phLight = phLight;
        this.phLightState = new PHLightState();
        this.ensureLightOnState(phLight.getLastKnownLightState(), on);
    }

    private void ensureNoColorModeYet() {
        if (this.phLightState.getColorMode() != PHLightColorMode.COLORMODE_UNKNOWN) {
            throw new IllegalStateException("Cannot set the color mode twice.");
        }
    }

    /**
     * Ensure the light will be in the desired state by setting it to on if it is currently off and vice versa.
     * So we do not set the 'on' parameter if the light is already in the desired state, for performance reasons.
     * (http://www.developers.meethue.com/faq-page - Why is the bridge response so slow?)
     */
    private void ensureLightOnState(final PHLightState lastKnownLightState, final boolean on) {
        if (lastKnownLightState.isOn() != on) {
            this.phLightState.setOn(on);
        }
    }

    public PHLight getPHLight() {
        return this.phLight;
    }

    public PHLightStateBuilder dim(final int dimBasisPoints) {
        this.phLightState.setBrightness(ValueUtil.basisPointsToBrightness(dimBasisPoints));
        return this;
    }

    public PHLightStateBuilder colorRGB(final Color color) {
        final float[] xy = PHUtilities.calculateXYFromRGB(color.getRed(), color.getGreen(), color.getBlue(), this.phLight.getModelNumber());
        return this.colorXY(xy[0], xy[1]);
    }

    public PHLightStateBuilder colorXY(final float x, final float y) {
        this.ensureNoColorModeYet();
        this.phLightState.setColorMode(PHLightColorMode.COLORMODE_XY);
        this.phLightState.setX(x);
        this.phLightState.setY(y);
        return this;
    }

    public PHLightStateBuilder colorHueSaturation(final int hueBasisPoints, final int saturationBasisPoints) {
        this.ensureNoColorModeYet();
        this.phLightState.setColorMode(PHLightColorMode.COLORMODE_HUE_SATURATION);
        this.phLightState.setHue(ValueUtil.basisPointsToHue(hueBasisPoints));
        this.phLightState.setSaturation(ValueUtil.basisPointsToSaturation(saturationBasisPoints));
        return this;
    }

    public PHLightStateBuilder colorTemperature(final int temperatureBasisPoints) {
        return this.setColorTemperature(ValueUtil.basisPointsToColorTemperature(temperatureBasisPoints));
    }

    private PHLightStateBuilder setColorTemperature(final int temperature) {
        this.ensureNoColorModeYet();
        this.phLightState.setColorMode(PHLightColorMode.COLORMODE_CT);
        this.phLightState.setCt(temperature);
        return this;
    }

    public PHLightStateBuilder mood(final Mood mood) {
        return this.setColorTemperature(mood.getTemperature());
    }

    /**
     * Return the actual light state object after it was build with the other methods.
     *
     * @return the light state object that was built
     */
    public PHLightState build() {
        return this.phLightState;
    }

}
