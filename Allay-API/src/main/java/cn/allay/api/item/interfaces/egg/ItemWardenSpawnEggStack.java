package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWardenSpawnEggStack extends ItemStack {
  ItemType<ItemWardenSpawnEggStack> WARDEN_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemWardenSpawnEggStack.class)
          .vanillaItem(VanillaItemId.WARDEN_SPAWN_EGG)
          .build();
}
