package org.allaymc.server.world;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.packet.SetTimePacket;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {
    @Getter
    protected final WorldStorage worldStorage;
    @Getter
    protected final WorldData worldData;
    @Getter
    protected final Server server;
    @Getter
    protected final Int2ObjectOpenHashMap<Dimension> dimensionMap;
    protected long nextTimeSendTick = 12 * 20;

    private AllayWorld(Server server,
                       WorldStorage worldStorage,
                       WorldData worldData,
                       Map<Integer, Dimension> dimensions) {
        this.worldStorage = worldStorage;
        this.worldData = worldData == null ? worldStorage.getWorldDataCache() : worldData;
        this.server = server;
        this.dimensionMap = new Int2ObjectOpenHashMap<>(dimensions);

        // set inject world instance
        dimensions.values().forEach(d -> d.setWorld(this));
        this.worldStorage.setWorld(this);
    }

    public void tickTime(long tickNumber) {
        if (worldData.getGameRule(GameRule.DO_DAYLIGHT_CYCLE)) {
            if (tickNumber >= nextTimeSendTick) {
                worldData.addTime();
                nextTimeSendTick = tickNumber + 12 * 20; //Client send the time every 12 seconds
            }
        }
    }

    public void viewTime(Collection<EntityPlayer> players) {
        var setTimePk = new SetTimePacket();
        setTimePk.setTime((int) worldData.getTime());
        for (var player : players) {
            player.sendPacket(setTimePk);
        }
    }

    @Override
    public Dimension getDimension(int dimensionId) {
        return dimensionMap.get(dimensionId);
    }

    @Override
    public @UnmodifiableView Map<Integer, Dimension> getDimensions() {
        return Collections.unmodifiableMap(dimensionMap);
    }

    @Override
    public Collection<EntityPlayer> getPlayers() {
        return Collections.unmodifiableCollection(getDimensions().values().stream().map(Dimension::getPlayers).reduce(new HashSet<>(), (entityPlayers, entityPlayers2) -> {
            entityPlayers.addAll(entityPlayers2);
            return entityPlayers;
        }));
    }

    @Override
    public void close() {
        getDimensions().forEach((integer, dimension) -> dimension.getChunkService().unloadAllChunks());
        saveWorldData();
        getWorldStorage().close();
    }

    @Override
    public void saveWorldData() {
        getWorldStorage().writeWorldData(worldData);
    }

    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    public static class WorldBuilder {
        @Nullable
        private WorldData worldData;
        private WorldStorage worldStorage;
        private final HashMap<Integer, Dimension> dimensionMap = new HashMap<>(3);

        private WorldBuilder() {
        }

        public WorldBuilder worldData(WorldData worldData) {
            this.worldData = worldData;
            return this;
        }

        public WorldBuilder setWorldStorage(WorldStorage worldStorage) {
            this.worldStorage = worldStorage;
            return this;
        }

        public WorldBuilder addDimension(Dimension dimension) {
            dimensionMap.put(dimension.getDimensionInfo().dimensionId(), dimension);
            return this;
        }

        public World build() {
            return new AllayWorld(Server.getInstance(), worldStorage, worldData, dimensionMap);
        }
    }
}
