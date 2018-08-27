package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.DimLightsAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class DimLightsActionExecutor extends AbstractPhilipsHueExecutor<DimLightsAction> {

    @Override
    public void execute(DimLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        action.lights.forEach(light -> philipsHue.dim(light.idInBridge, action.basisPoints));
    }

}
