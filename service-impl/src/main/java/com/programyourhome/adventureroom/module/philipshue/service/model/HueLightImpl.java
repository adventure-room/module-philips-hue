package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.philips.lighting.model.PHLight;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLight;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLightState;
import com.programyourhome.adventureroom.module.philipshue.model.service.HueLightType;

/**
 * For more details on the meaning of the properties, see the UpdateLightStateBuilder class.
 */
public class HueLightImpl implements HueLight {

    private final int id;
    private final String name;
    private final HueLightType type;
    private final HueLightStateImpl state;

    public HueLightImpl(final PHLight phLight) {
        this.id = Integer.parseInt(phLight.getIdentifier());
        this.name = phLight.getName();
        this.type = HueLightType.fromModelAbbreviation(phLight.getModelNumber().substring(0, 3));
        this.state = new HueLightStateImpl(phLight);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public HueLightType getType() {
        return this.type;
    }

    @Override
    public HueLightState getState() {
        return this.state;
    }

}
