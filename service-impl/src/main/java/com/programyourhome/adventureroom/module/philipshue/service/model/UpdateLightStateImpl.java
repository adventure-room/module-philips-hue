package com.programyourhome.adventureroom.module.philipshue.service.model;

public class UpdateLightStateImpl implements UpdateLightState {

    private final HueLightState newState;
    private final int transitionTime;

    public UpdateLightStateImpl(HueLightState newState, int transitionTime) {
        this.newState = newState;
        this.transitionTime = transitionTime;
    }

    @Override
    public HueLightState getNewState() {
        return this.newState;
    }

    @Override
    public int getTransitionTime() {
        return this.transitionTime;
    }

}
