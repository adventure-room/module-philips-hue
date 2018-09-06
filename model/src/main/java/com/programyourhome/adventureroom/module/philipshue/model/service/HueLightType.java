package com.programyourhome.adventureroom.module.philipshue.model.service;

import java.util.Arrays;

public enum HueLightType {

    HUE_FULL_COLOR_BULB("LCT", true, true, true, true),
    LIVING_COLORS("LLC", true, true, true, false),
    HUE_LUX_BULB("LWB", true, true, false, false),
    LIVING_WHITES_PLUG("LWL", true, false, false, false);

    private String modelAbbreviation;
    private boolean onOffSwitch;
    private boolean dimmable;
    private boolean fullColor;
    private boolean moodLight;

    private HueLightType(final String modelAbbreviation, final boolean onOffSwitch, final boolean dimmable, final boolean fullColor, final boolean moodLight) {
        this.modelAbbreviation = modelAbbreviation;
        this.onOffSwitch = onOffSwitch;
        this.dimmable = dimmable;
        this.fullColor = fullColor;
        this.moodLight = moodLight;
    }

    public String getModelAbbreviation() {
        return this.modelAbbreviation;
    }

    public boolean isOnOffSwitch() {
        return this.onOffSwitch;
    }

    public boolean isDimmable() {
        return this.dimmable;
    }

    public boolean isFullColor() {
        return this.fullColor;
    }

    public boolean isMoodLight() {
        return this.moodLight;
    }

    public static HueLightType fromModelAbbreviation(final String modelAbbreviation) {
        return Arrays.asList(HueLightType.values()).stream()
                .filter(type -> type.getModelAbbreviation().equals(modelAbbreviation))
                .findFirst()
                .get();
    }

}
