package org.allaymc.api.pack;

import com.google.gson.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.SemVersion;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkDataPacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackDataInfoPacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksInfoPacket;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author IWareQ, Cloudburst Server
 */
@Slf4j
@Getter
public abstract class Pack implements AutoCloseable {

    public static final int MAX_CHUNK_SIZE = Server.SETTINGS.resourcePackSettings().maxChunkSize() * 1024;

    private final PackLoader loader;
    private final PackManifest manifest;
    // Will be empty if this is not an encrypted pack
    private final String contentKey;
    private final byte[] hash;
    private final ByteBuffer buffer;

    public Pack(PackLoader loader, PackManifest manifest, String contentKey) {
        this.loader = loader;
        this.manifest = manifest;
        this.contentKey = contentKey;
        var bytes = loader.readAllBytes();
        try {
            this.hash = MessageDigest.getInstance("SHA-256").digest(bytes);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to calculate the hash of pack " + getName(), exception);
        }
        this.buffer = ByteBuffer.allocateDirect(bytes.length);
        this.buffer.put(bytes);
        this.buffer.flip();
    }

    public String getName() {
        return this.manifest.getHeader().getName();
    }

    public UUID getId() {
        return this.manifest.getHeader().getUuid();
    }

    public SemVersion getVersion() {
        return this.manifest.getHeader().getVersion();
    }

    public String getStringVersion() {
        var version = this.getVersion();
        return String.join(
                ".",
                String.valueOf(version.major()),
                String.valueOf(version.minor()),
                String.valueOf(version.patch())
        );
    }

    public int getSize() {
        return this.buffer.limit();
    }

    public byte[] getHash() {
        return this.hash;
    }

    public ByteBuf getChunk(int offset, int length) {
        byte[] chunk;
        if ((this.getSize() - offset) > length) {
            chunk = new byte[length];
        } else {
            chunk = new byte[(int) (this.getSize() - offset)];
        }

        try {
            buffer.get(offset, chunk);
        } catch (Exception exception) {
            log.error("An error occurred while processing the resource pack {} at offset {} and length {}", getName(), offset, length, exception);
        }

        return Unpooled.wrappedBuffer(chunk);
    }

    public abstract Type getType();

    public ResourcePackDataInfoPacket toNetwork() {
        var packet = new ResourcePackDataInfoPacket();
        packet.setPackId(this.getId());
        packet.setPackVersion(this.getStringVersion());
        packet.setMaxChunkSize(MAX_CHUNK_SIZE);
        packet.setChunkCount((long) Math.ceil(this.getSize() / (double) MAX_CHUNK_SIZE));
        packet.setCompressedPackSize(this.getSize());
        packet.setHash(this.getHash());
        packet.setType(this.getType().getNetworkType());
        return packet;
    }

    public ResourcePackChunkDataPacket getChunkDataPacket(int chunkIndex) {
        var packet = new ResourcePackChunkDataPacket();
        packet.setPackId(this.getId());
        packet.setPackVersion(this.getStringVersion());
        packet.setChunkIndex(chunkIndex);
        packet.setData(this.getChunk(MAX_CHUNK_SIZE * chunkIndex, MAX_CHUNK_SIZE));
        packet.setProgress((long) MAX_CHUNK_SIZE * chunkIndex);
        return packet;
    }

    public ResourcePacksInfoPacket.Entry toEntryInfo() {
        return new ResourcePacksInfoPacket.Entry(
                this.getId(),
                this.getStringVersion(),
                this.getSize(),
                this.getContentKey(),
                "",
                this.getId().toString(),
                this.getType() == Pack.Type.SCRIPT,
                this.manifest.getCapabilities().contains(PackManifest.Capability.RAYTRACED),
                false,
                null
        );
    }

    public ResourcePackStackPacket.Entry toEntryStack() {
        return new ResourcePackStackPacket.Entry(this.getId().toString(), this.getStringVersion(), "");
    }

    @Override
    public void close() throws Exception {
        this.loader.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pack that)) return false;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    /**
     * @see <a href="https://learn.microsoft.com/en-us/minecraft/creator/reference/content/addonsreference/packmanifest?view=minecraft-bedrock-stable#modules">packmanifest</a>
     */
    @Getter
    @RequiredArgsConstructor
    public enum Type {
        RESOURCES(ResourcePackType.RESOURCES),
        DATA(ResourcePackType.DATA_ADD_ON),
        WORLD_TEMPLATE(ResourcePackType.WORLD_TEMPLATE),
        SCRIPT(ResourcePackType.ADDON);

        private final ResourcePackType networkType;

        public static class Serializer implements JsonSerializer<Type> {
            @Override
            public JsonElement serialize(Type src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.name().toLowerCase());
            }
        }

        public static class Deserializer implements JsonDeserializer<Type> {
            @Override
            public Type deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return Type.valueOf(json.getAsString().toUpperCase());
            }
        }
    }

    @FunctionalInterface
    public interface Factory {
        Pack create(PackLoader loader, PackManifest manifest);
    }
}
