package cn.allay.api.item.interfaces.dripstoneblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDripstoneBlockStack extends ItemStack {
    ItemType<ItemDripstoneBlockStack> DRIPSTONE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemDripstoneBlockStack.class)
            .vanillaItem(VanillaItemId.DRIPSTONE_BLOCK)
            .build();
}
