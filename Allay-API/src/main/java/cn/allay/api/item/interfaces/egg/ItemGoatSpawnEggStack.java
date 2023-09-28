package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoatSpawnEggStack extends ItemStack {
  ItemType<ItemGoatSpawnEggStack> GOAT_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemGoatSpawnEggStack.class)
          .vanillaItem(VanillaItemId.GOAT_SPAWN_EGG)
          .build();
}
