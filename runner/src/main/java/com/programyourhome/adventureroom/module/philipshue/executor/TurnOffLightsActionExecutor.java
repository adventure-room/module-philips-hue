package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.module.philipshue.model.TurnOffLightsAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;

public class TurnOffLightsActionExecutor extends AbstractPhilipsHueExecutor<TurnOffLightsAction> {

    @Override
    public void execute(TurnOffLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        action.lights.forEach(light -> philipsHue.updateLightState(light.idInBridge, philipsHue.updateLightStateBuilder().off().build()));
    }

    @Override
    public void stop(ExecutionContext context) {
        // No stop action (yet), cause stopping has no explicit transition time.
        // Should be added though and then stop should stop at current intensity
    }

}
