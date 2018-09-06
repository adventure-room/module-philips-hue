package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;

/**
 * The Hue Light interface defines the properties you can get from a Hue Light.
 * For more details on the meaning of these properties, see the HueLightStateBuilder class.
 * NB: The color mode defines which values are relevant for the light color!
 * If the color more is NONE, that means this light does not support colors.
 */
public interface HueLight {

    /**
     * The id of the light.
     */
    public int getId();

    /**
     * The name of the light. Must be unique.
     */
    public String getName();

    /**
     * The type of the light, e.g. full color hue, lux, etc.
     */
    public HueLightType getType();

    /**
     * Whether or not the light is on.
     */
    public boolean isOn();

    /**
     * The 'dim' of this light in basis points [0, 10000].
     */
    public Integer getDim();

    /**
     * The color mode.
     */
    public ColorMode getColorMode();

    /**
     * The 'hue' of this light in basis points [0, 10000].
     */
    public Integer getHue();

    /**
     * The 'saturation' of this light in basis points [0, 10000].
     */
    public Integer getSaturation();

    /**
     * The 'color temperature' of this light in basis points [0, 10000].
     */
    public Integer getColorTemperature();

    /**
     * The (approximated) color of the light in RGB values, [0, 255] each.
     */
    public ColorRgb getColorRgb();

}
