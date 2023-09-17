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
public interface ItemSmoothRedSandstoneStairsStack extends ItemStack {
    ItemType<ItemSmoothRedSandstoneStairsStack> SMOOTH_RED_SANDSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSmoothRedSandstoneStairsStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_RED_SANDSTONE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
