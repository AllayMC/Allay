package org.allaymc.server.world;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityService;
import org.allaymc.server.world.service.AllayBlockUpdateService;
import org.allaymc.server.world.service.AllayChunkService;
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import org.allaymc.server.world.service.AllayEntityService;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.allaymc.api.block.type.BlockTypes.AIR_TYPE;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
@Slf4j
@Getter
public class AllayDimension implements Dimension {
    protected final WorldGenerator worldGenerator;
    protected final ChunkService chunkService;
    protected final EntityPhysicsService entityPhysicsService;
    protected final BlockUpdateService blockUpdateService;
    protected final EntityService entityService;
    protected final DimensionInfo dimensionInfo;
    protected final World world;
    protected final Set<EntityPlayer> players;
    protected final Set<EntityPlayer> unmodifiablePlayersView;

    public AllayDimension(World world, WorldGenerator worldGenerator, DimensionInfo dimensionInfo) {
        this.world = world;
        this.dimensionInfo = dimensionInfo;
        this.worldGenerator = worldGenerator;
        this.worldGenerator.setDimension(this);
        this.chunkService = new AllayChunkService(this, world.getWorldStorage());
        this.entityPhysicsService = new AllayEntityPhysicsService(this);
        this.entityService = new AllayEntityService(entityPhysicsService);
        this.blockUpdateService = new AllayBlockUpdateService(this);
        this.players = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.unmodifiablePlayersView = Collections.unmodifiableSet(players);
    }

    @Override
    public void tick(long currentTick) {
        chunkService.tick();
        entityService.tick();
        entityPhysicsService.tick();
        blockUpdateService.tick(currentTick);
    }

    @Override
    public void close() {
        chunkService.unloadAllChunks();
    }

    @Override
    public void addPlayer(EntityPlayer player, Runnable runnable) {
        players.add(player);
        chunkService.addChunkLoader(player);
        entityService.addEntity(player, runnable);
    }

    @Override
    public void removePlayer(EntityPlayer player, Runnable runnable) {
        if (player.isSpawned()) {
            // 玩家死亡后重生到另外一个维度时，玩家实体已经被卸载
            // 所以说在卸载玩家实体时，需要先检查玩家实体是否生成
            entityService.removeEntity(player, runnable);
            chunkService.removeChunkLoader(player);
            players.remove(player);
        } else {
            chunkService.removeChunkLoader(player);
            players.remove(player);
            // 直接运行回调
            runnable.run();
        }
    }

    @Override
    @UnmodifiableView
    public Set<EntityPlayer> getPlayers() {
        return unmodifiablePlayersView;
    }

    @Override
    public void breakBlock(int x, int y, int z, ItemStack usedItem, EntityPlayer player) {
        var block = getBlockState(x, y, z);
        if (block.getBlockType() == AIR_TYPE) {
            log.warn("Trying to break air block at x={}, y={}, z={}", x, y, z);
            return;
        }
        var pk = new LevelEventPacket();
        pk.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
        pk.setPosition(Vector3f.from(x + 0.5f, y + 0.5f, z + 0.5f));
        pk.setData(block.blockStateHash());
        getChunkService().getChunkByLevelPos(x, z).addChunkPacket(pk);
        block.getBehavior().onBreak(
                new BlockStateWithPos(block, new Position3i(x, y, z, this), 0),
                usedItem,
                player
        );
        setBlockState(x, y, z, AIR_TYPE.getDefaultState());
    }

    @Override
    public String toString() {
        return "world=" + this.world.getWorldData().getName() + " dimId=" + this.dimensionInfo.dimensionId();
    }
}
