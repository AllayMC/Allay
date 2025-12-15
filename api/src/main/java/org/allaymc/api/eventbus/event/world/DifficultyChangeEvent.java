package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.Difficulty;

/**
 * Called when the difficulty of a world changes.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class DifficultyChangeEvent extends WorldEvent implements CancellableEvent {
    protected Difficulty oldDifficulty;
    @Setter
    protected Difficulty newDifficulty;

    public DifficultyChangeEvent(World world, Difficulty oldDifficulty, Difficulty newDifficulty) {
        super(world);
        this.oldDifficulty = oldDifficulty;
        this.newDifficulty = newDifficulty;
    }
}
