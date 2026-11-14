package org.allaymc.server.network.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.data.TintMethod;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.CustomBlockDefinition;
import org.allaymc.server.block.type.CustomBlockStateDefinition;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.item.type.CustomItemDefinition;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.utils.MolangUtils;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import org.cloudburstmc.protocol.bedrock.definition.DefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;
import org.joml.Vector3fc;

import java.util.*;

/**
 * {@code Protocol} represents one Bedrock wire version and client distribution
 * together with its processor factories, definition registries, recipe index,
 * and packet encoder. The constructor records the codec and distribution
 * without invoking subtype hooks. Callers must invoke {@code initialize} after
 * subtype construction and startup content registration finish, so every hook
 * derives network data from the completed content set.
 *
 * <p> Dependent payloads resolve items and blocks through definition registries
 * prepared for this same protocol. The encoder must retain the exact
 * {@code ProtocolData} instance assembled from those payloads, and
 * {@code initialized} publishes successful initialization through a final
 * volatile write. Runtime failures during dependent construction clear the
 * provisional definition references without undoing subtype side effects.
 * {@code createRecipeTable()} assigns encoded network IDs, and
 * {@code addRecipePayload} associates each ID with its originating server
 * recipe. Legacy furnace payloads remain unindexed when their format carries no
 * network ID.
 */
public abstract class Protocol {
    /**
     * Codec defining this wire version.
     */
    private final BedrockCodec codec;
    /**
     * Client distribution selected by registration.
     */
    private final ClientVariant variant;

    // processorRegistry, data, encoder, itemDefinitionRegistry, and
    // blockDefinitionRegistry are initialized together; initialized publishes
    // their completed values, while guarded accessors reject observations
    // before that publication.
    /**
     * Frozen processor factories for connections.
     */
    private PacketProcessorRegistry processorRegistry;
    /**
     * Completed protocol payload bundle.
     */
    private ProtocolData data;
    /**
     * Encoder retaining the data instance.
     */
    private PacketEncoder encoder;
    /**
     * {@code itemDefinitionRegistry} holds item definitions used during
     * dependent payload construction and after publication. {@code initialize}
     * clears this reference if dependent construction or encoder validation
     * fails.
     */
    private DefinitionRegistry<ItemDefinition> itemDefinitionRegistry;
    /**
     * {@code blockDefinitionRegistry} holds block definitions used during
     * dependent payload construction and after publication. {@code initialize}
     * clears it alongside {@code itemDefinitionRegistry} if the dependent
     * construction phase fails.
     */
    private DefinitionRegistry<BlockDefinition> blockDefinitionRegistry;
    /**
     * {@code blockPalette} holds the block states this protocol's clients know,
     * which chunks, block updates and block definitions are encoded with.
     */
    private NetworkBlockPalette blockPalette;
    /**
     * {@code initialized} publishes completion after every required component
     * has been assigned. Guarded accessors read this volatile flag before
     * exposing initialization results to other threads.
     */
    private volatile boolean initialized;

    /**
     * Creates an uninitialized protocol using a nonnull codec and client
     * distribution. The constructor does not invoke subtype hooks; callers must
     * finish subtype construction before explicitly initializing its runtime
     * data.
     */
    protected Protocol(BedrockCodec codec, ClientVariant variant) {
        this.codec = Objects.requireNonNull(codec, "codec");
        this.variant = Objects.requireNonNull(variant, "variant");
    }

    /**
     * Builds this protocol's components once and publishes them after
     * validation. Callers must finish subtype construction and source registry
     * registration before invoking this method; an already initialized protocol
     * rejects another call.
     *
     * <p> Item and block registries are made available internally before
     * dependent payload hooks run, so all encoded references use the same
     * definitions. The encoder must retain the newly assembled data object by
     * identity. Only then does the volatile initialized flag expose the
     * components to readers. {@code RuntimeException} or {@code Error} during
     * the dependent phase clears the temporary definition references and leaves
     * readiness {@code false}, allowing another attempt without promising to
     * undo side effects inside subtype hooks.
     */
    public final synchronized void initialize() {
        if (initialized) {
            throw new IllegalStateException("Protocol is already initialized: " + this);
        }

        var registry = new PacketProcessorRegistry();
        registerProcessors(registry);
        registry.freeze();

        var encodedItemDefinitions = createItemDefinitions();
        var itemDefinitions = SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(encodedItemDefinitions)
                .build();
        // Block definitions are built from the palette, so it must be known first.
        this.blockPalette = Objects.requireNonNull(createBlockPalette(), "createBlockPalette returned null");
        var encodedBlockDefinitions = createBlockDefinitions();
        var blockDefinitions = SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(encodedBlockDefinitions)
                .build();

        // Publish definitions internally before dependent hooks so creative
        // items and recipes resolve identities from the same protocol inputs.
        this.itemDefinitionRegistry = itemDefinitions;
        this.blockDefinitionRegistry = blockDefinitions;
        try {
            var protocolData = new ProtocolData(
                    encodedItemDefinitions,
                    encodedBlockDefinitions,
                    createCreativeGroups(),
                    createCreativeItems(),
                    createCustomBlockProperties(),
                    createRecipeTable(),
                    blockPalette
            );
            var packetEncoder = Objects.requireNonNull(createEncoder(protocolData), "createEncoder returned null");
            if (packetEncoder.getData() != protocolData) {
                throw new IllegalStateException("Packet encoder does not reference the protocol's data instance: " + this);
            }
            this.processorRegistry = registry;
            this.data = protocolData;
            this.encoder = packetEncoder;
            // Publish readiness last so guarded readers cannot observe an
            // encoder or payload bundle before all matching components exist.
            this.initialized = true;
        } catch (RuntimeException | Error exception) {
            // Failed dependent construction must not retain provisional
            // definitions for a later attempt, although subtype side effects
            // cannot be reversed here.
            this.itemDefinitionRegistry = null;
            this.blockDefinitionRegistry = null;
            this.blockPalette = null;
            throw exception;
        }
    }

