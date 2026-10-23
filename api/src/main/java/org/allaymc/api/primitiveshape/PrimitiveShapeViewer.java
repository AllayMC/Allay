package org.allaymc.api.primitiveshape;

import java.util.Set;

/**
 * {@code PrimitiveShapeViewer} receives requests to display, update, and remove primitive
 * shapes for one viewing destination. Displaying an existing shape updates its rendering
 * properties. These operations control the destination only;
 * {@link PrimitiveShape#addViewer(PrimitiveShapeViewer)} and its removal counterpart
 * manage automatic update registration.
 * @author daoge_cmd
 */
public interface PrimitiveShapeViewer {
    /**
     * Displays or updates the supplied shape for this viewing destination. This operation
     * does not register or unregister automatic updates; callers manage that relationship
     * through the shape's viewer methods when needed.
     */
    void viewPrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Displays or updates the supplied set of shapes for this viewing destination. This
     * operation does not register or unregister automatic updates; callers manage that
     * relationship through the shape's viewer methods when needed.
     */
    void viewPrimitiveShapes(Set<PrimitiveShape> primitiveShapes);

    /**
     * Removes the supplied shape from this viewing destination. This operation does not
     * register or unregister automatic updates; callers manage that relationship through
     * the shape's viewer methods when needed.
     */
    void removePrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Removes the supplied set of shapes from this viewing destination. This operation
     * does not register or unregister automatic updates; callers manage that relationship
     * through the shape's viewer methods when needed.
     */
    void removePrimitiveShapes(Set<PrimitiveShape> primitiveShapes);
}
