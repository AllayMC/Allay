package org.allaymc.api.entity.component;

/**
 * Component for sheep-specific behavior.
 *
 * @author daoge_cmd
 */
public interface EntitySheepBaseComponent extends EntityBaseComponent {

    /**
     * Check if this sheep has been sheared.
     *
     * @return {@code true} if the sheep is sheared
     */
    boolean isSheared();

    /**
     * Set whether this sheep is sheared.
     *
     * @param sheared {@code true} to set the sheep as sheared
     */
    void setSheared(boolean sheared);
}
