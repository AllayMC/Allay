package cn.allay.api.item.interfaces.polishedgranitestairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedGraniteStairsStack extends ItemStack {
    ItemType<ItemPolishedGraniteStairsStack> POLISHED_GRANITE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedGraniteStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_GRANITE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
