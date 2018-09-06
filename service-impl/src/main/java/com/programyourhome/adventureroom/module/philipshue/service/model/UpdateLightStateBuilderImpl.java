package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;
import com.programyourhome.adventureroom.module.philipshue.service.model.HueLightStateImpl.Builder;
import com.programyourhome.adventureroom.module.philipshue.service.util.ValueUtil;

public class UpdateLightStateBuilderImpl implements UpdateLightStateBuilder {

    public static final int NO_VALUE = -1;

    private final Builder lightBuilder;
    private int transitionTime;

    public UpdateLightStateBuilderImpl() {
        this.lightBuilder = new Builder()
                .dim(NO_VALUE);
        this.transitionTime = NO_VALUE;
    }

    @Override
    public UpdateLightStateBuilder on() {
        this.lightBuilder.on();
        return this;
    }

    @Override
    public UpdateLightStateBuilder off() {
        this.lightBuilder.off();
        return this;
    }

    @Override
    public UpdateLightStateBuilder setOn(boolean on) {
        this.lightBuilder.setOn(on);
        return this;
    }

    @Override
    public UpdateLightStateBuilder dim(int dimBasisPoints) {
        this.lightBuilder.dim(dimBasisPoints);
        return this;
    }

    @Override
    public UpdateLightStateBuilder colorRgb(ColorRgb color) {
        this.lightBuilder.colorRgb(color);
        return this;
    }

    @Override
    public UpdateLightStateBuilder colorHueSaturation(int hueBasisPoints, int saturationBasisPoints) {
        this.lightBuilder.hueSaturation(hueBasisPoints, saturationBasisPoints);
        return this;
    }

    @Override
    public UpdateLightStateBuilder colorTemperature(int temperatureBasisPoints) {
        this.lightBuilder.colorTemperature(temperatureBasisPoints);
        return this;
    }

    @Override
    public UpdateLightStateBuilder mood(Mood mood) {
        this.lightBuilder.colorTemperature(ValueUtil.colorTemperatureToBasisPoints(mood.getTemperature()));
        return this;
    }

    @Override
    public UpdateLightStateBuilder transitionTime(int milliseconds) {
        this.transitionTime = milliseconds;
        return this;
    }

    @Override
    public UpdateLightState build() {
        return new UpdateLightStateImpl(this.lightBuilder.build(), this.transitionTime);
    }
}
