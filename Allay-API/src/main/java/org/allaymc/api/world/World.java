package org.allaymc.api.world;

import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.storage.WorldStorage;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Map;

/**
 * Describe a world
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface World {
    Dimension getDimension(int dimensionId);

    @UnmodifiableView
    Map<Integer, Dimension> getDimensions();

    @UnmodifiableView
    Collection<EntityPlayer> getPlayers();

    Server getServer();

    WorldStorage getWorldStorage();

    WorldData getWorldData();

    void saveWorldData();

    void close();

    @ApiStatus.Internal
    void viewTime(Collection<EntityPlayer> players);

    @ApiStatus.Internal
    void tickTime(long tickNumber);
}
