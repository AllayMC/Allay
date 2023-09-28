package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombiePigmanSpawnEggStack extends ItemStack {
  ItemType<ItemZombiePigmanSpawnEggStack> ZOMBIE_PIGMAN_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemZombiePigmanSpawnEggStack.class)
          .vanillaItem(VanillaItemId.ZOMBIE_PIGMAN_SPAWN_EGG)
          .build();
}
