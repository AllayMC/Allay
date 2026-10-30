package org.allaymc.server.world.biome;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.config.Config;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@code CustomBiomeIdAllocator} allocates custom biome IDs and associates them
 * with namespaced identifiers in {@code biome-ids.yml}. Startup loads existing
 * numeric mappings, and new IDs begin above both the custom-ID base and the
 * largest loaded value.
 *
 * <p>
 * Allocation is synchronized on the singleton instance so concurrent requests
 * through that instance share one lookup-and-assignment sequence. A new mapping
 * enters memory before the configuration save is attempted, without rollback
 * when saving fails. The save result is not checked, and a later request for
 * that identifier returns the cached value without retrying persistence. Stable
 * IDs across restarts therefore depend on retaining a successfully written
 * file; singleton replacement and stored-value validation remain startup
 * responsibilities.
 *
 * <p>
 * Identifiers containing {@code "."} are accepted, but {@code Config.set}
 * interprets those characters as nested section separators. Such an allocation
 * can be saved as a section that this allocator cannot reload as a number,
 * causing the next initialization to fail.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class CustomBiomeIdAllocator {

    /**
     * {@code CUSTOM_BIOME_ID_START} defines the lowest new custom ID.
     * Loaded mappings above this base advance the allocation sequence.
     */
    public static final int CUSTOM_BIOME_ID_START = 30000;

    /**
     * {@code FILE_NAME} selects the working-directory YAML file used to reload
     * and save identifier-to-ID assignments.
     */
    private static final String FILE_NAME = "biome-ids.yml";

    /**
     * {@code instance} holds the startup-assigned singleton and starts as
     * {@code null}. Repeated initialization replaces it without coordinating
     * callers still holding the previous allocator.
     */
    private static CustomBiomeIdAllocator instance;

    /**
     * {@code config} retains the YAML configuration updated before synchronous
     * save attempts. Failed persistence does not restore its prior in-memory
     * contents.
     */
    private final Config config;
    /**
     * {@code identifierToId} caches assignments loaded at startup and changed
     * before saving. Existing hits bypass any later persistence retry.
     */
    private final Map<String, Integer> identifierToId;
    /**
     * {@code nextId} holds the next candidate above the loaded maximum. Its
     * increment precedes saving, and integer overflow is not guarded.
     */
    private final AtomicInteger nextId;

    /**
     * Loads numeric identifier assignments from the configured YAML file and
     * selects the next allocation candidate. The initial maximum is one below
     * the custom-ID base, so an empty file starts at that base while larger
     * stored values advance it. Loaded values are narrowed through
     * {@code Number.intValue()} without checking reserved ranges or duplicate IDs.
     * Non-numeric values fail construction, and a maximum integer value can
     * overflow the next candidate.
     */
    private CustomBiomeIdAllocator() {
        this.config = new Config(FILE_NAME, Config.YAML);
        this.identifierToId = new HashMap<>();

        // The base-minus-one seed reserves lower IDs for built-in use while
        // still advancing past any larger value found in the persisted mapping.
        int maxId = CUSTOM_BIOME_ID_START - 1;
        for (var entry : config.getAll().entrySet()) {
            var id = ((Number) entry.getValue()).intValue();
            identifierToId.put(entry.getKey(), id);
            if (id > maxId) {
                maxId = id;
            }
        }

        this.nextId = new AtomicInteger(maxId + 1);
    }

    /**
     * Replaces the singleton by loading its mapping file during server startup.
     * Callers must serialize initialization with allocator use; repeated calls
     * do not preserve references held to the previous instance.
     */
    public static void init() {
        instance = new CustomBiomeIdAllocator();
    }

    /**
     * Returns the allocator initialized for this server process, or throws
     * {@link IllegalStateException} before initialization. Callers must
     * complete startup publication before accessing this unsynchronized
     * singleton getter from other threads.
     */
    public static CustomBiomeIdAllocator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CustomBiomeIdAllocator has not been initialized");
        }
        return instance;
    }

    /**
     * Returns the cached ID for an identifier or reserves the next candidate
     * and attempts to save it. The synchronized method serializes map lookup,
     * candidate advancement, and saving through this allocator instance.
     *
     * <p>
     * New
     * assignments enter both in-memory maps before persistence, so a thrown
     * save leaves the ID reserved and later calls return it without retrying. A
     * {@code false} save result is ignored, and no range or overflow validation is
     * performed here.
     * Identifiers containing {@code "."} create nested configuration sections,
     * which the flat startup loader cannot reload as numeric assignments.
     */
    public synchronized int allocateId(Identifier identifier) {
        var key = identifier.toString();
        var existing = identifierToId.get(key);
        if (existing != null) {
            return existing;
        }

        // Reserve memory state before writing the file so callers in this
        // instance reuse one ID; persistence failure does not roll back or
        // retry that reservation.
        var id = nextId.getAndIncrement();
        identifierToId.put(key, id);
        // Config.set interprets dots as section separators, while the cache
        // retains the complete identifier; that mismatch can make the saved
        // top-level mapping unusable by the numeric startup loader.
        config.set(key, id);
        config.save();
        return id;
    }
}
