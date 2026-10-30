package org.allaymc.server.world.light;

import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.datastruct.ChunkSectionNibbleArray;
import org.jctools.maps.NonBlockingHashMapLong;

/**
 * {@code CachedLightDataAccessor} maps dimension block coordinates to section
 * nibble arrays for propagation data, dampening, and emission. Each channel
 * retains a separate last-chunk cache, and vertical indices subtract the
 * dimension minimum height. Callers serialize access and ensure the requested
 * chunk and height are available; cached references can survive backing-map
 * removal.
 *
 * @author daoge_cmd
 */
public class CachedLightDataAccessor implements LightDataAccessor {
    /**
     * Dimension bounds defining section array offsets.
     */
    protected final DimensionType dimensionType;
    // cachedLight, cachedLightDampening, and cachedLightEmission retain channel
    // references independently. Their one-entry caches require serialized use and
    // do not invalidate entries when chunk maps remove or replace arrays.
    /**
     * Last-chunk cache for propagated illumination arrays.
     */
    protected final CachedChunkMapAccessor<ChunkSectionNibbleArray[]> cachedLight;
    /**
     * Last-chunk cache for block dampening arrays.
     */
    protected final CachedChunkMapAccessor<ChunkSectionNibbleArray[]> cachedLightDampening;
    /**
     * Last-chunk cache for block emission arrays.
     */
    protected final CachedChunkMapAccessor<ChunkSectionNibbleArray[]> cachedLightEmission;

    /**
     * Creates coordinate accessors retaining the supplied channel maps and
     * dimension bounds. A channel map must exist before its operations are
     * used, and callers retain responsibility for chunk availability and
     * serialized access.
     */
    public CachedLightDataAccessor(DimensionType dimensionType,
                                   NonBlockingHashMapLong<ChunkSectionNibbleArray[]> light,
                                   NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightDampening,
                                   NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightEmission) {
        this.dimensionType = dimensionType;
        this.cachedLight = new CachedChunkMapAccessor<>(light);
        this.cachedLightDampening = new CachedChunkMapAccessor<>(lightDampening);
        this.cachedLightEmission = new CachedChunkMapAccessor<>(lightEmission);
    }

    /**
     * Returns propagated illumination at dimension block coordinates using the
     * last-chunk cache. Callers provide an in-range height and available
     * channel data; missing data can throw {@code NullPointerException}.
     */
    @Override
    public int getLight(int x, int y, int z) {
        // Dimension sections start at minHeight, including negative block heights.
        return cachedLight.get(x, z)[(y - dimensionType.getMinHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    /**
     * Stores propagated illumination at dimension block coordinates through
     * the last-chunk cache. Callers supply an in-range height, available
     * channel data, and {@code value} from {@code 0} through {@code 15};
     * larger or negative values can corrupt nibble checksum state.
     */
    @Override
    public void setLight(int x, int y, int z, int value) {
        // Dimension sections start at minHeight, including negative block heights.
        cachedLight.get(x, z)[(y - dimensionType.getMinHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    /**
     * Returns block dampening at dimension block coordinates through its
     * channel cache. Callers provide available chunk data and an in-range
     * height; this accessor does not request a chunk load.
     */
    @Override
    public int getLightDampening(int x, int y, int z) {
        // Dimension sections start at minHeight, including negative block heights.
        return cachedLightDampening.get(x, z)[(y - dimensionType.getMinHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    /**
     * Returns block emission at dimension block coordinates through its
     * channel cache. Callers provide available chunk data and an in-range
     * height; this accessor does not request a chunk load.
     */
    @Override
    public int getLightEmission(int x, int y, int z) {
        // Dimension sections start at minHeight, including negative block heights.
        return cachedLightEmission.get(x, z)[(y - dimensionType.getMinHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    /**
     * Stores block emission at dimension block coordinates through its channel
     * cache. Callers supply an in-range height, available data, and {@code
     * value} from {@code 0} through {@code 15}; changes do not schedule
     * propagation by themselves.
     */
    @Override
    public void setLightEmission(int x, int y, int z, int value) {
        // Dimension sections start at minHeight, including negative block heights.
        cachedLightEmission.get(x, z)[(y - dimensionType.getMinHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    /**
     * Returns whether the height lies inside the inclusive dimension bounds.
     */
    @Override
    public boolean isYInRange(int y) {
        return y >= dimensionType.getMinHeight() && y <= dimensionType.getMaxHeight();
    }

}
