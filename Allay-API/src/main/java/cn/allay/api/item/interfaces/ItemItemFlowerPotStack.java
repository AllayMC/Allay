package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemFlowerPotStack extends ItemStack {
  ItemType<ItemItemFlowerPotStack> ITEM_FLOWER_POT_TYPE = ItemTypeBuilder
          .builder(ItemItemFlowerPotStack.class)
          .vanillaItem(VanillaItemId.ITEM_FLOWER_POT)
          .build();
}
