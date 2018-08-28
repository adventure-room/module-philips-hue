package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.TurnOffLightsAction;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class TurnOffLightsActionExecutor extends AbstractPhilipsHueExecutor<TurnOffLightsAction> {

    @Override
    public void execute(TurnOffLightsAction action, ExecutionContext context) {
        action.lights.forEach(light -> this.getPhilipsHue(context).turnOffLight(light.idInBridge));
    }

}
