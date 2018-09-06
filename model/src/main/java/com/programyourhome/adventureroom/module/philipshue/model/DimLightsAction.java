package com.programyourhome.adventureroom.module.philipshue.model;

import java.time.Duration;
import java.util.Collection;
import java.util.Optional;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;

public class DimLightsAction implements Action {

    public int basisPoints;
    public Collection<Light> lights;
    public Optional<Duration> transitionTime = Optional.empty();

}
