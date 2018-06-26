package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.TurnOffLightsAction;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class TurnOffLightsActionExecutor extends AbstractPhilipsHueExecutor<TurnOffLightsAction> {

    @Override
    public void execute(TurnOffLightsAction action, ExecutionContext context) {
        context.getResources(Light.class).forEach(light -> this.getPhilipsHue(context).turnOffLight(light.idInBridge));
    }

}
