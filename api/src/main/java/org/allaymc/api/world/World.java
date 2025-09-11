package org.allaymc.api.world;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.data.Weather;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Map;

/**
 * Represents a world in the server.
 *
 * @author daoge_cmd
 */
public interface World extends TaskCreator {

    /**
     * Get the name of the world.
     * <p>
     * Unlike the display name, this name is considered to be the id of the world.
     *
     * @return the name;
     */
    String getName();

    /**
     * Get the thread which the world is running on.
     *
     * @return the thread which the world is running on
     */
    Thread getWorldThread();

    /**
     * Get the tick of the world.
     *
     * @return the tick of the world
     */
    long getTick();

    /**
     * Get the TPS of the world.
     *
     * @return the TPS of the world
     */
    float getTPS();

    /**
     * Get the MSPT of the world.
     *
     * @return the MSPT of the world
     */
    float getMSPT();

    /**
     * Get the tick usage of the world.
     *
     * @return the tick usage of the world
     */
    float getTickUsage();

    /**
     * Get the dimension by the dimension id in this world.
     *
     * @param dimensionId the dimension id
     * @return the dimension, or {@code null} if the dimension is not found
     */
    Dimension getDimension(int dimensionId);

    /**
     * Get the overworld dimension in this world.
     *
     * @return the overworld dimension, {@code null} should never being returned
     */
    default Dimension getOverWorld() {
        return getDimension(DimensionInfo.OVERWORLD.dimensionId());
    }

    /**
     * Get the nether dimension in this world.
     *
     * @return the nether dimension, or {@code null} if the nether dimension is not found
     */
    default Dimension getNether() {
        return getDimension(DimensionInfo.NETHER.dimensionId());
    }

    /**
     * Get the end dimension in this world.
     *
     * @return the end dimension, or {@code null} if the end dimension is not found
     */
    default Dimension getTheEnd() {
        return getDimension(DimensionInfo.THE_END.dimensionId());
    }

    /**
     * Get all dimensions in this world.
     *
     * @return all dimensions in this world
     */
    @UnmodifiableView
    Map<Integer, Dimension> getDimensions();

    /**
     * Get the players in this world.
     *
     * @return the players in this world
     */
    @UnmodifiableView
    Collection<EntityPlayer> getPlayers();

    /**
     * Get the scheduler of the world.
     *
     * @return the scheduler of the world
     */
    Scheduler getScheduler();

    /**
     * Get the world data of the world.
     *
     * @return the world data of the world
     */
    WorldData getWorldData();

    /**
     * Save the world data of the world.
     */
    void saveWorldData();

    /**
     * Retrieves the current state of the world.
     *
     * @return the world state
     */
    WorldState getState();

    /**
     * Broadcast a packet to all players in this world.
     *
     * @param packet the packet to broadcast
     */
    default void broadcastPacket(BedrockPacket packet) {
        getDimensions().values().forEach(dim -> dim.broadcastPacket(packet));
    }

    /**
     * Get the weather of the world.
     *
     * @return the weathers of the world
     */
    Weather getWeather();

    /**
     * Set the weather of the world.
     *
     * @param weather the weather to set
     */
    void setWeather(Weather weather);

    /**
     * Set the weather to {@link Weather#CLEAR}.
     */
    default void clearWeather() {
        setWeather(Weather.CLEAR);
    }

    /**
     * Get the spawn point of the world.
     *
     * @return the spawn point of the world
     */
    default Location3dc getSpawnPoint() {
        var vec = getWorldData().getSpawnPoint();
        return new Location3d(vec.x(), vec.y(), vec.z(), getOverWorld());
    }

    @Override
    default boolean isValid() {
        return getState() == WorldState.RUNNING;
    }
}
