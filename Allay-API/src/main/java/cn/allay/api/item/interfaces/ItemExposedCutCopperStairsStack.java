package cn.allay.api.item.interfaces;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.CommonUseItemFunctions;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCutCopperStairsStack extends ItemStack {
    ItemType<ItemExposedCutCopperStairsStack> EXPOSED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemExposedCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_CUT_COPPER_STAIRS)
            .addComponent(ComponentProvider.of(initInfo -> new ItemBaseComponentImpl<>((ItemStackInitInfo<?>) initInfo, CommonUseItemFunctions.ITEM_STAIR_USE_ON), ItemBaseComponentImpl.class))
            .build();
}
