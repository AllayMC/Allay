package cn.allay.api.item.interfaces.warpedfencegate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFenceGateStack extends ItemStack {
    ItemType<ItemWarpedFenceGateStack> WARPED_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemWarpedFenceGateStack.class)
            .vanillaItem(VanillaItemId.WARPED_FENCE_GATE)
            .build();
}
