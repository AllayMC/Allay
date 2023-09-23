package cn.allay.api.item.interfaces.cobbleddeepslatestairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateStairsStack extends ItemStack {
    ItemType<ItemCobbledDeepslateStairsStack> COBBLED_DEEPSLATE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateStairsStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
