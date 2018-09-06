package com.programyourhome.adventureroom.module.philipshue.model;

import java.util.Collection;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;
import com.programyourhome.adventureroom.module.philipshue.model.service.UpdateLightState;

public class UpdateLightsAction implements Action {

    public Collection<Light> lights;
    public UpdateLightState updateLightState;

}
