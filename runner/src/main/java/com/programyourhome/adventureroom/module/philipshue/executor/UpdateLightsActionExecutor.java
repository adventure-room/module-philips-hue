package com.programyourhome.adventureroom.module.philipshue.executor;

import java.time.Duration;

import com.programyourhome.adventureroom.module.philipshue.model.UpdateLightsAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightState;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilder;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class UpdateLightsActionExecutor extends AbstractPhilipsHueExecutor<UpdateLightsAction> {

    @Override
    public void execute(UpdateLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        UpdateLightStateBuilder builder = philipsHue.updateLightStateBuilder();
        action.dim.ifPresent(builder::dim);
        action.color.ifPresent(builder::colorRgb);
        // TODO: get default from context
        int transitionTime = action.transitionTime.map(Duration::toMillis).orElse(0L).intValue();
        builder.transitionTime(transitionTime);

        UpdateLightState updateLightState = builder.build();
        action.lights.forEach(light -> philipsHue.updateLightState(light.idInBridge, updateLightState));
        // TODO: 'sleep util'?
        try {
            Thread.sleep(updateLightState.getTransitionTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
