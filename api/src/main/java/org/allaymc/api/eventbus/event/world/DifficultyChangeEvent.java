package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.Difficulty;

/**
 * {@code DifficultyChangeEvent} carries the previous difficulty and a proposed
 * replacement for a world. Listeners may replace {@code newDifficulty} or
 * cancel the event; the world data setter stores the resulting value only when
 * dispatch returns without cancellation.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class DifficultyChangeEvent extends WorldEvent implements CancellableEvent {
    /**
     * Difficulty before the proposed change.
     */
    protected Difficulty oldDifficulty;
    /**
     * {@code newDifficulty} holds the listener-editable replacement that the
     * world data setter stores after accepted dispatch.
     */
    @Setter
    protected Difficulty newDifficulty;

    /**
     * Creates a difficulty proposal retaining the old and requested world
     * settings.
     */
    public DifficultyChangeEvent(World world, Difficulty oldDifficulty, Difficulty newDifficulty) {
        super(world);
        this.oldDifficulty = oldDifficulty;
        this.newDifficulty = newDifficulty;
    }
}
