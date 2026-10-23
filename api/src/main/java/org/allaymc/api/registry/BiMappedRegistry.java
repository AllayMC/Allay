package org.allaymc.api.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

/**
 * {@code BiMappedRegistry} stores unique associations that can be queried from
 * either side. The loader supplies a {@link BiMap}, whose value uniqueness
 * rules apply during registration. Freezing replaces the map with an immutable
 * shallow snapshot; the associated objects themselves are retained by
 * reference.
 *
 * @author daoge_cmd
 */
public class BiMappedRegistry<LEFT, RIGHT> extends AbstractRegistry<BiMap<LEFT, RIGHT>> {
    /**
     * Loads the bidirectional map immediately from the supplied input. The
     * loaded map is retained without copying until freezing or replacement, so
     * loader-held references initially share the registry's mappings.
     */
    protected <INPUT> BiMappedRegistry(INPUT input, RegistryLoader<INPUT, BiMap<LEFT, RIGHT>> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Returns the right value associated with the left key. Missing
     * associations return {@code null}, and the result shares its object
     * identity with the stored mapping rather than being copied.
     */
    public RIGHT getByLeft(LEFT left) {
        return content.get(left);
    }

    /**
     * Returns the left value associated with the right key. Missing
     * associations return {@code null} through the inverse map; the returned
     * object is the same one retained by the registry.
     */
    public LEFT getByRight(RIGHT right) {
        return content.inverse().get(right);
    }

    /**
     * Returns the right mapping or a default when the left key is absent. The
     * map's default-value semantics apply, so an existing {@code null} mapping
     * does not ordinarily select {@code defaultValue}.
     */
    public RIGHT getByLeftOrDefault(LEFT left, RIGHT defaultValue) {
        return content.getOrDefault(left, defaultValue);
    }

    /**
     * Returns the left mapping or a default when the right key is absent. The
     * inverse map's default-value semantics apply, so an existing {@code null}
     * mapping does not ordinarily select {@code defaultValue}.
     */
    public LEFT getByRightOrDefault(RIGHT right, LEFT defaultValue) {
        return content.inverse().getOrDefault(right, defaultValue);
    }

    /**
     * Associates the two values while enforcing the backing map's uniqueness
     * constraints. An existing left mapping is replaced, but a right value
     * already associated with another left value causes
     * {@link IllegalArgumentException}. A frozen registry rejects the call with
     * {@link IllegalStateException}.
     */
    public synchronized void register(LEFT left, RIGHT right) {
        ensureMutable();
        content.put(left, right);
    }

    /**
     * Freezes registration and exposes an immutable snapshot supporting both
     * lookup directions. Repeated calls keep the existing snapshot, and objects
     * within it remain shared; external writers must not mutate the original
     * map while the snapshot is being copied. {@code null} keys or values cause
     * snapshot creation to fail.
     */
    @Override
    public synchronized void freeze() {
        if (frozen) {
            return;
        }
        // Finish copying before publishing content and freeze state, so a failed
        // copy leaves the previous mappings and mutation policy in place.
        content = ImmutableBiMap.copyOf(content);
        super.freeze();
    }
}
