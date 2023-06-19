package cn.allay.server.world.anvil;

import cn.allay.api.utils.zlib.CompressionType;
import cn.allay.api.utils.zlib.ZlibProviderType;
import cn.allay.api.world.DimensionInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.BitSet;

/**
 * Author: Cool_Loong <br>
 * Date: 6/4/2023 <br>
 * Allay Project
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

        if (this.channel.size() < HEADER_LENGTH) { // new file, fill in data
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
        ByteBuffer locations = ByteBuffer.allocate(SECTOR_SIZE * 2);//8K Bytes
        while (locations.hasRemaining()) {
            if (channel.read(locations) == -1) {
                throw new EOFException();
            }
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
            if (loc != 0 && index >= 0 && index + count <= availableSectors) {
                usedSectors.set(index, index + count + 1);
            }
        }
        for (int i = 0; i < MAX_ENTRY_COUNT; i++) {
            this.timestamps.add(ints.get());
        }
    }

    public synchronized NbtMap readChunkData(int chunkX, int chunkZ) throws IOException {
        int loc = this.locations.getInt(index(chunkX, chunkZ));
        int fileOffset = sectorIndex(loc) * SECTOR_SIZE;
        int sectorCount = sectorCount(loc);
        // Seek to the sector position in file channel.
        this.channel.position(fileOffset);
        // The number of bytes occupied by the chunk is allocated according to the number of sectors
        int bytes = sectorCount * SECTOR_SIZE;
        ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer(bytes);
        // Read data from the file channel to the buffer
        while (buffer.writerIndex() < bytes) {
            int written = this.channel.read(buffer.internalNioBuffer(buffer.writerIndex(), buffer.writableBytes()));
            if (written == -1) {
                throw new EOFException();
            }
            buffer.writerIndex(buffer.writerIndex() + written);
        }
        // Read the chunk data length bits
        int length = buffer.readInt();
        ByteBuf chunk = buffer.readSlice(length);
        // The first byte represent the compression type, and the remain of the data is raw chunk data
        byte compressionType = chunk.readByte();
        byte[] input = new byte[chunk.readableBytes()];
        byte[] output;
        chunk.readBytes(input);
        switch (compressionType) {
            case GZIP_COMPRESSION:
                output = ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.GZIP, 6).inflate(input, CHUNK_SIZE_LIMIT);
                break;
            case ZLIB_COMPRESSION:
                output = ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.ZLIB, 6).inflate(input, CHUNK_SIZE_LIMIT);
                break;
            default:
                throw new IllegalArgumentException("Unknown compression type: " + compressionType);
        }
        NBTInputStream reader = NbtUtils.createReader(new BufferedInputStream(new ByteArrayInputStream(output)));
        return (NbtMap) reader.readTag();
    }

    public synchronized void writeChunk(int chunkX, int chunkZ, NbtMap chunkData) throws IOException {
        // Convert chunk data to byte stream and compress
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        NBTOutputStream writer = NbtUtils.createWriter(new BufferedOutputStream(byteArrayOutputStream));
        writer.writeTag(chunkData);
        byte[] deflateData = ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.ZLIB, 6).deflate(byteArrayOutputStream.toByteArray());
        writer.close();
        // Calculate the number of sector needed for the chunk
        int sectorCount = (deflateData.length + CHUNK_HEADER_LENGTH) / SECTOR_SIZE;
        // The maximum sector count of a chunk can use 256 sector, that is to say, the maximum size of a chunk is 256 * 4096 = 1M
        if (sectorCount >= SECTOR_COUNT_PER1M) {
            throw new IllegalArgumentException("Writing this chunk would take too many sectors (limit is 255, but " + sectorCount + " is needed)");
        }

        // Get the sector index and size previously used by this chunk
        var index = index(chunkX, chunkZ);
        var previousSectorCount = sectorCount(locations.getInt(index));
        var previousSectorStart = sectorIndex(locations.getInt(index));

        var appendToEnd = false;
        long position;
        int sectorStartCount;

        // Find the first sector index that is continuous and enough to store data
        sectorStartCount = findAvailableSectors(sectorCount);
        // Not found , we need to allocate sectors
        if (sectorStartCount == -1) {
            var eof = channel.size();
            position = eof;
            sectorStartCount = (int) eof / SECTOR_SIZE;
            // fill up sectors
            for (int i = 0; i < sectorCount; i++) {
                channel.position(eof + i * SECTOR_SIZE);
                channel.write(ByteBuffer.allocate(SECTOR_SIZE));
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
        locations.set(index, buildLocation(sectorStartCount, sectorCount));
        timestamps.set(index, Long.valueOf(System.currentTimeMillis()).intValue());
        ByteBuffer location = ByteBuffer.allocateDirect(4);//int
        header.putInt(locations.getInt(index));
        header.flip();
        ByteBuffer timestamp = ByteBuffer.allocateDirect(4);//int
        header.putInt(timestamps.getInt(index));
        header.flip();
        channel.write(location, index * 4L);
        channel.write(timestamp, index * 4L + 4096);

        // the data has been written, now free previous storage
        usedSectors.set(previousSectorStart, previousSectorStart + previousSectorCount + 1, false);
    }

    /**
     * Finds the first location in freeSectors which has sectorCount consecutive free sectors.
     * If none can be found in the file, returns -1. That means allocations will have to take place.
     */
    private int findAvailableSectors(int sectorCount) {
        for (int start = 0; start < usedSectors.size() - sectorCount; start++) {
            boolean found = false;
            for (int i = 0; i < sectorCount; i++) {
                found = !usedSectors.get(i + start);
            }
            if (found) {
                return start;
            }
        }
        return -1;
    }

    private void alignment4K() throws IOException {
        // file is not a multiple of 4kib, add padding
        var missingPadding = channel.size() % SECTOR_SIZE;
        if (missingPadding > 0) {
            channel.write(ByteBuffer.allocate((int) (SECTOR_SIZE - missingPadding)));
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
