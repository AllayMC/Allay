package cn.allay.api.item.interfaces.itemhopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemHopperStack extends ItemStack {
    ItemType<ItemItemHopperStack> ITEM_HOPPER_TYPE = ItemTypeBuilder
            .builder(ItemItemHopperStack.class)
            .vanillaItem(VanillaItemId.ITEM_HOPPER)
            .build();
}
