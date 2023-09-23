package cn.allay.api.item.interfaces.nautilusshell;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNautilusShellStack extends ItemStack {
    ItemType<ItemNautilusShellStack> NAUTILUS_SHELL_TYPE = ItemTypeBuilder
            .builder(ItemNautilusShellStack.class)
            .vanillaItem(VanillaItemId.NAUTILUS_SHELL)
            .build();
}
