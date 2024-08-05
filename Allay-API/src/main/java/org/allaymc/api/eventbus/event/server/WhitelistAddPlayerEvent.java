package org.allaymc.api.eventbus.event.server;

import org.allaymc.api.eventbus.event.CancellableEvent;

import lombok.AllArgsConstructor;

/**
 * Allay Project 2024/8/5
 *
 * @author Dhaiven
 */
@AllArgsConstructor
public class WhitelistAddPlayerEvent extends ServerEvent implements CancellableEvent {
    protected String uuidOrName;
}
