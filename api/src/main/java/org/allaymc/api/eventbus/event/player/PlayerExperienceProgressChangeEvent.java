package org.allaymc.api.eventbus.event.player;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerExperienceProgressChangeEvent extends PlayerEvent implements CancellableEvent {
    protected float oldExperienceProgress;
    protected float newExperienceProgress;

    public PlayerExperienceProgressChangeEvent(EntityPlayer player, float oldExperienceProgress, float newExperienceProgress) {
        super(player);
        this.oldExperienceProgress = oldExperienceProgress;
        setNewExperienceProgress(newExperienceProgress);
    }

    public void setNewExperienceProgress(float value) {
        Preconditions.checkArgument(value >= 0 && value <= 1);
        this.newExperienceProgress = value;
    }
}
