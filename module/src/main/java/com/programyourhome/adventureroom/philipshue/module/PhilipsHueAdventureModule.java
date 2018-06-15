package com.programyourhome.adventureroom.philipshue.module;

import java.util.ServiceLoader;

import com.programyourhome.adventureroom.dsl.antlr.AbstractAntlrDslAdventureModule;
import com.programyourhome.adventureroom.hue.PhilipsHue;
import com.programyourhome.adventureroom.model.resource.ResourceDescriptor;
import com.programyourhome.adventureroom.philipshue.model.resources.lights.Light;
import com.programyourhome.adventureroom.philipshue.model.resources.plugs.Plug;

public class PhilipsHueAdventureModule extends AbstractAntlrDslAdventureModule {

    public static final String ID = "philipshue";

    private PhilipsHue philipsHue;
    private PhilipsHueConfig config;

    public PhilipsHueAdventureModule() {
        super("PhilipsHue");
        // We assume there will be one implementation available on the classpath. If not, behavior is undefined.
        ServiceLoader.load(PhilipsHue.class).forEach(impl -> this.philipsHue = impl);
        this.initConfig();
    }

    private void initConfig() {
        this.config = new PhilipsHueConfig();
        this.config.id = ID;
        this.config.name = "Philips Hue";
        this.config.description = "Module to control Philips Hue devices";

        ResourceDescriptor lightsDescriptor = new ResourceDescriptor();
        lightsDescriptor.id = "lights";
        lightsDescriptor.name = "Hue Light Bulbs";
        lightsDescriptor.clazz = Light.class;
        this.config.resourceDescriptors.put(lightsDescriptor.getId(), lightsDescriptor);

        ResourceDescriptor plugsDescriptor = new ResourceDescriptor();
        plugsDescriptor.id = "plugs";
        plugsDescriptor.name = "Hue Compatible socket plugs";
        plugsDescriptor.clazz = Plug.class;
        this.config.resourceDescriptors.put(plugsDescriptor.getId(), plugsDescriptor);

        this.config.deamons.put("Hue SDK", () -> this.philipsHue.connectToBridge(this.config.bridgeHost, this.config.bridgeUsername));
    }

    public PhilipsHue getPhilipsHue() {
        return this.philipsHue;
    }

    @Override
    public PhilipsHueConfig getConfig() {
        return this.config;
    }

}
