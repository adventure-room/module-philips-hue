package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.dsl.regex.RegexVariable;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;

import one.util.streamex.StreamEx;

public abstract class AbstractPhilipsHueActionConverter<A extends Action> implements RegexActionConverter<A> {

    public static final RegexVariable LIGHT_ID = RegexActionConverter.RESOURCE_ID.withName("lightId");
    public static final RegexVariable LIGHT_IDS = RegexActionConverter.RESOURCE_IDS.withName("lightIds");
    public static final RegexVariable COLOR_ID = RegexActionConverter.RESOURCE_ID.withName("colorId");
    public static final RegexVariable COLOR_IDS = RegexActionConverter.RESOURCE_IDS.withName("colorIds");

    protected Collection<Light> getSingleMultipleOrAllLights(MatchResult matchResult, Adventure adventure) {
        Collection<String> lightIds = new ArrayList<>();
        if (matchResult.is(SINGLE)) {
            lightIds = Arrays.asList(matchResult.getValue(LIGHT_ID));
        } else if (matchResult.is(MULTIPLE)) {
            lightIds = Arrays.asList(matchResult.getValue(LIGHT_IDS).split(","));
        } else if (matchResult.is(ALL)) {
            lightIds = adventure.getResourceMap(Light.class).keySet();
        }
        return StreamEx.of(lightIds)
                .map(lightId -> adventure.getResource(Light.class, lightId))
                .toList();
    }

}
