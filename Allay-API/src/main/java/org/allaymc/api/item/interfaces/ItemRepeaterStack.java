package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRepeaterStack extends ItemStack {
  ItemType<ItemRepeaterStack> REPEATER_TYPE = ItemTypeBuilder
          .builder(ItemRepeaterStack.class)
          .vanillaItem(VanillaItemId.REPEATER)
          .build();
}
