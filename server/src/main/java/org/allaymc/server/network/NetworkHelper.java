package org.allaymc.server.network;

import com.google.gson.annotations.SerializedName;
import lombok.experimental.UtilityClass;
import org.allaymc.api.debugshape.*;
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
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.biome.BiomeTag;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.world.biome.CustomBiomeIdAllocator;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityProperties;
import org.cloudburstmc.protocol.bedrock.data.entity.FloatEntityProperty;
import org.cloudburstmc.protocol.bedrock.data.entity.IntEntityProperty;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantData;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import org.joml.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Helper class to convert types between allay and the protocol library.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class NetworkHelper {

    // Constants used in UpdateSubChunkBlocksPacket
    public static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    public static final int BLOCK_UPDATE_NETWORK = 0b0010;
    public static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    public static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    // Constants used in BlockEventPacket
    public static final int BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE = 1;
    public static final int BLOCK_EVENT_DATA_OPEN_CHEST = 1;
    public static final int BLOCK_EVENT_DATA_CLOSE_CHEST = 0;

    /**
     * A map which contains the network offset of some entities. The network offset is the additional offset in
     * y coordinate when sent over network. This is mostly the case for older entities such as player and TNT.
     */
    public static final Map<Identifier, Float> NETWORK_OFFSETS = new HashMap<>() {{
        put(EntityId.PLAYER.getIdentifier(), 1.62f);
        put(EntityId.FALLING_BLOCK.getIdentifier(), 0.49f);
        put(EntityId.ITEM.getIdentifier(), 0.125f);
        put(EntityId.TNT.getIdentifier(), 0.49f);
        put(EntityId.FIREWORKS_ROCKET.getIdentifier(), 0.49f);
    }};

    public static List<GameRuleData<?>> toNetwork(Map<GameRule, Object> gameRules) {
        return gameRules.entrySet().stream()
                .map(entry -> new GameRuleData<>(entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static EntityProperties toNetworkProperties(Entity entity) {
        var result = new EntityProperties();
        var propertyValues = entity.getPropertyValues();

        int intIndex = 0;
        int floatIndex = 0;
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

    public static org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptor toNetwork(ItemDescriptor descriptor) {
        return switch (descriptor) {
            case ItemTypeDescriptor type -> new DefaultDescriptor(toNetwork(type.getItemType()), type.getMeta());
            case ItemTagDescriptor tag -> new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemTagDescriptor(tag.getItemTag().name());
            default -> throw new IllegalArgumentException("Unexpected value: " + descriptor);
        };
    }

    public static org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount toNetworkWithCount(ItemDescriptor descriptor) {
        return new org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount(toNetwork(descriptor), 1);
    }

    public static BiomeDefinitionData toNetwork(BiomeType biome) {
        var data = biome.getBiomeData();
        var tags = data.tags().stream().map(BiomeTag::name).toList();
        if (biome.getId() < CustomBiomeIdAllocator.CUSTOM_BIOME_ID_START) {
            // Vanilla biome
            // NOTICE: id field is only set for custom biome
            return new BiomeDefinitionData(
                    null, data.temperature(), data.downfall(), data.redSporeDensity(),
                    data.blueSporeDensity(), data.ashDensity(), data.whiteAshDensity(), data.foliageSnow(),
                    data.depth(), data.scale(), data.mapWaterColor(), data.rain(), tags, null
            );
        } else {
            // Custom biome
            return new BiomeDefinitionData(
                    biome.getId(), data.temperature(), data.downfall(), data.redSporeDensity(),
                    data.blueSporeDensity(), data.ashDensity(), data.whiteAshDensity(), data.foliageSnow(),
                    data.depth(), data.scale(), data.mapWaterColor(), data.rain(), tags, null
            );
        }
    }

    public static EnchantData toNetwork(EnchantmentInstance instance) {
        return new EnchantData(instance.getType().getId(), instance.getLevel());
    }

    public static EnchantOptionData toNetwork(Pair<Integer, EnchantOption> pair) {
        var networkId = pair.left();
        var option = pair.right();
        return new EnchantOptionData(
                option.requiredXpLevel(), 0,
                option.enchantments().stream().map(NetworkHelper::toNetwork).toList(),
                List.of(), List.of(), option.optionName(), networkId
        );
    }

    public ItemDefinition toNetwork(ItemType<?> itemType) {
        var itemDefinition = ((AllayItemType<?>) itemType).getItemDefinition();
        return new SimpleItemDefinition(
                itemType.getIdentifier().toString(), itemType.getRuntimeId(), itemDefinition.version(),
                itemDefinition.componentBased(), itemDefinition.components()
        );
    }

    public static ItemData toNetwork(ItemStack itemStack) {
        var itemType = itemStack.getItemType();
        if (itemType == ItemTypes.AIR) {
            return ItemData.AIR;
        }

        var blockState = itemStack.toBlockState();
        return ItemData
                .builder()
                .definition(NetworkHelper.toNetwork(itemType))
                .blockDefinition(blockState != null ? blockState::blockStateHash : () -> 0)
                .count(itemStack.getCount())
                .damage(itemStack.getMeta())
                .tag(itemStack.saveExtraTag())
                .usingNetId(itemStack.hasUniqueId())
                .netId(itemStack.getUniqueId())
                .build();

    }

    public static List<ItemData> toNetwork(List<ItemStack> items) {
        return items.stream().map(NetworkHelper::toNetwork).toList();
    }

    public static Vector3ic fromNetwork(org.cloudburstmc.math.vector.Vector3i vec) {
        if (vec == null) {
            return null;
        }

        return new Vector3i(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vector3dc fromNetwork(org.cloudburstmc.math.vector.Vector3d vec) {
        if (vec == null) {
            return null;
        }

        return new Vector3d(vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vector3fc fromNetwork(org.cloudburstmc.math.vector.Vector3f vec) {
        if (vec == null) {
            return null;
        }

        return new Vector3f(vec.getX(), vec.getY(), vec.getZ());
    }

    public static org.cloudburstmc.math.vector.Vector3i toNetwork(Vector3ic vec) {
        if (vec == null) {
            return null;
        }

        return org.cloudburstmc.math.vector.Vector3i.from(vec.x(), vec.y(), vec.z());
    }

    public static org.cloudburstmc.math.vector.Vector3d toNetwork(Vector3dc vec) {
        if (vec == null) {
            return null;
        }

        return org.cloudburstmc.math.vector.Vector3d.from(vec.x(), vec.y(), vec.z());
    }

    public static org.cloudburstmc.math.vector.Vector3f toNetwork(Vector3fc vec) {
        if (vec == null) {
            return null;
        }

        return org.cloudburstmc.math.vector.Vector3f.from(vec.x(), vec.y(), vec.z());
    }

    public static GameType toNetwork(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> GameType.SURVIVAL;
            case CREATIVE -> GameType.CREATIVE;
            case ADVENTURE -> GameType.ADVENTURE;
            case SPECTATOR -> GameType.SPECTATOR;
        };
    }

    public static GameMode fromNetwork(GameType gameType) {
        return switch (gameType) {
            case SURVIVAL -> GameMode.SURVIVAL;
            case CREATIVE -> GameMode.CREATIVE;
            case ADVENTURE -> GameMode.ADVENTURE;
            case SPECTATOR -> GameMode.SPECTATOR;
            default -> null;
        };
    }

    public static org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveShape toNetwork(DebugShape shape, int dimension, Entity attachedEntity) {
        var entityId = attachedEntity != null ? attachedEntity.getRuntimeId() : null;
        var networkPos = toNetwork(shape.getPosition());
        if (attachedEntity != null) {
            networkPos = networkPos.add(0, -NETWORK_OFFSETS.getOrDefault(attachedEntity.getEntityType().getIdentifier(), 0.0f), 0);
        }
        return switch (shape) {
            case DebugArrow arrow -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveArrow(
                    arrow.getId(), dimension, networkPos, arrow.getArrowHeadScale(), null,
                    null, arrow.getColor(), toNetwork(arrow.getEndPosition()), arrow.getArrowHeadLength(),
                    arrow.getArrowHeadRadius(), arrow.getArrowHeadSegments(), entityId
            );
            case DebugBox box -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveBox(
                    box.getId(), dimension, networkPos, box.getScale(), null,
                    null, box.getColor(), toNetwork(box.getBoxBounds()), entityId
            );
            case DebugCircle circle -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveCircle(
                    circle.getId(), dimension, networkPos, circle.getScale(), null,
                    null, circle.getColor(), circle.getSegments(), entityId
            );
            case DebugLine line -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveLine(
                    line.getId(), dimension, networkPos, null, null,
                    null, line.getColor(), toNetwork(line.getEndPosition()), entityId
            );
            case DebugSphere sphere -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveSphere(
                    sphere.getId(), dimension, networkPos, sphere.getScale(), null,
                    null, sphere.getColor(), sphere.getSegments(), entityId
            );
            case DebugText text -> new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveText(
                    text.getId(), dimension, networkPos, null, null,
                    null, text.getColor(), text.getText(), entityId
            );
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };
    }

    public static org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveShape toNetworkRemovalNotice(DebugShape shape) {
        return new org.cloudburstmc.protocol.bedrock.data.primitiveshape.PrimitiveShape(shape.getId());
    }

    public record PortraitOffsets(double[] translate, double[] rotate, double[] scale) {
    }

    public static PortraitOffsets toNetwork(ModelSettings settings) {
        // Translate needs to be multiplied by -32 to get a rough block equivalent
        var translate = settings.offset().mul(-32, new Vector3d());
        // Entity is rotated by 32 degrees by default
        var rotate = settings.rotation();
        var scale = settings.scale();
        return new PortraitOffsets(
                toDoubleArray(translate),
                toDoubleArray(rotate),
                toDoubleArray(scale)
        );
    }

    private static double[] toDoubleArray(Vector3dc vec) {
        return new double[]{vec.x(), vec.y(), vec.z()};
    }

    public record NetworkDialogButton(
            @SerializedName("button_name")
            String buttonName,
            String text,
            // "Click" activation
            int mode,
            // "Command" type
            int type
    ) {
        public NetworkDialogButton(Button button) {
            this(button.getText(), "", 0, 1);
        }
    }

    public static List<NetworkDialogButton> toNetworkDialogButtons(List<Button> buttons) {
        return buttons.stream().map(NetworkDialogButton::new).toList();
    }

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
