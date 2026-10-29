package org.allaymc.api.primitiveshape;

import lombok.Getter;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * Represents text rendered by the client's primitive shape system.
 *
 * @author daoge_cmd
 */
public class PrimitiveText extends PrimitiveShape {

    /**
     * The text to display.
     */
    @Getter
    protected String text;
    /**
     * Whether this text uses the shape rotation.
     */
    @Getter
    protected boolean useRotation;
    /**
     * The background color of this text.
     * <p>
     * Can be {@code null}, and in that case no explicit background color is sent to the client.
     */
    protected Color backgroundColor;
    /**
     * Whether this text participates in depth testing.
     */
    @Getter
    protected boolean depthTest;
    /**
     * Whether the text background renders its backface.
     */
    @Getter
    protected boolean showBackface;
    /**
     * Whether the text renders its backface.
     */
    @Getter
    protected boolean showTextBackface;

    /**
     * Creates a PrimitiveText with the specified position, color, and text.
     *
     * @param position the position of the text in the world; can be {@code null}
     * @param color    the color of the text; can be {@code null}
     * @param text     the text to display
     */
    public PrimitiveText(Vector3fc position, Color color, String text) {
        this(position, color, text, null);
    }

    /**
     * Creates a PrimitiveText with the specified position, color, and text.
     *
     * @param position the position of the text in the world; can be {@code null}
     * @param color    the color of the text; can be {@code null}
     * @param text     the text to display
     * @param scale    the scale of the text; can be {@code null}
     */
    public PrimitiveText(Vector3fc position, Color color, String text, Float scale) {
        this(position, color, text, scale, false, null, false, false, false);
    }

    /**
     * Creates a PrimitiveText with the specified position, color, text, and text rendering options.
     *
     * @param position         the position of the text in the world; can be {@code null}
     * @param color            the color of the text; can be {@code null}
     * @param text             the text to display
     * @param scale            the scale of the text; can be {@code null}
     * @param useRotation      whether this text uses the shape rotation
     * @param backgroundColor  the background color of this text; can be {@code null}
     * @param depthTest        whether this text participates in depth testing
     * @param showBackface     whether the text background renders its backface
     * @param showTextBackface whether the text renders its backface
     */
    public PrimitiveText(
            Vector3fc position,
            Color color,
            String text,
            Float scale,
            boolean useRotation,
            Color backgroundColor,
            boolean depthTest,
            boolean showBackface,
            boolean showTextBackface
    ) {
        super(position, color, scale, null, null, null);
        this.text = text;
        this.useRotation = useRotation;
        this.backgroundColor = backgroundColor;
        this.depthTest = depthTest;
        this.showBackface = showBackface;
        this.showTextBackface = showTextBackface;
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
     * Sets whether this text uses the shape rotation.
     *
     * @param useRotation whether this text uses the shape rotation
     */
    public void setUseRotation(boolean useRotation) {
        this.useRotation = useRotation;
        this.onChange();
    }

    /**
     * Gets the background color of this text.
     *
     * @return the background color of this text, or {@code null} if no explicit background color is sent
     */
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets the background color of this text.
     *
     * @param backgroundColor the background color of this text; can be {@code null}
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.onChange();
    }

    /**
     * Sets whether this text participates in depth testing.
     *
     * @param depthTest whether this text participates in depth testing
     */
    public void setDepthTest(boolean depthTest) {
        this.depthTest = depthTest;
        this.onChange();
    }

    /**
     * Sets whether the text background renders its backface.
     *
     * @param showBackface whether the text background renders its backface
     */
    public void setShowBackface(boolean showBackface) {
        this.showBackface = showBackface;
        this.onChange();
    }

    /**
     * Sets whether the text renders its backface.
     *
     * @param showTextBackface whether the text renders its backface
     */
    public void setShowTextBackface(boolean showTextBackface) {
        this.showTextBackface = showTextBackface;
        this.onChange();
    }
}
