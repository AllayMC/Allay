package org.allaymc.server.network;

import com.google.common.base.Suppliers;
import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.registry.InternalRegistries;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.ExperimentData;
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitions;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import org.cloudburstmc.protocol.bedrock.packet.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * A utility class that provides memoized suppliers for various encoded data packets and definitions.
 * These suppliers lazily compute and cache the results of their respective encoding operations,
 * simplifying access to precomputed data structures for use in other parts of the application.
 * <p>
 * This class serves purposes such as defining items, blocks, experiments, crafting data,
 * biome definitions, and resource pack information. The encoded data is typically used
 * for network communication with clients.
 * <p>
 * All members of this class are static and are designed for utility purposes.
 * Instances of this class cannot be created.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class NetworkData {

    public static final Supplier<List<ItemDefinition>> ITEM_DEFINITIONS = Suppliers.memoize(NetworkData::encodeItemDefinitions);
    public static final Supplier<List<BlockDefinition>> BLOCK_DEFINITIONS = Suppliers.memoize(NetworkData::encodeBlockDefinitions);
    public static final Supplier<List<BlockPropertyData>> CUSTOM_BLOCK_PROPERTIES = Suppliers.memoize(NetworkData::encodeCustomBlockProperties);
    public static final Supplier<List<ExperimentData>> EXPERIMENT_DATA_LIST = Suppliers.memoize(NetworkData::encodeExperimentDataList);

    public static final Supplier<ItemComponentPacket> ITEM_REGISTRY_PACKET = Suppliers.memoize(NetworkData::encodeItemRegistryPacket);
    public static final Supplier<CreativeContentPacket> CREATIVE_CONTENT_PACKET = Suppliers.memoize(NetworkData::encodeCreativeContentPacket);
    public static final Supplier<CraftingDataPacket> CRAFTING_DATA_PACKET = Suppliers.memoize(NetworkData::encodeCraftingDataPacket);
    public static final Supplier<AvailableEntityIdentifiersPacket> AVAILABLE_ENTITY_IDENTIFIERS_PACKET = Suppliers.memoize(NetworkData::encodeAvailableEntityIdentifiersPacket);
    public static final Supplier<BiomeDefinitionListPacket> BIOME_DEFINITION_LIST_PACKET = Suppliers.memoize(NetworkData::encodeBiomeDefinitionListPacket);
    public static final Supplier<ResourcePacksInfoPacket> RESOURCE_PACKS_INFO_PACKET = Suppliers.memoize(NetworkData::encodeResourcePacksInfoPacket);
    public static final Supplier<ResourcePackStackPacket> RESOURCES_PACK_STACK_PACKET = Suppliers.memoize(NetworkData::encodeResourcesPackStackPacket);
    public static final Supplier<TrimDataPacket> TRIM_DATA_PACKET = Suppliers.memoize(NetworkData::encodeTrimDataPacket);

    public static final List<Recipe> INDEXED_RECIPES = new ArrayList<>();

    public static List<ItemDefinition> encodeItemDefinitions() {
        return Registries.ITEMS.getContent().values().stream().map(NetworkHelper::toNetwork).toList();
    }

    public static List<BlockDefinition> encodeBlockDefinitions() {
        return Registries.BLOCKS.getContent().values().stream()
                .flatMap(block -> block.getAllStates().stream())
                .map(blockState -> (BlockDefinition) blockState::blockStateHash)
                .toList();
    }

    /**
     * Encodes custom block property definitions for the StartGamePacket.
     * This is required for the client to understand custom block components and states.
     *
     * @return a list of BlockPropertyData containing custom block definitions
     */
    public static List<BlockPropertyData> encodeCustomBlockProperties() {
        var result = new ArrayList<BlockPropertyData>();

        for (var blockType : Registries.BLOCKS.getContent().values()) {
            var allayBlockType = (AllayBlockType<?>) blockType;
            var blockDefinition = allayBlockType.getBlockDefinition();
            if (blockDefinition == org.allaymc.server.block.type.BlockDefinition.DEFAULT) {
                // Skip vanilla blocks
                continue;
            }

            result.add(new BlockPropertyData(blockType.getIdentifier().toString(), blockDefinition.data()));
        }

        return result;
    }

    public static List<ExperimentData> encodeExperimentDataList() {
        // See https://learn.microsoft.com/en-us/minecraft/creator/documents/experimentalfeaturestoggle for info on each experiment
        return List.of(
                // data_driven_items (Holiday Creator Features) is needed for blocks and items
                new ExperimentData("data_driven_items", true),
                // Needed for block properties for states
                new ExperimentData("upcoming_creator_features", true),
                // Needed for certain molang queries used in blocks and items
                new ExperimentData("experimental_molang_features", true)
        );
    }

    private static ItemComponentPacket encodeItemRegistryPacket() {
        var packet = new ItemComponentPacket();
        packet.getItems().addAll(NetworkData.ITEM_DEFINITIONS.get());
        return packet;
    }

    private static CreativeContentPacket encodeCreativeContentPacket() {
        var packet = new CreativeContentPacket();
        for (var group : Registries.CREATIVE_ITEMS.getGroups()) {
            packet.getGroups().add(new CreativeItemGroup(
                    switch (group.getCategory().getType()) {
                        case CONSTRUCTION -> CreativeItemCategory.CONSTRUCTION;
                        case NATURE -> CreativeItemCategory.NATURE;
                        case EQUIPMENT -> CreativeItemCategory.EQUIPMENT;
                        case ITEMS -> CreativeItemCategory.ITEMS;
                    },
                    group.getName(),
                    NetworkHelper.toNetwork(group.getIcon())
            ));
        }
        for (var entry : Registries.CREATIVE_ITEMS.getEntries()) {
            packet.getContents().add(new CreativeItemData(
                    NetworkHelper.toNetwork(entry.itemStack()),
                    // NOTICE: 0 is not indexed by the client for items
                    entry.index() + 1,
                    entry.group().getIndex()
            ));
        }
        return packet;
    }

    private static CraftingDataPacket encodeCraftingDataPacket() {
        var packet = new CraftingDataPacket();
        // NOTICE: network id is start at 1
        int idCounter = 1;
        for (var recipe : Registries.RECIPES.getContent().values()) {
            switch (recipe) {
                // Indexed recipe (has network id)
                case ShapedRecipe shaped -> {
                    var id = idCounter++;
                    var data = ShapedRecipeData.of(
                            CraftingDataType.SHAPED, shaped.getIdentifier().toString(),
                            shaped.getPattern()[0].length, shaped.getPattern().length,
                            buildNetworkIngredients(shaped), buildNetworkOutputs(shaped.getOutputs()),
                            UUID.randomUUID(), "crafting_table", shaped.getPriority(), id
                    );
                    packet.getCraftingData().add(data);
                    NetworkData.INDEXED_RECIPES.add(recipe);
                }
                case ShapelessRecipe shapeless -> {
                    var id = idCounter++;
                    var data = ShapelessRecipeData.of(
                            CraftingDataType.SHAPELESS, shapeless.getIdentifier().toString(),
                            buildNetworkIngredients(shapeless), buildNetworkOutputs(shapeless.getOutputs()),
                            UUID.randomUUID(),
                            switch (shapeless.getType()) {
                                case CRAFTING -> "crafting_table";
                                case STONECUTTER -> "stonecutter";
                                case CARTOGRAPHY_TABLE -> "cartography_table";
                            },
                            shapeless.getPriority(), id
                    );
                    packet.getCraftingData().add(data);
                    NetworkData.INDEXED_RECIPES.add(recipe);
                }
                case SmithingTransformRecipe smithingTrans -> {
                    var id = idCounter++;
                    var data = SmithingTransformRecipeData.of(
                            smithingTrans.getIdentifier().toString(),
                            NetworkHelper.toNetworkWithCount(smithingTrans.getTemplate()),
                            NetworkHelper.toNetworkWithCount(smithingTrans.getBase()),
                            NetworkHelper.toNetworkWithCount(smithingTrans.getAddition()),
                            NetworkHelper.toNetwork(smithingTrans.getOutput()),
                            "smithing_table", id
                    );
                    packet.getCraftingData().add(data);
                    NetworkData.INDEXED_RECIPES.add(recipe);
                }
                case SmithingTrimRecipe smithingTrim -> {
                    var id = idCounter++;
                    var data = SmithingTrimRecipeData.of(
                            smithingTrim.getIdentifier().toString(),
                            NetworkHelper.toNetworkWithCount(smithingTrim.getBase()),
                            NetworkHelper.toNetworkWithCount(smithingTrim.getAddition()),
                            NetworkHelper.toNetworkWithCount(smithingTrim.getTemplate()),
                            "smithing_table", id
                    );
                    packet.getCraftingData().add(data);
                    NetworkData.INDEXED_RECIPES.add(recipe);
                }
                case ComplexRecipe complex -> {
                    var id = idCounter++;
                    var data = MultiRecipeData.of(complex.getUuid(), id);
                    packet.getCraftingData().add(data);
                    NetworkData.INDEXED_RECIPES.add(recipe);
                }
                // Unindexed recipe (doesn't have network id)
                case FurnaceRecipe furnace -> {
                    var data = FurnaceRecipeData.of(
                            CraftingDataType.FURNACE, furnace.getIngredient().getItemType().getRuntimeId(),
                            0, NetworkHelper.toNetwork(furnace.getOutput()),
                            furnace.getType().name().toLowerCase(Locale.ROOT)
                    );
                    packet.getCraftingData().add(data);
                }
                case PotionRecipe potion -> {
                    var data = new PotionMixData(
                            potion.getIngredient().getItemType().getRuntimeId(), potion.getIngredient().getMeta(),
                            potion.getReagent().getItemType().getRuntimeId(), potion.getReagent().getMeta(),
                            potion.getOutput().getItemType().getRuntimeId(), potion.getOutput().getMeta()
                    );
                    packet.getPotionMixData().add(data);
                }
                default -> throw new IllegalStateException("Unexpected value: " + recipe);
            }
        }
        packet.setCleanRecipes(true);
        return packet;
    }

    private static List<ItemData> buildNetworkOutputs(ItemStack[] shaped) {
        return Arrays.stream(shaped).map(NetworkHelper::toNetwork).toList();
    }

    private static List<ItemDescriptorWithCount> buildNetworkIngredients(ShapedRecipe recipe) {
        List<ItemDescriptorWithCount> ingredients = new ArrayList<>();
        for (var sub : recipe.getPattern()) {
            for (var k : sub) {
                if (k == ShapedRecipe.EMPTY_KEY_CHAR) {
                    ingredients.add(ItemDescriptorWithCount.EMPTY);
                    continue;
                }

                var descriptor = recipe.getKeys().get(k);
                ingredients.add(NetworkHelper.toNetworkWithCount(descriptor));
            }
        }
        return ingredients;
    }

    private static List<ItemDescriptorWithCount> buildNetworkIngredients(ShapelessRecipe recipe) {
        return Arrays.stream(recipe.getIngredients()).map(NetworkHelper::toNetworkWithCount).toList();
    }

    public static AvailableEntityIdentifiersPacket encodeAvailableEntityIdentifiersPacket() {
        var ids = new LinkedList<NbtMap>();
        for (var type : Registries.ENTITIES.getContent().values()) {
            ids.add(NbtMap.builder()
                    .putString("id", type.getIdentifier().toString())
                    .build()
            );
        }

        var packet = new AvailableEntityIdentifiersPacket();
        packet.setIdentifiers(NbtMap.builder()
                .putList("idlist", NbtType.COMPOUND, ids)
                .build()
        );
        return packet;
    }

    public static BiomeDefinitionListPacket encodeBiomeDefinitionListPacket() {
        Map<String, BiomeDefinitionData> definitions = new HashMap<>();
        for (var biome : Registries.BIOMES.getContent().m1().values()) {
            definitions.put(biome.getIdentifier().path(), NetworkHelper.toNetwork(biome));
        }
        var packet = new BiomeDefinitionListPacket();
        packet.setBiomes(new BiomeDefinitions(definitions));
        return packet;
    }

    public static ResourcePacksInfoPacket encodeResourcePacksInfoPacket() {
        var settings = AllayServer.getSettings().resourcePackSettings();

        var packet = new ResourcePacksInfoPacket();
        packet.setForcedToAccept(settings.forceResourcePacks());
        packet.setWorldTemplateId(new UUID(0, 0));
        packet.setWorldTemplateVersion("");
        packet.setVibrantVisualsForceDisabled(settings.disableVibrantVisuals());
        packet.setScriptingEnabled(true);

        for (var pack : Registries.PACKS.getContent().values()) {
            var info = switch (pack.getType()) {
                case RESOURCES -> new ResourcePacksInfoPacket.Entry(
                        pack.getId(), pack.getStringVersion(), pack.getSize(), pack.getContentKey(),
                        "", pack.getId().toString(), pack.getType() == Pack.Type.SCRIPT,
                        pack.getManifest().getCapabilities().contains(PackManifest.Capability.RAYTRACED),
                        false, null
                );
                case DATA -> {
                    packet.setHasAddonPacks(true);
                    yield new ResourcePacksInfoPacket.Entry(
                            pack.getId(), pack.getStringVersion(), pack.getSize(), pack.getContentKey(),
                            "", pack.getId().toString(), pack.getType() == Pack.Type.SCRIPT,
                            pack.getManifest().getCapabilities().contains(PackManifest.Capability.RAYTRACED),
                            true, null
                    );
                }
                case null, default -> null;
            };
            if (info != null) {
                packet.getResourcePackInfos().add(info);
            }
        }

        return packet;
    }

    public static ResourcePackStackPacket encodeResourcesPackStackPacket() {
        var settings = AllayServer.getSettings().resourcePackSettings();

        var packet = new ResourcePackStackPacket();
        packet.setForcedToAccept(settings.forceResourcePacks() && !settings.allowClientResourcePacks());
        // Just left '*' here. If we put in an exact game version, it is possible that client
        // won't send back ResourcePackClientResponsePacket(packIds=[*], status=COMPLETED)
        packet.setGameVersion("*");
        packet.getExperiments().addAll(EXPERIMENT_DATA_LIST.get());

        for (var pack : Registries.PACKS.getContent().values()) {
            var entry = switch (pack.getType()) {
                case RESOURCES, DATA -> new ResourcePackStackPacket.Entry(pack.getId().toString(), pack.getStringVersion(), "");
                case null, default -> null;
            };
            if (entry != null) {
                packet.getResourcePacks().add(entry);
            }
        }

        return packet;
    }

    public static TrimDataPacket encodeTrimDataPacket() {
        var packet = new TrimDataPacket();
        packet.getPatterns().addAll(InternalRegistries.TRIM_PATTERNS.getContent().values().stream().map(pattern ->
                new TrimPattern(
                        pattern.itemType().getIdentifier().toString(),
                        pattern.patternId()
                )
        ).collect(Collectors.toSet()));
        packet.getMaterials().addAll(InternalRegistries.TRIM_MATERIALS.getContent().values().stream().map(material ->
                new TrimMaterial(
                        material.materialId(),
                        material.color(),
                        material.itemType().getIdentifier().toString()
                )
        ).collect(Collectors.toSet()));
        return packet;
    }
}
