package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedStairsStack extends ItemStack {
    ItemType<ItemWarpedStairsStack> WARPED_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWarpedStairsStack.class)
            .vanillaItem(VanillaItemId.WARPED_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
