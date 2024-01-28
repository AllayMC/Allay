package org.allaymc.api.pack;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.SemVersion;
import org.allaymc.api.utils.JSONUtils;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackType;

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

    private Metadata metadata;

    private List<Module> modules = Collections.emptyList();

    private List<Dependency> dependencies = Collections.emptyList();

    private List<String> capabilities = Collections.emptyList();

    @SerializedName("subpacks")
    private List<SubPack> subPacks = Collections.emptyList();

    public static PackManifest load(PackLoader loader) {
        Objects.requireNonNull(loader);

        if (!loader.hasAsset(PATH)) return null;

        try {
            return JSONUtils.from(loader.getAsset(PATH), PackManifest.class);
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
        @SerializedName("platform_locked")
        private boolean platformLocked;
        private SemVersion version;
        @SerializedName("min_engine_version")
        private SemVersion minEngineVersion;
        @SerializedName("pack_scope")
        private String packScope = "global";
        @SerializedName("directory_load")
        private boolean directoryLoad;
        @SerializedName("load_before_game")
        private boolean loadBeforeGame;
        @SerializedName("lock_template_options")
        private boolean lockTemplateOptions;
        @SerializedName("population_control")
        private boolean populationControl;
    }

    @Data
    public static class Module {

        private UUID uuid;
        private String description;
        private SemVersion version;
        private ResourcePackType type;
    }

    @Data
    public static class Metadata {

        private List<String> authors;
        private String license;
        private String url;
    }

    @Data
    public static class Dependency {

        private UUID uuid;
        private SemVersion version;
    }

    @Data
    public static class SubPack {

        @SerializedName("folder_name")
        private String folderName;
        private String name;
        @SerializedName("memory_tier")
        private int memoryTier;
    }
}
