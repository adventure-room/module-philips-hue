package com.programyourhome.adventureroom.module.philipshue.service.model;

public class UpdateLightStateImpl implements UpdateLightState {

    private final HueLight newState;
    private final int transitionTime;

    public UpdateLightStateImpl(HueLight newState, int transitionTime) {
        this.newState = newState;
        this.transitionTime = transitionTime;
    }

    @Override
    public HueLight getNewState() {
        return this.newState;
    }

    @Override
    public int getTransitionTime() {
        return this.transitionTime;
    }

}
