package org.allaymc.api.pack;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * {@code PackManifest} holds the mutable header, modules, and capabilities read
 * from a pack's manifest JSON. Its adapters handle manifest-specific version
 * and enum representations. {@link #isValid()} checks required header values
 * and top-level presence, so callers still need to validate module contents
 * before selecting a pack implementation.
 *
 * <p>
 * {@code modules} and {@code capabilities} initially reference empty lists.
 * Deserialization and generated setters can replace those references, including
 * with {@code null}; generated getters expose the current lists and their mutable
 * elements without copying them.
 *
 * @author IWareQ, Cloudburst Server
 */
@Data
@Slf4j
public class PackManifest {

    /**
     * {@code FILE_NAME} identifies the root JSON entry that loaders inspect when
     * reading a pack's metadata and identity.
     */
    public static final String FILE_NAME = "manifest.json";

    /**
     * {@code GSON} supplies manifest-specific version and enum adapters for the
     * shared JSON read and write entry points.
     */
    private static final Gson GSON;

    // Install paired readers and writers so manifest versions and category names
    // use the same representation rules in both JSON directions.
    static {
        var builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        builder.registerTypeAdapter(PackVersion.class, new PackVersion.Deserializer());
        builder.registerTypeAdapter(PackVersion.class, new PackVersion.Serializer());
        builder.registerTypeAdapter(Pack.Type.class, new Pack.Type.Deserializer());
        builder.registerTypeAdapter(Pack.Type.class, new Pack.Type.Serializer());
        builder.registerTypeAdapter(PackManifest.Capability.class, new PackManifest.Capability.Deserializer());
        builder.registerTypeAdapter(PackManifest.Capability.class, new PackManifest.Capability.Serializer());
        GSON = builder.create();
    }

    /** Manifest schema version text. */
    @SerializedName("format_version")
    private String formatVersion;

    /**
     * {@code header} holds mutable pack identity metadata. The generated getter
     * exposes the same object subsequently read by loaded packs.
     */
    private Header header;

    /** Declared pack module list. */
    private List<Module> modules = Collections.emptyList();
    /** Declared pack capability list. */
    private List<Capability> capabilities = Collections.emptyList();

    /**
     * Loads the root manifest entry using the supplied loader.
     * A missing file or a failure while opening, parsing, or closing its reader
     * returns {@code null}; this method closes the reader but leaves the loader
     * open and does not validate the parsed manifest.
     *
     * @throws NullPointerException if {@code loader} is {@code null}
     */
    public static PackManifest load(PackLoader loader) {
        Objects.requireNonNull(loader);

        if (!loader.hasFile(FILE_NAME)) {
            return null;
        }

        try (var reader = new InputStreamReader(Objects.requireNonNull(loader.getFile(FILE_NAME)))) {
            return fromJson(reader);
        } catch (Throwable t) {
            // Parsing and reader cleanup failures reject this manifest together;
            // the registry can skip this source after receiving the null result.
            log.error("Failed to load {}", loader.getLocation(), t);
            return null;
        }
    }

    /**
     * Parses a manifest with its version and enum adapters.
     * The caller retains ownership of {@code reader}; this method neither closes
     * it nor validates the result, and empty input or JSON {@code null} can
     * produce {@code null}.
     *
     * @throws NullPointerException if {@code reader} is {@code null}
     */
    public static PackManifest fromJson(Reader reader) {
        Objects.requireNonNull(reader);

        return GSON.fromJson(new JsonReader(reader), PackManifest.class);
    }

    /**
     * Returns whether required top-level and header references are present.
     * This check accepts empty {@code modules}, empty text, and unchecked module
     * or capability contents; callers needing the first module must validate
     * that separately.
     */
    public boolean isValid() {
        if (this.formatVersion == null || this.header == null || this.modules == null) {
            return false;
        }

        return this.header.description != null &&
               this.header.name != null &&
               this.header.uuid != null &&
               this.header.version != null;
    }

    /**
     * {@code Capability} identifies optional pack features declared in manifest JSON.
     * Its adapters use lowercase enum names and accept case-insensitive names
     * when reading the manifest.
     */
    public enum Capability {

        /** Chemistry feature capability. */
        CHEMISTRY,
        /** Editor extension capability. */
        EDITOREXTENSION,
        /** Experimental custom-interface capability. */
        EXPERIMENTAL_CUSTOM_UI,
        /** Ray-traced rendering capability. */
        RAYTRACED;

        /**
         * {@code Serializer} writes manifest capabilities using lowercase JSON names.
         */
        public static class Serializer implements JsonSerializer<Capability> {

            /**
             * Returns a lowercase JSON string naming the supplied capability value.
             */
            @Override
            public JsonElement serialize(Capability src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                // Manifest capabilities use lowercase names, while Java constants
                // use uppercase; Locale.ROOT keeps output independent of the process locale.
                return new JsonPrimitive(src.name().toLowerCase(Locale.ROOT));
            }
        }

        /**
         * {@code Deserializer} resolves capability names through case-insensitive enum lookup.
         */
        public static class Deserializer implements JsonDeserializer<Capability> {

            /**
             * Reads a capability from its case-insensitive JSON string name.
             * Unknown names produce {@code IllegalArgumentException}; this adapter
             * does not discard unsupported capabilities or replace them with a
             * default value.
             */
            @Override
            public Capability deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                // Accept alternate casing for manifest capabilities without
                // allowing the process locale to change their Java constant lookup.
                return Capability.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
            }
        }
    }

    /**
     * {@code Header} holds the display metadata, UUID, and version identifying a pack.
     */
    @Data
    public static class Header {

        /** Pack display name. */
        private String name;
        /** Pack description text. */
        private String description;
        /** Pack identity UUID. */
        private UUID uuid;
        /** Declared pack version. */
        private PackVersion version;
    }

    /**
     * {@code Module} holds the identity, version, and category of a manifest module.
     */
    @Data
    public static class Module {

        /** Module identity UUID. */
        private UUID uuid;
        /** Module description text. */
        private String description;
        /** Declared module version. */
        private PackVersion version;
        /** Declared module category. */
        private Pack.Type type;
    }
}
