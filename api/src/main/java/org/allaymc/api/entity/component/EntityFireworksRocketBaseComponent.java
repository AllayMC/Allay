package org.allaymc.api.entity.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public interface EntityFireworksRocketBaseComponent extends EntityBaseComponent {
    /**
     * Gets the duration of the firework's existence in ticks. The firework rocket will
     * explode after this many ticks.
     *
     * @return the duration of the firework's existence in ticks
     */
    int getExistenceTicks();

    /**
     * Sets the duration of the firework's existence in ticks.
     *
     * @param ticks the duration of the firework's existence in ticks; must be greater than or equal to 0
     */
    void setExistenceTicks(int ticks);

    /**
     * Gets the number of ticks that this firework rocket has existed.
     *
     * @return the number of ticks that this firework rocket has been active, which is always a non-negative integer
     */
    int getExistedTicks();

    /**
     * Sets the number of ticks that this entity has existed.
     *
     * @param ticks the number of ticks this entity has existed; should be a non-negative value
     */
    void setExistedTicks(int ticks);

    /**
     * Retrieves the set of firework explosions associated with the firework rocket.
     *
     * @return a {@code Set} of {@code FireworkExplosion} objects representing the explosions configured for the firework rocket
     */
    @UnmodifiableView
    Set<FireworkExplosion> getExplosions();

    /**
     * Sets the firework explosions associated with this item.
     *
     * @param explosions a set of {@link FireworkExplosion} that specifies the various explosions to be used for this
     *                   firework rocket. Passing an empty set will result in no explosions
     */
    void setExplosions(Set<FireworkExplosion> explosions);

    /**
     * Gets the player attached to this firework rocket. When a firework rocket is attached to a player,
     * the firework will boost the speed of the player when flying.
     *
     * @return the attached player, or {@code null} if no player is attached
     */
    EntityPlayer getAttachedPlayer();

    /**
     * Sets the player attached to this firework rocket.
     *
     * @param player the player to attach to the firework rocket, or {@code null} to detach a currently attached player
     */
    void setAttachedPlayer(EntityPlayer player);

    /**
     * Determines whether a player is currently attached to this firework rocket.
     *
     * @return {@code true} if a player is attached to this firework rocket; {@code false} otherwise
     */
    default boolean isAttached() {
        return getAttachedPlayer() != null;
    }
}
