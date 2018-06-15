package com.programyourhome.adventureroom.philipshue.service;

import java.util.List;
import java.util.Map;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHHueParsingError;
import com.philips.lighting.model.PHLight;

public class SDKListener implements PHSDKListener, PHLightListener {

    // private final PHHueSDK sdk;
    //
    // public SDKListener() {
    // this.sdk = PHHueSDK.getInstance();
    // }

    @Override
    public void onAccessPointsFound(final List<PHAccessPoint> accessPoints) {
        // Handle your bridge search results here. Typically if multiple results are returned you will want to display
        // them in a list and let the user select their bridge. If one is found you may opt to connect automatically to
        // that bridge.
        System.out.println("onAccessPointsFound: " + accessPoints);
    }

    @Override
    public void onAuthenticationRequired(final PHAccessPoint accessPoint) {
        // Arriving here indicates that Pushlinking is required (to prove the User has physical access to the bridge).
        // Typically here you will display a pushlink image (with a timer) indicating to to the user they need to push
        // the button on their bridge within 30 seconds.
        System.out.println("onAuthenticationRequired: " + accessPoint);
    }

    @Override
    public void onBridgeConnected(final PHBridge bridge, String username) {
        System.out.println("onBridgeConnected: " + bridge);
    }

    @Override
    public void onCacheUpdated(final List<Integer> cacheNotificationsList, final PHBridge bridge) {
        System.out.println("onCacheUpdated: " + cacheNotificationsList + ", " + bridge);
    }

    @Override
    public void onReceivingLightDetails(final PHLight paramPHLight) {
        System.out.println("onReceivingLightDetails: " + paramPHLight);
    }

    @Override
    public void onReceivingLights(final List<PHBridgeResource> paramList) {
        System.out.println("onReceivingLights: " + paramList);
    }

    @Override
    public void onSearchComplete() {
        System.out.println("onSearchComplete");
    }

    @Override
    public void onStateUpdate(final Map<String, String> paramMap, final List<PHHueError> paramList) {
        System.out.println("onStateUpdate: " + paramMap + ", " + paramList);
    }

    @Override
    public void onSuccess() {
        System.out.println("onSuccess");
    }

    @Override
    public void onConnectionLost(final PHAccessPoint accessPoint) {
        // Bridge disconnected
        System.out.println("onConnectionLost: " + accessPoint);
    }

    @Override
    public void onConnectionResumed(final PHBridge bridge) {
        // This method will be called on every 'tick' of the heartbeat. Not sure this is a bug or a feature.
        // No implementation necessary.
        System.out.println("onConnectionResumed aka heartbeat");
    }

    @Override
    public void onError(final int code, final String message) {
        System.out.println("onError: " + code + ", " + message);
    }

    @Override
    public void onParsingErrors(final List<PHHueParsingError> parsingErrorsList) {
        // Any JSON parsing errors are returned here. Typically your program should never return these.
        System.out.println("onParsingErrors: " + parsingErrorsList);
    }

}
