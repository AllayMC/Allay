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
     * The scale of the text, which is a multiplier for the size of the text.
     * <p>
     * Can be {@code null}, and in that case that the scale will be set to 1 client-side.
     */
    protected Float scale;

    /**
     * @see #DebugText(Vector3fc, Color, String, Float)
     */
    public DebugText(Vector3fc position, Color color, String text) {
        this(position, color, text, null);
    }

    /**
     * Creates a DebugText with the specified position, color, and text.
     *
     * @param position The position of the text in the world
     * @param color    The color of the text
     * @param text     The text to display
     */
    public DebugText(Vector3fc position, Color color, String text, Float scale) {
        super(position, color);
        this.text = text;
        this.scale = scale;
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

    /**
     * Gets the scale of the text.
     *
     * @return the scale of the text
     */
    public float getScale() {
        return scale != null ? scale : 1.0f;
    }

    /**
     * Sets the scale of the text.
     *
     * @param scale the scale of the text, which is a multiplier for the size of the text
     */
    public void setScale(Float scale) {
        this.scale = scale;
        this.onChange();
    }
}
