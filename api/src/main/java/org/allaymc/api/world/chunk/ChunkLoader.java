package org.allaymc.api.world.chunk;

import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.world.WorldViewer;

/**
 * {@code ChunkLoader} describes
 * a moving or fixed source of chunk loading demand in a dimension.
 * Its location and radius determine the requested region, while the sending
 * limit controls per-tick work and an optional world viewer receives visible
 * world data.
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {

    /**
     * Returns the current location used to center this loader's requested chunk
     * region.
     */
    Location3dc getLocation();

    /**
     * Returns the requested loading radius measured in chunks around this
     * loader.
     */
    int getChunkLoadingRadius();

    /**
     * Requests a new loading radius measured in chunks around this loader.
     * Implementations define whether changes are supported; an unsupported
     * change may throw {@link UnsupportedOperationException} or follow an
     * implementation-specific fixed-radius policy.
     */
    void setChunkLoadingRadius(int radius);

    /**
     * Returns the maximum number of chunk sending attempts requested per tick.
     */
    int getChunkMaxSendCountPerTick();

    /**
     * Returns whether this loader currently supplies a world viewer instead of
     * {@code null}.
     */
    default boolean isWorldViewer() {
        return toWorldViewer() != null;
    }

    /**
     * Returns the world viewer associated with this loader, or {@code null}
     * when it only keeps chunks loaded. Callers must check the result before
     * sending visible world data.
     */
    WorldViewer toWorldViewer();
}