package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherWartBlockStack extends ItemStack {
  ItemType<ItemNetherWartBlockStack> NETHER_WART_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemNetherWartBlockStack.class)
          .vanillaItem(VanillaItemId.NETHER_WART_BLOCK)
          .build();
}
