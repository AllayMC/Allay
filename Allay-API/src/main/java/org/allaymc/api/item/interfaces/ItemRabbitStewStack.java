package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitStewStack extends ItemStack {
  ItemType<ItemRabbitStewStack> RABBIT_STEW_TYPE = ItemTypeBuilder
          .builder(ItemRabbitStewStack.class)
          .vanillaItem(VanillaItemId.RABBIT_STEW)
          .build();
}
