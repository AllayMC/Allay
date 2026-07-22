package org.allaymc.server.network;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.property.type.BooleanPropertyType;
import org.allaymc.api.entity.property.type.EntityPropertyType;
import org.allaymc.api.entity.property.type.EnumPropertyType;
import org.allaymc.api.entity.property.type.FloatPropertyType;
import org.allaymc.api.entity.property.type.IntPropertyType;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.data.TrimMaterial;
import org.allaymc.api.item.data.TrimPattern;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.PotionRecipe;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.recipe.ShapelessRecipe;
import org.allaymc.api.item.recipe.SmithingTransformRecipe;
import org.allaymc.api.item.recipe.SmithingTrimRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.biome.BiomeData;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.AllayServer;
import org.allaymc.server.item.creative.AllayCreativeItemRegistry;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.item.recipe.DecoratedPotRecipe;
import org.allaymc.server.item.recipe.FireworkRecipe;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.world.dimension.DimensionId;
import org.allaymc.server.world.dimension.VanillaGeneratorType;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * An immutable snapshot of protocol-independent data used to build protocol data and packets.
 * The snapshot must be captured after plugins have finished registering content.
 */
public final class NetworkData {
    private final List<ItemType<?>> itemTypes;
    private final List<BlockType<?>> blockTypes;
    private final List<BlockState> blockStates;
    private final List<Integer> blockStateRuntimeIds;
    private final List<Recipe> recipes;
    private final List<CreativeGroupSnapshot> creativeGroups;
    private final List<CreativeItemSnapshot> creativeItems;
    private final List<EntitySnapshot> entityTypes;
    private final List<BiomeType> biomeTypes;
    private final List<DimensionSnapshot> dimensionSnapshots;
    private final List<PackSnapshot> packs;
    private final Map<UUID, PackSnapshot> packsById;
    private final List<TrimPattern> trimPatterns;
    private final List<TrimMaterial> trimMaterials;
    private final EncodingSettings encodingSettings;

    NetworkData(
            List<ItemType<?>> itemTypes,
            List<BlockType<?>> blockTypes,
            List<BlockState> blockStates,
            List<Recipe> recipes,
            List<CreativeGroupSnapshot> creativeGroups,
            List<CreativeItemSnapshot> creativeItems,
            List<EntitySnapshot> entityTypes,
            List<? extends BiomeType> biomeTypes,
            List<DimensionType> dimensionTypes,
            List<PackSnapshot> packs,
            List<TrimPattern> trimPatterns,
            List<TrimMaterial> trimMaterials
    ) {
        this(
                itemTypes,
                blockTypes,
                blockStates,
                recipes,
                creativeGroups,
                creativeItems,
                entityTypes,
                biomeTypes,
                dimensionTypes,
                packs,
                trimPatterns,
                trimMaterials,
                new EncodingSettings(false, true, false, "", "MEMBER", 4)
        );
    }

    NetworkData(
            List<ItemType<?>> itemTypes,
            List<BlockType<?>> blockTypes,
            List<BlockState> blockStates,
            List<Recipe> recipes,
            List<CreativeGroupSnapshot> creativeGroups,
            List<CreativeItemSnapshot> creativeItems,
            List<EntitySnapshot> entityTypes,
            List<? extends BiomeType> biomeTypes,
            List<DimensionType> dimensionTypes,
            List<PackSnapshot> packs,
            List<TrimPattern> trimPatterns,
            List<TrimMaterial> trimMaterials,
            EncodingSettings encodingSettings
    ) {
        this.itemTypes = List.copyOf(itemTypes);
        this.blockTypes = List.copyOf(blockTypes);
        this.blockStates = List.copyOf(blockStates);
        this.blockStateRuntimeIds = this.blockStates.stream()
                .map(BlockState::blockStateHash)
                .toList();
        this.recipes = List.copyOf(recipes);
        this.creativeGroups = List.copyOf(creativeGroups);
        this.creativeItems = List.copyOf(creativeItems);
        this.entityTypes = List.copyOf(entityTypes);
        this.biomeTypes = List.copyOf(biomeTypes);
        this.dimensionSnapshots = dimensionTypes.stream()
                .map(DimensionSnapshot::from)
                .toList();
        this.packs = List.copyOf(packs);
        var packsById = new LinkedHashMap<UUID, PackSnapshot>();
        for (var pack : this.packs) {
            if (packsById.putIfAbsent(pack.id(), pack) != null) {
                throw new IllegalArgumentException("Duplicate resource-pack ID: " + pack.id());
            }
        }
        this.packsById = Map.copyOf(packsById);
        this.trimPatterns = List.copyOf(trimPatterns);
        this.trimMaterials = List.copyOf(trimMaterials);
        this.encodingSettings = Objects.requireNonNull(encodingSettings, "encodingSettings");
    }

