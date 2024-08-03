package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerExperienceChangeEvent extends PlayerEvent implements CancellableEvent {

    protected float oldExperience;
    protected float newExperience;

    public PlayerExperienceChangeEvent(EntityPlayer player, float oldExperience, float newExperience) {
        super(player);
        this.oldExperience = oldExperience;
        this.newExperience = newExperience;
    }
}
