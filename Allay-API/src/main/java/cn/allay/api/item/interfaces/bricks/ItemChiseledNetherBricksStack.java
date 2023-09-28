package cn.allay.api.item.interfaces.bricks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledNetherBricksStack extends ItemStack {
  ItemType<ItemChiseledNetherBricksStack> CHISELED_NETHER_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemChiseledNetherBricksStack.class)
          .vanillaItem(VanillaItemId.CHISELED_NETHER_BRICKS)
          .build();
}
