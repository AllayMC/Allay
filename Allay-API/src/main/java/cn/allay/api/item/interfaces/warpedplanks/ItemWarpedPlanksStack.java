package cn.allay.api.item.interfaces.warpedplanks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedPlanksStack extends ItemStack {
    ItemType<ItemWarpedPlanksStack> WARPED_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemWarpedPlanksStack.class)
            .vanillaItem(VanillaItemId.WARPED_PLANKS)
            .build();
}
