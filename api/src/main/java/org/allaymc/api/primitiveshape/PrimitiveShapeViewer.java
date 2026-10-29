package org.allaymc.api.primitiveshape;

import java.util.Set;

/**
 * Represents an object that can view {@link PrimitiveShape}.
 *
 * @author daoge_cmd
 */
public interface PrimitiveShapeViewer {
    /**
     * View a primitive shape, which will make a specific primitive shape being
     * displayed for this viewer. This method is also used to update a
     * primitive shape in viewer's side when the primitive shape is changed.
     *
     * @param primitiveShape the primitive shape to view
     */
    void viewPrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * View multiple primitive shapes. This method is similar to {@link #viewPrimitiveShape(PrimitiveShape)}
     * but can view multiple primitive shapes at once.
     *
     * @param primitiveShapes the primitive shapes to view
     */
    void viewPrimitiveShapes(Set<PrimitiveShape> primitiveShapes);

    /**
     * Remove a primitive shape, which will make a specific primitive shape being
     * removed from this viewer.
     *
     * @param primitiveShape the primitive shape to remove.
     */
    void removePrimitiveShape(PrimitiveShape primitiveShape);

    /**
     * Remove multiple primitive shapes. This method is similar to {@link #removePrimitiveShape(PrimitiveShape)}
     * but can remove multiple primitive shapes at once.
     *
     * @param primitiveShapes the primitive shapes to remove.
     */
    void removePrimitiveShapes(Set<PrimitiveShape> primitiveShapes);
}
