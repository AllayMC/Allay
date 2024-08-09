package org.allaymc.api.world;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
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
    @ApiStatus.Internal
    void addSyncPacketToQueue(EntityPlayer player, BedrockPacket packet, long time);

    Thread getThread();

    long getTick();

    float getTps();

    float getMSPT();

    float getTickUsage();

    void startTick();

    Dimension getDimension(int dimensionId);

    default Dimension getOverWorld() {
        return getDimension(DimensionInfo.OVERWORLD.dimensionId());
    }

    default Dimension getNether() {
        return getDimension(DimensionInfo.NETHER.dimensionId());
    }

    default Dimension getTheEnd() {
        return getDimension(DimensionInfo.THE_END.dimensionId());
    }

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

    void setDimension(Dimension dimension);

    default void broadcastPacket(BedrockPacket packet) {
        getDimensions().values().forEach(dim -> dim.broadcastPacket(packet));
    }
}
