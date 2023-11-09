package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireworkRocketStack extends ItemStack {
  ItemType<ItemFireworkRocketStack> FIREWORK_ROCKET_TYPE = ItemTypeBuilder
          .builder(ItemFireworkRocketStack.class)
          .vanillaItem(VanillaItemId.FIREWORK_ROCKET)
          .build();
}
