package com.programyourhome.adventureroom.module.philipshue.executor;

import com.programyourhome.adventureroom.module.philipshue.model.ColorLightAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.adventureroom.module.philipshue.service.model.ColorRGB;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class ColorLightActionExecutor extends AbstractPhilipsHueExecutor<ColorLightAction> {

    @Override
    public void execute(ColorLightAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        // TODO: there must be an easier way...
        philipsHue.setColorRGB(action.light.idInBridge, new ColorRGB() {
            @Override
            public int getRed() {
                return action.color.red;
            }

            @Override
            public int getGreen() {
                return action.color.green;
            }

            @Override
            public int getBlue() {
                return action.color.blue;
            }
        });
    }

}
