package cn.allay.api.item.interfaces.junglechestboat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleChestBoatStack extends ItemStack {
    ItemType<ItemJungleChestBoatStack> JUNGLE_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemJungleChestBoatStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_CHEST_BOAT)
            .build();
}
