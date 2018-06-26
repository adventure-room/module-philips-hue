package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.philipshue.model.ColorLightAction;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRGB;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;

public class ColorLightActionConverter extends AbstractPhilipsHueActionConverter<ColorLightAction> {

    @Override
    public String getRegexLine() {
        return "color light " + LIGHT_ID + " to " + COLOR_ID;
    }

    @Override
    public ColorLightAction convert(MatchResult matchResult, Adventure adventure) {
        ColorLightAction action = new ColorLightAction();
        action.light = adventure.getResource(Light.class, matchResult.getValue(LIGHT_ID));
        action.color = adventure.getResource(ColorRGB.class, matchResult.getValue(COLOR_ID));
        return action;
    }

}
