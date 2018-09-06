package com.programyourhome.adventureroom.module.philipshue.executor;

import java.time.Duration;

import com.programyourhome.adventureroom.module.philipshue.model.DimLightsAction;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class DimLightsActionExecutor extends AbstractPhilipsHueExecutor<DimLightsAction> {

    @Override
    public void execute(DimLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        int transitionTime = action.transitionTime.map(Duration::toMillis).orElse(0L).intValue();// TODO: get default from context
        action.lights.forEach(light -> philipsHue.updateLightState(philipsHue.lightStateBuilder(light.idInBridge)
                .dim(action.basisPoints)
                .transitionTime(transitionTime)));
        try {
            Thread.sleep(transitionTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
