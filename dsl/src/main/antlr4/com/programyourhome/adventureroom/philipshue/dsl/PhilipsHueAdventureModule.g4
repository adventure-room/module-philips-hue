grammar PhilipsHueAdventureModule;

action: dimTheLightsAction | someOtherAction;

dimTheLightsAction: 'dim the lights to ' NUM;

someOtherAction: 'currently undefined';

NUM: [0-9]+;
