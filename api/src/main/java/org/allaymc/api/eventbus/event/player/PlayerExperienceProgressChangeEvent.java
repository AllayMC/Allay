package org.allaymc.api.eventbus.event.player;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerExperienceProgressChangeEvent} holds the old and proposed progress
 * within a player's current experience level. Listeners can cancel the update and
 * change {@code newExperienceProgress}; the current player component nevertheless
 * stores its original argument after approval rather than reading the replacement.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class PlayerExperienceProgressChangeEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Progress fraction before the update.
     */
    protected float oldExperienceProgress;
    /**
     * {@code newExperienceProgress} holds a validated progress fraction. The setter
     * accepts {@code 0} through {@code 1}, but the current producer ignores listener
     * replacements.
     */
    protected float newExperienceProgress;

    /**
     * Creates an experience-change event with the supplied old and proposed values.
     * The constructor invokes its setter to validate the proposed progress fraction
     * in the inclusive range {@code 0} through {@code 1} before the event can be
     * dispatched.
     */
    public PlayerExperienceProgressChangeEvent(EntityPlayer player, float oldExperienceProgress, float newExperienceProgress) {
        super(player);
        this.oldExperienceProgress = oldExperienceProgress;
        setNewExperienceProgress(newExperienceProgress);
    }

    /**
     * Sets the proposed experience progress fraction after validating its supported
     * range. Values outside {@code 0} through {@code 1} are rejected before the
     * stored proposal changes.
     *
     * @throws IllegalArgumentException if {@code value} is outside the inclusive
     * supported range
     */
    public void setNewExperienceProgress(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 1);
        this.newExperienceProgress = value;
    }
}
