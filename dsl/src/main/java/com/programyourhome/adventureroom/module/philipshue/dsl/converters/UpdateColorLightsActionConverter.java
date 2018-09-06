package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import java.util.Map;

public class UpdateColorLightsActionConverter extends AbstractUpdateLightsActionConverter {

    @Override
    public Map<String, String> getRegexMap() {
        String colorOptionalDimOptionalDuration = COLOR_ID + this.optional(" and dim to " + INTEGER) + this.optional(" in " + DURATION);
        return this.createRegexes(
                SINGLE, "color light " + LIGHT_ID + " to " + colorOptionalDimOptionalDuration,
                MULTIPLE, "color lights " + LIGHT_IDS + " to " + colorOptionalDimOptionalDuration,
                ALL, "color all lights to " + colorOptionalDimOptionalDuration);
    }

}
