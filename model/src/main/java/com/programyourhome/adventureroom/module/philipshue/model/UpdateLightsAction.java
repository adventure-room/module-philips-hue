package com.programyourhome.adventureroom.module.philipshue.model;

import java.time.Duration;
import java.util.Collection;
import java.util.Optional;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;

public class UpdateLightsAction implements Action {

    public Collection<Light> lights;
    public Optional<Integer> dim = Optional.empty();
    public Optional<ColorRgb> color = Optional.empty();
    public Optional<Duration> transitionTime = Optional.empty();

}
