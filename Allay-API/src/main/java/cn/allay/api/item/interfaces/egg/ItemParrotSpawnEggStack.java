package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemParrotSpawnEggStack extends ItemStack {
  ItemType<ItemParrotSpawnEggStack> PARROT_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemParrotSpawnEggStack.class)
          .vanillaItem(VanillaItemId.PARROT_SPAWN_EGG)
          .build();
}
