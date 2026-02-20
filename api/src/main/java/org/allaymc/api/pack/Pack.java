package org.allaymc.api.pack;

import com.google.gson.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.semver4j.Semver;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.UUID;

/**
 * @author IWareQ | Cloudburst Server | daoge_cmd
 */
@Slf4j
@Getter
public abstract class Pack implements AutoCloseable {

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

    public Semver getVersion() {
        return this.manifest.getHeader().getVersion();
    }

    public String getStringVersion() {
        return this.getVersion().getVersion();
    }

    public int getSize() {
        return this.buffer.limit();
    }

    public byte[] getHash() {
        return this.hash;
    }

    public byte[] getChunk(int offset, int length) {
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

        return chunk;
    }

    public abstract Type getType();

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
     * Type represents the type of pack.
     *
     * @see <a href="https://learn.microsoft.com/en-us/minecraft/creator/reference/content/addonsreference/packmanifest?view=minecraft-bedrock-stable#modules">packmanifest</a>
     */
    @Getter
    @RequiredArgsConstructor
    public enum Type {
        RESOURCES,
        DATA,
        WORLD_TEMPLATE,
        SCRIPT;

        public static class Serializer implements JsonSerializer<Type> {
            @Override
            public JsonElement serialize(Type src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.name().toLowerCase(Locale.ROOT));
            }
        }

        public static class Deserializer implements JsonDeserializer<Type> {
            @Override
            public Type deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return Type.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
            }
        }
    }

    @FunctionalInterface
    public interface Factory {
        Pack create(PackLoader loader, PackManifest manifest);
    }
}
