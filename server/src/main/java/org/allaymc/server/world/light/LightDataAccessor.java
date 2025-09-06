package org.allaymc.server.world.light;

/**
 * @author daoge_cmd
 */
public interface LightDataAccessor {
    int getLight(int x, int y, int z);

    void setLight(int x, int y, int z, int value);

    int getLightDampening(int x, int y, int z);

    // No setter to light dampening because change to light dampening may also cause change to light height

    int getLightEmission(int x, int y, int z);

    void setLightEmission(int x, int y, int z, int value);

    boolean isYInRange(int y);
}
