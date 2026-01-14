package org.allaymc.api.item.component;

import org.allaymc.api.world.explosion.FireworkExplosion;

/**
 * @author daoge_cmd
 */
public interface ItemFireworkStarBaseComponent extends ItemBaseComponent {
    /**
     * Retrieves the {@code FireworkExplosion} associated with this instance.
     *
     * @return the {@code FireworkExplosion} representing the explosion of the firework,
     * or {@code null} if no explosion is set.
     */
    FireworkExplosion getExplosion();

    /**
     * Sets the {@code FireworkExplosion} for this component.
     *
     * @param explosion the {@code FireworkExplosion} to set. Provide {@code null} to remove the explosion.
     */
    void setExplosion(FireworkExplosion explosion);
}
