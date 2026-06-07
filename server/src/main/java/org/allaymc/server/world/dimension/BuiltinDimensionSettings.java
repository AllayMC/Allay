package org.allaymc.server.world.dimension;

import com.google.common.base.Preconditions;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfigInitializer;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import org.allaymc.server.world.AllayWorldPool;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Resolves the effective height settings for the built-in dimensions from {@code world-settings.yml}.
 *
 * @author daoge_cmd
 */
public final class BuiltinDimensionSettings {

    private static final Path WORLD_SETTINGS_PATH = Path.of("worlds").resolve("world-settings.yml");
    private static final AtomicReference<BuiltinDimensionSettings> INSTANCE = new AtomicReference<>();

    private final EnumMap<DimensionId, HeightRange> heightRanges;

    private BuiltinDimensionSettings(EnumMap<DimensionId, HeightRange> heightRanges) {
        this.heightRanges = heightRanges;
    }

    public static BuiltinDimensionSettings get() {
        var instance = INSTANCE.get();
        if (instance != null) {
            return instance;
        }

        var worldSettings = ConfigManager.create(
                AllayWorldPool.WorldSettings.class,
                createWorldSettingsInitializer()
        );
        Preconditions.checkNotNull(worldSettings, "World settings cannot be null");
        var loaded = from(worldSettings);
        INSTANCE.compareAndSet(null, loaded);
        return INSTANCE.get();
    }

    public static void useWorldSettings(AllayWorldPool.WorldSettings worldSettings) {
        INSTANCE.set(from(worldSettings));
    }

    public int getMinHeight(DimensionId dimensionId) {
        return getHeightRange(dimensionId).minHeight();
    }

    public int getMaxHeight(DimensionId dimensionId) {
        return getHeightRange(dimensionId).maxHeight();
    }

    public HeightRange getHeightRange(DimensionId dimensionId) {
        return Preconditions.checkNotNull(heightRanges.get(dimensionId), "Missing height range for %s", dimensionId);
    }

    private static BuiltinDimensionSettings from(AllayWorldPool.WorldSettings worldSettings) {
        var heightRanges = new EnumMap<DimensionId, HeightRange>(DimensionId.class);
        for (var entry : worldSettings.normalizedBuiltinDimensions().entrySet()) {
            var dimensionId = entry.getKey();
            var setting = entry.getValue();
            var range = new HeightRange(setting.minHeight(), setting.maxHeight());
            validateHeightRange(dimensionId, range);
            heightRanges.put(dimensionId, range);
        }

        for (var dimensionId : DimensionId.values()) {
            heightRanges.putIfAbsent(
                    dimensionId,
                    new HeightRange(dimensionId.getDefaultMinHeight(), dimensionId.getDefaultMaxHeight())
            );
        }
        return new BuiltinDimensionSettings(heightRanges);
    }

    private static OkaeriConfigInitializer createWorldSettingsInitializer() {
        return it -> {
            it.withConfigurer(new YamlSnakeYamlConfigurer());
            it.withBindFile(WORLD_SETTINGS_PATH);
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load();
        };
    }

    private static void validateHeightRange(DimensionId dimensionId, HeightRange range) {
        try {
            AllayDimensionType.validateHeights(range.minHeight(), range.maxHeight());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid height range for " + dimensionId.getIdentifier() + ": " + e.getMessage(), e);
        }
    }

    public record HeightRange(int minHeight, int maxHeight) {
    }
}
