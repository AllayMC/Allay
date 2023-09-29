package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemNetherSproutsStack extends ItemStack {
  ItemType<ItemItemNetherSproutsStack> ITEM_NETHER_SPROUTS_TYPE = ItemTypeBuilder
          .builder(ItemItemNetherSproutsStack.class)
          .vanillaItem(VanillaItemId.ITEM_NETHER_SPROUTS)
          .build();
}
