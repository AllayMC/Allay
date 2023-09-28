package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowGolemSpawnEggStack extends ItemStack {
  ItemType<ItemSnowGolemSpawnEggStack> SNOW_GOLEM_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemSnowGolemSpawnEggStack.class)
          .vanillaItem(VanillaItemId.SNOW_GOLEM_SPAWN_EGG)
          .build();
}
