package cn.allay.api.item.interfaces.bricks;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedPolishedBlackstoneBricksStack extends ItemStack {
  ItemType<ItemCrackedPolishedBlackstoneBricksStack> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemCrackedPolishedBlackstoneBricksStack.class)
          .vanillaItem(VanillaItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
          .build();
}
