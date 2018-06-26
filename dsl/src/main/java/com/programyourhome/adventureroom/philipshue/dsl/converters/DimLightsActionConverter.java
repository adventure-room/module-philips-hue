package com.programyourhome.adventureroom.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.philipshue.model.DimLightsAction;

public class DimLightsActionConverter extends AbstractPhilipsHueActionConverter<DimLightsAction> {

    @Override
    public String getRegexLine() {
        return "dim the lights to " + INTEGER;
    }

    @Override
    public DimLightsAction convert(MatchResult matchResult, Adventure adventure) {
        DimLightsAction action = new DimLightsAction();
        action.basisPoints = Integer.parseInt(matchResult.getValue(INTEGER)) * 100;
        return action;
    }

}
