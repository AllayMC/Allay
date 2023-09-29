package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondOreStack extends ItemStack {
  ItemType<ItemDiamondOreStack> DIAMOND_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDiamondOreStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_ORE)
          .build();
}
