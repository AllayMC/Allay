package cn.allay.api.item.interfaces.stoneblockslab3;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlab3Stack extends ItemStack {
    ItemType<ItemStoneBlockSlab3Stack> STONE_BLOCK_SLAB3_TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlab3Stack.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB3)
            .build();
}
