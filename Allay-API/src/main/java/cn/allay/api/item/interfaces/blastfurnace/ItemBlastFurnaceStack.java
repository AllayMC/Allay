package cn.allay.api.item.interfaces.blastfurnace;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlastFurnaceStack extends ItemStack {
    ItemType<ItemBlastFurnaceStack> BLAST_FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemBlastFurnaceStack.class)
            .vanillaItem(VanillaItemId.BLAST_FURNACE)
            .build();
}
