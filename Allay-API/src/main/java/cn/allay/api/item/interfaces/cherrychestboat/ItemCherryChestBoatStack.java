package cn.allay.api.item.interfaces.cherrychestboat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryChestBoatStack extends ItemStack {
    ItemType<ItemCherryChestBoatStack> CHERRY_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemCherryChestBoatStack.class)
            .vanillaItem(VanillaItemId.CHERRY_CHEST_BOAT)
            .build();
}
