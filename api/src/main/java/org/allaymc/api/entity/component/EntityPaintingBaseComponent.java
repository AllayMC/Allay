package org.allaymc.api.entity.component;

import org.allaymc.api.entity.data.PaintingType;

/**
 * @author daoge_cmd
 */
public interface EntityPaintingBaseComponent extends EntityBaseComponent {
    /**
     * Retrieves the type of painting associated with this component.
     *
     * @return the {@code PaintingType} of this entity's painting
     */
    PaintingType getPaintingType();

    /**
     * Sets the type of the painting for this entity.
     *
     * @param paintingType the {@code PaintingType} to be set for the painting.
     *                     Must not be {@code null}.
     */
    void setPaintingType(PaintingType paintingType);
}
