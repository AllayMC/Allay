package org.allaymc.server.network;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.creative.CreativeItemEntry;
import org.allaymc.api.item.creative.CreativeItemGroup;
import org.allaymc.api.item.data.TrimMaterial;
import org.allaymc.api.item.data.TrimPattern;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.AllayServer;
import org.allaymc.server.item.creative.AllayCreativeItemRegistry;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.world.dimension.DimensionId;
import org.allaymc.server.world.dimension.VanillaGeneratorType;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Protocol-independent source data used to build protocol-specific data and packets.
 * The source registries are frozen after plugins have finished registering content.
 */
@Getter
@Accessors(fluent = true)
public final class NetworkData {
    private final List<ItemType<?>> itemTypes;
    private final List<BlockType<?>> blockTypes;
    private final List<BlockState> blockStates;
    private final List<Recipe> recipes;
    private final List<CreativeItemGroup> creativeGroups;
    private final List<CreativeItemEntry> creativeItems;
    private final List<EntityType<?>> entityTypes;
    private final List<BiomeType> biomeTypes;
    private final List<DimensionType> dimensionTypes;
    private final List<Pack> packs;
    @Getter(AccessLevel.NONE)
    private final Map<UUID, Pack> packsById;
    private final List<TrimPattern> trimPatterns;
    private final List<TrimMaterial> trimMaterials;
    private final EncodingSettings encodingSettings;

    private NetworkData(
            List<ItemType<?>> itemTypes,
            List<BlockType<?>> blockTypes,
            List<BlockState> blockStates,
            List<Recipe> recipes,
            List<CreativeItemGroup> creativeGroups,
            List<CreativeItemEntry> creativeItems,
            List<EntityType<?>> entityTypes,
            List<BiomeType> biomeTypes,
            List<DimensionType> dimensionTypes,
            List<Pack> packs,
            List<TrimPattern> trimPatterns,
            List<TrimMaterial> trimMaterials,
            EncodingSettings encodingSettings
    ) {
        this.itemTypes = List.copyOf(itemTypes);
        this.blockTypes = List.copyOf(blockTypes);
        this.blockStates = List.copyOf(blockStates);
        this.recipes = List.copyOf(recipes);
        this.creativeGroups = List.copyOf(creativeGroups);
        this.creativeItems = List.copyOf(creativeItems);
        this.entityTypes = List.copyOf(entityTypes);
        this.biomeTypes = List.copyOf(biomeTypes);
        this.dimensionTypes = List.copyOf(dimensionTypes);
        this.packs = List.copyOf(packs);

        var packsById = new LinkedHashMap<UUID, Pack>();
        for (var pack : packs) {
            if (packsById.putIfAbsent(pack.getId(), pack) != null) {
                throw new IllegalArgumentException("Duplicate resource-pack ID: " + pack.getId());
            }
        }
        this.packsById = Map.copyOf(packsById);
        this.trimPatterns = List.copyOf(trimPatterns);
        this.trimMaterials = List.copyOf(trimMaterials);
        this.encodingSettings = Objects.requireNonNull(encodingSettings, "encodingSettings");
    }

    /**
     * Freezes the source registries and captures their current contents.
     *
     * @return protocol-independent source data
     */
    public static NetworkData capture() {
        freezeSourceRegistries();

        var blockTypes = List.copyOf(Registries.BLOCKS.getContent().values());
        var settings = AllayServer.getSettings();
        return new NetworkData(
                List.copyOf(Registries.ITEMS.getContent().values()),
                blockTypes,
                blockTypes.stream()
                        .flatMap(blockType -> blockType.getAllStates().stream())
                        .toList(),
                Registries.RECIPES.getContent().values().stream()
                        .sorted(Comparator.comparing(recipe -> recipe.getIdentifier().toString()))
                        .toList(),
                List.copyOf(Registries.CREATIVE_ITEMS.getGroups()),
                List.copyOf(Registries.CREATIVE_ITEMS.getEntries()),
                List.copyOf(Registries.ENTITIES.getContent().values()),
                List.copyOf(Registries.BIOMES.getContent().m1().values()),
                List.copyOf(Registries.DIMENSIONS.getContent().m1().values()),
                List.copyOf(Registries.PACKS.getContent().values()),
                List.copyOf(InternalRegistries.TRIM_PATTERNS.getContent().values()),
                List.copyOf(InternalRegistries.TRIM_MATERIALS.getContent().values()),
                new EncodingSettings(
                        settings.resourcePackSettings().forceResourcePacks(),
                        settings.resourcePackSettings().allowClientResourcePacks(),
                        settings.resourcePackSettings().disableVibrantVisuals(),
                        settings.genericSettings().motd(),
                        settings.genericSettings().defaultPermission(),
                        settings.worldSettings().tickRadius()
                )
        );
    }

    private static void freezeSourceRegistries() {
        Registries.ITEMS.freeze();
        Registries.BLOCKS.freeze();
        Registries.BLOCK_STATE_PALETTE.freeze();
        Registries.RECIPES.freeze();
        Registries.ENTITIES.freeze();
        Registries.BIOMES.freeze();
        Registries.DIMENSIONS.freeze();
        Registries.PACKS.freeze();
        InternalRegistries.TRIM_PATTERNS.freeze();
        InternalRegistries.TRIM_MATERIALS.freeze();
        ((AllayCreativeItemRegistry) Registries.CREATIVE_ITEMS).freeze();
    }

    public Pack pack(UUID id) {
        return packsById.get(id);
    }

    /**
     * Resolves the generator type used by a dimension definition.
     *
     * @param dimensionType the dimension type
     * @return the vanilla generator type
     */
    public static VanillaGeneratorType getVanillaGeneratorType(DimensionType dimensionType) {
        var dimensionId = DimensionId.fromDimensionType(dimensionType);
        return dimensionId == null ? DimensionId.OVERWORLD.getVanillaGeneratorType() : dimensionId.getVanillaGeneratorType();
    }

    /** Server settings used while constructing packets. */
    public record EncodingSettings(
            boolean forceResourcePacks,
            boolean allowClientResourcePacks,
            boolean disableVibrantVisuals,
            String levelName,
            String defaultPermission,
            int serverChunkTickRange
    ) {
        public EncodingSettings {
            levelName = Objects.requireNonNull(levelName, "levelName");
            defaultPermission = Objects.requireNonNull(defaultPermission, "defaultPermission");
        }
    }
}
