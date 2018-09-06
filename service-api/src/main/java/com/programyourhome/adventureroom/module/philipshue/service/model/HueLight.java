package com.programyourhome.adventureroom.module.philipshue.service.model;

/**
 * The Hue Light interface has the static properties and a state.
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
     * The state of the light.
     */
    public HueLightState getState();

}
