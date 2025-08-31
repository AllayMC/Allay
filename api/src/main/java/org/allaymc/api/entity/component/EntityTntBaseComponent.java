package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityTntBaseComponent extends EntityBaseComponent {
    /**
     * Get the fuse of the tnt entity.
     *
     * @return the fuse of the tnt entity
     */
    int getFuse();

    /**
     * Set the fuse of the tnt entity.
     *
     * @param fuse the fuse of the tnt entity
     */
    void setFuse(int fuse);
}
