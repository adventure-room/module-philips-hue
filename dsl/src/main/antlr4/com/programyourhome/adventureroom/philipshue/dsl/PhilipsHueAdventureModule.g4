grammar PhilipsHueAdventureModule;

action: dimTheLightsAction | turnOffLightsAction;

dimTheLightsAction: 'dim the lights to ' NUM;

turnOffLightsAction: 'turn off the lights';

NUM: [0-9]+;
