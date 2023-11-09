package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoweredRepeaterStack extends ItemStack {
  ItemType<ItemPoweredRepeaterStack> POWERED_REPEATER_TYPE = ItemTypeBuilder
          .builder(ItemPoweredRepeaterStack.class)
          .vanillaItem(VanillaItemId.POWERED_REPEATER)
          .build();
}
