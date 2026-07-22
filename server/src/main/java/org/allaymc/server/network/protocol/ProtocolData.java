package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.NetworkHelper;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

import java.util.*;

/**
 * Immutable, protocol-specific precomputed data shared by an encoder and packet processors.
 */
public final class ProtocolData {
    private final NetworkData source;
    private final int protocolVersion;
    private final ClientVariant variant;
    private final List<ItemDefinition> itemDefinitions;
    private final List<BlockDefinition> blockDefinitions;
    private final List<CreativeItemGroup> creativeGroups;
    private final List<CreativeItemData> creativeItems;
    private final List<BlockPropertyData> customBlockProperties;
    private final RecipeTable recipeTable;
    private final boolean materialInstancesUsePackedBooleans;
    private final boolean multipleCollisionBoxes;

    private ProtocolData(
            NetworkData source,
            int protocolVersion,
            ClientVariant variant,
            List<ItemDefinition> itemDefinitions,
            List<BlockDefinition> blockDefinitions,
            List<CreativeItemGroup> creativeGroups,
            List<CreativeItemData> creativeItems,
            List<BlockPropertyData> customBlockProperties,
            RecipeTable recipeTable,
            boolean materialInstancesUsePackedBooleans,
            boolean multipleCollisionBoxes
    ) {
        this.source = Objects.requireNonNull(source, "source");
        this.protocolVersion = protocolVersion;
        this.variant = Objects.requireNonNull(variant, "variant");
        this.itemDefinitions = ProtocolPayloadCopier.copyItemDefinitions(itemDefinitions);
        this.blockDefinitions = List.copyOf(blockDefinitions);
        this.creativeGroups = ProtocolPayloadCopier.copyCreativeGroups(creativeGroups);
        this.creativeItems = ProtocolPayloadCopier.copyCreativeItems(creativeItems);
        this.customBlockProperties = ProtocolPayloadCopier.copyBlockProperties(customBlockProperties);
        this.recipeTable = Objects.requireNonNull(recipeTable, "recipeTable");
        this.materialInstancesUsePackedBooleans = materialInstancesUsePackedBooleans;
        this.multipleCollisionBoxes = multipleCollisionBoxes;
    }

    /**
     * Creates a protocol data builder whose feature switches describe the oldest baseline.
     *
     * @param source immutable protocol-independent source data
     * @param protocolVersion Bedrock protocol number
     * @param variant client distribution
     * @return a protocol data builder
     */
    public static Builder builder(NetworkData source, int protocolVersion, ClientVariant variant) {
        return new Builder(source, protocolVersion, variant);
    }

    private static ProtocolData build(Builder builder) {
        var source = builder.source;
        var protocolVersion = builder.protocolVersion;
        var variant = builder.variant;
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(variant, "variant");

        var itemDefinitions = source.itemTypes().stream()
                .map(NetworkHelper::toNetwork)
                .toList();
        var blockDefinitions = source.blockStateRuntimeIds().stream()
                .map(runtimeId -> (BlockDefinition) () -> runtimeId)
                .toList();
        var creativeGroups = source.creativeGroups().stream()
                .map(group -> new CreativeItemGroup(
                        switch (group.category()) {
                            case CONSTRUCTION -> CreativeItemCategory.CONSTRUCTION;
                            case NATURE -> CreativeItemCategory.NATURE;
                            case EQUIPMENT -> CreativeItemCategory.EQUIPMENT;
                            case ITEMS -> CreativeItemCategory.ITEMS;
                        },
                        group.name(),
                        NetworkHelper.toNetwork(group.icon())
                ))
                .toList();
        var creativeItems = source.creativeItems().stream()
                .map(entry -> new CreativeItemData(
                        NetworkHelper.toNetwork(entry.itemStack()),
                        entry.index() + 1,
                        entry.groupIndex()
                ))
                .toList();

        return new ProtocolData(
                source,
                protocolVersion,
                variant,
                itemDefinitions,
                blockDefinitions,
                creativeGroups,
                creativeItems,
                createCustomBlockProperties(
                        source,
                        builder.materialInstancesUsePackedBooleans,
                        builder.multipleCollisionBoxes
                ),
                createRecipeTable(source, builder.furnaceRecipesAreShapeless),
                builder.materialInstancesUsePackedBooleans,
                builder.multipleCollisionBoxes
        );
    }

    public NetworkData source() {
        return source;
    }

    public int protocolVersion() {
        return protocolVersion;
    }

    public ClientVariant variant() {
        return variant;
    }

    public List<ItemDefinition> itemDefinitions() {
        return ProtocolPayloadCopier.copyItemDefinitions(itemDefinitions);
    }

