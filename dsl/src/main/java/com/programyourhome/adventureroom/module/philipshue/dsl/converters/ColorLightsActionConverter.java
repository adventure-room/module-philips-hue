package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import java.util.Map;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.philipshue.model.ColorLightsAction;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRGB;

public class ColorLightsActionConverter extends AbstractPhilipsHueActionConverter<ColorLightsAction> {

    @Override
    public Map<String, String> getRegexMap() {
        return this.createRegexes(
                SINGLE, "color light " + LIGHT_ID + " to " + COLOR_ID,
                MULTIPLE, "color lights " + LIGHT_IDS + " to " + COLOR_ID,
                ALL, "color all lights to " + COLOR_ID);
    }

    @Override
    public ColorLightsAction convert(MatchResult matchResult, Adventure adventure) {
        ColorLightsAction action = new ColorLightsAction();
        action.color = adventure.getResource(ColorRGB.class, matchResult.getValue(COLOR_ID));
        action.lights = this.getSingleMultipleOrAllLights(matchResult, adventure);
        return action;
    }

}
