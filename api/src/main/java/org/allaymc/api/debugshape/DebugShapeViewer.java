package org.allaymc.api.debugshape;

import java.util.Set;

/**
 * Represents an object that can view {@link DebugShape}.
 *
 * @author daoge_cmd
 */
public interface DebugShapeViewer {
    /**
     * View a debug shape, which will make a specific debug shape being
     * displayed for this viewer. This method is also used to update a
     * debug shape in viewer's side when the debug shape is changed.
     *
     * @param debugShape the debug shape to view
     */
    void viewDebugShape(DebugShape debugShape);

    /**
     * View multiple debug shapes. This method is similar to {@link #viewDebugShape(DebugShape)}
     * but can view multiple debug shapes at once.
     *
     * @param debugShapes the debug shapes to view
     */
    void viewDebugShapes(Set<DebugShape> debugShapes);

    /**
     * Remove a debug shape, which will make a specific debug shape being
     * removed from this viewer.
     *
     * @param debugShape the debug shape to remove.
     */
    void removeDebugShape(DebugShape debugShape);

    /**
     * Remove multiple debug shapes. This method is similar to {@link #removeDebugShape(DebugShape)}
     * but can remove multiple debug shapes at once.
     *
     * @param debugShapes the debug shapes to remove.
     */
    void removeDebugShapes(Set<DebugShape> debugShapes);
}
