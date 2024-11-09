package org.allaymc.server.world.service;

/**
 * @author daoge_cmd
 */
public interface LightAccessor {
    int getLight(int x, int y, int z);

    int getLightDampening(int x, int y, int z);

    void setLight(int x, int y, int z, int lightValue);

    boolean isChunkLoaded(int cx, int cz);

    boolean isYInRange(int y);
}
