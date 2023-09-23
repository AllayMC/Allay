package cn.allay.api.item.interfaces.purpurstairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpurStairsStack extends ItemStack {
    ItemType<ItemPurpurStairsStack> PURPUR_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPurpurStairsStack.class)
            .vanillaItem(VanillaItemId.PURPUR_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
