package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.ColorLightsAction;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class ColorLightsActionExecutor extends AbstractPhilipsHueExecutor<ColorLightsAction> {

    @Override
    public void execute(ColorLightsAction action, ExecutionContext context) {
        action.lights.forEach(light -> this.getPhilipsHue(context).setColorRGB(light.idInBridge, action.color));
    }

}
