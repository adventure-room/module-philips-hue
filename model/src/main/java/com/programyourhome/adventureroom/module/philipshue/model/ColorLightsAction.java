package com.programyourhome.adventureroom.module.philipshue.model;

import java.util.Collection;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRGB;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;

public class ColorLightsAction implements Action {

    public Collection<Light> lights;
    public ColorRGB color;

}
