package cn.allay.api.item.interfaces.azalealeavesflowered;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaLeavesFloweredStack extends ItemStack {
    ItemType<ItemAzaleaLeavesFloweredStack> AZALEA_LEAVES_FLOWERED_TYPE = ItemTypeBuilder
            .builder(ItemAzaleaLeavesFloweredStack.class)
            .vanillaItem(VanillaItemId.AZALEA_LEAVES_FLOWERED)
            .build();
}
