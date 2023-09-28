package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRavagerSpawnEggStack extends ItemStack {
  ItemType<ItemRavagerSpawnEggStack> RAVAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemRavagerSpawnEggStack.class)
          .vanillaItem(VanillaItemId.RAVAGER_SPAWN_EGG)
          .build();
}
