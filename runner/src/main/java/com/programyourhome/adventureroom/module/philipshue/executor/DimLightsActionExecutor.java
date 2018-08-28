package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.DimLightsAction;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class DimLightsActionExecutor extends AbstractPhilipsHueExecutor<DimLightsAction> {

    @Override
    public void execute(DimLightsAction action, ExecutionContext context) {
        action.lights.forEach(light -> this.getPhilipsHue(context).dim(light.idInBridge, action.basisPoints));
    }

}
