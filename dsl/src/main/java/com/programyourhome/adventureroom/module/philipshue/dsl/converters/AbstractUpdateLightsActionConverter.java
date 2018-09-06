package com.programyourhome.adventureroom.module.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.philipshue.model.UpdateLightsAction;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;

public abstract class AbstractUpdateLightsActionConverter extends AbstractPhilipsHueActionConverter<UpdateLightsAction> {

    @Override
    public UpdateLightsAction convert(MatchResult matchResult, Adventure adventure) {
        UpdateLightsAction action = new UpdateLightsAction();
        action.color = matchResult.getOptionalValue(COLOR_ID).map(colorId -> adventure.getResource(ColorRgb.class, colorId));
        action.lights = this.getSingleMultipleOrAllLights(matchResult, adventure);
        action.dim = matchResult.getOptionalValue(INTEGER).map(Integer::parseInt).map(percentage -> percentage * 100);
        action.transitionTime = matchResult.getOptionalValue(DURATION).map(durationMatch -> this.parseDuration(durationMatch));
        return action;
    }

}
