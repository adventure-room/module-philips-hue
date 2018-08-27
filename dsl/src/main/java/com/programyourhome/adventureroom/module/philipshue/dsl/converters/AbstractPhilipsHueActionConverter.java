package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.dsl.regex.RegexVariable;
import com.programyourhome.adventureroom.model.script.action.Action;

public abstract class AbstractPhilipsHueActionConverter<A extends Action> implements RegexActionConverter<A> {

    public static final RegexVariable LIGHT_ID = RegexActionConverter.RESOURCE_ID.withName("lightId");
    public static final RegexVariable LIGHT_IDS = RegexActionConverter.RESOURCE_IDS.withName("lightIds");
    public static final RegexVariable COLOR_ID = RegexActionConverter.RESOURCE_ID.withName("colorId");
    public static final RegexVariable COLOR_IDS = RegexActionConverter.RESOURCE_IDS.withName("colorIds");

}
