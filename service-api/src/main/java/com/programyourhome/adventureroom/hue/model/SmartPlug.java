package com.programyourhome.adventureroom.hue.model;

public interface SmartPlug {

    /**
     * The id of the plug.
     *
     * @return the id of the plug
     */
    public int getId();

    /**
     * The name of the plug. Must be unique.
     *
     * @return the name of the plug
     */
    public String getName();

}
