package com.programyourhome.adventureroom.module.philipshue.executor;

import java.time.Duration;

import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.module.philipshue.model.UpdateLightsAction;
import com.programyourhome.adventureroom.module.philipshue.module.PhilipsHueAdventureModule;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightState;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilder;

public class UpdateLightsActionExecutor extends AbstractPhilipsHueExecutor<UpdateLightsAction> {

    @Override
    public void execute(UpdateLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        UpdateLightStateBuilder builder = philipsHue.updateLightStateBuilder();
        action.dim.ifPresent(builder::dim);
        action.color.ifPresent(builder::colorRgb);
        int defaultTransitionTime = context.getPropertyValue(PhilipsHueAdventureModule.DEFAULT_TRANSITION_TIME_PROPERTY_NAME);
        int transitionTime = action.transitionTime.map(Duration::toMillis).map(Long::intValue).orElse(defaultTransitionTime).intValue();
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
