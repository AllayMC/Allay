package org.allaymc.api.world;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.scheduler.Scheduler;
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
    long getTick();

    float getTps();

    void startTick();

    Dimension getDimension(int dimensionId);

    @UnmodifiableView
    Map<Integer, Dimension> getDimensions();

    @UnmodifiableView
    Collection<EntityPlayer> getPlayers();

    void tick(long currentTick);

    Scheduler getScheduler();

    WorldStorage getWorldStorage();

    WorldData getWorldData();

    void saveWorldData();

    void close();

    @ApiStatus.Internal
    void viewTime(Collection<EntityPlayer> players);

    @ApiStatus.Internal
    void tickTime(long tickNumber);

    void setDimension(Dimension dimension);
}