    /**
     * Captures the current registry contents without retaining registry collection views.
     *
     * @return an immutable network source snapshot
     */
    public static NetworkData capture() {
        freezeSourceRegistries();
        var itemTypes = List.copyOf(Registries.ITEMS.getContent().values());
        var blockTypes = List.copyOf(Registries.BLOCKS.getContent().values());
        var blockStates = blockTypes.stream()
                .flatMap(blockType -> blockType.getAllStates().stream())
                .toList();
        var recipes = Registries.RECIPES.getContent().values().stream()
                .sorted(Comparator.comparing(recipe -> recipe.getIdentifier().toString()))
                .map(NetworkData::snapshotRecipe)
                .toList();
        var creativeGroups = Registries.CREATIVE_ITEMS.getGroups().stream()
                .map(group -> new CreativeGroupSnapshot(
                        group.getIndex(),
                        group.getCategory().getType(),
                        group.getName(),
                        group.getIcon()
                ))
                .toList();
        var creativeItems = Registries.CREATIVE_ITEMS.getEntries().stream()
                .map(entry -> new CreativeItemSnapshot(
                        entry.index(),
                        entry.group().getIndex(),
                        entry.itemStack()
                ))
                .toList();

        var settings = AllayServer.getSettings();
        return new NetworkData(
                itemTypes,
                blockTypes,
                blockStates,
                recipes,
                creativeGroups,
                creativeItems,
                Registries.ENTITIES.getContent().values().stream()
                        .map(EntitySnapshot::from)
                        .toList(),
                Registries.BIOMES.getContent().m1().values().stream()
                        .map(BiomeSnapshot::from)
                        .toList(),
                List.copyOf(Registries.DIMENSIONS.getContent().m1().values()),
                Registries.PACKS.getContent().entrySet().stream()
                        .map(entry -> PackSnapshot.from(entry.getKey(), entry.getValue()))
                        .toList(),
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

    public List<ItemType<?>> itemTypes() {
        return itemTypes;
    }

    public List<BlockType<?>> blockTypes() {
        return blockTypes;
    }

    public List<BlockState> blockStates() {
        return blockStates;
    }

    /**
     * Returns the block-state runtime IDs captured with this network snapshot.
     *
     * @return immutable block-state runtime IDs
     */
    public List<Integer> blockStateRuntimeIds() {
        return blockStateRuntimeIds;
    }

    /** Returns immutable server settings that affect packet encoding. */
    public EncodingSettings encodingSettings() {
        return encodingSettings;
    }

    public List<Recipe> recipes() {
        return recipes;
    }

    public List<CreativeGroupSnapshot> creativeGroups() {
        return creativeGroups;
    }

    public List<CreativeItemSnapshot> creativeItems() {
        return creativeItems;
    }

    public List<EntitySnapshot> entityTypes() {
        return entityTypes;
    }

    public List<BiomeType> biomeTypes() {
        return biomeTypes;
    }

    public List<DimensionType> dimensionTypes() {
        return List.copyOf(dimensionSnapshots);
    }

    /**
     * Returns immutable dimension metadata captured from the dimension registry.
     *
     * @return immutable dimension snapshots
     */
    public List<DimensionSnapshot> dimensionSnapshots() {
        return dimensionSnapshots;
    }

    public List<PackSnapshot> packs() {
        return packs;
    }

    /**
     * Looks up immutable resource-pack metadata and content by its registered ID.
     *
     * @param id the registered pack ID
     * @return the pack snapshot, or {@code null} when no such pack was captured
     */
    public PackSnapshot pack(UUID id) {
        return packsById.get(id);
    }

    public List<TrimPattern> trimPatterns() {
        return trimPatterns;
    }

    public List<TrimMaterial> trimMaterials() {
        return trimMaterials;
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

    static Recipe snapshotRecipe(Recipe recipe) {
        Objects.requireNonNull(recipe, "recipe");
        return switch (recipe) {
            case ShapedRecipe shaped -> new ShapedRecipeSnapshot(shaped);
            case ShapelessRecipe shapeless -> new ShapelessRecipeSnapshot(shapeless);
            case SmithingTransformRecipe smithing -> new SmithingTransformRecipeSnapshot(smithing);
            case SmithingTrimRecipe smithing -> new SmithingTrimRecipeSnapshot(smithing);
            case FurnaceRecipe furnace -> new FurnaceRecipeSnapshot(furnace);
            case PotionRecipe potion -> new PotionRecipeSnapshot(potion);
            case ComplexRecipe complex -> ComplexRecipeSnapshot.from(complex);
            default -> throw new IllegalArgumentException(
                    "Unsupported recipe type for network snapshot: " + recipe.getClass().getName()
            );
        };
    }

    private static ItemStack copyItemStack(ItemStack itemStack) {
        Objects.requireNonNull(itemStack, "itemStack");
        return Objects.requireNonNull(itemStack.copy(false), "item stack copy");
    }

    private static ItemStack[] copyItemStacks(ItemStack[] itemStacks) {
        if (itemStacks == null) {
            return null;
        }
        var copy = new ItemStack[itemStacks.length];
        for (int index = 0; index < itemStacks.length; index++) {
            copy[index] = copyItemStack(itemStacks[index]);
        }
        return copy;
    }

    private static ItemDescriptor copyDescriptor(ItemDescriptor descriptor) {
        Objects.requireNonNull(descriptor, "descriptor");
        return switch (descriptor) {
            case ItemTypeDescriptor item -> new ItemTypeDescriptor(item.getItemType(), item.getMeta());
            case ItemTagDescriptor tag -> new ItemTagDescriptor(tag.getItemTag());
            default -> throw new IllegalArgumentException(
                    "Unsupported item descriptor type for network snapshot: " + descriptor.getClass().getName()
            );
        };
    }

    private static ItemDescriptor[] copyDescriptors(ItemDescriptor[] descriptors) {
        Objects.requireNonNull(descriptors, "descriptors");
        var copy = new ItemDescriptor[descriptors.length];
        for (int index = 0; index < descriptors.length; index++) {
            copy[index] = copyDescriptor(descriptors[index]);
        }
        return copy;
    }

    private static Map<Character, ItemDescriptor> copyDescriptorMap(Map<Character, ItemDescriptor> descriptors) {
        Objects.requireNonNull(descriptors, "descriptors");
        var copy = new LinkedHashMap<Character, ItemDescriptor>();
        for (var entry : descriptors.entrySet()) {
            copy.put(
                    Objects.requireNonNull(entry.getKey(), "descriptor key"),
                    copyDescriptor(entry.getValue())
            );
        }
        return Collections.unmodifiableMap(copy);
    }

    private static char[][] copyPattern(char[][] pattern) {
        Objects.requireNonNull(pattern, "pattern");
        var copy = new char[pattern.length][];
        for (int row = 0; row < pattern.length; row++) {
            copy[row] = Objects.requireNonNull(pattern[row], "pattern row").clone();
        }
        return copy;
    }

    private static final class ShapedRecipeSnapshot extends ShapedRecipe {
        private ShapedRecipeSnapshot(ShapedRecipe source) {
            super(
                    source.getIdentifier(),
                    copyItemStacks(source.getOutputs()),
                    source.getPriority(),
                    copyPattern(source.getPattern()),
                    copyDescriptorMap(source.getKeys())
            );
        }

        @Override
        public ItemStack[] getOutputs() {
            return copyItemStacks(outputs);
        }

        @Override
        public ItemStack getOutput() {
            return copyItemStack(outputs[0]);
        }

        @Override
        public Map<Character, ItemDescriptor> getKeys() {
            return copyDescriptorMap(keys);
        }

        @Override
        public char[][] getPattern() {
            return copyPattern(pattern);
        }
    }

    private static final class ShapelessRecipeSnapshot extends ShapelessRecipe {
        private ShapelessRecipeSnapshot(ShapelessRecipe source) {
            super(
                    source.getIdentifier(),
                    copyItemStacks(source.getOutputs()),
                    source.getPriority(),
                    copyDescriptors(source.getIngredients()),
                    source.getType()
            );
        }

        @Override
        public ItemStack[] getOutputs() {
            return copyItemStacks(outputs);
        }

        @Override
        public ItemStack getOutput() {
            return copyItemStack(outputs[0]);
        }

        @Override
        public ItemDescriptor[] getIngredients() {
            return copyDescriptors(ingredients);
        }
    }

    private static final class SmithingTransformRecipeSnapshot extends SmithingTransformRecipe {
        private SmithingTransformRecipeSnapshot(SmithingTransformRecipe source) {
            super(
                    source.getIdentifier(),
                    copyItemStacks(source.getOutputs()),
                    source.getPriority(),
                    copyDescriptor(source.getTemplate()),
                    copyDescriptor(source.getBase()),
                    copyDescriptor(source.getAddition())
            );
        }

        @Override
        public ItemStack[] getOutputs() {
            return copyItemStacks(outputs);
        }

        @Override
        public ItemStack getOutput() {
            return copyItemStack(outputs[0]);
        }

        @Override
        public ItemDescriptor getTemplate() {
            return copyDescriptor(template);
        }

        @Override
        public ItemDescriptor getBase() {
            return copyDescriptor(base);
        }

        @Override
        public ItemDescriptor getAddition() {
            return copyDescriptor(addition);
        }
    }

    private static final class SmithingTrimRecipeSnapshot extends SmithingTrimRecipe {
        private SmithingTrimRecipeSnapshot(SmithingTrimRecipe source) {
            super(
                    source.getIdentifier(),
                    copyItemStacks(source.getOutputs()),
                    source.getPriority(),
                    copyDescriptor(source.getTemplate()),
                    copyDescriptor(source.getBase()),
                    copyDescriptor(source.getAddition())
            );
        }

        @Override
        public ItemStack[] getOutputs() {
            return copyItemStacks(outputs);
        }

        @Override
        public ItemStack getOutput() {
            return copyItemStack(outputs[0]);
        }

        @Override
        public ItemDescriptor getTemplate() {
            return copyDescriptor(template);
        }

        @Override
        public ItemDescriptor getBase() {
            return copyDescriptor(base);
        }

        @Override
        public ItemDescriptor getAddition() {
            return copyDescriptor(addition);
        }
    }

    private static final class FurnaceRecipeSnapshot extends FurnaceRecipe {
        private final Identifier snapshotIdentifier;

        private FurnaceRecipeSnapshot(FurnaceRecipe source) {
            super(
                    copyDescriptor(source.getIngredient()),
                    copyItemStack(source.getOutput()),
                    source.getPriority(),
                    source.getType()
            );
            this.snapshotIdentifier = source.getIdentifier();
        }

        @Override
        public Identifier getIdentifier() {
            return snapshotIdentifier;
        }

        @Override
        public ItemStack[] getOutputs() {
            return copyItemStacks(outputs);
        }

        @Override
        public ItemStack getOutput() {
            return copyItemStack(outputs[0]);
        }

        @Override
        public ItemDescriptor getIngredient() {
            return copyDescriptor(ingredient);
        }
    }

    private static final class PotionRecipeSnapshot extends PotionRecipe {
        private final Identifier snapshotIdentifier;

        private PotionRecipeSnapshot(PotionRecipe source) {
            super(
                    copyItemStack(source.getIngredient()),
                    copyItemStack(source.getOutput()),
                    copyItemStack(source.getReagent())
            );
            this.snapshotIdentifier = source.getIdentifier();
        }

        @Override
        public Identifier getIdentifier() {
            return snapshotIdentifier;
        }

        @Override
        public ItemStack[] getOutputs() {
            return copyItemStacks(outputs);
        }

        @Override
        public ItemStack getOutput() {
            return copyItemStack(outputs[0]);
        }

        @Override
        public ItemStack getIngredient() {
            return copyItemStack(ingredient);
        }

        @Override
        public ItemStack getReagent() {
            return copyItemStack(reagent);
        }
    }

    private static final class ComplexRecipeSnapshot extends ComplexRecipe {
        private final ComplexRecipeKind kind;

        private ComplexRecipeSnapshot(ComplexRecipeKind kind) {
            super(kind.uuid());
            this.kind = kind;
        }

        private static ComplexRecipeSnapshot from(ComplexRecipe source) {
            if (source == FireworkRecipe.INSTANCE) {
                return new ComplexRecipeSnapshot(ComplexRecipeKind.FIREWORK);
            }
            if (source == DecoratedPotRecipe.INSTANCE) {
                return new ComplexRecipeSnapshot(ComplexRecipeKind.DECORATED_POT);
            }
            throw new IllegalArgumentException(
                    "Unsupported complex recipe type for network snapshot: " + source.getClass().getName()
            );
        }

        @Override
        public boolean match(RecipeInput input) {
            return switch (kind) {
                case FIREWORK -> FireworkRecipe.INSTANCE.match(input);
                case DECORATED_POT -> DecoratedPotRecipe.INSTANCE.match(input);
            };
        }

        @Override
        public ItemStack[] getOutputs(RecipeInput input) {
            return copyItemStacks(switch (kind) {
                case FIREWORK -> FireworkRecipe.INSTANCE.getOutputs(input);
                case DECORATED_POT -> DecoratedPotRecipe.INSTANCE.getOutputs(input);
            });
        }
    }

    private enum ComplexRecipeKind {
        FIREWORK(FireworkRecipe.INSTANCE.getUuid()),
        DECORATED_POT(DecoratedPotRecipe.INSTANCE.getUuid());

        private final UUID uuid;

        ComplexRecipeKind(UUID uuid) {
            this.uuid = uuid;
        }

        private UUID uuid() {
            return uuid;
        }
    }

    /**
     * Immutable dimension metadata compatible with existing dimension APIs.
     *
     * @param id the numeric dimension ID
     * @param identifier the dimension identifier
     * @param minHeight the minimum build height
     * @param maxHeight the maximum build height
     * @param hasSkyLight whether the dimension has sky light
     * @param bedWorks whether beds work in the dimension
     * @param respawnAnchorWorks whether respawn anchors work in the dimension
     * @param waterEvaporates whether water evaporates in the dimension
     */
    public record DimensionSnapshot(
            int id,
            Identifier identifier,
            int minHeight,
            int maxHeight,
            boolean hasSkyLight,
            boolean bedWorks,
            boolean respawnAnchorWorks,
            boolean waterEvaporates
    ) implements DimensionType {
        public DimensionSnapshot {
            identifier = Objects.requireNonNull(identifier, "identifier");
        }

        static DimensionSnapshot from(DimensionType dimensionType) {
            Objects.requireNonNull(dimensionType, "dimensionType");
            return new DimensionSnapshot(
                    dimensionType.getId(),
                    dimensionType.getIdentifier(),
                    dimensionType.getMinHeight(),
                    dimensionType.getMaxHeight(),
                    dimensionType.hasSkyLight(),
                    dimensionType.bedWorks(),
                    dimensionType.respawnAnchorWorks(),
                    dimensionType.waterEvaporates()
            );
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

    /**
     * Immutable biome source data compatible with the existing network conversion helper.
     *
     * @param identifier the biome identifier
     * @param id the biome runtime ID
     * @param biomeData the immutable biome data
     */
    public record BiomeSnapshot(Identifier identifier, int id, BiomeData biomeData) implements BiomeType {
        public BiomeSnapshot {
            identifier = Objects.requireNonNull(identifier, "identifier");
            biomeData = copyBiomeData(biomeData);
        }

        static BiomeSnapshot from(BiomeType biomeType) {
            Objects.requireNonNull(biomeType, "biomeType");
            return new BiomeSnapshot(
                    biomeType.getIdentifier(),
                    biomeType.getId(),
                    biomeType.getBiomeData()
            );
        }

        @Override
        public Identifier getIdentifier() {
            return identifier;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public BiomeData getBiomeData() {
            return biomeData;
        }

        private static BiomeData copyBiomeData(BiomeData source) {
            Objects.requireNonNull(source, "source");
            return new BiomeData(
                    source.temperature(),
                    source.downfall(),
                    source.redSporeDensity(),
                    source.blueSporeDensity(),
                    source.ashDensity(),
                    source.whiteAshDensity(),
                    source.foliageSnow(),
                    source.depth(),
                    source.scale(),
                    source.mapWaterColor(),
                    source.rain(),
                    List.copyOf(source.tags())
            );
        }
    }

    /**
     * Immutable resource-pack metadata with access to the pack's fixed encoded content.
     */
    public static final class PackSnapshot {
        private final UUID id;
        private final String version;
        private final int size;
        private final String contentKey;
        private final Pack.Type type;
        private final boolean raytraced;
        private final byte[] hash;
        private final Pack contentSource;

        private PackSnapshot(
                UUID id,
                String version,
                int size,
                String contentKey,
                Pack.Type type,
                boolean raytraced,
                byte[] hash,
                Pack contentSource
        ) {
            this.id = Objects.requireNonNull(id, "id");
            this.version = Objects.requireNonNull(version, "version");
            if (size < 0) {
                throw new IllegalArgumentException("Resource-pack size cannot be negative");
            }
            this.size = size;
            this.contentKey = contentKey;
            this.type = Objects.requireNonNull(type, "type");
            this.raytraced = raytraced;
            this.hash = Objects.requireNonNull(hash, "hash").clone();
            this.contentSource = Objects.requireNonNull(contentSource, "contentSource");
        }

        static PackSnapshot from(Pack pack) {
            Objects.requireNonNull(pack, "pack");
            return from(pack.getId(), pack);
        }

        static PackSnapshot from(UUID registeredId, Pack pack) {
            Objects.requireNonNull(pack, "pack");
            return new PackSnapshot(
                    registeredId,
                    pack.getStringVersion(),
                    pack.getSize(),
                    pack.getContentKey(),
                    pack.getType(),
                    pack.getManifest().getCapabilities().contains(PackManifest.Capability.RAYTRACED),
                    pack.getHash(),
                    pack
            );
        }

        public UUID id() {
            return id;
        }

        public String version() {
            return version;
        }

        public int size() {
            return size;
        }

        public String contentKey() {
            return contentKey;
        }

        public Pack.Type type() {
            return type;
        }

        public boolean raytraced() {
            return raytraced;
        }

        /**
         * Returns a copy of the encoded pack hash.
         *
         * @return the copied SHA-256 hash
         */
        public byte[] hash() {
            return hash.clone();
        }

        /**
         * Reads one independent chunk of the fixed encoded pack content.
         *
         * @param offset byte offset in the pack
         * @param maximumLength maximum number of bytes to return
         * @return a new byte array containing the requested chunk
         */
        public byte[] chunk(int offset, int maximumLength) {
            if (offset < 0 || offset > size) {
                throw new IllegalArgumentException("Resource-pack chunk offset is out of bounds: " + offset);
            }
            if (maximumLength <= 0) {
                throw new IllegalArgumentException("Resource-pack chunk length must be positive");
            }

            int length = Math.min(maximumLength, size - offset);
            var chunk = Objects.requireNonNull(contentSource.getChunk(offset, length), "pack chunk");
            if (chunk.length != length) {
                throw new IllegalStateException(
                        "Resource-pack chunk length mismatch: expected " + length + ", got " + chunk.length
                );
            }
            return chunk.clone();
        }
    }

    /**
     * Immutable entity identifier and property definitions used by protocol encoders.
     *
     * @param identifier the entity identifier
     * @param properties immutable entity property definitions
     */
    public record EntitySnapshot(Identifier identifier, List<EntityPropertySnapshot> properties) {
        public EntitySnapshot {
            identifier = Objects.requireNonNull(identifier, "identifier");
            properties = List.copyOf(properties);
        }

        static EntitySnapshot from(EntityType<?> entityType) {
            Objects.requireNonNull(entityType, "entityType");
            var properties = entityType.getProperties().values().stream()
                    .map(EntityPropertySnapshot::from)
                    .toList();
            return new EntitySnapshot(entityType.getIdentifier(), properties);
        }
    }

    /**
     * Immutable source representation of an entity property definition.
     */
    public sealed interface EntityPropertySnapshot permits
            BooleanEntityPropertySnapshot,
            EnumEntityPropertySnapshot,
            IntEntityPropertySnapshot,
            FloatEntityPropertySnapshot {

        String name();

        EntityPropertyType.Type type();

        private static EntityPropertySnapshot from(EntityPropertyType<?> propertyType) {
            Objects.requireNonNull(propertyType, "propertyType");
            return switch (propertyType) {
                case BooleanPropertyType property -> new BooleanEntityPropertySnapshot(property.getName());
                case EnumPropertyType<?> property -> new EnumEntityPropertySnapshot(
                        property.getName(),
                        List.of(property.serializedValues())
                );
                case IntPropertyType property -> new IntEntityPropertySnapshot(
                        property.getName(),
                        property.getDefaultValue(),
                        property.getMin(),
                        property.getMax()
                );
                case FloatPropertyType property -> new FloatEntityPropertySnapshot(
                        property.getName(),
                        property.getDefaultValue(),
                        property.getMin(),
                        property.getMax()
                );
            };
        }
    }

    /** Entity boolean property snapshot. */
    public record BooleanEntityPropertySnapshot(String name) implements EntityPropertySnapshot {
        public BooleanEntityPropertySnapshot {
            name = Objects.requireNonNull(name, "name");
        }

        @Override
        public EntityPropertyType.Type type() {
            return EntityPropertyType.Type.BOOLEAN;
        }
    }

    /** Entity enum property snapshot. */
    public record EnumEntityPropertySnapshot(String name, List<String> serializedValues) implements EntityPropertySnapshot {
        public EnumEntityPropertySnapshot {
            name = Objects.requireNonNull(name, "name");
            serializedValues = List.copyOf(serializedValues);
        }

        @Override
        public EntityPropertyType.Type type() {
            return EntityPropertyType.Type.ENUM;
        }
    }

    /** Entity integer property snapshot. */
    public record IntEntityPropertySnapshot(String name, int defaultValue, int min, int max) implements EntityPropertySnapshot {
        public IntEntityPropertySnapshot {
            name = Objects.requireNonNull(name, "name");
        }

        @Override
        public EntityPropertyType.Type type() {
            return EntityPropertyType.Type.INT;
        }
    }

    /** Entity floating-point property snapshot. */
    public record FloatEntityPropertySnapshot(String name, float defaultValue, float min, float max) implements EntityPropertySnapshot {
        public FloatEntityPropertySnapshot {
            name = Objects.requireNonNull(name, "name");
        }

        @Override
        public EntityPropertyType.Type type() {
            return EntityPropertyType.Type.FLOAT;
        }
    }

    /** Immutable server settings used while constructing packets. */
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

    /**
     * Immutable creative group source data. The icon accessor returns a copy because item stacks
     * are mutable domain objects.
     */
    public static final class CreativeGroupSnapshot {
        private final int index;
        private final CreativeItemCategory.Type category;
        private final String name;
        private final ItemStack icon;

        private CreativeGroupSnapshot(int index, CreativeItemCategory.Type category, String name, ItemStack icon) {
            this.index = index;
            this.category = Objects.requireNonNull(category, "category");
            this.name = Objects.requireNonNull(name, "name");
            this.icon = Objects.requireNonNull(icon, "icon").copy(false);
        }

        public int index() {
            return index;
        }

        public CreativeItemCategory.Type category() {
            return category;
        }

        public String name() {
            return name;
        }

        public ItemStack icon() {
            return icon.copy(false);
        }
    }

    /**
     * Immutable creative item source data. The item accessor returns a copy because item stacks
     * are mutable domain objects.
     */
    public static final class CreativeItemSnapshot {
        private final int index;
        private final int groupIndex;
        private final ItemStack itemStack;

        private CreativeItemSnapshot(int index, int groupIndex, ItemStack itemStack) {
            this.index = index;
            this.groupIndex = groupIndex;
            this.itemStack = Objects.requireNonNull(itemStack, "itemStack").copy(false);
        }

        public int index() {
            return index;
        }

        public int groupIndex() {
            return groupIndex;
        }

        public ItemStack itemStack() {
            return itemStack.copy(false);
        }
    }
}
