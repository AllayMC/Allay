package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

import java.util.List;

/**
 * Helper class to convert types in allay to the types used in the protocol library.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class NetworkHelper {
    public static ItemData toNetwork(ItemStack itemStack) {
        var itemType = itemStack.getItemType();
        if (itemType == ItemTypes.AIR) {
            return ItemData.AIR;
        }

        var blockState = itemStack.toBlockState();
        return ItemData
                .builder()
                .definition(itemType.toNetworkDefinition())
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
}
