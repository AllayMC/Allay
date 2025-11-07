package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.gamerule.GameRule;

/**
 * @author Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
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
