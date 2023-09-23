package cn.allay.api.item.interfaces.polishedblackstonestairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneStairsStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneStairsStack> POLISHED_BLACKSTONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
