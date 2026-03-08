package org.allaymc.api.eventbus.event.player;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player's experience level changes.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
public class PlayerExperienceLevelChangeEvent extends PlayerEvent implements CancellableEvent {
    protected int oldExperienceLevel;
    protected int newExperienceLevel;

    public PlayerExperienceLevelChangeEvent(EntityPlayer player, int oldExperienceLevel, int newExperienceLevel) {
        super(player);
        this.oldExperienceLevel = oldExperienceLevel;
        setNewExperienceLevel(newExperienceLevel);
    }

    public void setNewExperienceLevel(int value) {
        Preconditions.checkArgument(value >= 0 && value <= 24791);
        this.newExperienceLevel = value;
    }
}
