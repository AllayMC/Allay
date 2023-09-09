package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab2Stack extends ItemStack {
    ItemType<ItemDoubleStoneBlockSlab2Stack> DOUBLE_STONE_BLOCK_SLAB2_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab2Stack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB2)
            .build();
}
