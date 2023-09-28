package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoglinSpawnEggStack extends ItemStack {
  ItemType<ItemHoglinSpawnEggStack> HOGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemHoglinSpawnEggStack.class)
          .vanillaItem(VanillaItemId.HOGLIN_SPAWN_EGG)
          .build();
}
