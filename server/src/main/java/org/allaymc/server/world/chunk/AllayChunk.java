package org.allaymc.server.world.chunk;

import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.poi.PoiType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.function.Consumer;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayChunk implements Chunk {

    @Delegate(types = ThreadSafeMethods.class)
    protected final AllayUnsafeChunk unsafeChunk;

    protected final ChunkSectionLocks blockLocks;
    protected final ChunkSectionLocks biomeLocks;

    AllayChunk(AllayUnsafeChunk unsafeChunk) {
        this.unsafeChunk = unsafeChunk;

        var dimensionInfo = unsafeChunk.getDimensionInfo();
        this.blockLocks = new ChunkSectionLocks(dimensionInfo);
        this.biomeLocks = new ChunkSectionLocks(dimensionInfo);
    }

    @Override
    public short getHeight(int x, int z) {
        AllayUnsafeChunk.checkXZ(x, z);
        short[] height = new short[1];
        applyOperation(chunk -> height[0] = chunk.getHeight(x, z), OperationType.READ, OperationType.NONE);
        return height[0];
    }

    @Override
    public BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight()) {
            return BlockTypes.AIR.getDefaultState();
        }

        AllayUnsafeChunk.checkXZ(x, z);
        var sectionY = y >> 4;
        blockLocks.lockReadLockAt(sectionY);
        try {
            return unsafeChunk.getBlockState(x, y, z, layer);
        } finally {
            blockLocks.unlockReadLockAt(sectionY);
        }
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        unsafeChunk.checkXYZ(x, y, z);

        var sectionY = y >> 4;
        blockLocks.lockWriteLockAt(sectionY);
        try {
            unsafeChunk.setBlockState(x, y, z, blockState, layer, send);
        } finally {
            blockLocks.unlockWriteLockAt(sectionY);
        }
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        if (y < getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight()) {
            return BiomeTypes.PLAINS;
        }

        AllayUnsafeChunk.checkXZ(x, z);
        var sectionY = y >> 4;
        biomeLocks.lockReadLockAt(sectionY);
        try {
            return unsafeChunk.getBiome(x, y, z);
        } finally {
            biomeLocks.unlockReadLockAt(sectionY);
        }
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        unsafeChunk.checkXYZ(x, y, z);

        var sectionY = y >> 4;
        biomeLocks.lockWriteLockAt(sectionY);
        try {
            unsafeChunk.setBiome(x, y, z, biomeType);
        } finally {
            biomeLocks.unlockWriteLockAt(sectionY);
        }
    }

    @Override
    public void applyOperation(Consumer<UnsafeChunk> operation, OperationType block, OperationType biome) {
        tryLockAllSections(block, blockLocks);
        tryLockAllSections(biome, biomeLocks);
        try {
            operation.accept(unsafeChunk);
        } finally {
            tryUnlockAllSections(block, blockLocks);
            tryUnlockAllSections(biome, biomeLocks);
        }
    }

    @Override
    public void applyOperationInSection(int sectionY, Consumer<ChunkSection> operation, OperationType block, OperationType biome) {
        unsafeChunk.checkSectionY(sectionY);
        tryLockSection(sectionY, block, blockLocks);
        tryLockSection(sectionY, biome, biomeLocks);
        try {
            operation.accept(unsafeChunk.getSection(sectionY));
        } finally {
            tryUnlockSection(sectionY, block, blockLocks);
            tryUnlockSection(sectionY, biome, biomeLocks);
        }
    }

    protected void tryLockAllSections(OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.lockAllReadLocks();
            case WRITE -> locks.lockAllWriteLocks();
        }
    }

    protected void tryLockSection(int sectionY, OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.lockReadLockAt(sectionY);
            case WRITE -> locks.lockWriteLockAt(sectionY);
        }
    }

    protected void tryUnlockAllSections(OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.unlockAllReadLocks();
            case WRITE -> locks.unlockAllWriteLocks();
        }
    }

    protected void tryUnlockSection(int sectionY, OperationType operationType, ChunkSectionLocks locks) {
        switch (operationType) {
            case READ -> locks.unlockReadLockAt(sectionY);
            case WRITE -> locks.unlockWriteLockAt(sectionY);
        }
    }

    protected void tryLock(OperationType operationType, ReadWriteLock lock) {
        switch (operationType) {
            case READ -> lock.readLock().lock();
            case WRITE -> lock.writeLock().lock();
        }
    }

    protected void tryUnlock(OperationType operationType, ReadWriteLock lock) {
        switch (operationType) {
            case READ -> lock.readLock().unlock();
            case WRITE -> lock.writeLock().unlock();
        }
    }

    @Override
    public UnsafeChunk toUnsafeChunk() {
        return unsafeChunk;
    }

    private interface ThreadSafeMethods {
        boolean isLoaded();

        Set<ChunkLoader> getChunkLoaders();

        void addChunkLoader(ChunkLoader chunkLoader);

        void removeChunkLoader(ChunkLoader chunkLoader);

        void addChunkTask(Runnable task);

        ChunkState getState();

        DimensionInfo getDimensionInfo();

        int getX();

        int getZ();

        BlockEntity removeBlockEntity(int x, int y, int z);

        BlockEntity getBlockEntity(int x, int y, int z);

        Map<Integer, BlockEntity> getBlockEntities();

        void addBlockEntity(BlockEntity blockEntity);

        Collection<BlockEntity> getSectionBlockEntities(int sectionY);

        void addScheduledUpdate(int x, int y, int z, long time);

        boolean hasScheduledUpdate(int x, int y, int z);

        Map<Integer, PoiType> getPoiEntries();

        void addPoi(int x, int y, int z, PoiType type);

        void removePoi(int x, int y, int z);

        PoiType getPoi(int x, int y, int z);

        ChunkSection getSection(int sectionY);

        List<ChunkSection> getSections();

        Chunk toSafeChunk();
    }
}
