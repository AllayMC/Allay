package cn.allay.api.item.interfaces.weatheredcutcopperstairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperStairsStack extends ItemStack {
    ItemType<ItemWeatheredCutCopperStairsStack> WEATHERED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