    /**
     * Populates the mutable processor factory registry for this protocol during
     * initialization. The caller freezes the registry after this hook returns;
     * implementations should register factories for the supported client states
     * before that boundary.
     */
    protected abstract void registerProcessors(PacketProcessorRegistry registry);

    /**
     * Creates a nonnull encoder retaining the supplied protocol data by
     * identity. Initialization rejects an encoder backed by another data
     * instance, even when the two bundles contain equal definitions or
     * payloads.
     */
    protected abstract PacketEncoder createEncoder(ProtocolData data);

    /**
     * Builds item definitions in ascending server runtime-ID order for this
     * protocol. Each definition uses the registered item identity and selects
     * vanilla or custom component encoding; subtype overrides may adapt the
     * resulting format.
     */
    protected List<ItemDefinition> createItemDefinitions() {
        return Registries.ITEMS.getContent().values().stream()
                .sorted(Comparator.comparingInt(org.allaymc.api.item.type.ItemType::getRuntimeId))
                .map(itemType -> encodeItemDefinition((AllayItemType<?>) itemType))
                .toList();
    }

    /**
     * Encodes one item using its vanilla definition when available, otherwise
     * its custom definition or an empty {@code NONE} definition. Vanilla
     * {@code NONE} entries omit component data, while all paths retain the item
     * type identifier and runtime ID.
     */
    private ItemDefinition encodeItemDefinition(AllayItemType<?> itemType) {
        if (!itemType.isCustomItem()) {
            var itemId = ItemId.fromIdentifier(itemType.getIdentifier());
            var vanillaDefinition = InternalRegistries.VANILLA_ITEM_DEFINITIONS.get(itemId);
            if (vanillaDefinition != null) {
                // NONE definitions use an empty component tag; versioned
                // definitions retain their components so the registry matches
                // its declared item format.
                var components = vanillaDefinition.version() == ItemVersion.NONE ? NbtMap.EMPTY : vanillaDefinition.components();
                return new SimpleItemDefinition(
                        itemType.getIdentifier().toString(),
                        itemType.getRuntimeId(),
                        vanillaDefinition.version(),
                        vanillaDefinition.componentBased(),
                        components
                );
            }
        }

        // Without a usable vanilla definition, prefer explicit custom metadata;
        // otherwise preserve identity using NONE rather than inventing
        // component payloads.
        var customDefinition = itemType.getCustomItemDefinition();
        if (customDefinition != null) {
            return encodeCustomItemDefinition(itemType, customDefinition);
        }
        return new SimpleItemDefinition(
                itemType.getIdentifier().toString(),
                itemType.getRuntimeId(),
                ItemVersion.NONE,
                false,
                NbtMap.EMPTY
        );
    }

    /**
     * Builds a data-driven item definition from the item type and its
     * customization. Common item properties come from registered item data,
     * while an instantiated stack determines wearable, edible, and
     * tool-specific components.
     *
     * <p> Explicit raw properties replace automatically derived properties, and
     * raw components are applied last so they can replace complete generated
     * components, including {@code "item_properties"}. The resulting definition
     * retains the registered runtime ID and identifier. An absent display name
     * uses the identifier, while optional offsets, cooldown, and other
     * component data are emitted only when applicable.
     */
    private ItemDefinition encodeCustomItemDefinition(
            AllayItemType<?> itemType,
            CustomItemDefinition definition
    ) {
        var itemData = itemType.getItemData();
        var itemStack = itemType.createItemStack();
        var properties = NbtMap.builder()
                .putInt("max_stack_size", itemData.maxStackSize())
                .putBoolean("allow_off_hand", itemType.hasItemTag(ItemTags.ALLOW_OFFHAND))
                .putBoolean("can_destroy_in_creative", definition.canDestroyInCreative())
                .putCompound("minecraft:icon", NbtMap.builder()
                        .putCompound("textures", NbtMap.builder()
                                .putString("default", definition.texture())
                                .build())
                        .build());
        var components = NbtMap.builder();

        if (definition.renderOffsets() != null) {
            components.putCompound("minecraft:render_offsets", encodeRenderOffsets(definition.renderOffsets()));
        }
        if (definition.foil()) {
            properties.putBoolean("foil", true);
        }
        components.putCompound("minecraft:display_name", NbtMap.builder()
                .putString("value", definition.displayName() != null
                        ? definition.displayName()
                        : itemType.getIdentifier().toString())
                .build());

        var tags = itemType.getItemTags();
        if (!tags.isEmpty()) {
            components.putList("item_tags", NbtType.STRING, tags.stream().map(ItemTag::name).toArray(String[]::new));
        }
        if (itemData.isDamageable()) {
            components.putCompound("minecraft:durability", NbtMap.builder()
                    .putInt("max_durability", itemData.maxDamage())
                    .build());
        }
        if (itemStack instanceof ItemWearableComponent wearable) {
            components.putCompound("minecraft:armor", NbtMap.builder()
                    .putInt("protection", itemData.armorValue())
                    .build());
            var slot = switch (wearable.getArmorType()) {
                case HELMET -> "slot.armor.head";
                case CHESTPLATE -> "slot.armor.chest";
                case LEGGINGS -> "slot.armor.legs";
                case BOOTS -> "slot.armor.feet";
            };
            properties.putString("wearable_slot", slot);
            components.putCompound("minecraft:wearable", NbtMap.builder().putString("slot", slot).build());
        }
        if (itemStack instanceof ItemEdibleComponent edible) {
            properties.putInt("use_duration", edible.getEatingTime())
                    .putInt("use_animation", edible.isDrink() ? 2 : 1);
            components.putCompound("minecraft:food", NbtMap.builder()
                    .putBoolean("can_always_eat", edible.canBeAlwaysEaten())
                    .build());
        }
        if (definition.cooldown() != null) {
            components.putCompound("minecraft:cooldown", NbtMap.builder()
                    .putString("category", itemType.getIdentifier().toString())
                    .putFloat("duration", definition.cooldown())
                    .build());
        }
        if (itemStack instanceof ItemToolComponent) {
            properties.putBoolean("hand_equipped", true)
                    .putInt("damage", itemData.attackDamage());
        }

        // Apply explicit properties and components last so custom definitions
        // can replace derived defaults, including the entire item_properties
        // component.
        properties.putAll(definition.rawProperties());
        components.putCompound("item_properties", properties.build());
        components.putAll(definition.rawComponents());
        return new SimpleItemDefinition(
                itemType.getIdentifier().toString(),
                itemType.getRuntimeId(),
                ItemVersion.DATA_DRIVEN,
                true,
                NbtMap.builder().putCompound("components", components.build()).build()
        );
    }

