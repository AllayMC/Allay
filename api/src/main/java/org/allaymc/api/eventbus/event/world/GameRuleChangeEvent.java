package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.gamerule.GameRule;

/**
 * {@code GameRuleChangeEvent} carries a proposed change to one world game
 * rule. Listeners may replace {@code newValue} or cancel the event before the
 * world data setter applies it; replacement values must retain the type
 * expected by {@code gameRule}.
 *
 * @author Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class GameRuleChangeEvent extends WorldEvent implements CancellableEvent {
    /**
     * Rule identifying the expected value type.
     */
    protected GameRule gameRule;
    /**
     * Rule value before the proposed change.
     */
    protected Object oldValue;
    /**
     * {@code newValue} holds the listener-editable replacement; consumers
     * expect the value type declared by {@code gameRule}.
     */
    @Setter
    protected Object newValue;

    /**
     * Creates a game rule proposal retaining both supplied value references.
     */
    public GameRuleChangeEvent(World world, GameRule gameRule, Object oldValue, Object newValue) {
        super(world);
        this.gameRule = gameRule;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
