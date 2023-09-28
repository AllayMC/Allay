package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPiglinSpawnEggStack extends ItemStack {
  ItemType<ItemPiglinSpawnEggStack> PIGLIN_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemPiglinSpawnEggStack.class)
          .vanillaItem(VanillaItemId.PIGLIN_SPAWN_EGG)
          .build();
}
