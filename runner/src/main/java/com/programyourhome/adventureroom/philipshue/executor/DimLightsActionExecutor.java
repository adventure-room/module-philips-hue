package com.programyourhome.adventureroom.philipshue.executor;

import com.programyourhome.adventureroom.philipshue.model.DimLightsAction;
import com.programyourhome.adventureroom.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class DimLightsActionExecutor extends AbstractPhilipsHueExecutor<DimLightsAction> {

    @Override
    public void execute(DimLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        philipsHue.getLights().forEach(light -> philipsHue.dim(light.getId(), action.basisPoints));
    }

}
