package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntityFallEvent extends Event {
    protected float fallDistance;
    protected float damageReductionFactor;
}
