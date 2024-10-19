package org.allaymc.api.world;

import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Map;

/**
 * WorldPool contains all worlds in the server.
 *
 * @author daoge_cmd
 */
public interface WorldPool {
    /**
     * Get the world settings of the server.
     *
     * @return the world settings of the server.
     */
    WorldSettings getWorldConfig();

    /**
     * Get the world by the name.
     *
     * @param name the name of the world.
     *
     * @return the world, or {@code null} if the world is not found.
     */
    World getWorld(String name);

    /**
     * Get all worlds in the server.
     *
     * @return all worlds in the server.
     */
    @UnmodifiableView
    Map<String, World> getWorlds();

    /**
     * Get the default world.
     *
     * @return the default world.
     */
    World getDefaultWorld();

    /**
     * Load the world with the specific name and settings.
     * <p>
     * If the world is not exists in {@code WorldSettings}, the world will be added into the {@code WorldSettings}.
     *
     * @param name         the name of the world.
     * @param worldSetting the settings of the world.
     *
     * @throws IllegalArgumentException if the world with the specific name already exists.
     */
    void loadWorld(String name, WorldSettings.WorldEntry worldSetting);

    // TODO: unload world

    /**
     * Get the global spawn point of the server.
     * <p>
     * Global spawn point is the location of the player who joins the server for the first time.
     *
     * @return the global spawn point of the server.
     */
    default Location3ic getGlobalSpawnPoint() {
        var vec = getDefaultWorld().getWorldData().getSpawnPoint();
        return new Location3i(vec.x(), vec.y(), vec.z(), getDefaultWorld().getOverWorld());
    }

    /**
     * Get the global spawn point of the server.
     * <p>
     * Global spawn point is the location of the player who joins the server for the first time.
     *
     * @return the global spawn point of the server.
     */
    default Vector3fc getGlobalSpawnPointVec3f() {
        var vec = getDefaultWorld().getWorldData().getSpawnPoint();
        return new Vector3f(vec.x(), vec.y(), vec.z());
    }
}
