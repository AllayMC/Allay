package cn.allay.api.item.interfaces.soultorch;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulTorchStack extends ItemStack {
    ItemType<ItemSoulTorchStack> SOUL_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemSoulTorchStack.class)
            .vanillaItem(VanillaItemId.SOUL_TORCH)
            .build();
}