    /**
     * Encodes the supplied hand render offsets into a new NBT compound. Missing
     * main-hand or offhand settings are omitted independently, allowing the
     * remaining hand configuration to be advertised without a synthetic
     * replacement.
     */
    private static NbtMap encodeRenderOffsets(CustomItemDefinition.RenderOffsets renderOffsets) {
        var builder = NbtMap.builder();
        if (renderOffsets.mainHand() != null) {
            builder.putCompound("main_hand", encodeHandOffsets(renderOffsets.mainHand()));
        }
        if (renderOffsets.offHand() != null) {
            builder.putCompound("off_hand", encodeHandOffsets(renderOffsets.offHand()));
        }
        return builder.build();
    }

    /**
     * Encodes first-person and third-person offsets for one hand. Missing
     * perspective settings are omitted rather than represented by empty
     * transforms, while present settings are encoded into their corresponding
     * named compounds.
     */
    private static NbtMap encodeHandOffsets(CustomItemDefinition.RenderOffsets.Hand hand) {
        var builder = NbtMap.builder();
        if (hand.firstPerson() != null) {
            builder.putCompound("first_person", encodeOffset(hand.firstPerson()));
        }
        if (hand.thirdPerson() != null) {
            builder.putCompound("third_person", encodeOffset(hand.thirdPerson()));
        }
        return builder.build();
    }

    /**
     * Encodes the supplied position, rotation, and scale vectors for one
     * perspective. Each absent vector is omitted independently, and present
     * vectors retain their x, y, z ordering as floating-point NBT lists.
     */
    private static NbtMap encodeOffset(CustomItemDefinition.RenderOffsets.Offset offset) {
        var builder = NbtMap.builder();
        if (offset.position() != null) {
            builder.putList("position", NbtType.FLOAT, vectorValues(offset.position()));
        }
        if (offset.rotation() != null) {
            builder.putList("rotation", NbtType.FLOAT, vectorValues(offset.rotation()));
        }
        if (offset.scale() != null) {
            builder.putList("scale", NbtType.FLOAT, vectorValues(offset.scale()));
        }
        return builder.build();
    }

    /**
     * Returns the vector components in x, y, z order.
     */
    private static List<Float> vectorValues(Vector3fc vector) {
        return List.of(vector.x(), vector.y(), vector.z());
    }

    /**
     * Builds the definitions this protocol's clients decode, which by default are
     * those of its block palette. Each definition exposes the palette's network
     * hash as its runtime ID.
     */
    protected List<BlockDefinition> createBlockDefinitions() {
        return blockPalette.definitions();
    }

    /**
     * Returns the block palette this protocol's clients know block states by. Protocols older than the current block
     * states override this with their palette.
     *
     * @return the block palette
     */
    protected NetworkBlockPalette createBlockPalette() {
        return NetworkBlockPalette.current();
    }

