package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBricksStack extends ItemStack {
  ItemType<ItemDeepslateBricksStack> DEEPSLATE_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateBricksStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_BRICKS)
          .build();
}
