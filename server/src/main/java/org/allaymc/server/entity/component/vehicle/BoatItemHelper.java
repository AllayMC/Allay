package org.allaymc.server.entity.component.vehicle;

import lombok.experimental.UtilityClass;
import org.allaymc.api.entity.data.BoatVariant;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

@UtilityClass
final class BoatItemHelper {
    static ItemStack createItem(BoatVariant variant) {
        return switch (variant) {
            case OAK -> ItemTypes.OAK_BOAT.createItemStack();
            case SPRUCE -> ItemTypes.SPRUCE_BOAT.createItemStack();
            case BIRCH -> ItemTypes.BIRCH_BOAT.createItemStack();
            case JUNGLE -> ItemTypes.JUNGLE_BOAT.createItemStack();
            case ACACIA -> ItemTypes.ACACIA_BOAT.createItemStack();
            case DARK_OAK -> ItemTypes.DARK_OAK_BOAT.createItemStack();
            case MANGROVE -> ItemTypes.MANGROVE_BOAT.createItemStack();
            case BAMBOO -> ItemTypes.BAMBOO_RAFT.createItemStack();
            case CHERRY -> ItemTypes.CHERRY_BOAT.createItemStack();
            case PALE_OAK -> ItemTypes.PALE_OAK_BOAT.createItemStack();
        };
    }
}
