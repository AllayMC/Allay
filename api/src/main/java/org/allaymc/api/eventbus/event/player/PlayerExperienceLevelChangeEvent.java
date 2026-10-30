package org.allaymc.api.eventbus.event.player;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerExperienceLevelChangeEvent} holds the old and proposed experience
 * levels of a player entity. Listeners can cancel the update or select another
 * supported {@code newExperienceLevel}; the player component reads that replacement
 * before storing and sending the accepted level.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class PlayerExperienceLevelChangeEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Experience level before the update.
     */
    protected int oldExperienceLevel;
    /**
     * {@code newExperienceLevel} holds the accepted level. Its setter enforces the
     * inclusive range {@code 0} through {@code 24791}.
     */
    protected int newExperienceLevel;

    /**
     * Creates an experience-change event with the supplied old and proposed values.
     * The constructor invokes its setter to validate the proposed level in the
     * inclusive range {@code 0} through {@code 24791} before the event can be
     * dispatched.
     */
    public PlayerExperienceLevelChangeEvent(EntityPlayer player, int oldExperienceLevel, int newExperienceLevel) {
        super(player);
        this.oldExperienceLevel = oldExperienceLevel;
        setNewExperienceLevel(newExperienceLevel);
    }

    /**
     * Sets the proposed experience level after validating its supported range.
     * Values outside {@code 0} through {@code 24791} are rejected before the stored
     * proposal changes.
     *
     * @throws IllegalArgumentException if {@code value} is outside the inclusive
     * supported range
     */
    public void setNewExperienceLevel(int value) {
        Preconditions.checkArgument(value >= 0 && value <= 24791);
        this.newExperienceLevel = value;
    }
}
