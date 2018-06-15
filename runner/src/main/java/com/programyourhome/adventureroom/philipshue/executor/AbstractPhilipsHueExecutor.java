package com.programyourhome.adventureroom.philipshue.executor;

import com.programyourhome.adventureroom.hue.PhilipsHue;
import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.philipshue.module.PhilipsHueAdventureModule;
import com.programyourhome.iotadventure.runner.action.executor.ActionExecutor;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public abstract class AbstractPhilipsHueExecutor<A extends Action> implements ActionExecutor<A> {

    protected PhilipsHueAdventureModule getModule(ExecutionContext context) {
        return context.getModule(PhilipsHueAdventureModule.ID);
    }

    protected PhilipsHue getPhilipsHue(ExecutionContext context) {
        return this.getModule(context).getPhilipsHue();
    }

}
