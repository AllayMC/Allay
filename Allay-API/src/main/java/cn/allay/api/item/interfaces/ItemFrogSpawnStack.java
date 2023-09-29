package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrogSpawnStack extends ItemStack {
  ItemType<ItemFrogSpawnStack> FROG_SPAWN_TYPE = ItemTypeBuilder
          .builder(ItemFrogSpawnStack.class)
          .vanillaItem(VanillaItemId.FROG_SPAWN)
          .build();
}
