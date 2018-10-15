package com.programyourhome.adventureroom.module.philipshue.executor;

import java.time.Duration;
import java.util.Collection;

import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.model.util.IOUtil;
import com.programyourhome.adventureroom.module.philipshue.model.UpdateLightsAction;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;
import com.programyourhome.adventureroom.module.philipshue.module.PhilipsHueAdventureModule;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightState;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilder;

public class UpdateLightsActionExecutor extends AbstractPhilipsHueExecutor<UpdateLightsAction> {

    private Collection<Light> lights;
    private UpdateLightState updateLightState;
    private boolean shouldStop;

    public UpdateLightsActionExecutor() {
        this.shouldStop = false;
    }

    @Override
    public void execute(UpdateLightsAction action, ExecutionContext context) {
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        UpdateLightStateBuilder builder = philipsHue.updateLightStateBuilder();
        action.dim.ifPresent(builder::dim);
        action.color.ifPresent(builder::colorRgb);
        int defaultTransitionTime = context.getPropertyValue(PhilipsHueAdventureModule.DEFAULT_TRANSITION_TIME_PROPERTY_NAME);
        int transitionTime = action.transitionTime.map(Duration::toMillis).map(Long::intValue).orElse(defaultTransitionTime).intValue();
        builder.transitionTime(transitionTime);

        this.lights = action.lights;
        this.updateLightState = builder.build();
        this.lights.forEach(light -> philipsHue.updateLightState(light.idInBridge, this.updateLightState));
        IOUtil.waitMillisWithCondition(this.updateLightState.getTransitionTime(), () -> this.shouldStop);
    }

    @Override
    public void stop(ExecutionContext context) {
        this.shouldStop = true;
        PhilipsHue philipsHue = this.getPhilipsHue(context);
        this.lights.forEach(light -> philipsHue.updateLightState(light.idInBridge, this.updateLightState.withTransitionTime(0)));
    }

}
