package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.WorldViewer;

import java.util.function.Supplier;

/**
 * {@code FakeChunkLoader} keeps
 * chunks loaded around a supplied location without providing a world
 * viewer. The radius is fixed at construction, location queries invoke the
 * retained supplier each time, and the sending limit imposes no practical
 * per-tick cap of its own.
 *
 * @param locationSupplier {@code locationSupplier} supplies each queried location.
 *                         Callers must provide a valid location whenever this
 *                         loader is registered.
 * @param radius fixed loading radius in chunks
 *
 * @author daoge_cmd
 */
public record FakeChunkLoader(Supplier<Location3dc> locationSupplier, int radius) implements ChunkLoader {

    /**
     * Returns the location supplied for this query without retaining its
     * result.
     */
    @Override
    public Location3dc getLocation() {
        return locationSupplier.get();
    }

    /**
     * Returns the fixed radius supplied when this loader was constructed.
     */
    @Override
    public int getChunkLoadingRadius() {
        return radius;
    }

    /**
     * Ignores the requested radius because this loader retains its
     * construction-time radius. Callers needing a different region must replace
     * the loader or supply another implementation supporting radius changes.
     */
    @Override
    public void setChunkLoadingRadius(int radius) {
    }

    /**
     * Returns {@link Integer#MAX_VALUE} so this loader supplies no smaller
     * per-tick sending limit. The chunk manager may still stop an attempt when
     * the next requested chunk is unavailable.
     */
    @Override
    public int getChunkMaxSendCountPerTick() {
        return Integer.MAX_VALUE;
    }

    /**
     * Returns {@code null} because this loader requests chunk residency without
     * displaying world data. Registering it does not create a viewer or a
     * player connection to receive chunk packets.
     */
    @Override
    public WorldViewer toWorldViewer() {
        return null;
    }
}
