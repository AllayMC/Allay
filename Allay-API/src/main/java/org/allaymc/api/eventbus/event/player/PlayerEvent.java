package org.allaymc.api.eventbus.event.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class PlayerEvent extends Event {
    protected EntityPlayer player;
}
