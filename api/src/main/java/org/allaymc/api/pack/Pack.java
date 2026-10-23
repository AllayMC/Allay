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
 * {@code Pack} represents a loaded Bedrock pack with manifest metadata, archive
 * bytes, a SHA-256 digest, and an optional encryption key. The constructor reads
 * the archive once into direct storage for chunk transfer. Metadata and buffer
 * accessors expose retained objects, and {@link #close()} closes the associated
 * loader rather than explicitly freeing the direct buffer.
 *
 * @author IWareQ | Cloudburst Server | daoge_cmd
 */
@Slf4j
@Getter
public abstract class Pack implements AutoCloseable {

    /**
     * {@code loader} retains the source whose lifetime ends through
     * {@link #close()}. Its generated getter exposes the same loader instance.
     */
    private final PackLoader loader;
    /**
     * {@code manifest} retains mutable metadata supplied by the caller. Its
     * generated getter exposes the same object used for identity and version
     * lookups.
     */
    private final PackManifest manifest;
    /** Pack content key; an empty string denotes unencrypted content. */
    private final String contentKey;
    /** Digest of the archive bytes read during construction. */
    private final byte[] hash;
    /**
     * {@code buffer} holds archive bytes in direct storage. Its generated getter
     * exposes mutable contents and limits, so consumers must preserve them for
     * subsequent chunk reads.
     */
    private final ByteBuffer buffer;

    /**
     * Creates a loaded pack by reading all archive bytes from {@code loader}.
     * The constructor retains {@code loader} and {@code manifest}; callers must
     * close the pack after use, and construction failure does not close the
     * loader automatically.
     *
     * @throws IllegalStateException if the archive digest cannot be calculated
     */
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

    /**
     * Returns the display name currently stored in the manifest header.
     */
    public String getName() {
        return this.manifest.getHeader().getName();
    }

    /**
     * Returns the manifest header UUID used as this pack's identity.
     */
    public UUID getId() {
        return this.manifest.getHeader().getUuid();
    }

    /**
     * Returns the version object currently stored in the manifest header.
     */
    public Semver getVersion() {
        return this.manifest.getHeader().getVersion();
    }

    /**
     * Returns the semantic version text from the current manifest header.
     */
    public String getStringVersion() {
        return this.getVersion().getVersion();
    }

    /**
     * Returns the archive buffer's current limit measured in bytes.
     */
    public int getSize() {
        return this.buffer.limit();
    }

    /**
     * Returns the SHA-256 digest computed from the original archive bytes.
     * The returned array is shared with this pack, so callers must copy it
     * before modifying its contents.
     */
    public byte[] getHash() {
        return this.hash;
    }

    /**
     * Returns at most {@code length} archive bytes beginning at {@code offset},
     * stopping at the current buffer limit. Callers must supply a nonnegative
     * length and an offset between zero and that limit, inclusive. Allocation
     * failures propagate, while a failed buffer read is logged and returns the
     * allocated array.
     *
     * @param offset byte offset from the archive's beginning
     * @param length maximum number of bytes to copy
     */
    public byte[] getChunk(int offset, int length) {
        byte[] chunk;
        // Limit the final chunk to remaining bytes instead of adding archive padding.
        if ((this.getSize() - offset) > length) {
            chunk = new byte[length];
        } else {
            chunk = new byte[(int) (this.getSize() - offset)];
        }

        try {
            buffer.get(offset, chunk);
        } catch (Exception exception) {
            // A failed buffer read leaves this allocation as the result; callers
            // receive no failure signal beyond the server's diagnostic log.
            log.error("An error occurred while processing the resource pack {} at offset {} and length {}", getName(), offset, length, exception);
        }

        return chunk;
    }

    /**
     * Returns the module category represented by this loaded pack implementation.
     */
    public abstract Type getType();

    /**
     * Closes the retained loader and propagates any cleanup failure it reports.
     * The method does not invalidate the manifest or explicitly release direct
     * buffer storage; loader-specific resources become unavailable after closing.
     */
    @Override
    public void close() throws Exception {
        this.loader.close();
    }

    /**
     * Returns whether another pack has the same current manifest UUID.
     * Equality ignores archive contents, version, and concrete pack type; callers
     * must preserve the manifest UUID while using packs in hashed collections.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pack that)) return false;
        return this.getId().equals(that.getId());
    }

    /**
     * Returns a hash derived from the current manifest UUID.
     * Callers must preserve that UUID while this pack is stored in a hashed
     * collection because changing the manifest changes this result.
     */
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    /**
     * {@code Type} identifies the module category declared by a pack manifest.
     * Manifest adapters encode these categories as lowercase names for JSON
     * interchange.
     *
     * @see <a href="https://learn.microsoft.com/en-us/minecraft/creator/reference/content/addonsreference/packmanifest?view=minecraft-bedrock-stable#modules">packmanifest</a>
     */
    @Getter
    @RequiredArgsConstructor
    public enum Type {
        /** Client resource module. */
        RESOURCES,
        /** Behavior data module. */
        DATA,
        /** World template module. */
        WORLD_TEMPLATE,
        /** Executable script module. */
        SCRIPT;

        /**
         * {@code Serializer} writes pack module categories as lowercase JSON strings.
         */
        public static class Serializer implements JsonSerializer<Type> {
            /**
             * Returns a lowercase JSON string naming the supplied module category.
             */
            @Override
            public JsonElement serialize(Type src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                // Manifest categories use lowercase names, while Java constants
                // use uppercase; Locale.ROOT keeps output independent of the process locale.
                return new JsonPrimitive(src.name().toLowerCase(Locale.ROOT));
            }
        }

        /**
         * {@code Deserializer} reads pack module categories from case-insensitive JSON strings.
         */
        public static class Deserializer implements JsonDeserializer<Type> {
            /**
             * Resolves a JSON string to its case-insensitive module category.
             * Unknown names produce {@code IllegalArgumentException}; this adapter
             * does not substitute a default category for unsupported module names.
             */
            @Override
            public Type deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                // Accept alternate casing for manifest categories without
                // allowing the process locale to change their Java constant lookup.
                return Type.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
            }
        }
    }

    /**
     * {@code Factory} creates loaded pack implementations from a loader and manifest.
     */
    @FunctionalInterface
    public interface Factory {
        /**
         * Creates a pack using the supplied loader and parsed manifest.
         * The returned pack retains the loader for later cleanup, so its owner
         * must close the pack when archive access is no longer needed.
         */
        Pack create(PackLoader loader, PackManifest manifest);
    }
}
