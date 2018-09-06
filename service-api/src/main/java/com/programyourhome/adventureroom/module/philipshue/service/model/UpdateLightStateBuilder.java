package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;

/**
 * Builder interface that defines how you can build a new state for a Hue Light to be set with the service.
 * Setting any property other than 'off' means the light will be turned on, even if 'on' is not called specifically.
 * NB: Only one of the colorXxx methods should be called, since they are mutually exclusive.
 * If multiple colorXxx methods are called, only the last one will have an effect.
 */
public interface UpdateLightStateBuilder {

    /**
     * Turn on the light.
     */
    public UpdateLightStateBuilder on();

    /**
     * Turn off the light.
     */
    public UpdateLightStateBuilder off();

    /**
     * Set the light to on or off, depending on the parameter value.
     */
    public UpdateLightStateBuilder setOn(boolean on);

    /**
     * Dim the light to the indicated basis points [0, 10000].
     * Dimming to 0 is possible, this is not the same as off.
     * Dimming to 10000 means setting the light to it's maximum brightness.
     */
    public UpdateLightStateBuilder dim(int dimBasisPoints);

    /**
     * Set the color of the light to the indicated color, defined with RGB values.
     * Please note that not all lights support all colors. For more information, see colorXY.
     */
    public UpdateLightStateBuilder colorRgb(ColorRgb color);

    /**
     * Set the color of the light to the specified hue and saturation basis points [0, 10000].
     * A hue of 0 translates to an internal value of 0 and 10000 translates to 65535. This in turn is translated
     * to a specific 'base' color with that hue value, see also http://en.wikipedia.org/wiki/Hue.
     * A saturation of 0 means the most 'white' (neutral) color, while 10000 means the most intense, saturated color.
     */
    public UpdateLightStateBuilder colorHueSaturation(int hueBasisPoints, int saturationBasisPoints);

    /**
     * Set the color temperature of the light to the specified basis points [0, 10000] where 0 is the coldest and 10000
     * is the warmest possible color.
     * This will translate (linearly) to an amount of mirek (http://en.wikipedia.org/wiki/Mired) in the range of 153
     * (coolest) to 500 (warmest).
     */
    public UpdateLightStateBuilder colorTemperature(int temperatureBasisPoints);

    /**
     * Set the mood of the light to the indicated mood.
     */
    public UpdateLightStateBuilder mood(Mood mood);

    /**
     * Set the transition time in milliseconds.
     * NB: Will be rounded to a multitude of 100 milliseconds, because that is the resolution of the internal Hue API.
     */
    public UpdateLightStateBuilder transitionTime(int milliseconds);

    /**
     * Build the desired light state update.
     */
    public UpdateLightState build();

}
