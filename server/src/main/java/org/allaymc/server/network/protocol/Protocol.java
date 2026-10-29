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
 * Defines one Bedrock protocol and its immutable, version-specific runtime data.
 *
 * <p>Construction records only the protocol identity. Call {@link #initialize()} after the
 * complete subtype has been constructed and before registering or using the protocol.</p>
 */
public abstract class Protocol {
    private final BedrockCodec codec;
    private final ClientVariant variant;

    private PacketProcessorRegistry processorRegistry;
    private ProtocolData data;
    private PacketEncoder encoder;
    private DefinitionRegistry<ItemDefinition> itemDefinitionRegistry;
    private DefinitionRegistry<BlockDefinition> blockDefinitionRegistry;
    private volatile boolean initialized;

    /**
     * Creates a protocol for the given codec and client variant.
     *
     * @param codec the packet codec that identifies the protocol version
     * @param variant the client family supported by this protocol
     */
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

        var encodedItemDefinitions = createItemDefinitions();
        var itemDefinitions = SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(encodedItemDefinitions)
                .build();
        var encodedBlockDefinitions = createBlockDefinitions();
        var blockDefinitions = SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(encodedBlockDefinitions)
                .build();

        // Dependent payloads must resolve definitions from these exact per-protocol registries.
        this.itemDefinitionRegistry = itemDefinitions;
        this.blockDefinitionRegistry = blockDefinitions;
        try {
            var protocolData = new ProtocolData(
                    encodedItemDefinitions,
                    encodedBlockDefinitions,
                    createCreativeGroups(),
                    createCreativeItems(),
                    createCustomBlockProperties(),
                    createRecipeTable()
            );
            var packetEncoder = Objects.requireNonNull(createEncoder(protocolData), "createEncoder returned null");
            if (packetEncoder.getData() != protocolData) {
                throw new IllegalStateException("Packet encoder does not reference the protocol's data instance: " + this);
            }
            this.processorRegistry = registry;
            this.data = protocolData;
            this.encoder = packetEncoder;
            this.initialized = true;
        } catch (RuntimeException | Error exception) {
            this.itemDefinitionRegistry = null;
            this.blockDefinitionRegistry = null;
            throw exception;
        }
    }

    /**
     * Registers the packet processors available to connections using this protocol.
     *
     * @param registry the mutable registry populated during initialization
     */
    protected abstract void registerProcessors(PacketProcessorRegistry registry);

    /**
     * Creates the packet encoder backed by this protocol's initialized data.
     *
     * @param data the immutable protocol data
     * @return the packet encoder
     */
    protected abstract PacketEncoder createEncoder(ProtocolData data);

    /**
     * Builds the item definitions exposed to this protocol.
     *
     * @return item definitions ordered by runtime ID
     */
    protected List<ItemDefinition> createItemDefinitions() {
        return Registries.ITEMS.getContent().values().stream()
                .sorted(Comparator.comparingInt(org.allaymc.api.item.type.ItemType::getRuntimeId))
                .map(itemType -> encodeItemDefinition((AllayItemType<?>) itemType))
                .toList();
    }

    private ItemDefinition encodeItemDefinition(AllayItemType<?> itemType) {
        if (!itemType.isCustomItem()) {
            var itemId = ItemId.fromIdentifier(itemType.getIdentifier());
            var vanillaDefinition = InternalRegistries.VANILLA_ITEM_DEFINITIONS.get(itemId);
            if (vanillaDefinition != null) {
                return new SimpleItemDefinition(
                        itemType.getIdentifier().toString(),
                        itemType.getRuntimeId(),
                        vanillaDefinition.version(),
                        vanillaDefinition.componentBased(),
                        vanillaDefinition.components()
                );
            }
        }

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

    private static List<Float> vectorValues(Vector3fc vector) {
        return List.of(vector.x(), vector.y(), vector.z());
    }

    /**
     * Builds the block definitions exposed to this protocol.
     *
     * @return block definitions ordered by runtime ID
     */
    protected List<BlockDefinition> createBlockDefinitions() {
        return Registries.BLOCKS.getContent().values().stream()
                .flatMap(blockType -> blockType.getAllStates().stream())
                .map(blockState -> (BlockDefinition) blockState::blockStateHash)
                .toList();
    }

    /**
     * Builds the creative inventory groups for this protocol.
     *
     * @return creative groups in network order
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
     * Builds the creative inventory entries for this protocol.
     *
     * @return creative items in network order
     */
    protected List<CreativeItemData> createCreativeItems() {
        return Registries.CREATIVE_ITEMS.getEntries().stream()
                .map(entry -> new CreativeItemData(
                        encodeItemStack(entry.itemStack()),
                        entry.index() + 1,
                        entry.group().getIndex()
                ))
                .toList();
    }

    /**
     * Builds the custom block properties advertised to this protocol.
     *
     * @return custom block properties ordered by block runtime ID
     */
    protected List<BlockPropertyData> createCustomBlockProperties() {
        var properties = new ArrayList<BlockPropertyData>();
        var blockTypes = Registries.BLOCKS.getContent().values().stream()
                .map(blockType -> (AllayBlockType<?>) blockType)
                .filter(blockType -> blockType.getCustomBlockDefinition() != null)
                .sorted(Comparator.comparing(blockType -> blockType.getIdentifier().toString()))
                .toList();
        int customBlockId = 10000;
        for (var blockType : blockTypes) {
            var definition = blockType.getCustomBlockDefinition();
            var data = encodeCustomBlockDefinition(blockType, definition, customBlockId++);
            properties.add(new BlockPropertyData(blockType.getIdentifier().toString(), data));
        }
        return List.copyOf(properties);
    }

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

            // Raw state components intentionally precede server-derived physical properties.
            components.putAll(stateDefinition.rawComponents());
            components.putCompound("minecraft:collision_box", encodeCollisionBox(stateData.collisionShape()));
            components.putCompound("minecraft:selection_box", encodeSelectionBox(stateData.shape()));
            components.putCompound("minecraft:light_emission", NbtMap.builder()
                    .putByte("emission", (byte) stateData.lightEmission())
                    .build());
            components.putCompound("minecraft:light_dampening", NbtMap.builder()
                    .putByte("lightLevel", (byte) stateData.lightDampening())
                    .build());
            components.putCompound("minecraft:friction", NbtMap.builder()
                    .putFloat("value", Math.clamp(1 - stateData.friction(), 0.0f, 0.9f))
                    .build());
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
        if (geometry.uvLockAll()) {
            builder.putBoolean("uv_lock", true);
        } else if (geometry.uvLockBones() != null && !geometry.uvLockBones().isEmpty()) {
            builder.putList("uv_lock", NbtType.STRING, geometry.uvLockBones());
        } else {
            builder.putBoolean("uv_lock", false);
        }
        return builder.build();
    }

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

    private static int normalizedQuarterTurns(int degrees) {
        return (((degrees % 360) + 360) % 360) / 90;
    }

    /**
     * Encodes material flags in the packed format used by modern protocols.
     */
    protected NbtMap encodeMaterialInstance(
            CustomBlockStateDefinition.MaterialInstance material,
            TintMethod tintMethod
    ) {
        return encodePackedMaterialInstance(material, tintMethod);
    }

    /**
     * Encodes a material instance using the packed boolean layout shared by modern protocols.
     *
     * @param material the material to encode
     * @param tintMethod the tint applied by the client
     * @return the encoded material component
     */
    protected final NbtMap encodePackedMaterialInstance(
            CustomBlockStateDefinition.MaterialInstance material,
            TintMethod tintMethod
    ) {
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
     * Encodes collision geometry in the multi-box format used by modern protocols.
     */
    protected NbtMap encodeCollisionBox(VoxelShape shape) {
        return encodeBoxListCollision(shape);
    }

    /**
     * Encodes every solid in a voxel shape in the client's sixteenth-block coordinate space.
     *
     * @param shape the shape to encode, or {@code null} to disable collision
     * @return the encoded collision component
     */
    protected final NbtMap encodeBoxListCollision(VoxelShape shape) {
        if (shape == null || shape.getSolids().isEmpty()) {
            return NbtMap.builder().putBoolean("enabled", false).build();
        }
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
     * Encodes the union of a voxel shape as the single box required by legacy protocols.
     *
     * @param shape the shape to encode, or {@code null} to disable the box
     * @return the encoded box component
     */
    protected static NbtMap encodeMergedBox(VoxelShape shape) {
        if (shape == null || shape.getSolids().isEmpty()) {
            return NbtMap.builder().putBoolean("enabled", false).build();
        }
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

    private static NbtMap encodeSelectionBox(VoxelShape shape) {
        return encodeMergedBox(shape);
    }

    private static NbtMap defaultMenuCategory() {
        return NbtMap.builder()
                .putString("category", "construction")
                .putString("group", "")
                .putByte("is_hidden_in_commands", (byte) 0)
                .build();
    }

    private static NbtList<NbtMap> buildTraits(BlockType<?> blockType, float rotationOffset) {
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

    private static List<NbtMap> buildPropertyDefinitions(BlockType<?> blockType) {
        return blockType.getProperties().values().stream()
                .filter(property -> !property.getName().startsWith("minecraft:"))
                .map(Protocol::buildPropertyDefinition)
                .toList();
    }

    private static NbtMap buildPropertyDefinition(BlockPropertyType<?> property) {
        var builder = NbtMap.builder().putString("name", property.getName());
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
     * Builds this protocol's recipe payloads and matching server-side network ID index.
     *
     * <p>Recipes are sorted by identifier before positive network IDs are assigned so the
     * mapping remains deterministic for a fixed registry.</p>
     *
     * @return the immutable recipe table
     */
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
     * Encodes a furnace recipe when its ingredient can be represented by this protocol.
     *
     * @param recipe the source recipe
     * @param networkId the candidate network ID
     * @return the encoded recipe, or {@code null} when the ingredient is unsupported
     */
    protected RecipeData encodeFurnaceRecipe(FurnaceRecipe recipe, int networkId) {
        if (!(recipe.getIngredient() instanceof ItemTypeDescriptor ingredient)) {
            return null;
        }
        return FurnaceRecipeData.of(
                ingredient.getItemType().getRuntimeId(),
                encodeItemStack(recipe.getOutput()),
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

    /**
     * Creates an unlocking requirement with an immutable, empty ingredient list.
     *
     * @param context the unlocking context
     * @return the immutable requirement
     */
    protected static RecipeUnlockingRequirement immutableRequirement(
            RecipeUnlockingRequirement.UnlockingContext context
    ) {
        return new RecipeUnlockingRequirement(context, List.of());
    }

    /**
     * Encodes recipe outputs against this protocol's definition registries.
     *
     * @param outputs the server-side outputs
     * @return the encoded outputs
     */
    protected final List<ItemData> buildNetworkOutputs(ItemStack[] outputs) {
        return Arrays.stream(outputs).map(this::encodeItemStack).toList();
    }

    private List<ItemDescriptorWithCount> buildNetworkIngredients(ShapedRecipe recipe) {
        var ingredients = new ArrayList<ItemDescriptorWithCount>();
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

    private List<ItemDescriptorWithCount> buildNetworkIngredients(ShapelessRecipe recipe) {
        return Arrays.stream(recipe.getIngredients())
                .map(this::encodeItemDescriptorWithCount)
                .toList();
    }

    /**
     * Encodes an ingredient descriptor against this protocol's item definitions.
     *
     * @param descriptor the descriptor to encode
     * @return the network descriptor
     */
    protected final ItemDescriptorWithCount encodeItemDescriptorWithCount(
            ItemDescriptor descriptor
    ) {
        return NetworkHelper.toNetworkWithCount(descriptor, itemDefinitionRegistry);
    }

    /**
     * Encodes an item stack against this protocol's item and block definitions.
     *
     * @param itemStack the stack to encode
     * @return the network item data
     */
    protected final ItemData encodeItemStack(ItemStack itemStack) {
        return NetworkHelper.toNetwork(itemStack, itemDefinitionRegistry, blockDefinitionRegistry);
    }

    /**
     * Returns the codec used by this protocol.
     *
     * @return the protocol codec
     */
    public final BedrockCodec getCodec() {
        return codec;
    }

    /**
     * Returns the numeric Bedrock protocol version.
     *
     * @return the protocol version
     */
    public final int getProtocolVersion() {
        return codec.getProtocolVersion();
    }

    /**
     * Returns the Minecraft version reported by the codec.
     *
     * @return the Minecraft version
     */
    public final String getMinecraftVersion() {
        return codec.getMinecraftVersion();
    }

    /**
     * Returns the client family supported by this protocol.
     *
     * @return the client variant
     */
    public final ClientVariant getVariant() {
        return variant;
    }

    /**
     * Returns the immutable data produced during initialization.
     *
     * @return the protocol data
     * @throws IllegalStateException if this protocol has not been initialized
     */
    public final ProtocolData getData() {
        ensureInitialized();
        return data;
    }

    /**
     * Returns the packet encoder created during initialization.
     *
     * @return the packet encoder
     * @throws IllegalStateException if this protocol has not been initialized
     */
    public final PacketEncoder getEncoder() {
        ensureInitialized();
        return encoder;
    }

    /**
     * Creates connection-local processor state from this protocol's frozen registry.
     *
     * @return a new processor holder
     * @throws IllegalStateException if this protocol has not been initialized
     */
    public final PacketProcessorHolder createProcessorHolder() {
        ensureInitialized();
        return new PacketProcessorHolder(processorRegistry);
    }

    final DefinitionRegistry<ItemDefinition> getItemDefinitionRegistry() {
        ensureInitialized();
        return itemDefinitionRegistry;
    }

    final DefinitionRegistry<BlockDefinition> getBlockDefinitionRegistry() {
        ensureInitialized();
        return blockDefinitionRegistry;
    }

    /**
     * Checks whether one-time initialization completed successfully.
     *
     * @return {@code true} when this protocol is ready for use
     */
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
