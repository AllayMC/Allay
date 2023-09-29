package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedMushroomBlockStack extends ItemStack {
  ItemType<ItemRedMushroomBlockStack> RED_MUSHROOM_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemRedMushroomBlockStack.class)
          .vanillaItem(VanillaItemId.RED_MUSHROOM_BLOCK)
          .build();
}
