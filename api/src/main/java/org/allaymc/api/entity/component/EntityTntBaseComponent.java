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
    int getFuseTime();

    /**
     * Set the fuse of the tnt entity.
     *
     * @param fuseTime the fuse of the tnt entity
     */
    void setFuseTime(int fuseTime);
}
