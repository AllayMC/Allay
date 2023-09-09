package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaFenceGateStack extends ItemStack {
    ItemType<ItemAcaciaFenceGateStack> ACACIA_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaFenceGateStack.class)
            .vanillaItem(VanillaItemId.ACACIA_FENCE_GATE)
            .build();
}
