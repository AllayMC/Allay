package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlab4Stack extends ItemStack {
  ItemType<ItemStoneBlockSlab4Stack> STONE_BLOCK_SLAB4_TYPE = ItemTypeBuilder
          .builder(ItemStoneBlockSlab4Stack.class)
          .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB4)
          .build();
}
