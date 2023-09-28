package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSalmonSpawnEggStack extends ItemStack {
  ItemType<ItemSalmonSpawnEggStack> SALMON_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemSalmonSpawnEggStack.class)
          .vanillaItem(VanillaItemId.SALMON_SPAWN_EGG)
          .build();
}
