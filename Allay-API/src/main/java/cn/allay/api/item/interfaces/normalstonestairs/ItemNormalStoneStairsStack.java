package cn.allay.api.item.interfaces.normalstonestairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNormalStoneStairsStack extends ItemStack {
    ItemType<ItemNormalStoneStairsStack> NORMAL_STONE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemNormalStoneStairsStack.class)
            .vanillaItem(VanillaItemId.NORMAL_STONE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
