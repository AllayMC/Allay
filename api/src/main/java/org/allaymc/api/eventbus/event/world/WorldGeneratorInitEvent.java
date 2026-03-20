package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.function.GenerateFunction;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.api.world.generator.function.PostProcessor;

/**
 * Called after a {@link WorldGenerator} is created but before it is attached to its dimension.
 * <p>
 * Plugins can use this event to append custom {@link Noiser}s, {@link Populator}s, and
 * {@link PostProcessor}s to any registered generator. {@link GenerateFunction#init(String)}
 * is called automatically on each added function.
 * <p>
 * This event is fired once per dimension per world load. Use {@link #getDimensionInfo()} and
 * {@link #getGenerator()}'s name to selectively target generators.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class WorldGeneratorInitEvent extends WorldEvent {

    /**
     * The generator being initialized.
     */
    private final WorldGenerator generator;

    /**
     * The dimension this generator will be attached to.
     */
    private final DimensionInfo dimensionInfo;

    public WorldGeneratorInitEvent(World world, WorldGenerator generator, DimensionInfo dimensionInfo) {
        super(world);
        this.generator = generator;
        this.dimensionInfo = dimensionInfo;
    }
}
