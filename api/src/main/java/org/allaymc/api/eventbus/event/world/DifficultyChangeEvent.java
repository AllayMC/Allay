package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.Difficulty;

/**
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
