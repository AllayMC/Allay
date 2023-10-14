package cn.allay.server.world.service;

import cn.allay.api.annotation.SlowOperation;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.server.Server;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.utils.MathUtils;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.World;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.*;
import cn.allay.api.world.generator.ChunkGenerateContext;
import cn.allay.api.world.service.ChunkService;
import cn.allay.api.world.storage.WorldStorage;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.longs.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static cn.allay.api.world.chunk.ChunkState.*;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayChunkService implements ChunkService {
    public static final int REMOVE_UNNEEDED_CHUNK_CYCLE = 600;
    private final Map<Long, Chunk> loadedChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<Long, CompletableFuture<Chunk>> loadingChunks = new Long2ObjectNonBlockingMap<>();
    private final Map<ChunkLoader, ChunkLoaderManager> chunkLoaderManagers = new ConcurrentHashMap<>();
    private final World world;
    @Getter
    private final WorldStorage worldStorage;
    private final Map<Long, Integer> unusedChunkClearCountDown = new Long2IntOpenHashMap();
    private final Set<Long> keepLoadingChunks = Sets.newConcurrentHashSet();

    public AllayChunkService(World world, WorldStorage worldStorage) {
        this.world = world;
        this.worldStorage = worldStorage;
    }

    @Override
    public void tick() {
        sendChunkPackets();
        tickChunkLoaders();
        removeUnusedChunks();
        tickChunks();
    }

    private void tickChunks() {
        loadedChunks.values().forEach(Chunk::tick);
    }

    private void sendChunkPackets() {
        loadedChunks.values().forEach(Chunk::sendChunkPackets);
    }

    private void tickChunkLoaders() {
        //NOTICE: There is no need to use parallel stream here
        chunkLoaderManagers.values().forEach(ChunkLoaderManager::tick);
    }

    private void removeUnusedChunks() {
        unusedChunkClearCountDown.entrySet().removeIf(entry -> {
            var chunk = getChunk(entry.getKey());
            return chunk == null || chunk.getChunkLoaderCount() > 0 || keepLoadingChunks.contains(entry.getKey());
        });
        //Update countdown
        unusedChunkClearCountDown.replaceAll((chunkHash, countDown) -> countDown - 1);
        //Remove countdown ended unused chunks
        unusedChunkClearCountDown.entrySet().removeIf(entry -> {
            boolean shouldRemove = entry.getValue() == 0;
            if (shouldRemove) {
                unloadChunk(entry.getKey());
            }
            return shouldRemove;
        });

        //Add unused chunk to the clear countdown map
        for (var entry : loadedChunks.entrySet()) {
            Long chunkHash = entry.getKey();
            var loadedChunk = entry.getValue();
            if (loadedChunk.getChunkLoaderCount() == 0 && !keepLoadingChunks.contains(chunkHash) && !unusedChunkClearCountDown.containsKey(chunkHash)) {
                unusedChunkClearCountDown.put(chunkHash, REMOVE_UNNEEDED_CHUNK_CYCLE);
            }
        }
    }

    private Chunk prepareAndSetChunk(int x, int z, Chunk chunk) {
        long hashXZ = HashUtils.hashXZ(x, z);
        var unsafeChunk = chunk.toUnsafeChunk();
        if (unsafeChunk.getState() != FINISHED) {
            var chunkGenerateContext = new ChunkGenerateContext(unsafeChunk, world);
            if (unsafeChunk.getState() == NEW) {
                world.getWorldGenerator().generate(chunkGenerateContext);
                unsafeChunk.setState(ChunkState.GENERATED);
            }
            if (unsafeChunk.getState() == GENERATED) {
                world.getWorldGenerator().populate(chunkGenerateContext);
                unsafeChunk.setState(POPULATED);
            }
            if (unsafeChunk.getState() == POPULATED) {
                afterPopulate(unsafeChunk);
                unsafeChunk.setState(FINISHED);
            }
        }
        setChunk(x, z, chunk);
        loadingChunks.remove(hashXZ);
        return chunk;
    }

    private void setChunk(int x, int z, Chunk chunk) {
        var chunkHash = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(chunkHash))
            throw new IllegalStateException("Trying to set a chunk (" + x + "," + z + ") which is already loaded");
        loadedChunks.put(chunkHash, chunk);
    }

    @Override
    @Nullable
    public Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    @Nullable
    public Chunk getChunk(long chunkHash) {
        return loadedChunks.get(chunkHash);
    }

    @SlowOperation
    @Override
    public Chunk getChunkImmediately(int x, int z) {
        return getOrLoadChunk(x, z).join();
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return CompletableFuture.completedFuture(chunk);
        }
        return loadChunk(x, z);
    }

    @Override
    public CompletableFuture<Set<Chunk>> getOrLoadRangedChunk(int x, int z, int range) {
        // 用于存储CompletableFuture的集合
        Set<CompletableFuture<Chunk>> futureSet = new HashSet<>();

        // 遍历(x, z)为中心，半径为range的区块
        for (int dx = -range; dx <= range; dx++) {
            for (int dz = -range; dz <= range; dz++) {
                if (dx * dx + dz * dz <= range * range) {
                    // 获取或加载每一个块，并将返回的CompletableFuture添加到集合中
                    futureSet.add(getOrLoadChunk(x + dx, z + dz));
                }
            }
        }

        // 当所有的CompletableFuture都完成时，返回一个新的CompletableFuture
        return CompletableFuture.allOf(futureSet.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> futureSet.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toSet()), Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        var hashXZ = HashUtils.hashXZ(x, z);
        if (isChunkLoaded(hashXZ)) {
            throw new IllegalStateException("Chunk is already loaded");
        }
        //Prevent multiple threads from putting the same chunk into loadingChunks at the same time and wasting computing resources
        var presentValue = loadingChunks.putIfAbsent(hashXZ, worldStorage.readChunk(x, z)
                .exceptionally(t -> {
                    log.error("Error while reading chunk (" + x + "," + z + ") !", t);
                    return AllayUnsafeChunk.builder().emptyChunk(x, z, world.getDimensionInfo()).toSafeChunk();
                })
                .thenApplyAsync(loadedChunk -> prepareAndSetChunk(x, z, loadedChunk), Server.getInstance().getComputeThreadPool())
                .exceptionally(t -> {
                    log.error("Error while generating chunk (" + x + "," + z + ") !", t);
                    return AllayUnsafeChunk.builder().emptyChunk(x, z, world.getDimensionInfo()).toSafeChunk();
                })
        );
        if (presentValue == null) {
            return loadingChunks.get(hashXZ);
        } else {
            return presentValue;
        }
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return loadedChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoaded(long hashXZ) {
        return loadedChunks.containsKey(hashXZ);
    }

    @Override
    public boolean isChunkLoading(int x, int z) {
        return loadingChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoading(long hashXZ) {
        return loadingChunks.containsKey(hashXZ);
    }

    @Override
    public boolean isChunkUnloaded(int x, int z) {
        return isChunkUnloaded(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkUnloaded(long hashXZ) {
        return !isChunkLoading(hashXZ) && !isChunkLoaded(hashXZ);
    }

    @Override
    public void addKeepLoadingChunk(int x, int z) {
        keepLoadingChunks.add(HashUtils.hashXZ(x, z));
    }

    @Override
    public void removeKeepLoadingChunk(int x, int z) {
        keepLoadingChunks.remove(HashUtils.hashXZ(x, z));
    }

    @Override
    @UnmodifiableView
    public Set<Long> getKeepLoadingChunks() {
        return Collections.unmodifiableSet(keepLoadingChunks);
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaderManagers.keySet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        this.chunkLoaderManagers.put(chunkLoader, new ChunkLoaderManager(chunkLoader));
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        var removed = this.chunkLoaderManagers.remove(chunkLoader);
        if (removed != null) removed.onRemoved();
    }

    @Override
    public void forEachLoadedChunks(Consumer<Chunk> consumer) {
        loadedChunks.values().forEach(consumer);
    }


    private void afterPopulate(UnsafeChunk chunk) {
        //TODO works...
    }

    public void unloadChunk(int x, int z) {
        unloadChunk(HashUtils.hashXZ(x, z));
    }

    public void unloadChunk(long chunkHash) {
        var chunk = getChunk(chunkHash);
        if (chunk == null) {
            return;
        }
        loadedChunks.remove(chunkHash);
        chunk.save(worldStorage);
        chunk.getEntities().forEach((uniqueId, entity) -> {
            entity.despawnFromAll();
            world.getEntityPhysicsService().removeEntity(entity);
        });
    }

    @Override
    public void unloadAllChunks() {
        this.loadedChunks.values().forEach((c) -> unloadChunk(c.getX(), c.getZ()));
    }

    @Override
    public int maxChunkX() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int maxChunkZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int minChunkX() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int minChunkZ() {
        return Integer.MIN_VALUE;
    }

    private record SubChunkRequestData(org.cloudburstmc.math.vector.Vector3i center,
                                       org.cloudburstmc.math.vector.Vector3i offset) {
    }

    private final class ChunkLoaderManager {
        private final ChunkLoader chunkLoader;
        //保存着上tick已经发送的全部区块hash值
        private final LongOpenHashSet sentChunks = new LongOpenHashSet();
        //保存着上tick已经发送的SubChunkRequestData
        private final Map<Long, Set<SubChunkRequestData>> sentSubChunks = new Long2ObjectOpenHashMap<>();
        //保存着这tick将要发送的全部区块hash值
        private final LongOpenHashSet inRadiusChunks = new LongOpenHashSet();
        private final int chunkTrySendCountPerTick;
        LongComparator chunkDistanceComparator = new LongComparator() {
            @Override
            public int compare(long chunkHash1, long chunkHash2) {
                Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
                var loaderChunkX = floor.x >> 4;
                var loaderChunkZ = floor.z >> 4;
                var chunkDX1 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash1);
                var chunkDZ1 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash1);
                var chunkDX2 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash2);
                var chunkDZ2 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash2);
                //Compare distance to loader
                return Integer.compare(
                        chunkDX1 * chunkDX1 + chunkDZ1 * chunkDZ1,
                        chunkDX2 * chunkDX2 + chunkDZ2 * chunkDZ2
                );
            }
        };
        private final LongArrayPriorityQueue chunkSendQueue = new LongArrayPriorityQueue(200, chunkDistanceComparator);
        private long lastLoaderChunkPosHashed = -1;

        ChunkLoaderManager(ChunkLoader chunkLoader) {
            this.chunkLoader = chunkLoader;
            this.chunkTrySendCountPerTick = chunkLoader.getChunkTrySendCountPerTick();
            chunkLoader.setSubChunkRequestHandler(subChunkRequestPacket -> {
                List<SubChunkData> responseData = new ArrayList<>();
                var centerPosition = subChunkRequestPacket.getSubChunkPosition();
                var positionOffsets = subChunkRequestPacket.getPositionOffsets();
                DimensionInfo dimensionInfo = DimensionInfo.of(subChunkRequestPacket.getDimension());
                for (var offset : positionOffsets) {
                    int sectionY = centerPosition.getY() + offset.getY() - (dimensionInfo.minHeight() >> 4);

                    HeightMapDataType hMapType = HeightMapDataType.NO_DATA;
                    if (sectionY < 0 || sectionY >= dimensionInfo.chunkSectionSize()) {
                        createSubChunkData(responseData, SubChunkRequestResult.INDEX_OUT_OF_BOUNDS, offset, hMapType, null, null, null);
                        continue;
                    }

                    int cx = centerPosition.getX() + offset.getX(), cz = centerPosition.getZ() + offset.getZ();
                    Chunk chunk = getChunk(cx, cz);
                    if (chunk == null) {
                        log.trace("Chunk loader " + chunkLoader + " requested sub chunk which is not loaded");
                        createSubChunkData(responseData, SubChunkRequestResult.CHUNK_NOT_FOUND, offset, hMapType, null, null, null);
                        continue;
                    }

                    var chunkHash = chunk.computeChunkHash();
                    var sent = this.sentSubChunks.get(chunkHash);
                    SubChunkRequestData requestData = new SubChunkRequestData(centerPosition, offset);
                    if (sent != null) {
                        if (sent.contains(requestData)) {
                            log.trace("Chunk loader " + chunkLoader + " requested sub chunk which was already sent");
                            continue;
                        } else {
                            sent.add(requestData);
                        }
                    } else {
                        sent = new HashSet<>();
                        sent.add(requestData);
                        this.sentSubChunks.put(chunkHash, sent);
                    }

                    byte[] hMap = new byte[256];
                    boolean higher = true, lower = true;
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            int y = chunk.getHeight(x, z);
                            int i = (z << 4) | x;
                            int hSectionY = (y - dimensionInfo.minHeight()) >> 4;
                            if (hSectionY > sectionY) {
                                hMap[i] = 16;
                                lower = false;
                            } else if (hSectionY < sectionY) {
                                hMap[i] = -1;
                                higher = false;
                            } else {
                                hMap[i] = (byte) (y - ((hSectionY << 4) + dimensionInfo.minHeight()));
                                higher = false;
                                lower = false;
                            }
                        }
                    }
                    ByteBuf heightMapData;
                    if (higher) {
                        hMapType = HeightMapDataType.TOO_HIGH;
                        heightMapData = Unpooled.EMPTY_BUFFER;
                    } else if (lower) {
                        hMapType = HeightMapDataType.TOO_LOW;
                        heightMapData = Unpooled.EMPTY_BUFFER;
                    } else {
                        hMapType = HeightMapDataType.HAS_DATA;
                        heightMapData = Unpooled.wrappedBuffer(hMap);
                    }
                    var subChunk = chunk.getOrCreateSection(sectionY);
                    SubChunkRequestResult subChunkRequestResult;
                    if (subChunk.isEmpty()) {
                        subChunkRequestResult = SubChunkRequestResult.SUCCESS_ALL_AIR;
                    } else {
                        subChunkRequestResult = SubChunkRequestResult.SUCCESS;
                    }
                    createSubChunkData(responseData, subChunkRequestResult, offset, hMapType, heightMapData, subChunk, chunk.getSectionBlockEntities(sectionY));
                }
                SubChunkPacket subChunkPacket = new SubChunkPacket();
                subChunkPacket.setSubChunks(responseData);
                subChunkPacket.setDimension(subChunkRequestPacket.getDimension());
                subChunkPacket.setCenterPosition(centerPosition);
                return subChunkPacket;
            });
        }

        private static void createSubChunkData(List<SubChunkData> response,
                                               SubChunkRequestResult result,
                                               org.cloudburstmc.math.vector.Vector3i offset,
                                               HeightMapDataType type,
                                               @Nullable ByteBuf heightMapData,
                                               @Nullable ChunkSection subchunk,
                                               @Nullable Collection<BlockEntity> subChunkBlockEntities) {
            SubChunkData subChunkData = new SubChunkData();
            subChunkData.setResult(result);
            subChunkData.setPosition(offset);
            subChunkData.setHeightMapType(type);
            if (result == SubChunkRequestResult.SUCCESS || result == SubChunkRequestResult.SUCCESS_ALL_AIR) {
                if (type == HeightMapDataType.HAS_DATA) {
                    subChunkData.setHeightMapData(heightMapData);
                }
                ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
                subchunk.writeToNetwork(buffer);
                subchunk.biomes().writeToNetwork(buffer, BiomeType::getId);
                // edu - border blocks
                buffer.writeByte(0);
                if (subChunkBlockEntities != null) {
                    try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
                        for (BlockEntity blockEntity : subChunkBlockEntities) {
                            writer.writeTag(blockEntity.saveNBT());
                        }
                    } catch (IOException e) {
                        log.error("Error while encoding block entity in sub chunk!", e);
                    }
                }
                subChunkData.setData(buffer);
            } else {
                subChunkData.setHeightMapData(Unpooled.EMPTY_BUFFER);
                subChunkData.setData(Unpooled.EMPTY_BUFFER);
            }
            response.add(subChunkData);
        }

        public void onRemoved() {
            sentChunks.forEach(chunkHash -> {
                var chunk = getChunk(chunkHash);
                if (chunk != null)
                    chunk.removeChunkLoader(chunkLoader);
            });
            chunkLoader.onChunkOutOfRange(sentChunks);
        }

        public void tick() {
            if (!chunkLoader.isLoaderActive()) return;
            long currentLoaderChunkPosHashed;
            Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
            if ((currentLoaderChunkPosHashed = HashUtils.hashXZ(floor.x >> 4, floor.z >> 4)) != lastLoaderChunkPosHashed) {
                lastLoaderChunkPosHashed = currentLoaderChunkPosHashed;
                updateInRadiusChunks();
                removeOutOfRadiusChunks();
                updateChunkSendingQueue();
            }
            loadAndSendQueuedChunks();
        }

        private void updateInRadiusChunks() {
            Vector3i floor = MathUtils.floor(chunkLoader.getLocation());
            var loaderChunkX = floor.x >> 4;
            var loaderChunkZ = floor.z >> 4;
            var chunkLoadingRadius = chunkLoader.getChunkLoadingRadius();
            inRadiusChunks.clear();
            for (int rx = -chunkLoadingRadius; rx <= chunkLoadingRadius; rx++) {
                for (int rz = -chunkLoadingRadius; rz <= chunkLoadingRadius; rz++) {
                    if (!isChunkInRadius(rx, rz, chunkLoadingRadius)) continue;
                    var chunkX = loaderChunkX + rx;
                    var chunkZ = loaderChunkZ + rz;
                    var hashXZ = HashUtils.hashXZ(chunkX, chunkZ);
                    inRadiusChunks.add(hashXZ);
                }
            }
        }

        private void removeOutOfRadiusChunks() {
            Sets.SetView<Long> difference = Sets.difference(sentChunks, inRadiusChunks);
            difference.forEach(chunkHash -> {
                var chunk = getChunk(chunkHash);
                if (chunk != null)
                    chunk.removeChunkLoader(chunkLoader);
            });
            chunkLoader.onChunkOutOfRange(difference);
            //剩下sentChunks和inRadiusChunks的交集
            sentChunks.removeAll(difference);
            difference.forEach(sentSubChunks::remove);
        }

        private void updateChunkSendingQueue() {
            chunkSendQueue.clear();
            //已经发送的区块不再二次发送
            Sets.SetView<Long> difference = Sets.difference(inRadiusChunks, sentChunks);
            for (long hash : difference) {
                chunkSendQueue.enqueue(hash);
            }
        }

        private void loadAndSendQueuedChunks() {
            if (chunkSendQueue.isEmpty()) return;
            var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
            int triedSendChunkCount = 0;
            do {
                triedSendChunkCount++;
                long chunkHash = chunkSendQueue.firstLong();
                var chunk = getChunk(chunkHash);
                if (chunk == null) {
                    if (isChunkUnloaded(chunkHash)) {
                        loadChunk(HashUtils.getXFromHashXZ(chunkHash), HashUtils.getZFromHashXZ(chunkHash));
                    }
                    continue;
                }
                chunkSendQueue.dequeueLong();
                chunk.addChunkLoader(chunkLoader);
                chunkReadyToSend.put(chunkHash, chunk);
            } while (!chunkSendQueue.isEmpty() && triedSendChunkCount < chunkTrySendCountPerTick);
            chunkLoader.preSendChunks(chunkReadyToSend.keySet());
            chunkReadyToSend.forEach((chunkHash, chunk) -> sentChunks.add(chunkHash.longValue()));
            chunkReadyToSend.values().forEach(chunkLoader::onChunkInRangeLoaded);
        }

        private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
            return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
        }
    }
}
