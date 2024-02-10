package org.allaymc.spark;

import me.lucko.spark.common.platform.world.AbstractChunkInfo;
import me.lucko.spark.common.platform.world.CountMap;
import me.lucko.spark.common.platform.world.WorldInfoProvider;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.chunk.Chunk;

/**
 * Allay Project 10/02/2024
 *
 * @author IWareQ
 */
public class AllayWorldInfoProvider implements WorldInfoProvider {

    @Override
    public CountsResult pollCounts() {
        var server = Server.getInstance();
        int players = server.getOnlinePlayers().size();
        int entities = 0;
        var blockEntities = 0;
        int chunks = 0;

        for (var world : server.getWorldPool().getWorlds().values()) {
            for (var dimension : world.getDimensions().values()) {
                entities += dimension.getEntities().size();
                blockEntities += dimension.getChunkService().getLoadedChunks()
                        .stream()
                        .mapToInt(chunk -> chunk.getBlockEntities().size())
                        .sum();
                chunks += dimension.getChunkService().getLoadedChunks().size();
            }
        }

        return new CountsResult(players, entities, blockEntities, chunks);
    }

    @Override
    public ChunksResult<AllayChunkInfo> pollChunks() {
        ChunksResult<AllayChunkInfo> result = new ChunksResult<>();

        for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
            for (var dimension : world.getDimensions().values()) {
                var chunks = dimension.getChunkService().getLoadedChunks();
                var chunkInfos = chunks.stream().map(AllayChunkInfo::new).toList();

                result.put(world.getWorldData().getName() + "_" + dimension.getDimensionInfo().toString(), chunkInfos);
            }
        }

        return result;
    }

    @Override
    public boolean mustCallSync() {
        return true;
    }

    public static class AllayChunkInfo extends AbstractChunkInfo<EntityType<?>> {

        private final CountMap<EntityType<?>> entityCounts;

        protected AllayChunkInfo(Chunk chunk) {
            super(chunk.getX(), chunk.getZ());

            this.entityCounts = new CountMap.EnumKeyed(EntityType.class);
            for (var entity : chunk.getEntities().values())
                this.entityCounts.increment(entity.getEntityType());
        }

        @Override
        public CountMap<EntityType<?>> getEntityCounts() {
            return this.entityCounts;
        }

        @Override
        public String entityTypeName(EntityType type) {
            return type.getIdentifier().path();
        }
    }
}