    /**
     * Builds creative group payloads using the source registry iteration order.
     * Group icons are encoded against this protocol's prepared definition
     * registries, while category labels and names retain their registered group
     * meaning.
     */
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
                        encodeItemStack(group.getIcon())
                ))
                .toList();
    }

    /**
     * Builds creative entries with their registered group indexes and
     * protocol-specific item definitions. Entry indexes are increased by one
     * for the network ID, preserving the client's positive creative-entry
     * numbering.
     */
    protected List<CreativeItemData> createCreativeItems() {
        // Creative network IDs are positive; shift the zero-based entry index.
        return Registries.CREATIVE_ITEMS.getEntries().stream()
                .map(entry -> new CreativeItemData(
                        encodeItemStack(entry.itemStack()),
                        entry.index() + 1,
                        entry.group().getIndex()
                ))
                .toList();
    }

    /**
     * Builds custom block properties in lexicographic identifier order. Custom
     * block IDs begin at 10000 and increase within this generated list; these
     * advertised IDs are separate from the state hashes used for block
     * definitions.
     */
    protected List<BlockPropertyData> createCustomBlockProperties() {
        var properties = new ArrayList<BlockPropertyData>();
        var blockTypes = Registries.BLOCKS.getContent().values().stream()
                .map(blockType -> (AllayBlockType<?>) blockType)
                .filter(blockType -> blockType.getCustomBlockDefinition() != null)
                .sorted(Comparator.comparing(blockType -> blockType.getIdentifier().toString()))
                .toList();
        // Allocate this payload's custom IDs independently from block-state
        // hashes.
        int customBlockId = 10000;
        for (var blockType : blockTypes) {
            var definition = blockType.getCustomBlockDefinition();
            var data = encodeCustomBlockDefinition(blockType, definition, customBlockId++);
            properties.add(new BlockPropertyData(blockType.getIdentifier().toString(), data));
        }
        return List.copyOf(properties);
    }

    /**
     * Builds a custom block payload with global components and state-specific
     * permutations. Every registered state must have a matching state
     * definition keyed by its hash; missing entries fail initialization instead
     * of advertising an incomplete state set.
     *
     * <p> Global raw components override fallback geometry and material
     * choices. Within each permutation, raw state components precede
     * server-derived collision, selection, light, friction, and mining
     * properties, ensuring those physical values take precedence. Molang
     * conditions identify each state, while custom property declarations and
     * placement traits describe how clients select among them.
     */
    private NbtMap encodeCustomBlockDefinition(
            BlockType<?> blockType,
            CustomBlockDefinition definition,
            int customBlockId
    ) {
        var globalComponents = NbtMap.builder()
                .putCompound("minecraft:geometry", NbtMap.builder()
                        .putString("identifier", "minecraft:geometry.full_block")
                        .build())
                .putCompound("minecraft:material_instances", NbtMap.builder()
                        .putCompound("mappings", NbtMap.EMPTY)
                        .putCompound("materials", NbtMap.builder()
                                .putCompound("*", encodeMaterialInstance(
                                        CustomBlockStateDefinition.MaterialInstance.opaque("missing_texture"),
                                        TintMethod.NONE
                                ))
                                .build())
                        .build());
        if (blockType.hasBlockTag(BlockTags.REPLACEABLE)) {
            globalComponents.putCompound("minecraft:replaceable", NbtMap.EMPTY);
        }
        if (blockType.hasBlockTag(BlockTags.POTTABLE_PLANT)) {
            globalComponents.putCompound("minecraft:flower_pottable", NbtMap.EMPTY);
        }
        globalComponents.putAll(definition.rawComponents());

        var blockData = NbtMap.builder()
                .putCompound("components", globalComponents.build())
                .putCompound("menu_category", defaultMenuCategory())
                .putCompound("vanilla_block_data", NbtMap.builder().putInt("block_id", customBlockId).build())
                .putList("properties", NbtType.COMPOUND, buildPropertyDefinitions(blockType))
                .putList("traits", NbtType.COMPOUND, buildTraits(blockType, definition.rotationOffset()))
                .putInt("molangVersion", MolangUtils.MOLANG_VERSION);

        var permutations = new LinkedList<NbtMap>();
        for (var blockState : blockType.getAllStates()) {
            var stateDefinition = definition.stateDefinitions().get(blockState.blockStateHash());
            if (stateDefinition == null) {
                throw new IllegalStateException("Missing custom definition for block state " + blockState);
            }
            var stateData = blockState.getBlockStateData();
            var components = encodeCustomBlockStateDefinition(stateDefinition, stateData.tintMethod()).toBuilder();

            // Apply server-derived physical components after raw values so
            // client collision, lighting, and friction follow the registered
            // state data.
            components.putAll(stateDefinition.rawComponents());
            components.putCompound("minecraft:collision_box", encodeCollisionBox(stateData.collisionShape()));
            components.putCompound("minecraft:selection_box", encodeSelectionBox(stateData.shape()));
            components.putCompound("minecraft:light_emission", NbtMap.builder()
                    .putByte("emission", (byte) stateData.lightEmission())
                    .build());
            components.putCompound("minecraft:light_dampening", NbtMap.builder()
                    .putByte("lightLevel", (byte) stateData.lightDampening())
                    .build());
            // Client friction uses the inverse value within its supported
            // range.
            components.putCompound("minecraft:friction", NbtMap.builder()
                    .putFloat("value", Math.clamp(1 - stateData.friction(), 0.0f, 0.9f))
                    .build());
            // Advertise the maximum duration while server state supplies actual
            // mining properties.
            components.putCompound("minecraft:destructible_by_mining", NbtMap.builder()
                    .putFloat("value", Float.MAX_VALUE)
                    .build());
            for (var tag : blockType.getBlockTags()) {
                components.putCompound("tag:" + tag.name(), NbtMap.EMPTY);
            }
            permutations.add(NbtMap.builder()
                    .putString("condition", MolangUtils.buildSingleStateCondition(blockState, blockType))
                    .putCompound("components", components.build())
                    .build());
        }
        return blockData.putList("permutations", NbtType.COMPOUND, permutations).build();
    }

    /**
     * Encodes the explicitly configured visual components for one custom block
     * state. Missing names, geometry, materials, or transforms are omitted;
     * material encoding receives the state tint method, and raw components are
     * merged by the enclosing block encoder.
     */
    private NbtMap encodeCustomBlockStateDefinition(
            CustomBlockStateDefinition definition,
            TintMethod tintMethod
    ) {
        var components = NbtMap.builder();
        if (definition.displayName() != null) {
            components.putCompound("minecraft:display_name", NbtMap.builder()
                    .putString("value", definition.displayName())
                    .build());
        }
        if (definition.geometry() != null) {
            components.putCompound("minecraft:geometry", encodeGeometry(definition.geometry()));
        }
        if (definition.materials() != null && !definition.materials().isEmpty()) {
            var materials = NbtMap.builder();
            definition.materials().materials().forEach((name, material) ->
                    materials.putCompound(name, encodeMaterialInstance(material, tintMethod)));
            components.putCompound("minecraft:material_instances", NbtMap.builder()
                    .putCompound("mappings", NbtMap.EMPTY)
                    .putCompound("materials", materials.build())
                    .build());
        }
        if (definition.transformation() != null) {
            components.putCompound("minecraft:transformation", encodeTransformation(definition.transformation()));
        }
        return components.build();
    }

    /**
     * Encodes geometry settings, bone visibility expressions, and UV locking.
     * Locking all UVs takes precedence over a bone list; absent culling values
     * use the empty culling identifier and the undefined culling layer.
     */
    private static NbtMap encodeGeometry(CustomBlockStateDefinition.Geometry geometry) {
        var builder = NbtMap.builder()
                .putString("identifier", geometry.identifier())
                .putBoolean("ignoreGeometryForIsSolid", true)
                .putBoolean("needsLegacyTopRotation", false)
                .putBoolean("useBlockTypeLightAbsorption", false);
        if (geometry.boneVisibility() != null && !geometry.boneVisibility().isEmpty()) {
            var visibility = NbtMap.builder();
            geometry.boneVisibility().forEach((name, value) -> visibility.putCompound(name, NbtMap.builder()
                    .putString("expression", value.toMolang())
                    .putShort("version", (short) MolangUtils.MOLANG_VERSION)
                    .build()));
            builder.putCompound("bone_visibility", visibility.build());
        }
        builder.putString("culling", geometry.culling() != null ? geometry.culling() : "")
                .putString("culling_layer", geometry.cullingLayer() != null
                        ? geometry.cullingLayer()
                        : "minecraft:culling_layer.undefined");
        // The client accepts either one boolean or a bone list for uv_lock, so
        // the all-bones choice takes precedence.
        if (geometry.uvLockAll()) {
            builder.putBoolean("uv_lock", true);
        } else if (geometry.uvLockBones() != null && !geometry.uvLockBones().isEmpty()) {
            builder.putList("uv_lock", NbtType.STRING, geometry.uvLockBones());
        } else {
            builder.putBoolean("uv_lock", false);
        }
        return builder.build();
    }

    /**
     * Encodes rotation, scale, and translation into the client transformation
     * fields. Rotations are normalized and truncated to quarter turns; scale
     * and translation retain their supplied floating-point values without
     * additional normalization.
     */
    private static NbtMap encodeTransformation(CustomBlockStateDefinition.Transformation transformation) {
        return NbtMap.builder()
                .putInt("RX", normalizedQuarterTurns(transformation.rx()))
                .putInt("RY", normalizedQuarterTurns(transformation.ry()))
                .putInt("RZ", normalizedQuarterTurns(transformation.rz()))
                .putFloat("SX", transformation.sx())
                .putFloat("SY", transformation.sy())
                .putFloat("SZ", transformation.sz())
                .putFloat("TX", transformation.tx())
                .putFloat("TY", transformation.ty())
                .putFloat("TZ", transformation.tz())
                .build();
    }

    /**
     * Returns a nonnegative quarter-turn index after wrapping degrees within
     * one revolution. Values between quarter turns are truncated toward the
     * lower normalized index; this helper does not reject angles that are not
     * multiples of ninety.
     */
    private static int normalizedQuarterTurns(int degrees) {
        // Normalize negative rotations before truncating to client quarter
        // turns.
        return (((degrees % 360) + 360) % 360) / 90;
    }

    /**
     * Encodes material flags using the packed layout supplied by this base
     * protocol. Subclasses can replace the format for older clients while
     * retaining the material and tint inputs used by custom block construction.
     */
    protected NbtMap encodeMaterialInstance(
            CustomBlockStateDefinition.MaterialInstance material,
            TintMethod tintMethod
    ) {
        return encodePackedMaterialInstance(material, tintMethod);
    }

    /**
     * Encodes face dimming, random UV rotation, and texture variation into one
     * packed boolean field. Texture, rendering mode, ambient occlusion, and
     * tint retain their separate material fields.
     *
     * <p> Each enabled flag contributes its own bit through a bitwise union, so
     * simultaneous flags remain independent in {@code "packed_bools"}. Face
     * dimming uses bit zero, random UV rotation uses bit one, and texture
     * variation uses bit two. The tint name uses locale-independent lowercase
     * to match the client component representation.
     */
    protected final NbtMap encodePackedMaterialInstance(
            CustomBlockStateDefinition.MaterialInstance material,
            TintMethod tintMethod
    ) {
        // Pack the three material flags in the client bit order; independent
        // bits preserve combinations that separate booleans represent
        // internally.
        byte packedBooleans = 0;
        if (material.faceDimming()) packedBooleans |= 0x1;
        if (material.randomUVRotation()) packedBooleans |= 0x2;
        if (material.textureVariation()) packedBooleans |= 0x4;
        return NbtMap.builder()
                .putString("texture", material.texture())
                .putString("render_method", material.renderMethod().getId())
                .putFloat("ambient_occlusion", material.ambientOcclusionIntensity())
                .putByte("packed_bools", packedBooleans)
                .putString("tint_method", tintMethod.name().toLowerCase(Locale.ROOT))
                .build();
    }

    /**
     * Encodes collision as the modern list of individual solid boxes.
     * {@code null} or empty shapes disable collision; subclasses can select a
     * legacy representation without changing the shape supplied by custom block
     * construction.
     */
    protected NbtMap encodeCollisionBox(VoxelShape shape) {
        return encodeBoxListCollision(shape);
    }

    /**
     * Encodes every solid box separately in sixteenth-block coordinates. A
     * {@code null} or empty {@code shape} produces a disabled component;
     * otherwise each coordinate is multiplied by sixteen and narrowed to a
     * float without merging solids.
     */
    protected final NbtMap encodeBoxListCollision(VoxelShape shape) {
        if (shape == null || shape.getSolids().isEmpty()) {
            return NbtMap.builder().putBoolean("enabled", false).build();
        }
        // Convert block units to sixteenths without merging separate solids.
        var boxes = shape.getSolids().stream().map(solid -> NbtMap.builder()
                .putFloat("minX", (float) (solid.minX() * 16))
                .putFloat("minY", (float) (solid.minY() * 16))
                .putFloat("minZ", (float) (solid.minZ() * 16))
                .putFloat("maxX", (float) (solid.maxX() * 16))
                .putFloat("maxY", (float) (solid.maxY() * 16))
                .putFloat("maxZ", (float) (solid.maxZ() * 16))
                .build()).toList();
        return NbtMap.builder()
                .putBoolean("enabled", true)
                .putList("boxes", NbtType.COMPOUND, boxes)
                .build();
    }

    /**
     * Encodes the bounding union of all solids using the legacy origin-and-size
     * representation. A {@code null} or empty {@code shape} disables the
     * component instead of creating a box.
     *
     * <p> The legacy component represents one box, so {@code unionAABB()}
     * combines separated solids and loses their intervening gaps. Coordinates
     * use sixteenth-block units, horizontal origins shift by eight units, and
     * height is capped at sixteen units. The resulting selection or collision
     * component therefore approximates shapes whose occupied region cannot fit
     * that representation.
     */
    protected static NbtMap encodeMergedBox(VoxelShape shape) {
        if (shape == null || shape.getSolids().isEmpty()) {
            return NbtMap.builder().putBoolean("enabled", false).build();
        }
        // Legacy origin-and-size fields accept one box, so merge the solids,
        // shift horizontal origins, and cap height to one block.
        var bounds = shape.unionAABB();
        float minX = (float) (bounds.minX() * 16);
        float minY = (float) (bounds.minY() * 16);
        float minZ = (float) (bounds.minZ() * 16);
        float maxX = (float) (bounds.maxX() * 16);
        float maxY = (float) (bounds.maxY() * 16);
        float maxZ = (float) (bounds.maxZ() * 16);
        return NbtMap.builder()
                .putBoolean("enabled", true)
                .putList("origin", NbtType.FLOAT, List.of(minX - 8, minY, minZ - 8))
                .putList("size", NbtType.FLOAT, List.of(maxX - minX, Math.min(maxY - minY, 16), maxZ - minZ))
                .build();
    }

    /**
     * Encodes selection using the legacy merged-box representation shared with
     * collision fallback. {@code null} or empty shapes disable selection;
     * separate solids become one bounding box, with the same coordinate shift
     * and height cap.
     */
    private static NbtMap encodeSelectionBox(VoxelShape shape) {
        return encodeMergedBox(shape);
    }

    /**
     * Returns the construction menu category used by custom block payloads. The
     * group is empty and command visibility is enabled through the zero hidden
     * flag, independently of later state-specific permutations.
     */
    private static NbtMap defaultMenuCategory() {
        return NbtMap.builder()
                .putString("category", "construction")
                .putString("group", "")
                .putByte("is_hidden_in_commands", (byte) 0)
                .build();
    }

    /**
     * Builds placement-direction and placement-position traits from the block's
     * known vanilla properties. The supplied rotation offset applies to
     * direction placement; corner placement remains disabled and unsupported
     * state flags remain {@code false}.
     */
    private static NbtList<NbtMap> buildTraits(BlockType<?> blockType, float rotationOffset) {
        // Enable only placement states owned by this block type so advertised
        // traits cannot select states absent from its server definition.
        var placementDirection = NbtMap.builder()
                .putBoolean("cardinal_direction", blockType.hasProperty(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION))
                .putBoolean("facing_direction", blockType.hasProperty(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION))
                .putBoolean("corner_and_cardinal_direction", false)
                .build();
        var placementPosition = NbtMap.builder()
                .putBoolean("block_face", blockType.hasProperty(BlockPropertyTypes.MINECRAFT_BLOCK_FACE))
                .putBoolean("vertical_half", blockType.hasProperty(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF))
                .build();
        return new NbtList<>(NbtType.COMPOUND, List.of(
                NbtMap.builder()
                        .putString("name", "minecraft:placement_direction")
                        .putCompound("enabled_states", placementDirection)
                        .putFloat("y_rotation_offset", rotationOffset)
                        .putList("blocks_to_corner_with", NbtType.COMPOUND, List.of())
                        .build(),
                NbtMap.builder()
                        .putString("name", "minecraft:placement_position")
                        .putCompound("enabled_states", placementPosition)
                        .build()
        ));
    }

    /**
     * Builds client declarations for custom properties of the block type.
     * Properties in the minecraft namespace are omitted here because the
     * payload's placement traits separately describe the supported vanilla
     * placement states.
     */
    private static List<NbtMap> buildPropertyDefinitions(BlockType<?> blockType) {
        // Vanilla placement properties are represented by traits; declaring
        // them again as custom enum properties would duplicate those state
        // definitions.
        return blockType.getProperties().values().stream()
                .filter(property -> !property.getName().startsWith("minecraft:"))
                .map(Protocol::buildPropertyDefinition)
                .toList();
    }

    /**
     * Encodes one property's allowed values as an NBT enum list. Booleans use
     * byte values zero and one, integer bounds are inclusive, and Java enum
     * constants become locale-independent lowercase strings in their declared
     * valid-value order.
     */
    private static NbtMap buildPropertyDefinition(BlockPropertyType<?> property) {
        var builder = NbtMap.builder().putString("name", property.getName());
        // Match NBT element types to the property domain so boolean bytes,
        // inclusive integer values, and textual enums remain distinguishable.
        switch (property.getType()) {
            case BOOLEAN -> builder.putList("enum", NbtType.BYTE, List.of((byte) 0, (byte) 1));
            case INT -> {
                var intProperty = (IntPropertyType) property;
                var values = new ArrayList<Integer>();
                for (int value = intProperty.getMin(); value <= intProperty.getMax(); value++) {
                    values.add(value);
                }
                builder.putList("enum", NbtType.INT, values);
            }
            case ENUM -> builder.putList("enum", NbtType.STRING, property.getValidValues().stream()
                    .map(value -> ((Enum<?>) value).name().toLowerCase(Locale.ROOT))
                    .toList());
        }
        return builder.build();
    }

    /**
     * Builds encoded recipes and their matching server-side network-ID index
     * for this protocol. Source recipes are sorted by identifier before
     * positive IDs are allocated, making the ID assignment deterministic for
     * the same supported recipe set.
     *
     * <p> Payloads with network IDs enter the lookup index together with their
     * source recipe. Furnace encoders may omit unsupported ingredients or
     * return legacy payloads without network IDs; those cases do not consume an
     * indexed ID. Potion mixes remain outside this crafting index. Unsupported
     * recipe classes fail initialization, and {@code RecipeTable} validates the
     * completed association before the result is exposed.
     */
    protected RecipeTable createRecipeTable() {
        var encodedRecipes = new ArrayList<RecipeData>();
        var potionMixes = new ArrayList<PotionMixData>();
        var recipesByNetworkId = new Int2ObjectOpenHashMap<Recipe>();
        int nextNetworkId = 1;

        // Sort before assigning network IDs so registry iteration order cannot
        // associate the same client selection with different source recipes.
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
                            encodeItemDescriptorWithCount(smithing.getTemplate()),
                            encodeItemDescriptorWithCount(smithing.getBase()),
                            encodeItemDescriptorWithCount(smithing.getAddition()),
                            encodeItemStack(smithing.getOutput()),
                            "smithing_table",
                            networkId
                    ));
                }
                case SmithingTrimRecipe smithing -> {
                    int networkId = nextNetworkId++;
                    addRecipePayload(encodedRecipes, recipesByNetworkId, recipe, SmithingTrimRecipeData.of(
                            smithing.getIdentifier().toString(),
                            encodeItemDescriptorWithCount(smithing.getBase()),
                            encodeItemDescriptorWithCount(smithing.getAddition()),
                            encodeItemDescriptorWithCount(smithing.getTemplate()),
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
                // Furnace formats differ by version; allocate an ID only when
                // the returned payload actually participates in the network
                // lookup.
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

    /**
     * Encodes a legacy furnace payload only for an item-type ingredient. Other
     * descriptor types return {@code null} and are omitted from the recipe
     * table; this implementation does not use the candidate {@code networkId}
     * or preserve ingredient metadata in its furnace payload.
     */
    protected RecipeData encodeFurnaceRecipe(FurnaceRecipe recipe, int networkId) {
        // Legacy furnace payloads carry one concrete item runtime ID; tag
        // descriptors cannot supply that identity and are omitted from this
        // format.
        if (!(recipe.getIngredient() instanceof ItemTypeDescriptor ingredient)) {
            return null;
        }
        return FurnaceRecipeData.of(
                ingredient.getItemType().getRuntimeId(),
                encodeItemStack(recipe.getOutput()),
                recipe.getType().name().toLowerCase(Locale.ROOT)
        );
    }

    /**
     * Adds an encoded payload and, when applicable, records its originating
     * server recipe by network ID. Payloads without a network ID enter only the
     * encoded list, keeping legacy recipes separate from the lookup index.
     *
     * <p> Network IDs must be positive and unique within the index. Validation
     * failures throw after the payload has already been appended, so callers
     * must abandon the partially built local collections rather than publish
     * them. This helper does not roll back either collection; the enclosing
     * recipe construction publishes only after the completed
     * {@code RecipeTable} validates successfully.
     */
    private static void addRecipePayload(
            List<RecipeData> encodedRecipes,
            Int2ObjectOpenHashMap<Recipe> recipesByNetworkId,
            Recipe sourceRecipe,
            RecipeData encodedRecipe
    ) {
        // Keep the payload and source recipe paired when the payload exposes a
        // network ID; legacy payloads deliberately remain unindexed.
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

    /**
     * Creates an unlocking requirement containing the supplied context and an
     * empty ingredient list. The returned record retains that context without
     * ingredient descriptors, and callers cannot add unlock ingredients through
     * its unmodifiable list.
     */
    protected static RecipeUnlockingRequirement immutableRequirement(
            RecipeUnlockingRequirement.UnlockingContext context
    ) {
        return new RecipeUnlockingRequirement(context, List.of());
    }

    /**
     * Encodes recipe outputs in their existing array order using this
     * protocol's definitions. Missing item or block definitions fail
     * conversion, preventing recipes from silently referencing identities
     * absent from the advertised registries.
     */
    protected final List<ItemData> buildNetworkOutputs(ItemStack[] outputs) {
        return Arrays.stream(outputs).map(this::encodeItemStack).toList();
    }

    /**
     * Encodes a shaped recipe in row-major order while preserving empty cells.
     * Each explicit empty pattern character becomes the shared empty network
     * descriptor instead of disappearing from the ingredient list.
     *
     * <p> Preserving those positions keeps the flattened ingredients aligned
     * with the separately encoded pattern width and height. Nonempty keys
     * resolve through the recipe's descriptor map and this protocol's item
     * definitions. The returned list is unmodifiable, and invalid or
     * unsupported descriptors fail conversion rather than shifting the
     * remaining recipe slots.
     */
    private List<ItemDescriptorWithCount> buildNetworkIngredients(ShapedRecipe recipe) {
        var ingredients = new ArrayList<ItemDescriptorWithCount>();
        // Preserve each empty pattern cell during flattening so the client can
        // reconstruct the original layout from width and height.
        for (var row : recipe.getPattern()) {
            for (char key : row) {
                if (key == ShapedRecipe.EMPTY_KEY_CHAR) {
                    ingredients.add(ItemDescriptorWithCount.EMPTY);
                } else {
                    ingredients.add(encodeItemDescriptorWithCount(recipe.getKeys().get(key)));
                }
            }
        }
        return List.copyOf(ingredients);
    }

    /**
     * Encodes shapeless ingredients in their source array order using this
     * protocol's definitions. Each descriptor receives a count of one;
     * unsupported descriptors or unresolved item definitions fail instead of
     * being omitted.
     */
    private List<ItemDescriptorWithCount> buildNetworkIngredients(ShapelessRecipe recipe) {
        return Arrays.stream(recipe.getIngredients())
                .map(this::encodeItemDescriptorWithCount)
                .toList();
    }

    /**
     * Encodes an item-type or item-tag ingredient with a count of one. Item
     * types must resolve in this protocol's prepared definitions; unsupported
     * descriptor implementations and missing item definitions fail conversion.
     */
    protected final ItemDescriptorWithCount encodeItemDescriptorWithCount(
            ItemDescriptor descriptor
    ) {
        return NetworkHelper.toNetworkWithCount(descriptor, itemDefinitionRegistry);
    }

    /**
     * Encodes a stack using this protocol's prepared item and block registries.
     * Referenced definitions must exist in these registries; conversion
     * preserves the stack's applicable network metadata and fails on unresolved
     * identities.
     */
    protected final ItemData encodeItemStack(ItemStack itemStack) {
        return NetworkHelper.toNetwork(itemStack, itemDefinitionRegistry, blockDefinitionRegistry, blockPalette);
    }

    /**
     * Returns the codec recorded when this protocol was constructed.
     */
    public final BedrockCodec getCodec() {
        return codec;
    }

    /**
     * Returns the wire protocol version declared by this codec.
     */
    public final int getProtocolVersion() {
        return codec.getProtocolVersion();
    }

    /**
     * Returns the Minecraft version reported by the protocol codec.
     */
    public final String getMinecraftVersion() {
        return codec.getMinecraftVersion();
    }

    /**
     * Returns the client distribution selected when this protocol was
     * constructed.
     */
    public final ClientVariant getVariant() {
        return variant;
    }

    /**
     * Returns the shared payload bundle after successful protocol
     * initialization. Access before readiness throws
     * {@code IllegalStateException}; list containers are unmodifiable, but
     * contained payload objects retain the identities stored during
     * initialization.
     */
    public final ProtocolData getData() {
        ensureInitialized();
        return data;
    }

    /**
     * Returns the shared encoder created during successful protocol
     * initialization. Access before readiness throws
     * {@code IllegalStateException}, and the encoder retains the exact data
     * bundle returned by this protocol's data accessor.
     */
    public final PacketEncoder getEncoder() {
        ensureInitialized();
        return encoder;
    }

    /**
     * Creates a new connection-local processor holder from the frozen factory
     * registry. Initialization must have completed; each call instantiates
     * processors for that holder rather than sharing a previous connection's
     * holder.
     */
    public final PacketProcessorHolder createProcessorHolder() {
        ensureInitialized();
        return new PacketProcessorHolder(processorRegistry);
    }

    /**
     * Returns the prepared item-definition registry after successful
     * initialization. Access before readiness throws
     * {@code IllegalStateException}; sessions install this same registry on
     * their transport helpers to retain the negotiated item identities.
     */
    final DefinitionRegistry<ItemDefinition> getItemDefinitionRegistry() {
        ensureInitialized();
        return itemDefinitionRegistry;
    }

    /**
     * Returns the prepared block-definition registry after successful
     * initialization. Access before readiness throws
     * {@code IllegalStateException}; sessions install this registry alongside
     * item definitions so their helpers use the protocol's state hashes.
     */
    final DefinitionRegistry<BlockDefinition> getBlockDefinitionRegistry() {
        ensureInitialized();
        return blockDefinitionRegistry;
    }

    /**
     * Returns whether all protocol components have been successfully
     * initialized and published.
     */
    public final boolean isInitialized() {
        return initialized;
    }

    /**
     * Rejects access until the initialization completion flag has been
     * published. Reading the volatile flag also establishes visibility of the
     * component references assigned before a successful initialization
     * completed.
     */
    private void ensureInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Protocol has not been initialized: " + this);
        }
    }

    /**
     * Returns a diagnostic label containing the subtype, client distribution,
     * and wire version.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + variant.name() + ", v" + getProtocolVersion() + ']';
    }
}
