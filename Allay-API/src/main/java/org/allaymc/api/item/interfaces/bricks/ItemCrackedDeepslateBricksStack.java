package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedDeepslateBricksStack extends ItemStack {
  ItemType<ItemCrackedDeepslateBricksStack> CRACKED_DEEPSLATE_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemCrackedDeepslateBricksStack.class)
          .vanillaItem(VanillaItemId.CRACKED_DEEPSLATE_BRICKS)
          .build();
}
