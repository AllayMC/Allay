package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.PotionRecipe;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.recipe.ShapelessRecipe;
import org.allaymc.api.item.recipe.SmithingTransformRecipe;
import org.allaymc.api.item.recipe.SmithingTrimRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
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
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.MultiRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.NetworkRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTransformRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.SmithingTrimRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import org.cloudburstmc.protocol.bedrock.definition.DefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * An immutable, fully initialized Bedrock protocol implementation.
 *
 * <p>Construction only records the protocol identity. {@link #initialize()} performs
 * the overridable initialization steps after the complete subtype has been constructed.</p>
 */
public abstract class Protocol {
    private final BedrockCodec codec;
    private final ClientVariant variant;

    private PacketProcessorRegistry processorRegistry;
    private ProtocolData data;
    private PacketEncoder encoder;
    private Set<ProtocolFeature> features;
    private DefinitionRegistry<ItemDefinition> itemDefinitionRegistry;
    private DefinitionRegistry<BlockDefinition> blockDefinitionRegistry;
    private volatile boolean initialized;

    protected Protocol(BedrockCodec codec, ClientVariant variant) {
        this.codec = Objects.requireNonNull(codec, "codec");
        this.variant = Objects.requireNonNull(variant, "variant");
    }

    /**
     * Completes this protocol's one-time initialization from the frozen server registries.
     */
    public final synchronized void initialize() {
        if (initialized) {
            throw new IllegalStateException("Protocol is already initialized: " + this);
        }

        var registry = new PacketProcessorRegistry();
        registerProcessors(registry);
        registry.freeze();

        var protocolData = new ProtocolData(
                createItemDefinitions(),
                createBlockDefinitions(),
                createCreativeGroups(),
                createCreativeItems(),
                createCustomBlockProperties(),
                createRecipeTable()
        );
        var itemDefinitions = SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(protocolData.itemDefinitions())
                .build();
        var blockDefinitions = SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(protocolData.blockDefinitions())
                .build();
        var packetEncoder = Objects.requireNonNull(createEncoder(protocolData), "createEncoder returned null");
        if (packetEncoder.getData() != protocolData) {
            throw new IllegalStateException("Packet encoder does not reference the protocol's data instance: " + this);
        }
        var protocolFeatures = Set.copyOf(createFeatures());

        this.processorRegistry = registry;
        this.data = protocolData;
        this.encoder = packetEncoder;
        this.features = protocolFeatures;
        this.itemDefinitionRegistry = itemDefinitions;
        this.blockDefinitionRegistry = blockDefinitions;
        this.initialized = true;
    }

    protected abstract void registerProcessors(PacketProcessorRegistry registry);

    protected abstract PacketEncoder createEncoder(ProtocolData data);

    protected Set<ProtocolFeature> createFeatures() {
        return Set.of();
    }

    protected List<ItemDefinition> createItemDefinitions() {
        return Registries.ITEMS.getContent().values().stream()
                .map(NetworkHelper::toNetwork)
                .toList();
    }

    protected List<BlockDefinition> createBlockDefinitions() {
        return Registries.BLOCKS.getContent().values().stream()
                .flatMap(blockType -> blockType.getAllStates().stream())
                .map(blockState -> (BlockDefinition) blockState::blockStateHash)
                .toList();
    }

    protected List<CreativeItemGroup> createCreativeGroups() {
        return Registries.CREATIVE_ITEMS.getGroups().stream()
                .map(group -> new CreativeItemGroup(
                        switch (group.getCategory().getType()) {
                            case CONSTRUCTION -> CreativeItemCategory.CONSTRUCTION;
                            case NATURE -> CreativeItemCategory.NATURE;
                            case EQUIPMENT -> CreativeItemCategory.EQUIPMENT;
                            case ITEMS -> CreativeItemCategory.ITEMS;
                        },
                        group.getName(),
                        NetworkHelper.toNetwork(group.getIcon())
                ))
                .toList();
    }

    protected List<CreativeItemData> createCreativeItems() {
        return Registries.CREATIVE_ITEMS.getEntries().stream()
                .map(entry -> new CreativeItemData(
                        NetworkHelper.toNetwork(entry.itemStack()),
                        entry.index() + 1,
                        entry.group().getIndex()
                ))
                .toList();
    }

    protected List<BlockPropertyData> createCustomBlockProperties() {
        var properties = new ArrayList<BlockPropertyData>();
        for (var blockType : Registries.BLOCKS.getContent().values()) {
            var allayBlockType = (AllayBlockType<?>) blockType;
            var definition = allayBlockType.getBlockDefinition();
            if (definition == org.allaymc.server.block.type.BlockDefinition.DEFAULT) {
                continue;
            }

            var data = Objects.requireNonNull(
                    encodeCustomBlockDefinition(definition.data()),
                    "encodeCustomBlockDefinition returned null"
            );
            properties.add(new BlockPropertyData(blockType.getIdentifier().toString(), data));
        }
        return List.copyOf(properties);
    }

    protected NbtMap encodeCustomBlockDefinition(NbtMap definition) {
        return definition;
    }

    protected RecipeTable createRecipeTable() {
        var encodedRecipes = new ArrayList<RecipeData>();
        var potionMixes = new ArrayList<PotionMixData>();
        var recipesByNetworkId = new Int2ObjectOpenHashMap<Recipe>();
        int nextNetworkId = 1;

        var recipes = Registries.RECIPES.getContent().values().stream()
                .sorted(Comparator.comparing(recipe -> recipe.getIdentifier().toString()))
                .toList();
        for (var recipe : recipes) {
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
                    var encodedRecipe = encodeFurnaceRecipe(furnace, nextNetworkId);
                    if (encodedRecipe != null) {
                        addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, encodedRecipe);
                        if (encodedRecipe instanceof NetworkRecipeData) {
                            nextNetworkId++;
                        }
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

    protected RecipeData encodeFurnaceRecipe(FurnaceRecipe recipe, int networkId) {
        if (!(recipe.getIngredient() instanceof ItemTypeDescriptor ingredient)) {
            return null;
        }
        return FurnaceRecipeData.of(
                ingredient.getItemType().getRuntimeId(),
                NetworkHelper.toNetwork(recipe.getOutput()),
                recipe.getType().name().toLowerCase(Locale.ROOT)
        );
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

    protected static RecipeUnlockingRequirement immutableRequirement(
            RecipeUnlockingRequirement.UnlockingContext context
    ) {
        return new RecipeUnlockingRequirement(context, List.of());
    }

    protected static List<ItemData> buildNetworkOutputs(ItemStack[] outputs) {
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

    public final BedrockCodec getCodec() {
        return codec;
    }

    public final int getProtocolVersion() {
        return codec.getProtocolVersion();
    }

    public final String getMinecraftVersion() {
        return codec.getMinecraftVersion();
    }

    public final ClientVariant getVariant() {
        return variant;
    }

    public final ProtocolData getData() {
        ensureInitialized();
        return data;
    }

    public final PacketEncoder getEncoder() {
        ensureInitialized();
        return encoder;
    }

    public final PacketProcessorHolder createProcessorHolder() {
        ensureInitialized();
        return new PacketProcessorHolder(processorRegistry);
    }

    public final boolean supports(ProtocolFeature feature) {
        ensureInitialized();
        return features.contains(feature);
    }

    public final Set<ProtocolFeature> getFeatures() {
        ensureInitialized();
        return features;
    }

    final DefinitionRegistry<ItemDefinition> getItemDefinitionRegistry() {
        ensureInitialized();
        return itemDefinitionRegistry;
    }

    final DefinitionRegistry<BlockDefinition> getBlockDefinitionRegistry() {
        ensureInitialized();
        return blockDefinitionRegistry;
    }

    public final boolean isInitialized() {
        return initialized;
    }

    private void ensureInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Protocol has not been initialized: " + this);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + variant.name() + ", v" + getProtocolVersion() + ']';
    }
}
