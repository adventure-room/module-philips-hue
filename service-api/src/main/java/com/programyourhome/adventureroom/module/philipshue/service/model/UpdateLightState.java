package com.programyourhome.adventureroom.module.philipshue.service.model;

public interface UpdateLightState {

    public HueLightState getNewState();

    public int getTransitionTime();

    public default UpdateLightState withTransitionTime(int millis) {
        return new UpdateLightState() {

            @Override
            public HueLightState getNewState() {
                return UpdateLightState.this.getNewState();
            }

            @Override
            public int getTransitionTime() {
                return millis;
            }

        };
    }

}
