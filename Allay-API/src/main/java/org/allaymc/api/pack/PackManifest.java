package org.allaymc.api.pack;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.SemVersion;
import org.allaymc.api.utils.JSONUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Allay Project 28/01/2024
 *
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

    public static PackManifest load(PackLoader loader) {
        Objects.requireNonNull(loader);

        if (!loader.hasFile(PATH)) return null;

        try {
            return JSONUtils.from(loader.getFile(PATH), PackManifest.class);
        } catch (IllegalStateException | IOException exception) {
            log.error("Failed to load " + loader.getLocation(), exception);
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
