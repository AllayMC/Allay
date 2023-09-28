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
public interface ItemCutCopperStairsStack extends ItemStack {
    ItemType<ItemCutCopperStairsStack> CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.CUT_COPPER_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
