package cn.allay.api.item.interfaces.wall;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateWallStack extends ItemStack {
  ItemType<ItemPolishedDeepslateWallStack> POLISHED_DEEPSLATE_WALL_TYPE = ItemTypeBuilder
          .builder(ItemPolishedDeepslateWallStack.class)
          .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_WALL)
          .build();
}
