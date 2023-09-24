package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitBlastFurnaceStack extends ItemStack {
    ItemType<ItemLitBlastFurnaceStack> LIT_BLAST_FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemLitBlastFurnaceStack.class)
            .vanillaItem(VanillaItemId.LIT_BLAST_FURNACE)
            .build();
}
