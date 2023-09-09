package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShulkerShellStack extends ItemStack {
    ItemType<ItemShulkerShellStack> SHULKER_SHELL_TYPE = ItemTypeBuilder
            .builder(ItemShulkerShellStack.class)
            .vanillaItem(VanillaItemId.SHULKER_SHELL)
            .build();
}
