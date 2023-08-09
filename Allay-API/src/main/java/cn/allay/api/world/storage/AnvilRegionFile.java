package cn.allay.api.world.storage;

import cn.allay.api.zlib.CompressionType;
import cn.allay.api.zlib.ZlibProviderType;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.BitSet;

/**
 * Allay Project 2023/6/4
 *
 * @author Cool_Loong
 */
public final class AnvilRegionFile implements Cloneable {
    private static final byte GZIP_COMPRESSION = 1;
    private static final byte ZLIB_COMPRESSION = 2;
    private static final byte NO_COMPRESSION = 3;
    private static final int MAX_ENTRY_COUNT = 1024;
    private static final int SECTOR_SIZE = 4096;//4K Bytes per sector
    private static final int CHUNK_SIZE_LIMIT = 1024 * 1024;
    private static final int SECTOR_COUNT_PER1M = CHUNK_SIZE_LIMIT / SECTOR_SIZE;
    private static final ByteBuffer EMPTY_SECTOR = ByteBuffer.allocateDirect(SECTOR_SIZE).asReadOnlyBuffer();
    private static final int HEADER_LENGTH = MAX_ENTRY_COUNT * 2 * 4; // 2 4-byte field per entry
    private static final int CHUNK_HEADER_LENGTH = 4 + 1; // 4Bytes chunk length, 1Byte compression type

    private final FileChannel channel;
    private final IntArrayList locations = new IntArrayList(SECTOR_SIZE);
    private final IntArrayList timestamps = new IntArrayList(SECTOR_SIZE);
    private final BitSet usedSectors = new BitSet();
    private final int regionX;
    private final int regionZ;

    public AnvilRegionFile(Path region, int regionX, int regionZ) throws IOException {
        this.regionX = regionX;
        this.regionZ = regionZ;
        channel = FileChannel.open(region.resolve("r.%d.%d.mca".formatted(regionX, regionZ)), StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE);
        //Set the pointer in the file header
        channel.position(0);

        // new file, fill in data
        if (this.channel.size() < HEADER_LENGTH) {
            // If the file is empty, initialize the 8K Byte data
            if (channel.size() == 0) {
                channel.write(new ByteBuffer[]{EMPTY_SECTOR.duplicate(), EMPTY_SECTOR.duplicate()});
            } else {
                channel.write(ByteBuffer.allocate((int) (HEADER_LENGTH - channel.size())));
            }
        }
        alignment4K();

        // prepare sectors
        int availableSectors = (int) (channel.size() / SECTOR_SIZE);
        // Each sector is 4K Bytes, and the file header occupies two sectors(chunk offset table and timestamp table)
        usedSectors.set(0, 2);

        // read chunk locations
        channel.position(0);
        ByteBuffer locations = ByteBuffer.allocate(SECTOR_SIZE * 2);//8K Bytes
        if (channel.read(locations) == -1) {
            throw new EOFException();
        }
        // `locations` buffer to complete the preparation for reading
        locations.flip();
        IntBuffer ints = locations.asIntBuffer();
        // init locations and timestamps
        for (int i = 0; i < MAX_ENTRY_COUNT; i++) {
            int loc = ints.get();
            this.locations.add(loc);

            // mark already allocated sectors as taken.
            // loc 0 means the chunk is *not* stored in the file
            int index = sectorIndex(loc);
            int count = sectorCount(loc);
            if (loc != 0 && index + count <= availableSectors) {
                usedSectors.set(index, index + count);
            }
        }
        for (int i = 0; i < MAX_ENTRY_COUNT; i++) {
            this.timestamps.add(ints.get());
        }
    }

