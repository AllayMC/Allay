package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondLeggingsStack extends ItemStack {
  ItemType<ItemDiamondLeggingsStack> DIAMOND_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemDiamondLeggingsStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_LEGGINGS)
          .build();
}
