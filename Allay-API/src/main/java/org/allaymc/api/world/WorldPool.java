package org.allaymc.api.world;

import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Map;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public interface WorldPool {
    World getWorld(String name);

    @UnmodifiableView
    Map<String, World> getWorlds();

    void addWorld(World world);

    World getDefaultWorld();

    void setDefaultWorld(World world);

    default Location3ic getGlobalSpawnPoint() {
        var vec = getDefaultWorld().getWorldData().getSpawnPoint();
        return new Location3i(vec.x(), vec.y(), vec.z(), getDefaultWorld().getOverWorld());
    }

    default Vector3fc getGlobalSpawnPointVec3f() {
        var vec = getDefaultWorld().getWorldData().getSpawnPoint();
        return new Vector3f(vec.x(), vec.y(), vec.z());
    }
}
