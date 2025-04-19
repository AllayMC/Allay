package org.allaymc.server.world.service;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.server.datastruct.ChunkSectionNibbleArray;
import org.jctools.maps.NonBlockingHashMapLong;

/**
 * @author daoge_cmd
 */
public class CachedLightDataAccessor implements LightDataAccessor {
    protected final DimensionInfo dimensionInfo;
    protected final CachedChunkMapAccessor<ChunkSectionNibbleArray[]> cachedLight;
    protected final CachedChunkMapAccessor<ChunkSectionNibbleArray[]> cachedLightDampening;
    protected final CachedChunkMapAccessor<ChunkSectionNibbleArray[]> cachedLightEmission;

    public CachedLightDataAccessor(DimensionInfo dimensionInfo,
                                   NonBlockingHashMapLong<ChunkSectionNibbleArray[]> light,
                                   NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightDampening,
                                   NonBlockingHashMapLong<ChunkSectionNibbleArray[]> lightEmission) {
        this.dimensionInfo = dimensionInfo;
        this.cachedLight = new CachedChunkMapAccessor<>(light);
        this.cachedLightDampening = new CachedChunkMapAccessor<>(lightDampening);
        this.cachedLightEmission = new CachedChunkMapAccessor<>(lightEmission);
    }

    @Override
    public int getLight(int x, int y, int z) {
        return cachedLight.get(x, z)[(y - dimensionInfo.minHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    @Override
    public void setLight(int x, int y, int z, int value) {
        cachedLight.get(x, z)[(y - dimensionInfo.minHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    @Override
    public int getLightDampening(int x, int y, int z) {
        return cachedLightDampening.get(x, z)[(y - dimensionInfo.minHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    @Override
    public int getLightEmission(int x, int y, int z) {
        return cachedLightEmission.get(x, z)[(y - dimensionInfo.minHeight()) >> 4].get(x & 15, y & 15, z & 15);
    }

    @Override
    public void setLightEmission(int x, int y, int z, int value) {
        cachedLightEmission.get(x, z)[(y - dimensionInfo.minHeight()) >> 4].set(x & 15, y & 15, z & 15, value);
    }

    @Override
    public boolean isYInRange(int y) {
        return y >= dimensionInfo.minHeight() && y <= dimensionInfo.maxHeight();
    }

}
