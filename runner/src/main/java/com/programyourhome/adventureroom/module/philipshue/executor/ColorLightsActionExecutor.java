package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.ColorLightsAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class ColorLightsActionExecutor extends AbstractPhilipsHueExecutor<ColorLightsAction> {

    @Override
    public void execute(ColorLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        action.lights.forEach(light -> philipsHue.updateLightState(light.idInBridge, philipsHue.lightStateBuilder().colorRgb(action.color)));
    }

}
