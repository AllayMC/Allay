package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.gamerule.GameRule;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/08/12
 *
 * @author Dhaiven
 */
@Getter
public class GameRuleChangeEvent extends WorldEvent implements CancellableEvent {
    protected GameRule gameRule;
    protected Object oldValue;
    @Setter
    protected Object newValue;

    public GameRuleChangeEvent(World world, GameRule gameRule, Object oldValue, Object newValue) {
        super(world);
        this.gameRule = gameRule;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
