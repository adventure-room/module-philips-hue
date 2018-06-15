package com.programyourhome.adventureroom.philipshue.dsl.converters;

import com.programyourhome.adventureroom.dsl.antlr.AntlrActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.philipshue.dsl.PhilipsHueAdventureModuleParser.TurnOffLightsActionContext;
import com.programyourhome.adventureroom.philipshue.model.TurnOffLightsAction;

public class TurnOffLightsActionConverter implements AntlrActionConverter<TurnOffLightsActionContext, TurnOffLightsAction> {

    @Override
    public TurnOffLightsAction convert(TurnOffLightsActionContext context, Adventure adventure) {
        return new TurnOffLightsAction();
    }

}
