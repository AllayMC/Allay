package cn.allay.api.item.interfaces.cherrystairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryStairsStack extends ItemStack {
    ItemType<ItemCherryStairsStack> CHERRY_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCherryStairsStack.class)
            .vanillaItem(VanillaItemId.CHERRY_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
