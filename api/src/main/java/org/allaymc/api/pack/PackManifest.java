package org.allaymc.api.pack;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.util.*;

/**
 * Represents the manifest for a pack.
 *
 * @author IWareQ, Cloudburst Server
 */
@Data
@Slf4j
public class PackManifest {

    public static final String FILE_NAME = "manifest.json";

    private static final Gson GSON;

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

    @SerializedName("format_version")
    private String formatVersion;

    private Header header;

    private List<Module> modules = Collections.emptyList();
    private List<Capability> capabilities = Collections.emptyList();

    /**
     * Load pack manifest using the given loader.
     *
     * @param loader the loader to be used for loading the manifest
     * @return the loaded pack manifest, or {@code null} if the manifest does not exist or could not be loaded
     */
    public static PackManifest load(PackLoader loader) {
        Objects.requireNonNull(loader);

        if (!loader.hasFile(FILE_NAME)) {
            return null;
        }

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(loader.getFile(FILE_NAME))));
            return GSON.fromJson(reader, PackManifest.class);
        } catch (Throwable t) {
            log.error("Failed to load {}", loader.getLocation(), t);
            return null;
        }
    }

    /**
     * Check if the pack manifest is valid.
     *
     * @return {@code true} if the pack manifest is valid, {@code false} otherwise.
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

    public enum Capability {

        CHEMISTRY,
        EDITOREXTENSION,
        EXPERIMENTAL_CUSTOM_UI,
        RAYTRACED;

        public static class Serializer implements JsonSerializer<Capability> {

            @Override
            public JsonElement serialize(Capability src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.name().toLowerCase(Locale.ROOT));
            }
        }

        public static class Deserializer implements JsonDeserializer<Capability> {

            @Override
            public Capability deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return Capability.valueOf(json.getAsString().toUpperCase(Locale.ROOT));
            }
        }
    }

    @Data
    public static class Header {

        private String name;
        private String description;
        private UUID uuid;
        private PackVersion version;
    }

    @Data
    public static class Module {

        private UUID uuid;
        private String description;
        private PackVersion version;
        private Pack.Type type;
    }
}