    @NotNull
    public synchronized NbtMap readChunkData(@Range(from = 0, to = 31) int chunkX, @Range(from = 0, to = 31) int chunkZ) throws IOException {
        int loc = this.locations.getInt(index(chunkX, chunkZ));
        if (loc == 0) {
            return NbtMap.EMPTY;
        }
        long fileOffset = (long) sectorIndex(loc) * SECTOR_SIZE;
        int sectorCount = sectorCount(loc);
        // Seek to the sector position in file channel.
        this.channel.position(fileOffset);
        // The number of bytes occupied by the chunk is allocated according to the number of sectors
        int bytes = sectorCount * SECTOR_SIZE;
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        // Read data from the file channel to the buffer
        int written = this.channel.read(buffer);
        if (written == -1) {
            throw new EOFException();
        }
        buffer.flip();
        // The first byte represent the compression type, and the remain of the data is raw chunk data
        // Read the chunk data length
        int length = buffer.getInt();
        byte compressionType = buffer.get();
        byte[] input = new byte[length];
        buffer.get(input);
        byte[] output;
        output = switch (compressionType) {
            case GZIP_COMPRESSION ->
                    ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.GZIP, 6).inflate(input, CHUNK_SIZE_LIMIT);
            case ZLIB_COMPRESSION ->
                    ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.ZLIB, 6).inflate(input, CHUNK_SIZE_LIMIT);
            default -> throw new IllegalArgumentException("Unknown compression type: " + compressionType);
        };
        NBTInputStream reader = NbtUtils.createReader(new ByteArrayInputStream(output));
        return (NbtMap) reader.readTag();
    }

    /**
     * Write chunk.
     *
     * @param chunkX    the chunk x
     * @param chunkZ    the chunk z
     * @param chunkData the chunk data
     * @throws IOException the io exception
     */
    public synchronized void writeChunk(@Range(from = 0, to = 31) int chunkX, @Range(from = 0, to = 31) int chunkZ, NbtMap chunkData) throws IOException {
        // Convert chunk data to byte stream and compress
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(CHUNK_SIZE_LIMIT);
        NBTOutputStream writer = NbtUtils.createWriter(byteArrayOutputStream);
        writer.writeTag(chunkData);
        byte[] deflateData = ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.ZLIB, 6).deflate(byteArrayOutputStream.toByteArray());
        writer.close();
        // Calculate the number of sector needed for the chunk
        int sectorCount = (int) Math.ceil((double) (deflateData.length + CHUNK_HEADER_LENGTH) / SECTOR_SIZE);
        // The maximum sector count of a chunk can use 256 sector, that is to say, the maximum size of a chunk is 256 * 4096 = 1M
        if (sectorCount >= SECTOR_COUNT_PER1M) {
            throw new IllegalArgumentException("Writing this chunk would take too many sectors (limit is 255, but " + sectorCount + " is needed)");
        }

        // Get the sector index and size previously used by this chunk
        int index = index(chunkX, chunkZ);
        int previousSectorCount = sectorCount(locations.getInt(index));
        int previousSectorStart = sectorIndex(locations.getInt(index));

        boolean appendToEnd = false;
        long position;
        int sectorStartCount;

        // Find the first sector index that is continuous and enough to store data
        sectorStartCount = findAvailableSectors(sectorCount);
        // Not found , we need to allocate sectors
        if (sectorStartCount == -1) {
            var eof = channel.size();
            position = eof;
            sectorStartCount = (int) (eof / SECTOR_SIZE);
            // fill up sectors
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(SECTOR_SIZE);
            for (int i = 0; i < sectorCount; i++) {
                channel.position(eof + i * SECTOR_SIZE);
                channel.write(byteBuffer);
            }
            appendToEnd = true;
        } else {
            // found, calculate file offset
            position = (long) sectorStartCount * SECTOR_SIZE;
        }
        // Mark the new partition
        usedSectors.set(sectorStartCount, sectorStartCount + sectorCount + 1);

        // Write chunk header data
        ByteBuffer header = ByteBuffer.allocateDirect(CHUNK_HEADER_LENGTH);
        header.putInt(deflateData.length).put(ZLIB_COMPRESSION);
        header.flip();

        // write to file
        channel.position(position);
        channel.write(new ByteBuffer[]{header, ByteBuffer.wrap(deflateData)});
        // we are at the EOF, we may have to add some padding
        if (appendToEnd) {
            alignment4K();
        }

        // Update and write locations and timestamps
        int loc = buildLocation(sectorStartCount, sectorCount);
        int time = Long.valueOf(System.currentTimeMillis()).intValue();
        locations.set(index, loc);
        timestamps.set(index, time);
        ByteBuffer location = ByteBuffer.allocate(4);//int
        location.putInt(loc);
        location.flip();
        ByteBuffer timestamp = ByteBuffer.allocate(4);//int
        timestamp.putInt(time);
        timestamp.flip();
        System.out.println(channel.write(location, index * 4L));
        System.out.println(channel.write(timestamp, index * 4L + 4096));
        // the data has been written, now free previous storage
        usedSectors.set(previousSectorStart, previousSectorStart + previousSectorCount, false);
    }

    /**
     * Finds the first location in freeSectors which has sectorCount consecutive free sectors.
     * If none can be found in the file, returns -1. That means allocations will have to take place.
     */
    private int findAvailableSectors(int sectorCount) {
        for (int start = 0; start < usedSectors.size() - sectorCount; start++) {
            boolean found = true;
            for (int i = 0; i < sectorCount; i++) {
                if (usedSectors.get(i + start)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return start;
            }
        }
        return -1;
    }

    private void alignment4K() throws IOException {
        // file is not a multiple of 4kib, add padding
        int missingPadding = (int) (channel.size() % SECTOR_SIZE);
        if (missingPadding > 0) {
            channel.write(ByteBuffer.allocate(SECTOR_SIZE - missingPadding));
        }
    }

    //The lower eight bits are the number of sectors occupied by a chunk
    private static int sectorCount(int location) {
        return location & 0xFF;
    }

    //The upper eight bits are the index of the first sectors where a chunk resides
    private static int sectorIndex(int location) {
        return location >>> 8;
    }

    private static int index(int chunkX, int chunkZ) {
        return (chunkX & 31) + (chunkZ & 31) * 32;
    }

    private static int buildLocation(int start, int length) {
        return ((start << 8) | (length & 0xFF)) & 0xFFFFFFFF;
    }
}
