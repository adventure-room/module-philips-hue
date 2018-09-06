package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import java.util.Map;

public class UpdateDimLightsActionConverter extends AbstractUpdateLightsActionConverter {

    @Override
    public Map<String, String> getRegexMap() {
        String integerOptionalDuration = INTEGER + this.optional(" in " + DURATION);
        return this.createRegexes(
                SINGLE, "dim light " + LIGHT_ID + " to " + integerOptionalDuration,
                MULTIPLE, "dim lights " + LIGHT_IDS + " to " + integerOptionalDuration,
                ALL, "dim all lights to " + integerOptionalDuration);
    }

}
