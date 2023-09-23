package cn.allay.api.item.interfaces.melonblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonBlockStack extends ItemStack {
    ItemType<ItemMelonBlockStack> MELON_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemMelonBlockStack.class)
            .vanillaItem(VanillaItemId.MELON_BLOCK)
            .build();
}
