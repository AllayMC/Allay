package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoublePlantStack extends ItemStack {
  ItemType<ItemDoublePlantStack> DOUBLE_PLANT_TYPE = ItemTypeBuilder
          .builder(ItemDoublePlantStack.class)
          .vanillaItem(VanillaItemId.DOUBLE_PLANT)
          .build();
}
