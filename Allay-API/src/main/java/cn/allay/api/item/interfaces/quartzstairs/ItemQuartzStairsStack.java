package cn.allay.api.item.interfaces.quartzstairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzStairsStack extends ItemStack {
    ItemType<ItemQuartzStairsStack> QUARTZ_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemQuartzStairsStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
