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
public interface ItemSmoothQuartzStairsStack extends ItemStack {
    ItemType<ItemSmoothQuartzStairsStack> SMOOTH_QUARTZ_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSmoothQuartzStairsStack.class)
            .vanillaItem(VanillaItemId.SMOOTH_QUARTZ_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
