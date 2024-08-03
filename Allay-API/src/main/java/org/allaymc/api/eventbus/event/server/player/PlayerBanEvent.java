package org.allaymc.api.eventbus.event.server.player;

import lombok.AllArgsConstructor;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class PlayerBanEvent extends Event implements CancellableEvent {
    protected String uuidOrName;
}
