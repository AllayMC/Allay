package org.allaymc.server.world;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.generator.Generator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityUpdateService;
import org.allaymc.server.GameLoop;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.world.service.AllayBlockUpdateService;
import org.allaymc.server.world.service.AllayChunkService;
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import org.allaymc.server.world.service.AllayEntityUpdateService;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class AllayDimension implements Dimension {
    @Getter
    protected final Generator generator;
    @Getter
    protected final ChunkService chunkService;
    @Getter
    protected final EntityPhysicsService entityPhysicsService;
    @Getter
    protected final BlockUpdateService blockUpdateService;
    @Getter
    protected final EntityUpdateService entityUpdateService;
    @Getter
    protected final Scheduler scheduler;
    @Getter
    protected final DimensionInfo dimensionInfo;
    protected final Set<EntityPlayer> players;
    protected final Thread tickThread;
    protected final GameLoop gameLoop;
    @Getter
    protected World world;

    public AllayDimension(Generator generator, DimensionInfo dimensionInfo) {
        this.dimensionInfo = dimensionInfo;
        gameLoop = GameLoop.builder()
                .onTick(gameLoop -> {
                    try {
                        tick();
                    } catch (Throwable throwable) {
                        log.error("Error while ticking level " + world.getWorldData().getName() + " dimension " + dimensionInfo.dimensionId(), throwable);
                    }
                })
                .build();
        this.generator = generator;
        this.chunkService = new AllayChunkService(this, world.getWorldStorage());
        this.entityPhysicsService = new AllayEntityPhysicsService(this);
        this.entityUpdateService = new AllayEntityUpdateService(entityPhysicsService);
        this.blockUpdateService = new AllayBlockUpdateService(this);
        this.players = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.scheduler = new AllayScheduler();
        this.tickThread = Thread.ofPlatform()
                .name("World Thread - " + this.getWorld().getWorldData().getName() + " -" + getDimensionInfo().dimensionId())
                .unstarted(gameLoop::startLoop);
    }

    @ApiStatus.Internal
    public void setWorld(World world) {
        this.world = world;
    }

    protected void tick() {
        long currentTick = getTick();
        chunkService.tick();
        entityPhysicsService.tick();
        scheduler.tick();
        blockUpdateService.tick(currentTick);
    }

    @Override
    public void startTick() {
        if (tickThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World is already start ticking!");
        } else tickThread.start();
    }

    @Override
    public Server getServer() {
        return world.getServer();
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
    public void addPlayer(EntityPlayer player) {
        players.add(player);
        chunkService.addChunkLoader(player);
        this.entityUpdateService.addEntity(player);
    }

    @Override
    public void removePlayer(EntityPlayer player) {
        players.remove(player);
        chunkService.removeChunkLoader(player);
        this.entityUpdateService.removeEntity(player);
    }

    @Override
    public @UnmodifiableView Collection<EntityPlayer> getPlayers() {
        return Collections.unmodifiableCollection(players);
    }

    @Override
    public String toString() {
        return "world=" + this.world.getWorldData().getName() + " dimId=" + this.dimensionInfo.dimensionId();
    }
}
