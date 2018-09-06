package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import java.util.Map;
import java.util.Optional;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.philipshue.model.DimLightsAction;

public class DimLightsActionConverter extends AbstractPhilipsHueActionConverter<DimLightsAction> {

    @Override
    public Map<String, String> getRegexMap() {
        return this.createRegexes(
                SINGLE, "dim light " + LIGHT_ID + " to " + INTEGER + this.optional(" in " + DURATION),
                MULTIPLE, "dim lights " + LIGHT_IDS + " to " + INTEGER + this.optional(" in " + DURATION),
                ALL, "dim all lights to " + INTEGER + this.optional(" in " + DURATION));
    }

    @Override
    public DimLightsAction convert(MatchResult matchResult, Adventure adventure) {
        DimLightsAction action = new DimLightsAction();
        action.basisPoints = Integer.parseInt(matchResult.getValue(INTEGER)) * 100;
        action.lights = this.getSingleMultipleOrAllLights(matchResult, adventure);
        matchResult.getOptionalValue(DURATION).ifPresent(durationMatch -> action.transitionTime = Optional.of(this.parseDuration(durationMatch)));
        return action;
    }

}
