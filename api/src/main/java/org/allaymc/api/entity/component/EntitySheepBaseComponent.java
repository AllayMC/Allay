package org.allaymc.api.entity.component;

/**
 * {@code EntitySheepBaseComponent} exposes whether a sheep's wool has been
 * removed. The server uses this flag to decide whether shearing can succeed and
 * whether wool belongs in death drops, while dye color remains a separate
 * component's state.
 *
 * @author daoge_cmd
 */
public interface EntitySheepBaseComponent extends EntityBaseComponent {

    /**
     * Returns whether the sheep currently has its wool removed.
     */
    boolean isSheared();

    /**
     * Sets the sheep's wool-availability flag and publishes its state. The
     * server preserves dye color independently, so setting {@code sheared} to
     * {@code false} restores wool availability without selecting a new wool
     * color.
     */
    void setSheared(boolean sheared);
}
