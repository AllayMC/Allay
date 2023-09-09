package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemColoredTorchBpStack extends ItemStack {
    ItemType<ItemColoredTorchBpStack> COLORED_TORCH_BP_TYPE = ItemTypeBuilder
            .builder(ItemColoredTorchBpStack.class)
            .vanillaItem(VanillaItemId.COLORED_TORCH_BP)
            .build();
}
