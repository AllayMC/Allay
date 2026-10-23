package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.CompassRoseDirection;

/**
 * {@code BlockEntityHeadBaseComponent} exposes a head's compass orientation,
 * animation flag, and elapsed animation counter. The server advances that
 * counter while animation is active and includes the orientation and animation
 * fields in the inherited block-entity persistence representation.
 *
 * @author daoge_cmd
 */
public interface BlockEntityHeadBaseComponent extends BlockEntityBaseComponent {

    /**
     * Returns the retained sixteen-point compass orientation for this head. The
     * server initially leaves it {@code null} until placement, a supplied
     * rotation tag, or an explicit setter establishes an orientation.
     */
    CompassRoseDirection getDirection();

    /**
     * Stores a sixteen-point compass orientation and sends updated state to
     * viewers. The server retains the supplied reference directly, so callers
     * provide a usable direction before persistence dereferences it.
     */
    void setDirection(CompassRoseDirection direction);

    /**
     * Returns whether this head currently marks its animation as active.
     */
    boolean isPlayingAnimation();

    /**
     * Changes the animation-active flag and sends updated state to viewers. The
     * server does not reset the elapsed animation counter when this flag
     * changes, so callers manage that counter separately.
     */
    void setPlayingAnimation(boolean playingAnimation);

    /**
     * Returns the retained number of elapsed ticks in the head animation.
     */
    int getAnimationTicks();

    /**
     * Replaces the elapsed animation counter and sends updated state to
     * viewers. The server stores the supplied integer without a positivity
     * check; it is a counter rather than an animation-duration limit.
     */
    void setAnimationTicks(int ticks);
}
