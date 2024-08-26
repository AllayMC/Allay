package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/8/12
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class CEntityTryDamageEvent extends Event {
    protected DamageContainer damage;
    @Setter
    protected boolean canAttack;
}
