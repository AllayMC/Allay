package org.allaymc.api.blockentity.component;

public interface BlockEntityBedBaseComponent extends BlockEntityBaseComponent {
    /**
     * get the color of beds
     * @return the data value of color
     */
    byte getColor();

    /**
     * set the color of beds
     * @param color the data value of color
     */
    void setColor(byte color);
}
