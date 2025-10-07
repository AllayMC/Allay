package org.allaymc.server.network;

import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.item.type.AllayItemType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.biome.BiomeDefinitionData;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantData;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import org.joml.*;

import java.util.List;

/**
 * Helper class to convert types between allay and the protocol library.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class NetworkHelper {

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
        // NOTICE: `id` is not the same as the biome's identifier, left it null here
        // otherwise the client will be unable to join the server
        return new BiomeDefinitionData(
                null, data.temperature(), data.downfall(), data.redSporeDensity(),
                data.blueSporeDensity(), data.ashDensity(), data.whiteAshDensity(), data.foliageSnow(),
                data.depth(), data.scale(), data.mapWaterColor(), data.rain(), data.tags(), null
        );
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
}
