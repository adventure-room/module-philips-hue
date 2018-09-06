package com.programyourhome.adventureroom.module.philipshue.service.model;

public class SmartPlugImpl implements SmartPlug {

    private final int id;
    private final String name;

    public SmartPlugImpl(final HueLight light) {
        this.id = light.getId();
        this.name = light.getName();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
