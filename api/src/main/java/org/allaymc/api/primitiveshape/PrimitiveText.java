package org.allaymc.api.primitiveshape;

import lombok.Getter;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * {@code PrimitiveText} represents displayed text with optional background color and
 * client rendering flags. Its inherited position, color, scale, and viewer registration
 * follow the primitive-shape contract. Text and flag setters synchronously notify
 * registered viewers; constructors initially disable all rendering flags unless supplied
 * explicitly.
 * @author daoge_cmd
 */
public class PrimitiveText extends PrimitiveShape {

    /**
     * Text content sent to the viewing destination.
     */
    @Getter
    protected String text;
    /**
     * Whether the text uses the shape rotation.
     */
    @Getter
    protected boolean useRotation;
    /**
     * {@code backgroundColor} stores the optional text background color. A {@code null}
     * value remains absent when the shape is converted to its network representation.
     */
    protected Color backgroundColor;
    /**
     * Whether text participates in client depth testing.
     */
    @Getter
    protected boolean depthTest;
    /**
     * Whether the text background renders its backface.
     */
    @Getter
    protected boolean showBackface;
    /**
     * Whether the text glyphs render their backface.
     */
    @Getter
    protected boolean showTextBackface;

    /**
     * Creates a {@code PrimitiveText} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}. All text rendering flags initially use {@code false}, and the
     * background color is absent.
     */
    public PrimitiveText(Vector3fc position, Color color, String text) {
        this(position, color, text, null);
    }

    /**
     * Creates a {@code PrimitiveText} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}. All text rendering flags initially use {@code false}, and the
     * background color is absent.
     */
    public PrimitiveText(Vector3fc position, Color color, String text, Float scale) {
        this(position, color, text, scale, false, null, false, false, false);
    }

    /**
     * Creates a {@code PrimitiveText} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}.
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
     * Stores the supplied displayed text and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setText(String text) {
        this.text = text;
        this.onChange();
    }

    /**
     * Stores the supplied rotation flag and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setUseRotation(boolean useRotation) {
        this.useRotation = useRotation;
        this.onChange();
    }

    /**
     * Returns the stored text background color used during network conversion. A
     * {@code null} result leaves that rendering option unspecified for the client, rather
     * than supplying an explicit value.
     */
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Stores the supplied text background color and synchronously sends this shape to
     * every registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.onChange();
    }

    /**
     * Stores the supplied depth-testing flag and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setDepthTest(boolean depthTest) {
        this.depthTest = depthTest;
        this.onChange();
    }

    /**
     * Stores the supplied background backface flag and synchronously sends this shape to
     * every registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setShowBackface(boolean showBackface) {
        this.showBackface = showBackface;
        this.onChange();
    }

    /**
     * Stores the supplied glyph backface flag and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setShowTextBackface(boolean showTextBackface) {
        this.showTextBackface = showTextBackface;
        this.onChange();
    }
}
