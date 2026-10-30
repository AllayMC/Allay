package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.world.World;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.function.GenerateFunction;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.api.world.generator.function.PostProcessor;

/**
 * {@code WorldGeneratorInitEvent} exposes a created generator before its
 * attachment to a dimension during world loading. Listeners can append {@link
 * Noiser}, {@link Populator}, and {@link PostProcessor} functions; the
 * generator initializes each appended function with its preset when the
 * function is added. The dimension type and generator name allow listeners to
 * select a target.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class WorldGeneratorInitEvent extends WorldEvent {

    /**
     * {@code generator} references the instance that the pool attaches after
     * dispatch; listeners configure that same instance through its function
     * registration operations.
     */
    private final WorldGenerator generator;

    /**
     * Type of the target dimension.
     */
    private final DimensionType dimensionType;

    /**
     * Creates an initialization notification retaining the generator and
     * target dimension type.
     */
    public WorldGeneratorInitEvent(World world, WorldGenerator generator, DimensionType dimensionType) {
        super(world);
        this.generator = generator;
        this.dimensionType = dimensionType;
    }
}
