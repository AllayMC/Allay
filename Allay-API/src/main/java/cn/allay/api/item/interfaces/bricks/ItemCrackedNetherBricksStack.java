package cn.allay.api.item.interfaces.bricks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedNetherBricksStack extends ItemStack {
  ItemType<ItemCrackedNetherBricksStack> CRACKED_NETHER_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemCrackedNetherBricksStack.class)
          .vanillaItem(VanillaItemId.CRACKED_NETHER_BRICKS)
          .build();
}
