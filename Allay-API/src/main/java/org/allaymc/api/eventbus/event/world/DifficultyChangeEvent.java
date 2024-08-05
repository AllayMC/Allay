package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.World;

/**
 * Allay Project 2024/8/6
 *
 * @author daoge_cmd
 */
@Getter
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
