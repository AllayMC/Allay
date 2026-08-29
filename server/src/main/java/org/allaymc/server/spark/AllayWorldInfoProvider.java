package org.allaymc.server.spark;

import me.lucko.spark.common.platform.world.AbstractChunkInfo;
import me.lucko.spark.common.platform.world.CountMap;
import me.lucko.spark.common.platform.world.WorldInfoProvider;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AllayWorldInfoProvider implements WorldInfoProvider {

    @Override
    public CountsResult pollCounts() {
        int players = 0;
        int entities = 0;
        int tileEntities = 0;
        int chunks = 0;

        for (World world : Server.getInstance().getWorldPool().getWorlds().values()) {
            players += world.getPlayers().size();
            for (Dimension dimension : world.getDimensions().values()) {
                entities += dimension.getEntityManager().getEntities().size();
                tileEntities += dimension.getBlockEntityCount();
                chunks += dimension.getChunkManager().getLoadedChunks().size();
            }
        }

        return new CountsResult(players, entities, tileEntities, chunks);
    }

    @Override
    public ChunksResult<AllayChunkInfo> pollChunks() {
        ChunksResult<AllayChunkInfo> data = new ChunksResult<>();

        for (World world : Server.getInstance().getWorldPool().getWorlds().values()) {
            List<AllayChunkInfo> list = new ArrayList<>();
            for (Dimension dimension : world.getDimensions().values()) {
                for (var chunk : dimension.getChunkManager().getLoadedChunks()) {
                    list.add(new AllayChunkInfo(chunk.getX(), chunk.getZ(), dimension));
                }
            }
            data.put(world.getName(), list);
        }

        return data;
    }

    @Override
    public GameRulesResult pollGameRules() {
        GameRulesResult data = new GameRulesResult();

        boolean addDefaults = true;
        for (World world : Server.getInstance().getWorldPool().getWorlds().values()) {
            for (var entry : world.getWorldData().getGameRules().getGameRules().entrySet()) {
                if (addDefaults) {
                    data.putDefault(entry.getKey().getName(), String.valueOf(entry.getKey().getDefaultValue()));
                }
                data.put(entry.getKey().getName(), world.getName(), String.valueOf(entry.getValue()));
            }
            addDefaults = false;
        }

        return data;
    }

    @Override
    public Collection<DataPackInfo> pollDataPacks() {
        return null;
    }

    @Override
    public boolean mustCallSync() {
        return false;
    }

    static final class AllayChunkInfo extends AbstractChunkInfo<EntityType<?>> {
        private final CountMap<EntityType<?>> entityCounts;

        AllayChunkInfo(int x, int z, Dimension dimension) {
            super(x, z);
            this.entityCounts = new CountMap.Simple<>(new HashMap<>());
            dimension.getEntityManager().getEntitiesInChunk(x, z).values().forEach(entity -> this.entityCounts.increment(entity.getEntityType()));
        }

        @Override
        public CountMap<EntityType<?>> getEntityCounts() {
            return this.entityCounts;
        }

        @Override
        public String entityTypeName(EntityType<?> type) {
            return type.getIdentifier().toString();
        }
    }
}