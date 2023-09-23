package cn.allay.api.item.interfaces.polishedandesitestairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedAndesiteStairsStack extends ItemStack {
    ItemType<ItemPolishedAndesiteStairsStack> POLISHED_ANDESITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedAndesiteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_ANDESITE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
