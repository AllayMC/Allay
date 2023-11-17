package org.allaymc.server.world;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.generator.Generator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityUpdateService;
import org.allaymc.server.world.chunk.AllayChunk;
import org.allaymc.server.world.service.AllayBlockUpdateService;
import org.allaymc.server.world.service.AllayChunkService;
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import org.allaymc.server.world.service.AllayEntityUpdateService;
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
    protected final DimensionInfo dimensionInfo;
    @Getter
    protected final World world;
    protected final Set<EntityPlayer> players;


    public AllayDimension(World world, Generator generator, DimensionInfo dimensionInfo) {
        this.world = world;
        this.dimensionInfo = dimensionInfo;
        this.generator = generator;
        this.chunkService = new AllayChunkService(this, world.getWorldStorage());
        this.entityPhysicsService = new AllayEntityPhysicsService(this);
        this.entityUpdateService = new AllayEntityUpdateService(entityPhysicsService);
        this.blockUpdateService = new AllayBlockUpdateService(this);
        this.players = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void tick(long currentTick) {
        chunkService.tick();
        entityPhysicsService.tick();
        blockUpdateService.tick(currentTick);
    }

    @Override
    public void addPlayer(EntityPlayer player) {
        players.add(player);
        chunkService.addChunkLoader(player);
        entityUpdateService.addEntity(player);

//        //You can't use updateService because the player entity will be added on the next tick,
//        // and the netty thread will receive the move request asynchronously
//
//        //This sync prevents multi-threaded write, but does not guarantee strong consistency
//        synchronized (this) {
//            var chunk = (AllayChunk) player.getCurrentChunk();
//            if (chunk == null) throw new IllegalStateException("Entity can't spawn in unloaded chunk!");
//            chunk.addEntity(player);
//            player.spawnTo(chunk.getPlayerChunkLoaders());
//            entityPhysicsService.addEntity(player);
//        }
    }

    @Override
    public void removePlayer(EntityPlayer player) {
        players.remove(player);
        chunkService.removeChunkLoader(player);
        entityUpdateService.removeEntity(player);

//        //You can't use updateService because the player entity will be added on the next tick,
//        // and the netty thread will receive the move request asynchronously
//
//        //This sync prevents multi-threaded write, but does not guarantee strong consistency
//        synchronized (this) {
//            var chunk = (AllayChunk) player.getCurrentChunk();
//            if (chunk == null) throw new IllegalStateException("Trying to despawn an entity from an unload chunk!");
//            entityPhysicsService.removeEntity(player);
//            chunk.removeEntity(player.getUniqueId());
//            player.despawnFromAll();
//            player.setWillBeRemovedNextTick(false);
//        }
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
