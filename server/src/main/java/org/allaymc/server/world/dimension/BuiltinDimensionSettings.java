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
 * {@code BuiltinDimensionSettings} resolves effective built-in dimension height
 * ranges from world settings and caches the validated result for registry
 * initialization. Missing built-in entries receive vanilla defaults, while
 * invalid explicit bounds fail before a new snapshot is published.
 *
 * <p>
 * Lazy lookup loads the working-directory settings when no snapshot exists,
 * then uses compare-and-set so concurrent callers converge on the installed
 * snapshot. Competing callers can still perform redundant file initialization
 * and loading before publication. An explicit world-settings update replaces
 * the cached snapshot after validation; already constructed dimension types
 * retain their own final bounds. The height map stays private, and callers
 * receive immutable range records rather than a mutable configuration view.
 *
 * @author daoge_cmd
 */
public final class BuiltinDimensionSettings {

    /**
     * {@code WORLD_SETTINGS_PATH} locates the world-settings YAML used by lazy
     * initialization when no validated snapshot has been installed.
     */
    private static final Path WORLD_SETTINGS_PATH = Path.of("worlds").resolve("world-settings.yml");
    /**
     * {@code INSTANCE} publishes the current validated snapshot. Explicit settings
     * replacement can supersede the value installed by lazy initialization.
     */
    private static final AtomicReference<BuiltinDimensionSettings> INSTANCE = new AtomicReference<>();

    /**
     * {@code heightRanges} retains the private table completed before publication.
     * Callers receive individual records and cannot mutate this map through the
     * API.
     */
    private final EnumMap<DimensionId, HeightRange> heightRanges;

    /**
     * Creates a settings snapshot retaining the completed built-in range table.
     * The caller must finish validation and default insertion before
     * construction and must not mutate the retained map after publication.
     */
    private BuiltinDimensionSettings(EnumMap<DimensionId, HeightRange> heightRanges) {
        this.heightRanges = heightRanges;
    }

    /**
     * Returns the current snapshot, lazily loading and validating world
     * settings when absent. Loading occurs before compare-and-set, so
     * concurrent first callers may each perform file setup and parsing even
     * though only one candidate is initially installed.
     *
     * <p>
     * The returned value is
     * reread from the shared reference after publication, allowing an explicit
     * replacement to win a race. Failed loading or validation leaves this
     * candidate unpublished and propagates the failure.
     */
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
        // Only the first successful installation fills an empty cache;
        // rereading the reference returns the published winner rather than an
        // independently loaded losing candidate.
        INSTANCE.compareAndSet(null, loaded);
        return INSTANCE.get();
    }

    /**
     * Replaces the cached snapshot with validated ranges derived from the
     * supplied world settings. Validation finishes before publication, so
     * failure retains the prior snapshot; existing dimension type instances are
     * not updated.
     */
    public static void useWorldSettings(AllayWorldPool.WorldSettings worldSettings) {
        // Build and validate the replacement before publishing it, preserving
        // the previously installed snapshot if any explicit range or normalized
        // identifier fails validation.
        INSTANCE.set(from(worldSettings));
    }

    /**
     * Returns the inclusive minimum block height from this snapshot for a
     * built-in dimension. The requested identity must have a range; lookup
     * fails rather than selecting a default after publication.
     */
    public int getMinHeight(DimensionId dimensionId) {
        return getHeightRange(dimensionId).minHeight();
    }

    /**
     * Returns the inclusive maximum block height from this snapshot for a
     * built-in dimension. The requested identity must have a range; lookup
     * fails rather than selecting a default after publication.
     */
    public int getMaxHeight(DimensionId dimensionId) {
        return getHeightRange(dimensionId).maxHeight();
    }

    /**
     * Returns the immutable height-range record stored for the requested
     * built-in dimension. Missing entries fail the non-{@code null} invariant because
     * snapshot construction supplies defaults for every recognized built-in
     * identity.
     */
    public HeightRange getHeightRange(DimensionId dimensionId) {
        return Preconditions.checkNotNull(heightRanges.get(dimensionId), "Missing height range for %s", dimensionId);
    }

    /**
     * Builds a complete range snapshot from normalized built-in settings.
     * Explicit entries are validated first, including dimension identity and
     * section-aligned bounds, so an invalid supplied entry aborts rather than
     * silently selecting its vanilla default. Only identities absent from the
     * validated map receive vanilla bounds afterward.
     *
     * <p>
     * The new snapshot is
     * returned without publication, allowing callers to keep an existing cached
     * snapshot if this construction fails.
     */
    private static BuiltinDimensionSettings from(AllayWorldPool.WorldSettings worldSettings) {
        var heightRanges = new EnumMap<DimensionId, HeightRange>(DimensionId.class);
        for (var entry : worldSettings.normalizedBuiltinDimensions().entrySet()) {
            var dimensionId = entry.getKey();
            var setting = entry.getValue();
            var range = new HeightRange(setting.minHeight(), setting.maxHeight());
            validateHeightRange(dimensionId, range);
            heightRanges.put(dimensionId, range);
        }

        // Defaults fill only missing entries after explicit values have passed
        // validation, so malformed supplied bounds cannot silently disappear
        // behind a valid vanilla replacement.
        for (var dimensionId : DimensionId.values()) {
            heightRanges.putIfAbsent(
                    dimensionId,
                    new HeightRange(dimensionId.getDefaultMinHeight(), dimensionId.getDefaultMaxHeight())
            );
        }
        return new BuiltinDimensionSettings(heightRanges);
    }

    /**
     * Creates the initializer that binds YAML world settings, enables orphan
     * removal, saves defaults, and then loads persisted values. Callers
     * invoking it can perform filesystem work; it does not itself publish
     * validated dimension ranges.
     */
    private static OkaeriConfigInitializer createWorldSettingsInitializer() {
        return it -> {
            it.withConfigurer(new YamlSnakeYamlConfigurer());
            it.withBindFile(WORLD_SETTINGS_PATH);
            it.withRemoveOrphans(true);
            // Defaults are saved before loading so a missing settings file can
            // be initialized; loaded values still require the separate
            // range-normalization and validation phase.
            it.saveDefaults();
            it.load();
        };
    }

    /**
     * Validates section alignment and supported bounds using the dimension type
     * validator. Argument failures are rethrown with the built-in identifier in
     * the message while retaining the original exception as their cause.
     */
    private static void validateHeightRange(DimensionId dimensionId, HeightRange range) {
        try {
            AllayDimensionType.validateHeights(range.minHeight(), range.maxHeight());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid height range for " + dimensionId.getIdentifier() + ": " + e.getMessage(), e);
        }
    }

    /**
     * {@code HeightRange} carries inclusive minimum and maximum build heights for one resolved
     * dimension range. Construction itself performs no validation; the
     * enclosing settings factory validates configured ranges before publishing
     * them.
     *
     * @param minHeight inclusive minimum block height
     * @param maxHeight inclusive maximum block height
     */
    public record HeightRange(int minHeight, int maxHeight) {
    }
}
