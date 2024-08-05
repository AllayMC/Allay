package org.allaymc.api.eventbus.event.server;

import org.allaymc.api.eventbus.event.CancellableEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Allay Project 2024/8/5
 *
 * @author Dhaiven
 */
@AllArgsConstructor
@Getter
public class WhitelistChangeEvent extends ServerEvent implements CancellableEvent {
    protected boolean enable;
}
