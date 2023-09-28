package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazeSpawnEggStack extends ItemStack {
  ItemType<ItemBlazeSpawnEggStack> BLAZE_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemBlazeSpawnEggStack.class)
          .vanillaItem(VanillaItemId.BLAZE_SPAWN_EGG)
          .build();
}
