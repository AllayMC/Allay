package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnderwaterTorchStack extends ItemStack {
    ItemType<ItemUnderwaterTorchStack> UNDERWATER_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemUnderwaterTorchStack.class)
            .vanillaItem(VanillaItemId.UNDERWATER_TORCH)
            .build();
}
