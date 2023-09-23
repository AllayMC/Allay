package cn.allay.api.item.interfaces.junglefencegate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleFenceGateStack extends ItemStack {
    ItemType<ItemJungleFenceGateStack> JUNGLE_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemJungleFenceGateStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_FENCE_GATE)
            .build();
}
