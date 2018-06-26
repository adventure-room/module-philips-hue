package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.philipshue.model.TurnOffLightsAction;

public class TurnOffLightsActionConverter extends AbstractPhilipsHueActionConverter<TurnOffLightsAction> {

    @Override
    public String getRegexLine() {
        return "turn off the lights";
    }

    @Override
    public TurnOffLightsAction convert(MatchResult matchResult, Adventure adventure) {
        return new TurnOffLightsAction();
    }

}
