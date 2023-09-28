package cn.allay.api.item.interfaces.stairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDioriteStairsStack extends ItemStack {
    ItemType<ItemDioriteStairsStack> DIORITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDioriteStairsStack.class)
            .vanillaItem(VanillaItemId.DIORITE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
