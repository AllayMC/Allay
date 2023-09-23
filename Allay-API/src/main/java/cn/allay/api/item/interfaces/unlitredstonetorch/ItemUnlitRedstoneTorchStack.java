package cn.allay.api.item.interfaces.unlitredstonetorch;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnlitRedstoneTorchStack extends ItemStack {
    ItemType<ItemUnlitRedstoneTorchStack> UNLIT_REDSTONE_TORCH_TYPE = ItemTypeBuilder
            .builder(ItemUnlitRedstoneTorchStack.class)
            .vanillaItem(VanillaItemId.UNLIT_REDSTONE_TORCH)
            .build();
}
