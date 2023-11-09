package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireworkStarStack extends ItemStack {
  ItemType<ItemFireworkStarStack> FIREWORK_STAR_TYPE = ItemTypeBuilder
          .builder(ItemFireworkStarStack.class)
          .vanillaItem(VanillaItemId.FIREWORK_STAR)
          .build();
}
