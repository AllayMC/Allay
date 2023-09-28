package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPigSpawnEggStack extends ItemStack {
  ItemType<ItemPigSpawnEggStack> PIG_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemPigSpawnEggStack.class)
          .vanillaItem(VanillaItemId.PIG_SPAWN_EGG)
          .build();
}
