package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnpoweredRepeaterStack extends ItemStack {
  ItemType<ItemUnpoweredRepeaterStack> UNPOWERED_REPEATER_TYPE = ItemTypeBuilder
          .builder(ItemUnpoweredRepeaterStack.class)
          .vanillaItem(VanillaItemId.UNPOWERED_REPEATER)
          .build();
}
