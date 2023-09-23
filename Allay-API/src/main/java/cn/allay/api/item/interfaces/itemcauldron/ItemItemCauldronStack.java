package cn.allay.api.item.interfaces.itemcauldron;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCauldronStack extends ItemStack {
    ItemType<ItemItemCauldronStack> ITEM_CAULDRON_TYPE = ItemTypeBuilder
            .builder(ItemItemCauldronStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAULDRON)
            .build();
}
