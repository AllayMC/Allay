package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesStack extends ItemStack {
  ItemType<ItemCaveVinesStack> CAVE_VINES_TYPE = ItemTypeBuilder
          .builder(ItemCaveVinesStack.class)
          .vanillaItem(VanillaItemId.CAVE_VINES)
          .build();
}
