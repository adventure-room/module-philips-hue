package com.programyourhome.adventureroom.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.antlr.AntlrActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.philipshue.dsl.PhilipsHueAdventureModuleParser.DimTheLightsActionContext;
import com.programyourhome.adventureroom.philipshue.model.DimTheLightsAction;

public class DimTheLightsActionConverter implements AntlrActionConverter<DimTheLightsActionContext, DimTheLightsAction> {

    @Override
    public DimTheLightsAction convert(DimTheLightsActionContext context, Adventure adventure) {
        DimTheLightsAction action = new DimTheLightsAction();
        action.basisPoints = Integer.parseInt(context.NUM().getText()) * 100;
        return action;
    }

}
