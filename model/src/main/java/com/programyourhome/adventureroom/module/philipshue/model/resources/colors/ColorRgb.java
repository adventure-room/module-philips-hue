package com.programyourhome.adventureroom.module.philipshue.model.resources.colors;

import com.programyourhome.adventureroom.model.AbstractDescribable;
import com.programyourhome.adventureroom.model.resource.Resource;

public class ColorRgb extends AbstractDescribable implements Resource {

    private int red;
    private int green;
    private int blue;

    @SuppressWarnings("unused")
    private ColorRgb() {
    }

    public ColorRgb(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

}
