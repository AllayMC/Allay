package org.allaymc.api.pack;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.api.utils.SemVersion;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author IWareQ, Cloudburst Server
 */
@Data
@Slf4j
public class PackManifest {

    private static final Path PATH = Path.of("manifest.json");

    @SerializedName("format_version")
    private String formatVersion;

    private Header header;

    private List<Module> modules = Collections.emptyList();
    private List<Capability> capabilities = Collections.emptyList();

    public static PackManifest load(PackLoader loader) {
        Objects.requireNonNull(loader);

        if (!loader.hasFile(PATH)) return null;

        try {
            return JSONUtils.from(loader.getFile(PATH), PackManifest.class);
        } catch (IllegalStateException | IOException exception) {
            log.error("Failed to load {}", loader.getLocation(), exception);
            return null;
        }
    }

    public boolean isValid() {
        if (this.formatVersion == null || this.header == null || this.modules == null) return false;

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
                return new JsonPrimitive(src.name().toLowerCase());
            }
        }

        public static class Deserializer implements JsonDeserializer<Capability> {

            @Override
            public Capability deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return Capability.valueOf(json.getAsString().toUpperCase());
            }
        }
    }

    @Data
    public static class Header {

        private String name;
        private String description;
        private UUID uuid;
        private SemVersion version;
    }

    @Data
    public static class Module {

        private UUID uuid;
        private String description;
        private SemVersion version;
        private Pack.Type type;
    }
}
