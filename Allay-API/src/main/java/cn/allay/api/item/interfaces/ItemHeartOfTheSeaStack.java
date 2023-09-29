package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeartOfTheSeaStack extends ItemStack {
  ItemType<ItemHeartOfTheSeaStack> HEART_OF_THE_SEA_TYPE = ItemTypeBuilder
          .builder(ItemHeartOfTheSeaStack.class)
          .vanillaItem(VanillaItemId.HEART_OF_THE_SEA)
          .build();
}
