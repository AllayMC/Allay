package org.allaymc.server.world.dimension;

import com.google.common.base.Preconditions;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.dimension.DimensionType;

/**
 * @author daoge_cmd
 */
public class AllayDimensionType implements DimensionType {

    protected final int id;
    protected final Identifier identifier;
    protected final int minHeight, maxHeight;
    protected final boolean hasSkyLight, bedWorks, respawnAnchorWorks, waterEvaporates;

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

    public static void validateHeights(int minHeight, int maxHeight) {
        Preconditions.checkArgument(minHeight >= -512 && minHeight <= 512, "minHeight must be between -512 and 512");
        Preconditions.checkArgument(maxHeight >= -512 && maxHeight <= 512, "maxHeight must be between -512 and 512");
        Preconditions.checkArgument(maxHeight >= minHeight, "maxHeight must be greater than or equal to minHeight");
        Preconditions.checkArgument((minHeight & 15) == 0, "minHeight must align to section boundaries");
        Preconditions.checkArgument((maxHeight & 15) == 15, "maxHeight must align to section boundaries");
        Preconditions.checkArgument((maxHeight - minHeight + 1) % 16 == 0, "Dimension height must be a multiple of 16");
    }

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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public int getMinHeight() {
        return minHeight;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public boolean hasSkyLight() {
        return hasSkyLight;
    }

    @Override
    public boolean bedWorks() {
        return bedWorks;
    }

    @Override
    public boolean respawnAnchorWorks() {
        return respawnAnchorWorks;
    }

    @Override
    public boolean waterEvaporates() {
        return waterEvaporates;
    }
}
