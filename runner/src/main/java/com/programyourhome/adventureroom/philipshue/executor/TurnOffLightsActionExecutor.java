package com.programyourhome.adventureroom.philipshue.executor;

import com.programyourhome.adventureroom.philipshue.model.TurnOffLightsAction;
import com.programyourhome.adventureroom.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class TurnOffLightsActionExecutor extends AbstractPhilipsHueExecutor<TurnOffLightsAction> {

    @Override
    public void execute(TurnOffLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        philipsHue.getLights().forEach(light -> philipsHue.turnOffLight(light.getId()));
    }

}
