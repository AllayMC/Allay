package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.GameLoop;
import org.allaymc.server.scheduler.AllayScheduler;
import org.cloudburstmc.protocol.bedrock.packet.GameRulesChangedPacket;
import org.cloudburstmc.protocol.bedrock.packet.SetTimePacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

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
    protected final Int2ObjectOpenHashMap<Dimension> dimensionMap;
    @Getter
    protected final Scheduler scheduler;
    protected final GameLoop gameLoop;
    protected final Thread tickThread;
    protected long nextTimeSendTick = 12 * 20;

    public AllayWorld(WorldStorage worldStorage) {
        this.worldStorage = worldStorage;
        this.worldData = worldStorage.getWorldDataCache();
        this.worldData.setWorld(this);
        this.dimensionMap = new Int2ObjectOpenHashMap<>(3);
        this.scheduler = new AllayScheduler();
        this.gameLoop = GameLoop.builder()
                .onTick(gameLoop -> {
                    try {
                        tick(gameLoop.getTick());
                    } catch (Throwable throwable) {
                        log.error("Error while ticking level " + this.getWorldData().getName(), throwable);
                    }
                })
                .build();
        this.tickThread = Thread.ofPlatform()
                .name("World Thread - " + this.getWorldData().getName())
                .unstarted(gameLoop::startLoop);
    }

    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    @Override
    public float getTps() {
        return gameLoop.getTps();
    }

    @Override
    public void tick(long currentTick) {
        getDimensions().values().forEach(e -> e.getPlayers().forEach(EntityPlayerNetworkComponent::handleDataPacket));
        syncData();
        tickTime(currentTick);
        scheduler.tick();
        getDimensions().values().forEach(d -> d.tick(currentTick));
        worldStorage.tick(currentTick);
    }

    protected void syncData() {
        worldData.getGameRules().sync(this);
    }

    @Override
    public void startTick() {
        if (tickThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World is already start ticking!");
        } else tickThread.start();
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
    public void setDimension(Dimension dimension) {
        Preconditions.checkArgument(!this.dimensionMap.containsKey(dimension.getDimensionInfo().dimensionId()));
        this.dimensionMap.put(dimension.getDimensionInfo().dimensionId(), dimension);
    }

    @Override
    public void setGameRule(GameRule gamerule, Object value) {
        worldData.setGameRule(gamerule, value);
    }

    @Override
    public void saveWorldData() {
        getWorldStorage().writeWorldData(worldData);
    }

    @Override
    public void close() {
        getDimensions().forEach((integer, dimension) -> dimension.getChunkService().unloadAllChunks());
        saveWorldData();
        getWorldStorage().close();
    }
}