    public List<BlockDefinition> blockDefinitions() {
        return blockDefinitions;
    }

    public List<CreativeItemGroup> creativeGroups() {
        return ProtocolPayloadCopier.copyCreativeGroups(creativeGroups);
    }

    public List<CreativeItemData> creativeItems() {
        return ProtocolPayloadCopier.copyCreativeItems(creativeItems);
    }

    public List<BlockPropertyData> customBlockProperties() {
        return ProtocolPayloadCopier.copyBlockProperties(customBlockProperties);
    }

    public RecipeTable recipeTable() {
        return recipeTable;
    }

    boolean materialInstancesUsePackedBooleans() {
        return materialInstancesUsePackedBooleans;
    }

    boolean multipleCollisionBoxes() {
        return multipleCollisionBoxes;
    }

    private static List<BlockPropertyData> createCustomBlockProperties(
            NetworkData source,
            boolean materialInstancesUsePackedBooleans,
            boolean multipleCollisionBoxes
    ) {
        var properties = new ArrayList<BlockPropertyData>();
        for (var blockType : source.blockTypes()) {
            var allayBlockType = (AllayBlockType<?>) blockType;
            var definition = allayBlockType.getBlockDefinition();
            if (definition == org.allaymc.server.block.type.BlockDefinition.DEFAULT) {
                continue;
            }

            var data = definition.data();
            if (!multipleCollisionBoxes || !materialInstancesUsePackedBooleans) {
                data = adaptBlockDefinition(data, materialInstancesUsePackedBooleans, multipleCollisionBoxes);
            }
            properties.add(new BlockPropertyData(blockType.getIdentifier().toString(), data));
        }
        return List.copyOf(properties);
    }

    static NbtMap adaptBlockDefinition(
            NbtMap definition,
            boolean materialInstancesUsePackedBooleans,
            boolean multipleCollisionBoxes
    ) {
        var builder = definition.toBuilder();
        builder.putCompound("components", adaptComponents(
                definition.getCompound("components"),
                materialInstancesUsePackedBooleans,
                multipleCollisionBoxes
        ));

        var permutations = new ArrayList<NbtMap>();
        definition.getList("permutations", NbtType.COMPOUND).forEach(permutation -> permutations.add(
                permutation.toBuilder()
                        .putCompound("components", adaptComponents(
                                permutation.getCompound("components"),
                                materialInstancesUsePackedBooleans,
                                multipleCollisionBoxes
                        ))
                        .build()
        ));
        builder.putList("permutations", NbtType.COMPOUND, permutations);
        return builder.build();
    }

    private static NbtMap adaptComponents(
            NbtMap components,
            boolean materialInstancesUsePackedBooleans,
            boolean multipleCollisionBoxes
    ) {
        var adapted = components.toBuilder();
        var collisionBox = components.getCompound("minecraft:collision_box");
        if (!multipleCollisionBoxes && !collisionBox.isEmpty() && collisionBox.containsKey("boxes")) {
            adapted.putCompound("minecraft:collision_box", adaptCollisionBox(collisionBox));
        }

        if (!materialInstancesUsePackedBooleans) {
            var materialInstances = components.getCompound("minecraft:material_instances");
            if (!materialInstances.isEmpty()) {
                adapted.putCompound("minecraft:material_instances", adaptMaterialInstances(materialInstances));
            }
        }
        return adapted.build();
    }

    private static NbtMap adaptCollisionBox(NbtMap collisionBox) {
        var boxes = collisionBox.getList("boxes", NbtType.COMPOUND);
        if (boxes.isEmpty()) {
            return NbtMap.builder().putBoolean("enabled", false).build();
        }

        var first = boxes.getFirst();
        float minX = first.getFloat("minX");
        float minY = first.getFloat("minY");
        float minZ = first.getFloat("minZ");
        float maxX = first.getFloat("maxX");
        float maxY = first.getFloat("maxY");
        float maxZ = first.getFloat("maxZ");
        for (int index = 1; index < boxes.size(); index++) {
            var box = boxes.get(index);
            minX = Math.min(minX, box.getFloat("minX"));
            minY = Math.min(minY, box.getFloat("minY"));
            minZ = Math.min(minZ, box.getFloat("minZ"));
            maxX = Math.max(maxX, box.getFloat("maxX"));
            maxY = Math.max(maxY, box.getFloat("maxY"));
            maxZ = Math.max(maxZ, box.getFloat("maxZ"));
        }

        return NbtMap.builder()
                .putBoolean("enabled", collisionBox.getBoolean("enabled"))
                .putList("origin", NbtType.FLOAT, List.of(minX - 8, minY, minZ - 8))
                .putList("size", NbtType.FLOAT, List.of(maxX - minX, Math.min(maxY - minY, 16), maxZ - minZ))
                .build();
    }

