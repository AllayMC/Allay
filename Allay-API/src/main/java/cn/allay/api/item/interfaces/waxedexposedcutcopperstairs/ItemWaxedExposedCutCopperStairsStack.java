package cn.allay.api.item.interfaces.waxedexposedcutcopperstairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCutCopperStairsStack extends ItemStack {
    ItemType<ItemWaxedExposedCutCopperStairsStack> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
