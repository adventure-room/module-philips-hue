package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import java.util.Map;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.philipshue.model.TurnOffLightsAction;

public class TurnOffLightsActionConverter extends AbstractPhilipsHueActionConverter<TurnOffLightsAction> {

    @Override
    public Map<String, String> getRegexMap() {
        return this.createRegexes(
                SINGLE, "turn off light " + LIGHT_ID,
                MULTIPLE, "turn off lights " + LIGHT_IDS,
                ALL, "turn off all lights");
    }

    @Override
    public TurnOffLightsAction convert(MatchResult matchResult, Adventure adventure) {
        TurnOffLightsAction action = new TurnOffLightsAction();
        action.lights = this.getSingleMultipleOrAllLights(matchResult, adventure);
        return action;
    }

}
