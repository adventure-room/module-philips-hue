package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.philipshue.module.PhilipsHueAdventureModule;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.action.executor.ActionExecutor;

public abstract class AbstractPhilipsHueExecutor<A extends Action> implements ActionExecutor<A> {

    protected PhilipsHueAdventureModule getModule(ExecutionContext context) {
        return context.getModule(PhilipsHueAdventureModule.ID);
    }

    protected PhilipsHue getPhilipsHue(ExecutionContext context) {
        return this.getModule(context).getPhilipsHue();
    }

}
