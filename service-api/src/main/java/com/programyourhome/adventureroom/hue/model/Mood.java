package com.programyourhome.adventureroom.hue.model;

public enum Mood {

    ENERGY(155),
    FOCUS(234),
    READ(343),
    RELAX(467);

    private int temperature;

    private Mood(final int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return this.temperature;
    }

}
