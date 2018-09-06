package com.programyourhome.adventureroom.module.philipshue.service;

import java.util.Collection;
import java.util.Optional;

import com.programyourhome.adventureroom.module.philipshue.service.model.HueLight;
import com.programyourhome.adventureroom.module.philipshue.service.model.SmartPlug;
import com.programyourhome.adventureroom.module.philipshue.service.model.UpdateLightStateBuilder;

public interface PhilipsHue {

    /**
     * Connect to the Hue bridge.
     */
    public void connectToBridge(String host, String username);

    /**
     * Returns whether this module is currently connected to the Hue Bridge.
     */
    public boolean isConnectedToBridge();

    /**
     * Disconnect from the bridge.
     */
    public void disconnectFromBridge();

    /**
     * Returns all lights that are connected to the Hue Bridge.
     */
    public Collection<HueLight> getLights();

    /**
     * Return the light with the provided id.
     */
    public Optional<HueLight> getLight(int lightId);

    /**
     * Returns all plugs that are connected to the Hue Bridge.
     */
    public Collection<SmartPlug> getPlugs();

    /**
     * Return the plug with the provided id.
     */
    public Optional<SmartPlug> getPlug(int plugId);

    /**
     * Create a new light state builder for the given light id.
     */
    public UpdateLightStateBuilder lightStateBuilder(int lightId);

    /**
     * One method for updating the light state of a light.
     * It uses the builder to define the new state of the light and apply it via the Hue Bridge.
     * This way we prevent an enormous overload of update methods for all different properties of a light state.
     */
    public void updateLightState(UpdateLightStateBuilder hueLightStateBuilder);

    /**
     * Turn on the plug with this id.
     */
    public void turnOnPlug(int plugId);

    /**
     * Turn off the plug with this id.
     */
    public void turnOffPlug(int plugId);

}
