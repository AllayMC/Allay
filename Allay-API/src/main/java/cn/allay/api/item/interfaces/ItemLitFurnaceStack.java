package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitFurnaceStack extends ItemStack {
    ItemType<ItemLitFurnaceStack> LIT_FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemLitFurnaceStack.class)
            .vanillaItem(VanillaItemId.LIT_FURNACE)
            .build();
}
