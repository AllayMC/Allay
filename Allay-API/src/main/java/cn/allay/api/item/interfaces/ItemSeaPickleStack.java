package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeaPickleStack extends ItemStack {
  ItemType<ItemSeaPickleStack> SEA_PICKLE_TYPE = ItemTypeBuilder
          .builder(ItemSeaPickleStack.class)
          .vanillaItem(VanillaItemId.SEA_PICKLE)
          .build();
}
