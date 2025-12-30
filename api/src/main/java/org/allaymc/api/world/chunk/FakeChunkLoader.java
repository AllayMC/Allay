package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.WorldViewer;

import java.util.function.Supplier;

/**
 * Chunk loader that only loads chunks. Useful for loading a range of chunks in some places like spawn point.
 *
 * @author daoge_cmd
 */
public record FakeChunkLoader(Supplier<Location3dc> locationSupplier, int radius) implements ChunkLoader {

    @Override
    public Location3dc getLocation() {
        return locationSupplier.get();
    }

    @Override
    public int getChunkLoadingRadius() {
        return radius;
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
    }

    @Override
    public int getChunkMaxSendCountPerTick() {
        return Integer.MAX_VALUE;
    }

    @Override
    public WorldViewer toWorldViewer() {
        return null;
    }
}
