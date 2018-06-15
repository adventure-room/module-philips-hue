package com.programyourhome.adventureroom.philipshue.executor;

import com.programyourhome.adventureroom.philipshue.model.DimTheLightsAction;
import com.programyourhome.adventureroom.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class DimTheLightsActionExecutor extends AbstractPhilipsHueExecutor<DimTheLightsAction> {

    @Override
    public void execute(DimTheLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        philipsHue.getLights().forEach(light -> philipsHue.dim(light.getId(), action.basisPoints));
    }

}