    private static NbtMap adaptMaterialInstances(NbtMap materialInstances) {
        var materials = materialInstances.getCompound("materials");
        var adaptedMaterials = NbtMap.builder();
        for (var entry : materials.entrySet()) {
            if (!(entry.getValue() instanceof NbtMap material)) {
                continue;
            }

            var adaptedMaterial = NbtMap.builder();
            for (var materialEntry : material.entrySet()) {
                if (materialEntry.getKey().equals("packed_bools")) {
                    byte packedBools = (Byte) materialEntry.getValue();
                    adaptedMaterial.putBoolean("face_dimming", (packedBools & 1) != 0);
                } else {
                    adaptedMaterial.put(materialEntry.getKey(), materialEntry.getValue());
                }
            }
            adaptedMaterials.putCompound(entry.getKey(), adaptedMaterial.build());
        }
        return materialInstances.toBuilder()
                .putCompound("materials", adaptedMaterials.build())
                .build();
    }

    private static RecipeTable createRecipeTable(NetworkData source, boolean furnaceRecipesAreShapeless) {
        var encodedRecipes = new ArrayList<RecipeData>();
        var potionMixes = new ArrayList<PotionMixData>();
        var recipesByNetworkId = new Int2ObjectOpenHashMap<Recipe>();
        int nextNetworkId = 1;

        for (var recipe : source.recipes()) {
            switch (recipe) {
                case ShapedRecipe shaped -> {
                    int networkId = nextNetworkId++;
                    addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, ShapedRecipeData.of(
                            CraftingDataType.SHAPED,
                            shaped.getIdentifier().toString(),
                            shaped.getPattern()[0].length,
                            shaped.getPattern().length,
                            buildNetworkIngredients(shaped),
                            buildNetworkOutputs(shaped.getOutputs()),
                            UUID.randomUUID(),
                            "crafting_table",
                            shaped.getPriority(),
                            networkId,
                            false,
                            immutableRequirement(RecipeUnlockingRequirement.UnlockingContext.NONE)
                    ));
                }
                case ShapelessRecipe shapeless -> {
                    int networkId = nextNetworkId++;
                    addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, ShapelessRecipeData.of(
                            CraftingDataType.SHAPELESS,
                            shapeless.getIdentifier().toString(),
                            buildNetworkIngredients(shapeless),
                            buildNetworkOutputs(shapeless.getOutputs()),
                            UUID.randomUUID(),
                            switch (shapeless.getType()) {
                                case CRAFTING -> "crafting_table";
                                case STONECUTTER -> "stonecutter";
                                case CARTOGRAPHY_TABLE -> "cartography_table";
                            },
                            shapeless.getPriority(),
                            networkId,
                            immutableRequirement(RecipeUnlockingRequirement.UnlockingContext.NONE)
                    ));
                }
                case SmithingTransformRecipe smithing -> {
                    int networkId = nextNetworkId++;
                    addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, SmithingTransformRecipeData.of(
                            smithing.getIdentifier().toString(),
                            NetworkHelper.toNetworkWithCount(smithing.getTemplate()),
                            NetworkHelper.toNetworkWithCount(smithing.getBase()),
                            NetworkHelper.toNetworkWithCount(smithing.getAddition()),
                            NetworkHelper.toNetwork(smithing.getOutput()),
                            "smithing_table",
                            networkId
                    ));
                }
                case SmithingTrimRecipe smithing -> {
                    int networkId = nextNetworkId++;
                    addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, SmithingTrimRecipeData.of(
                            smithing.getIdentifier().toString(),
                            NetworkHelper.toNetworkWithCount(smithing.getBase()),
                            NetworkHelper.toNetworkWithCount(smithing.getAddition()),
                            NetworkHelper.toNetworkWithCount(smithing.getTemplate()),
                            "smithing_table",
                            networkId
                    ));
                }
                case ComplexRecipe complex -> {
                    int networkId = nextNetworkId++;
                    addRecipePayload(
                            encodedRecipes,
                            recipesByNetworkId,
                            recipe,
                            MultiRecipeData.of(complex.getUuid(), networkId)
                    );
                }
                case FurnaceRecipe furnace -> {
                    if (furnaceRecipesAreShapeless) {
                        int networkId = nextNetworkId++;
                        addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, ShapelessRecipeData.of(
                                CraftingDataType.SHAPELESS,
                                furnace.getIdentifier().toString(),
                                List.of(NetworkHelper.toNetworkWithCount(furnace.getIngredient())),
                                buildNetworkOutputs(furnace.getOutputs()),
                                UUID.randomUUID(),
                                furnace.getType().name().toLowerCase(Locale.ROOT),
                                furnace.getPriority(),
                                networkId,
                                immutableRequirement(RecipeUnlockingRequirement.UnlockingContext.ALWAYS_UNLOCKED)
                        ));
                    } else if (furnace.getIngredient() instanceof ItemTypeDescriptor ingredient) {
                        addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, FurnaceRecipeData.of(
                                ingredient.getItemType().getRuntimeId(),
                                NetworkHelper.toNetwork(furnace.getOutput()),
                                furnace.getType().name().toLowerCase(Locale.ROOT)
                        ));
                    }
                }
                case PotionRecipe potion -> potionMixes.add(new PotionMixData(
                        potion.getIngredient().getItemType().getRuntimeId(),
                        potion.getIngredient().getMeta(),
                        potion.getReagent().getItemType().getRuntimeId(),
                        potion.getReagent().getMeta(),
                        potion.getOutput().getItemType().getRuntimeId(),
                        potion.getOutput().getMeta()
                ));
                default -> throw new IllegalStateException("Unsupported recipe type: " + recipe.getClass().getName());
            }
        }
        return new RecipeTable(encodedRecipes, potionMixes, recipesByNetworkId);
    }

    private static void addRecipePayload(
            List<RecipeData> encodedRecipes,
            Int2ObjectOpenHashMap<Recipe> recipesByNetworkId,
            Recipe sourceRecipe,
            RecipeData encodedRecipe
    ) {
        encodedRecipes.add(encodedRecipe);
        if (!(encodedRecipe instanceof NetworkRecipeData networkRecipe)) {
            return;
        }

        int networkId = networkRecipe.getNetId();
        if (networkId <= 0) {
            throw new IllegalStateException("Network recipe must have a positive network ID: " + encodedRecipe);
        }
        if (recipesByNetworkId.putIfAbsent(networkId, sourceRecipe) != null) {
            throw new IllegalStateException("Duplicate recipe network ID: " + networkId);
        }
    }

    private static RecipeUnlockingRequirement immutableRequirement(RecipeUnlockingRequirement.UnlockingContext context) {
        return new RecipeUnlockingRequirement(context, List.of());
    }

    private static List<ItemData> buildNetworkOutputs(ItemStack[] outputs) {
        return Arrays.stream(outputs).map(NetworkHelper::toNetwork).toList();
    }

    private static List<ItemDescriptorWithCount> buildNetworkIngredients(ShapedRecipe recipe) {
        var ingredients = new ArrayList<ItemDescriptorWithCount>();
        for (var row : recipe.getPattern()) {
            for (char key : row) {
                if (key == ShapedRecipe.EMPTY_KEY_CHAR) {
                    ingredients.add(ItemDescriptorWithCount.EMPTY);
                } else {
                    ingredients.add(NetworkHelper.toNetworkWithCount(recipe.getKeys().get(key)));
                }
            }
        }
        return List.copyOf(ingredients);
    }

    private static List<ItemDescriptorWithCount> buildNetworkIngredients(ShapelessRecipe recipe) {
        return Arrays.stream(recipe.getIngredients())
                .map(NetworkHelper::toNetworkWithCount)
                .toList();
    }

    /**
     * Builds one immutable protocol data snapshot. Subclasses in the protocol inheritance chain
     * enable only the data format changes introduced by their version.
     */
    public static final class Builder {
        private final NetworkData source;
        private final int protocolVersion;
        private final ClientVariant variant;

        private boolean materialInstancesUsePackedBooleans;
        private boolean multipleCollisionBoxes;
        private boolean furnaceRecipesAreShapeless;

        private Builder(NetworkData source, int protocolVersion, ClientVariant variant) {
            this.source = Objects.requireNonNull(source, "source");
            this.protocolVersion = protocolVersion;
            this.variant = Objects.requireNonNull(variant, "variant");
        }

        /** Enables the packed material-instance boolean representation introduced at v844. */
        public Builder materialInstancesUsePackedBooleans() {
            materialInstancesUsePackedBooleans = true;
            return this;
        }

        /** Enables multiple collision boxes introduced at v898. */
        public Builder multipleCollisionBoxes() {
            multipleCollisionBoxes = true;
            return this;
        }

        /** Enables shapeless furnace recipe payloads introduced at v975. */
        public Builder furnaceRecipesAreShapeless() {
            furnaceRecipesAreShapeless = true;
            return this;
        }

        /** Builds the immutable protocol data snapshot. */
        public ProtocolData build() {
            return ProtocolData.build(this);
        }
    }
}
