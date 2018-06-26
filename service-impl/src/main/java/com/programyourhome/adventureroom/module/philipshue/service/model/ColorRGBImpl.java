package com.programyourhome.adventureroom.module.philipshue.service.model;

import com.philips.lighting.hue.sdk.utilities.impl.Color;
import com.programyourhome.adventureroom.module.philipshue.service.model.ColorRGB;

public class ColorRGBImpl implements ColorRGB {

    private final int red;
    private final int green;
    private final int blue;

    public ColorRGBImpl(final int philipsHueColor) {
        this(Color.red(philipsHueColor), Color.green(philipsHueColor), Color.blue(philipsHueColor));
    }

    public ColorRGBImpl(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public int getRed() {
        return this.red;
    }

    @Override
    public int getGreen() {
        return this.green;
    }

    @Override
    public int getBlue() {
        return this.blue;
    }

}
