package cn.allay.api.item.interfaces.bricks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndBricksStack extends ItemStack {
  ItemType<ItemEndBricksStack> END_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemEndBricksStack.class)
          .vanillaItem(VanillaItemId.END_BRICKS)
          .build();
}
