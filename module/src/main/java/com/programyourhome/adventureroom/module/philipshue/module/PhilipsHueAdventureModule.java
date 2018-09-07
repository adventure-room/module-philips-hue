package com.programyourhome.adventureroom.module.philipshue.module;

import java.util.Arrays;
import java.util.Collection;

import com.programyourhome.adventureroom.dsl.regex.AbstractRegexDslAdventureModule;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.model.resource.ResourceDescriptor;
import com.programyourhome.adventureroom.module.philipshue.dsl.converters.TurnOffLightsActionConverter;
import com.programyourhome.adventureroom.module.philipshue.dsl.converters.UpdateColorLightsActionConverter;
import com.programyourhome.adventureroom.module.philipshue.dsl.converters.UpdateDimLightsActionConverter;
import com.programyourhome.adventureroom.module.philipshue.model.resources.colors.ColorRgb;
import com.programyourhome.adventureroom.module.philipshue.model.resources.lights.Light;
import com.programyourhome.adventureroom.module.philipshue.model.resources.plugs.Plug;
import com.programyourhome.adventureroom.module.philipshue.service.PhilipsHue;

public class PhilipsHueAdventureModule extends AbstractRegexDslAdventureModule {

    public static final String ID = "philipshue";
    public static final String DEFAULT_TRANSITION_TIME_PROPERTY_NAME = "default-transition-time";

    private final PhilipsHue philipsHue;
    private PhilipsHueConfig config;

    public PhilipsHueAdventureModule() {
        this.philipsHue = this.loadApiImpl(PhilipsHue.class);
        this.initConfig();
    }

    private void initConfig() {
        this.config = new PhilipsHueConfig();
        this.config.id = ID;
        this.config.name = "Philips Hue";
        this.config.description = "Module to control Philips Hue devices";

        ResourceDescriptor<Light> lightsDescriptor = new ResourceDescriptor<>();
        lightsDescriptor.id = "lights";
        lightsDescriptor.name = "Hue Light Bulbs";
        lightsDescriptor.clazz = Light.class;
        this.config.addResourceDescriptor(lightsDescriptor);

        ResourceDescriptor<Plug> plugsDescriptor = new ResourceDescriptor<>();
        plugsDescriptor.id = "plugs";
        plugsDescriptor.name = "Hue Compatible socket plugs";
        plugsDescriptor.clazz = Plug.class;
        this.config.addResourceDescriptor(plugsDescriptor);

        ResourceDescriptor<ColorRgb> colorsDescriptor = new ResourceDescriptor<>();
        colorsDescriptor.id = "colors";
        colorsDescriptor.name = "RGB Colors";
        colorsDescriptor.clazz = ColorRgb.class;
        this.config.addResourceDescriptor(colorsDescriptor);

        this.config.addTask("Hue SDK", () -> this.philipsHue.connectToBridge(this.config.bridgeHost, this.config.bridgeUsername));
    }

    @Override
    public void start(Adventure adventure, ExecutionContext context) {
        context.setPropertyValue(DEFAULT_TRANSITION_TIME_PROPERTY_NAME, this.config.defaultTransitionTimeMillis);
    }

    public PhilipsHue getPhilipsHue() {
        return this.philipsHue;
    }

    @Override
    public PhilipsHueConfig getConfig() {
        return this.config;
    }

    @Override
    protected Collection<RegexActionConverter<?>> getRegexActionConverters() {
        return Arrays.asList(new UpdateDimLightsActionConverter(),
                new UpdateColorLightsActionConverter(),
                new TurnOffLightsActionConverter());
    }

    @Override
    public void stop(Adventure adventure, ExecutionContext context) {
        this.philipsHue.disconnectFromBridge();
    }

}
