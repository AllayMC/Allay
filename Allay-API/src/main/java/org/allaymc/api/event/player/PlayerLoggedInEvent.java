package org.allaymc.api.event.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.event.CancellableEvent;
import org.allaymc.api.event.Event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class PlayerLoggedInEvent extends Event implements CancellableEvent {
    protected EntityPlayer player;
}
