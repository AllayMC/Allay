package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAxolotlSpawnEggStack extends ItemStack {
  ItemType<ItemAxolotlSpawnEggStack> AXOLOTL_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemAxolotlSpawnEggStack.class)
          .vanillaItem(VanillaItemId.AXOLOTL_SPAWN_EGG)
          .build();
}
