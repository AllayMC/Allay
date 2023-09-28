package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitSpawnEggStack extends ItemStack {
  ItemType<ItemRabbitSpawnEggStack> RABBIT_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemRabbitSpawnEggStack.class)
          .vanillaItem(VanillaItemId.RABBIT_SPAWN_EGG)
          .build();
}
