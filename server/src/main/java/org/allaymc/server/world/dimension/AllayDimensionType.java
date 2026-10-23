package org.allaymc.server.world.dimension;

import com.google.common.base.Preconditions;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.dimension.DimensionType;

/**
 * {@code AllayDimensionType} represents a registered dimension identity with
 * aligned inclusive height bounds and fixed environmental behavior switches.
 * The explicitly identified constructor validates registry availability, reserved
 * numeric IDs, duplicate identities, and section alignment before registration.
 *
 * <p>
 * The custom constructor first asks the persistent allocator for an ID and then
 * performs the same validation, so invalid bounds or a duplicate registration
 * can leave an allocation recorded even though no type was created. Validation
 * and registration are separate calls rather than one atomic registry
 * transaction; callers must coordinate concurrent registrations. Height bounds
 * determine section indexing, while the behavior flags independently control
 * skylight, bed use, respawn anchors, and water evaporation.
 *
 * @author daoge_cmd
 */
public class AllayDimensionType implements DimensionType {

    /**
     * Registered numeric dimension identifier.
     */
    protected final int id;
    /**
     * Registered namespaced dimension identifier.
     */
    protected final Identifier identifier;
    /**
     * {@code minHeight} and {@code maxHeight} hold the inclusive minimum and
     * maximum block heights, respectively. Constructor validation makes both
     * endpoints delimit complete sixteen-block sections before registration.
     */
    protected final int minHeight, maxHeight;
    /**
     * {@code hasSkyLight}, {@code bedWorks}, {@code respawnAnchorWorks}, and
     * {@code waterEvaporates} control skylight, sleeping, respawn anchors, and
     * water evaporation, respectively. Each fixed switch operates independently
     * of the height bounds.
     */
    protected final boolean hasSkyLight, bedWorks, respawnAnchorWorks, waterEvaporates;

    /**
     * Creates and registers an explicitly identified dimension after validating
     * identity and height bounds. The registry must already exist, IDs must be
     * built-in or within the custom range, and neither ID nor identifier may
     * already be registered. Height validation completes before field
     * assignment and registration. These checks do not reserve registry keys
     * atomically, so callers serialize competing registrations and handle
     * failures without assuming a partially constructed type was installed.
     */
    public AllayDimensionType(
            int id,
            Identifier identifier,
            int minHeight,
            int maxHeight,
            boolean hasSkyLight,
            boolean bedWorks,
            boolean respawnAnchorWorks,
            boolean waterEvaporates
    ) {
        validateRegistration(id, identifier);
        validateHeights(minHeight, maxHeight);
        this.id = id;
        this.identifier = identifier;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.hasSkyLight = hasSkyLight;
        this.bedWorks = bedWorks;
        this.respawnAnchorWorks = respawnAnchorWorks;
        this.waterEvaporates = waterEvaporates;
        Registries.DIMENSIONS.register(this.id, this.identifier, this);
    }

    /**
     * Creates a custom dimension using the persistent allocator's ID for the
     * supplied identifier. Allocation occurs before height and
     * duplicate-registration validation, so a later failure does not undo the
     * allocator's mapping or save attempt. The same section alignment and
     * registry rules as explicit construction apply. Callers must initialize
     * both services and coordinate registration; this constructor does not
     * provide a combined allocation-and-registration transaction.
     */
    public AllayDimensionType(
            Identifier identifier,
            int minHeight,
            int maxHeight,
            boolean hasSkyLight,
            boolean bedWorks,
            boolean respawnAnchorWorks,
            boolean waterEvaporates
    ) {
        this(
                CustomDimensionIdAllocator.getInstance().allocateId(identifier),
                identifier,
                minHeight,
                maxHeight,
                hasSkyLight,
                bedWorks,
                respawnAnchorWorks,
                waterEvaporates
        );
    }

    /**
     * Rejects height bounds outside {@code -512} through {@code 512} or an inverted range.
     * The minimum must begin a sixteen-block section and the inclusive maximum
     * must end one, making the complete height a multiple of sixteen. All
     * checks throw {@link IllegalArgumentException} when violated. The upper
     * numeric limit does not by itself make {@code 512} a valid maximum, because the
     * required low four bits must also equal fifteen.
     */
    public static void validateHeights(int minHeight, int maxHeight) {
        Preconditions.checkArgument(minHeight >= -512 && minHeight <= 512, "minHeight must be between -512 and 512");
        Preconditions.checkArgument(maxHeight >= -512 && maxHeight <= 512, "maxHeight must be between -512 and 512");
        Preconditions.checkArgument(maxHeight >= minHeight, "maxHeight must be greater than or equal to minHeight");
        // Section arrays require complete vertical sections: the minimum starts
        // a section, while the inclusive maximum ends one, including when
        // coordinates are negative.
        Preconditions.checkArgument((minHeight & 15) == 0, "minHeight must align to section boundaries");
        Preconditions.checkArgument((maxHeight & 15) == 15, "maxHeight must align to section boundaries");
        Preconditions.checkArgument((maxHeight - minHeight + 1) % 16 == 0, "Dimension height must be a multiple of 16");
    }

    /**
     * Rejects unavailable registries, negative or reserved IDs, and identities
     * already present under either lookup key. Numeric IDs zero through two are
     * built-in and custom allocation begins at the configured custom-ID base.
     * ID and identifier checks happen independently and do not reserve either
     * key for the subsequent registration. Concurrent constructors must
     * therefore be coordinated externally; passing these checks alone is not an
     * atomic claim to the identity.
     */
    protected static void validateRegistration(int id, Identifier identifier) {
        Preconditions.checkState(Registries.DIMENSIONS != null, "Dimension registry has not been initialized");
        Preconditions.checkArgument(id >= 0, "Dimension id must be non-negative");
        Preconditions.checkArgument(
                id <= 2 || id >= CustomDimensionIdAllocator.CUSTOM_DIMENSION_ID_START,
                "Dimension id %s is reserved",
                id
        );
        var existingById = Registries.DIMENSIONS.getByK1(id);
        Preconditions.checkArgument(existingById == null, "Dimension id %s is already registered", id);
        var existingByIdentifier = Registries.DIMENSIONS.getByK2(identifier);
        Preconditions.checkArgument(existingByIdentifier == null, "Dimension identifier %s is already registered", identifier);
    }

    /**
     * Returns the numeric identifier retained during this type's registration.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns the namespaced identifier retained during this type's
     * registration.
     */
    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Returns the validated inclusive minimum height in absolute block
     * coordinates.
     */
    @Override
    public int getMinHeight() {
        return minHeight;
    }

    /**
     * Returns the validated inclusive maximum height in absolute block
     * coordinates.
     */
    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * Returns the fixed skylight support flag configured for this dimension.
     */
    @Override
    public boolean hasSkyLight() {
        return hasSkyLight;
    }

    /**
     * Returns the fixed normal-bed behavior flag configured for this dimension.
     */
    @Override
    public boolean bedWorks() {
        return bedWorks;
    }

    /**
     * Returns the fixed respawn-anchor support flag configured for this
     * dimension.
     */
    @Override
    public boolean respawnAnchorWorks() {
        return respawnAnchorWorks;
    }

    /**
     * Returns the fixed water-evaporation behavior flag configured for this
     * dimension.
     */
    @Override
    public boolean waterEvaporates() {
        return waterEvaporates;
    }
}
