package org.allaymc.server.network;

import com.google.gson.annotations.SerializedName;
import lombok.experimental.UtilityClass;
import org.allaymc.api.dialog.Button;
import org.allaymc.api.dialog.ModelSettings;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.property.type.BooleanPropertyType;
import org.allaymc.api.entity.property.type.EnumPropertyType;
import org.allaymc.api.entity.property.type.FloatPropertyType;
import org.allaymc.api.entity.property.type.IntPropertyType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.HudElement;
import org.allaymc.api.primitiveshape.*;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.biome.BiomeTag;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.world.biome.CustomBiomeIdAllocator;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityProperties;
import org.cloudburstmc.protocol.bedrock.data.entity.FloatEntityProperty;
import org.cloudburstmc.protocol.bedrock.data.entity.IntEntityProperty;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantData;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import org.cloudburstmc.protocol.bedrock.definition.DefinitionRegistry;
import org.joml.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@code NetworkHelper} converts server domain values into Bedrock protocol data
 * and converts protocol vectors back into server vectors. Item conversions use
 * the target protocol's definition registries rather than assume that a server
 * runtime ID is valid for every negotiated version. Missing required definitions
 * fail before the resulting item can be submitted to a client.
 *
 * <p>
 * Entity properties use separate integer and floating-point index spaces while
 * traversing the entity type's property order. Enum ordinals and boolean numeric
 * values share the integer sequence, so that sequence must agree with the
 * corresponding advertised property definitions. Position conversions also
 * account for entity-specific vertical offsets when attaching primitive shapes,
 * allowing the client to place the shape relative to its displayed entity.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class NetworkHelper {

    // The block-update flags encode independent update effects for subchunk
    // update packets and can be combined in their protocol bitmask.
    /** Neighbor-update bit flag. */
    public static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    /** Client-network update bit. */
    public static final int BLOCK_UPDATE_NETWORK = 0b0010;
    /** Graphics-suppression update bit. */
    public static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    /** Priority-update bit flag. */
    public static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    // The chest-event constants identify the block-event category and its open
    // or closed payload values used by container animations.
    /** Chest-state event category. */
    public static final int BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE = 1;
    /** Open-chest event payload. */
    public static final int BLOCK_EVENT_DATA_OPEN_CHEST = 1;
    /** Closed-chest event payload. */
    public static final int BLOCK_EVENT_DATA_CLOSE_CHEST = 0;

    /**
     * {@code NETWORK_OFFSETS} maps entity identifiers to vertical network offsets
     * in blocks. Position encoders add these offsets, while attached-shape
     * conversion subtracts them; callers changing this shared mutable map affect
     * subsequent conversions and must coordinate concurrent access.
     */
    public static final Map<Identifier, Float> NETWORK_OFFSETS = new HashMap<>() {{
        // Initialize the built-in entity offsets used by position encoders.
        put(EntityId.PLAYER.getIdentifier(), 1.62f);
        put(EntityId.FALLING_BLOCK.getIdentifier(), 0.49f);
        put(EntityId.ITEM.getIdentifier(), 0.125f);
        put(EntityId.TNT.getIdentifier(), 0.49f);
        put(EntityId.FIREWORKS_ROCKET.getIdentifier(), 0.49f);
    }};

    /**
     * Returns protocol game-rule entries using each supplied rule's serialized name.
     */
    public static List<GameRuleData<?>> toNetwork(Map<GameRule, Object> gameRules) {
        return gameRules.entrySet().stream()
                .map(entry -> new GameRuleData<>(entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Encodes an entity's current property values into protocol index sequences.
     * Missing values use each property's declared default, and the entity type's
     * iteration order determines the indexes within each numeric family.
     *
     * <p>
     * Enum ordinals, integers, and booleans advance one shared integer index;
     * floating-point properties advance a separate index. The advertised
     * definitions must use that same ordering so a client associates each value
     * with the intended property. Boolean values become {@code 1} or {@code 0}
     * without consuming a floating-point index.
     */
    public static EntityProperties toNetworkProperties(Entity entity) {
        var result = new EntityProperties();
        var propertyValues = entity.getPropertyValues();

        int intIndex = 0;
        int floatIndex = 0;
        // Match definition traversal while keeping integer and float indexes
        // separate, so mixed property kinds retain their client-visible identities.
        for (var propType : entity.getEntityType().getProperties().values()) {
            switch (propType) {
                case EnumPropertyType<?> enumProp -> {
                    var value = (Enum<?>) propertyValues.getOrDefault(enumProp, enumProp.getDefaultValue());
                    result.intProperties().add(new IntEntityProperty(intIndex++, value.ordinal()));
                }
                case IntPropertyType intProp -> {
                    var value = (int) propertyValues.getOrDefault(intProp, intProp.getDefaultValue());
                    result.intProperties().add(new IntEntityProperty(intIndex++, value));
                }
                case BooleanPropertyType boolProp -> {
                    var value = (boolean) propertyValues.getOrDefault(boolProp, boolProp.getDefaultValue());
                    result.intProperties().add(new IntEntityProperty(intIndex++, value ? 1 : 0));
                }
                case FloatPropertyType floatProp -> {
                    var value = (float) propertyValues.getOrDefault(floatProp, floatProp.getDefaultValue());
                    result.floatProperties().add(new FloatEntityProperty(floatIndex++, value));
                }
            }
        }

        return result;
    }

    /**
     * Encodes an ingredient using definitions from the target protocol.
     * Item ingredients retain their metadata, tag ingredients retain their tag
     * name, and unsupported descriptor kinds throw {@code IllegalArgumentException}
     * instead of selecting an approximate ingredient.
     *
     * @param descriptor the server-side ingredient
     * @param itemDefinitions the target protocol's item definitions
     * @return the network ingredient
     * @throws IllegalStateException if a referenced item has no definition in the target protocol
     */
    public static org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork(
            ItemDescriptor descriptor,
            DefinitionRegistry<ItemDefinition> itemDefinitions
    ) {
        return switch (descriptor) {
            case ItemTypeDescriptor type -> new DefaultDescriptor(
                    getItemDefinition(type.getItemType(), itemDefinitions),
                    type.getMeta()
            );
            case ItemTagDescriptor tag -> new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemTagDescriptor(tag.getItemTag().name());
            default -> throw new IllegalArgumentException("Unexpected value: " + descriptor);
        };
    }

    /**
     * Encodes a single-count ingredient using the target protocol's item definitions.
     * Descriptor rejection and missing item definitions propagate from
     * {@code toNetwork}; the encoded count is always {@code 1}, independently
     * of any inventory stack quantity.
     *
     * @param descriptor the server-side ingredient
     * @param itemDefinitions the target protocol's item definitions
     * @return the counted network ingredient
     */
    public static org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount toNetworkWithCount(
            ItemDescriptor descriptor,
            DefinitionRegistry<ItemDefinition> itemDefinitions
    ) {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount(
                toNetwork(descriptor, itemDefinitions),
                1
        );
    }

    /**
     * Encodes biome climate values and tags for the client definition table.
     * Built-in biome IDs are omitted from the optional numeric-ID field; custom
     * biomes retain their allocated IDs so clients can associate those definitions.
     */
    public static BiomeDefinitionData toNetwork(BiomeType biome) {
        var data = biome.getBiomeData();
        var tags = data.tags().stream().map(BiomeTag::name).toList();
        if (biome.getId() < CustomBiomeIdAllocator.CUSTOM_BIOME_ID_START) {
            // The optional numeric ID identifies custom definitions. Built-in
            // biomes keep that field absent instead of entering the custom-ID path.
            return new BiomeDefinitionData(
                    null, data.temperature(), data.downfall(), data.redSporeDensity(),
                    data.blueSporeDensity(), data.ashDensity(), data.whiteAshDensity(), data.foliageSnow(),
                    data.depth(), data.scale(), data.mapWaterColor(), data.rain(), tags, null
            );
        } else {
            return new BiomeDefinitionData(
                    biome.getId(), data.temperature(), data.downfall(), data.redSporeDensity(),
                    data.blueSporeDensity(), data.ashDensity(), data.whiteAshDensity(), data.foliageSnow(),
                    data.depth(), data.scale(), data.mapWaterColor(), data.rain(), tags, null
            );
        }
    }

    /**
     * Returns protocol enchantment data containing the instance's numeric ID and level.
     */
    public static EnchantData toNetwork(EnchantmentInstance instance) {
        return new EnchantData(instance.getType().getId(), instance.getLevel());
    }

    /**
     * Encodes an enchanting option using the pair's assigned network identity.
     * The option's enchantments occupy the primary list, while the protocol's
     * additional enchantment lists remain empty and {@code primarySlot} is
     * {@code 0}.
     */
    public static EnchantOptionData toNetwork(Pair<Integer, EnchantOption> pair) {
        var networkId = pair.left();
        var option = pair.right();
        // Populate the first enchantment group and select primary slot zero,
        // matching the single group supplied by the server's option representation.
        return new EnchantOptionData(
                option.requiredXpLevel(), 0,
                option.enchantments().stream().map(NetworkHelper::toNetwork).toList(),
                List.of(), List.of(), option.optionName(), networkId
        );
    }

    /**
     * Returns whether network item data of the given item type carries its block. Like BDS, a vanilla item does so only
     * when it is a block item: doors, hanging signs, cauldrons and the like place a block, but the client draws them
     * from their item texture and expects no block. Custom items keep their block.
     *
     * @param itemType the item type
     * @return {@code true} if the block is sent with the item
     */
    static boolean hasNetworkBlock(ItemType<?> itemType) {
        if (!(itemType instanceof AllayItemType<?> allayItemType) || allayItemType.isCustomItem()) {
            return true;
        }
        var itemId = ItemId.fromIdentifier(itemType.getIdentifier());
        var definition = itemId == null ? null : InternalRegistries.VANILLA_ITEM_DEFINITIONS.get(itemId);
        return definition != null && definition.isBlockItem();
    }

    /**
     * Encodes an item stack using definitions from the target protocol.
     * Air uses the protocol air payload with the target item definition.
     * Other stacks retain count, metadata, extra data, and network identity;
     * missing required item or block definitions fail instead of selecting a
     * different registered value.
     *
     * @param itemStack the server-side item stack
     * @param itemDefinitions the target protocol's item definitions
     * @param blockDefinitions the target protocol's block definitions
     * @return the network item data
     * @throws IllegalStateException if a required definition is missing
     */
    public static ItemData toNetwork(
            ItemStack itemStack,
            DefinitionRegistry<ItemDefinition> itemDefinitions,
            DefinitionRegistry<BlockDefinition> blockDefinitions
    ) {
        var itemType = itemStack.getItemType();
        if (itemType == ItemTypes.AIR) {
            return ItemData.AIR.toBuilder()
                    .definition(getItemDefinition(itemType, itemDefinitions))
                    .build();
        }

        var blockState = hasNetworkBlock(itemType) ? itemStack.toBlockState() : null;
        var blockDefinition = blockState == null ? null : blockDefinitions.getDefinition(blockState.blockStateHash());
        if (blockState != null && blockDefinition == null) {
            throw new IllegalStateException("Missing block definition for state " + blockState.blockStateHash());
        }
        var extraTag = itemStack.saveExtraTag();
        return ItemData
                .builder()
                .definition(getItemDefinition(itemType, itemDefinitions))
                .blockDefinition(blockDefinition)
                .count(itemStack.getCount())
                .damage(itemStack.getMeta())
                // Like BDS, an item without extra data is sent without NBT.
                // With an empty compound, 1.26.50 clients draw no held model
                // for flat block items such as ladders, iron bars, and string.
                .tag(extraTag.isEmpty() ? null : extraTag)
                .usingNetId(itemStack.hasUniqueId())
                .netId(itemStack.getUniqueId())
                .build();

    }

    /**
     * Encodes item stacks in input order using the target protocol's definitions.
     * The returned list is unmodifiable, and an encoding failure terminates the
     * conversion rather than returning a partially populated result.
     *
     * @param items the server-side item stacks
     * @param itemDefinitions the target protocol's item definitions
     * @param blockDefinitions the target protocol's block definitions
     * @return the encoded item data in input order
     */
    public static List<ItemData> toNetwork(
            List<ItemStack> items,
            DefinitionRegistry<ItemDefinition> itemDefinitions,
            DefinitionRegistry<BlockDefinition> blockDefinitions
    ) {
        return items.stream().map(item -> toNetwork(item, itemDefinitions, blockDefinitions)).toList();
    }

    /**
     * Resolves an item type in the target protocol's definition registry.
     * A missing definition throws {@code IllegalStateException} with the item
     * identifier so callers cannot silently encode an unrelated or absent item.
     */
    private static ItemDefinition getItemDefinition(
            ItemType<?> itemType,
            DefinitionRegistry<ItemDefinition> itemDefinitions
    ) {
        var definition = itemDefinitions.getDefinition(itemType.getRuntimeId());
        if (definition == null) {
            throw new IllegalStateException("Missing item definition for " + itemType.getIdentifier());
        }
        return definition;
    }

    /**
     * Copies protocol integer coordinates into a new server vector.
     * A {@code null} input returns {@code null}, preserving absent optional
     * positions rather than creating a vector with zero-valued coordinates.
     */
    public static Vector3ic fromNetwork(org.cloudburstmc.math.vector.Vector3i vec) {
        if (vec == null) {
            return null;
        }

        return new Vector3i(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Copies protocol double-precision coordinates into a new server vector.
     * A {@code null} input returns {@code null}, preserving absent optional
     * positions rather than creating a vector with zero-valued coordinates.
     */
    public static Vector3dc fromNetwork(org.cloudburstmc.math.vector.Vector3d vec) {
        if (vec == null) {
            return null;
        }

        return new Vector3d(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Copies protocol floating-point coordinates into a new server vector.
     * A {@code null} input returns {@code null}, preserving absent optional
     * positions rather than creating a vector with zero-valued coordinates.
     */
    public static Vector3fc fromNetwork(org.cloudburstmc.math.vector.Vector3f vec) {
        if (vec == null) {
            return null;
        }

        return new Vector3f(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Copies server integer coordinates into a protocol vector value.
     * A {@code null} input returns {@code null}, preserving absent optional
     * positions rather than encoding a vector with zero-valued coordinates.
     */
    public static org.cloudburstmc.math.vector.Vector3i toNetwork(Vector3ic vec) {
        if (vec == null) {
            return null;
        }

        return org.cloudburstmc.math.vector.Vector3i.from(vec.x(), vec.y(), vec.z());
    }

    /**
     * Copies server double-precision coordinates into a protocol vector value.
     * A {@code null} input returns {@code null}, preserving absent optional
     * positions rather than encoding a vector with zero-valued coordinates.
     */
    public static org.cloudburstmc.math.vector.Vector3d toNetwork(Vector3dc vec) {
        if (vec == null) {
            return null;
        }

        return org.cloudburstmc.math.vector.Vector3d.from(vec.x(), vec.y(), vec.z());
    }

    /**
     * Copies server floating-point coordinates into a protocol vector value.
     * A {@code null} input returns {@code null}, preserving absent optional
     * positions rather than encoding a vector with zero-valued coordinates.
     */
    public static org.cloudburstmc.math.vector.Vector3f toNetwork(Vector3fc vec) {
        if (vec == null) {
            return null;
        }

        return org.cloudburstmc.math.vector.Vector3f.from(vec.x(), vec.y(), vec.z());
    }

    /**
     * Returns the protocol game type corresponding to the supplied server mode.
     */
    public static GameType toNetwork(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> GameType.SURVIVAL;
            case CREATIVE -> GameType.CREATIVE;
            case ADVENTURE -> GameType.ADVENTURE;
            case SPECTATOR -> GameType.SPECTATOR;
        };
    }

    /**
     * Returns the server mode represented by an ordinary protocol game type.
     * Protocol-only values outside the four supported player modes return
     * {@code null}, so callers must handle them before requiring a concrete mode.
     */
    public static GameMode fromNetwork(GameType gameType) {
        return switch (gameType) {
            case SURVIVAL -> GameMode.SURVIVAL;
            case CREATIVE -> GameMode.CREATIVE;
            case ADVENTURE -> GameMode.ADVENTURE;
            case SPECTATOR -> GameMode.SPECTATOR;
            default -> null;
        };
    }

    /**
     * Encodes a supported primitive shape for its dimension and optional attachment.
     * A non-{@code null} {@code attachedEntity} supplies the runtime ID and
     * subtracts its network vertical offset from the position; unsupported shape
     * implementations throw {@code IllegalStateException}.
     * Identifiers absent from {@code NETWORK_OFFSETS} use zero vertical adjustment.
     */
    public static org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveShape toNetwork(PrimitiveShape shape, int dimension, Entity attachedEntity) {
        var entityId = attachedEntity != null ? attachedEntity.getRuntimeId() : null;
        var networkPos = toNetwork(shape.getPosition());
        if (attachedEntity != null) {
            // Entities absent from NETWORK_OFFSETS need no adjustment; compensate
            // for known network heights so attached shapes keep their intended origin.
            networkPos = networkPos.add(0, -NETWORK_OFFSETS.getOrDefault(attachedEntity.getEntityType().getIdentifier(), 0.0f), 0);
        }
        return switch (shape) {
            case PrimitiveArrow arrow -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveArrow(
                    arrow.getId(), dimension, networkPos, arrow.getScale(), toNetwork(arrow.getRotation()),
                    arrow.getTotalTimeLeft(), arrow.getColor(), arrow.getMaximumRenderDistance(), toNetwork(arrow.getEndPosition()), arrow.getArrowHeadLength(),
                    arrow.getArrowHeadRadius(), arrow.getArrowHeadSegments(), entityId
            );
            case PrimitiveBox box -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveBox(
                    box.getId(), dimension, networkPos, box.getScale(), toNetwork(box.getRotation()),
                    box.getTotalTimeLeft(), box.getColor(), box.getMaximumRenderDistance(), toNetwork(box.getBoxBounds()), entityId
            );
            case PrimitiveCircle circle -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveCircle(
                    circle.getId(), dimension, networkPos, circle.getScale(), toNetwork(circle.getRotation()),
                    circle.getTotalTimeLeft(), circle.getColor(), circle.getMaximumRenderDistance(), circle.getSegments(), entityId
            );
            case PrimitiveLine line -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveLine(
                    line.getId(), dimension, networkPos, line.getScale(), toNetwork(line.getRotation()),
                    line.getTotalTimeLeft(), line.getColor(), line.getMaximumRenderDistance(), toNetwork(line.getEndPosition()), entityId
            );
            case PrimitiveSphere sphere -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveSphere(
                    sphere.getId(), dimension, networkPos, sphere.getScale(), toNetwork(sphere.getRotation()),
                    sphere.getTotalTimeLeft(), sphere.getColor(), sphere.getMaximumRenderDistance(), sphere.getSegments(), entityId
            );
            case PrimitiveText text -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveText(
                    text.getId(), dimension, networkPos, text.getScale(), toNetwork(text.getRotation()),
                    text.getTotalTimeLeft(), text.getColor(), text.getText(), text.isUseRotation(), text.getBackgroundColor(),
                    text.isDepthTest(), text.isShowBackface(), text.isShowTextBackface(), text.getMaximumRenderDistance(), entityId
            );
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };
    }

    /**
     * Returns a protocol removal notice containing only the shape's identity.
     */
    public static org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveShape toNetworkRemovalNotice(PrimitiveShape shape) {
        return new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveShape(shape.getId());
    }

    /**
     * {@code PortraitOffsets} carries the coordinate arrays serialized for dialog portraits.
     * The generated constructor and accessors retain these arrays without copying,
     * so callers share any subsequent changes to their components.
     *
     * @param translate three translation components
     * @param rotate three rotation components
     * @param scale three scale components
     */
    public record PortraitOffsets(double[] translate, double[] rotate, double[] scale) {
    }

    /**
     * Converts dialog model settings into arrays used by portrait serialization.
     * Translation is multiplied by {@code -32} for the portrait coordinate scale,
     * while rotation and scale components are copied into fresh arrays unchanged.
     */
    public static PortraitOffsets toNetwork(ModelSettings settings) {
        // Portrait translation uses an inverted scale of thirty-two units per block.
        var translate = settings.offset().mul(-32, new Vector3d());
        var rotate = settings.rotation();
        var scale = settings.scale();
        return new PortraitOffsets(
                toDoubleArray(translate),
                toDoubleArray(rotate),
                toDoubleArray(scale)
        );
    }

    /**
     * Returns a new array containing the vector's three double-precision coordinates.
     */
    private static double[] toDoubleArray(Vector3dc vec) {
        return new double[]{vec.x(), vec.y(), vec.z()};
    }

    /**
     * {@code NetworkDialogButton} carries the client-visible dialog button JSON fields.
     *
     * @param buttonName displayed button label
     * @param text command payload text
     * @param mode activation mode value
     * @param type button action category
     */
    public record NetworkDialogButton(
            @SerializedName("button_name")
            String buttonName,
            String text,
            int mode,
            int type
    ) {
        /**
         * Creates the client representation of a server-handled dialog button.
         * The label comes from {@code button}; empty command text, click activation
         * {@code 0}, and command type {@code 1} let the client expose the button
         * without embedding a command payload.
         */
        public NetworkDialogButton(Button button) {
            this(button.getText(), "", 0, 1);
        }
    }

    /**
     * Returns client dialog-button records in the supplied buttons' iteration order.
     * The returned list is unmodifiable, and each record captures its button's
     * current text when this conversion runs.
     */
    public static List<NetworkDialogButton> toNetworkDialogButtons(List<Button> buttons) {
        return buttons.stream().map(NetworkDialogButton::new).toList();
    }

    /**
     * Returns the protocol HUD element corresponding to the server's element name.
     */
    public org.cloudburstmc.protocol.bedrock.data.HudElement toNetwork(HudElement element) {
        return switch (element) {
            case PAPER_DOLL -> org.cloudburstmc.protocol.bedrock.data.HudElement.PAPER_DOLL;
            case ARMOR -> org.cloudburstmc.protocol.bedrock.data.HudElement.ARMOR;
            case TOOL_TIPS -> org.cloudburstmc.protocol.bedrock.data.HudElement.TOOL_TIPS;
            case TOUCH_CONTROLS -> org.cloudburstmc.protocol.bedrock.data.HudElement.TOUCH_CONTROLS;
            case CROSSHAIR -> org.cloudburstmc.protocol.bedrock.data.HudElement.CROSSHAIR;
            case HOTBAR -> org.cloudburstmc.protocol.bedrock.data.HudElement.HOTBAR;
            case HEALTH -> org.cloudburstmc.protocol.bedrock.data.HudElement.HEALTH;
            case EXPERIENCE_BAR -> org.cloudburstmc.protocol.bedrock.data.HudElement.PROGRESS_BAR;
            case FOOD_BAR -> org.cloudburstmc.protocol.bedrock.data.HudElement.FOOD_BAR;
            case AIR_SUPPLY_BAR -> org.cloudburstmc.protocol.bedrock.data.HudElement.AIR_BUBBLES_BAR;
            case VEHICLE_HEALTH -> org.cloudburstmc.protocol.bedrock.data.HudElement.VEHICLE_HEALTH;
            case EFFECTS -> org.cloudburstmc.protocol.bedrock.data.HudElement.EFFECTS_BAR;
            case ITEM_TEXT -> org.cloudburstmc.protocol.bedrock.data.HudElement.ITEM_TEXT_POPUP;
        };
    }
}
