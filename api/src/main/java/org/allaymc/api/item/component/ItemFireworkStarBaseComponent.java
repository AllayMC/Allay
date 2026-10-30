package org.allaymc.api.item.component;

import org.allaymc.api.world.explosion.FireworkExplosion;

/**
 * {@code ItemFireworkStarBaseComponent} exposes the explosion definition
 * stored by a firework star. The definition supplies the shape and colors used
 * by firework crafting and serialization; an absent definition is represented
 * by {@code null}.
 *
 * @author daoge_cmd
 */
public interface ItemFireworkStarBaseComponent extends ItemBaseComponent {
    /**
     * Returns the stored explosion definition, or {@code null} when absent.
     * The returned record can retain mutable color lists, so consumers must
     * consider those lists when retaining or changing the definition.
     */
    FireworkExplosion getExplosion();

    /**
     * Replaces the explosion definition stored by this firework star.
     * {@code null} removes the definition; the server retains the supplied
     * record rather than copying its color-list contents for independent
     * mutation.
     */
    void setExplosion(FireworkExplosion explosion);
}
