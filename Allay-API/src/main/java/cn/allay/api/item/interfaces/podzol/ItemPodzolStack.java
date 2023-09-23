package cn.allay.api.item.interfaces.podzol;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPodzolStack extends ItemStack {
    ItemType<ItemPodzolStack> PODZOL_TYPE = ItemTypeBuilder
            .builder(ItemPodzolStack.class)
            .vanillaItem(VanillaItemId.PODZOL)
            .build();
}
