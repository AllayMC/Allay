package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChickenSpawnEggStack extends ItemStack {
  ItemType<ItemChickenSpawnEggStack> CHICKEN_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemChickenSpawnEggStack.class)
          .vanillaItem(VanillaItemId.CHICKEN_SPAWN_EGG)
          .build();
}
