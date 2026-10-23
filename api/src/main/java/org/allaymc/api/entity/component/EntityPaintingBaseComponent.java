package org.allaymc.api.entity.component;

import org.allaymc.api.entity.data.PaintingType;

/**
 * {@code EntityPaintingBaseComponent} exposes the painting variant attached to
 * a painting entity. The selected {@link PaintingType} determines its title
 * and dimensions for persistence and client presentation, while the common
 * base component continues to provide location and lifecycle behavior.
 *
 * @author daoge_cmd
 */
public interface EntityPaintingBaseComponent extends EntityBaseComponent {
    /**
     * Returns the painting variant currently assigned to this entity.
     */
    PaintingType getPaintingType();

    /**
     * Selects the painting variant used by this entity's presentation and
     * bounds. The server rejects {@code null}; changing this value does not
     * itself respawn the painting to existing viewers.
     *
     * @throws NullPointerException if {@code paintingType} is {@code null}
     */
    void setPaintingType(PaintingType paintingType);
}
