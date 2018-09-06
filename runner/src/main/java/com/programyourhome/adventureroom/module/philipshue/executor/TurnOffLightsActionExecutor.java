package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.TurnOffLightsAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class TurnOffLightsActionExecutor extends AbstractPhilipsHueExecutor<TurnOffLightsAction> {

    @Override
    public void execute(TurnOffLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        action.lights.forEach(light -> philipsHue.updateLightState(light.idInBridge, philipsHue.updateLightStateBuilder().off().build()));
    }

}
