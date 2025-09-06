package org.allaymc.api.debugshape;

import lombok.Getter;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class DebugText extends DebugShape {

    /**
     * The text to display.
     */
    @Getter
    protected String text;

    /**
     * Creates a DebugText with the specified position, color, and text.
     *
     * @param position The position of the text in the world
     * @param color    The color of the text
     * @param text     The text to display
     */
    public DebugText(Vector3fc position, Color color, String text) {
        super(position, color);
        this.text = text;
    }

    /**
     * Sets the text to display.
     *
     * @param text The text to display
     */
    public void setText(String text) {
        this.text = text;
        this.onChange();
    }
}
