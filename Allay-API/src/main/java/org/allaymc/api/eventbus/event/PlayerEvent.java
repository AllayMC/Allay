package org.allaymc.api.eventbus.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public abstract class PlayerEvent extends Event {
    protected EntityPlayer player;
}
