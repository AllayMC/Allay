package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySaplingStack extends ItemStack {
    ItemType<ItemCherrySaplingStack> CHERRY_SAPLING_TYPE = ItemTypeBuilder
            .builder(ItemCherrySaplingStack.class)
            .vanillaItem(VanillaItemId.CHERRY_SAPLING)
            .build();
}
