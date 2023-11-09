package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitHideStack extends ItemStack {
  ItemType<ItemRabbitHideStack> RABBIT_HIDE_TYPE = ItemTypeBuilder
          .builder(ItemRabbitHideStack.class)
          .vanillaItem(VanillaItemId.RABBIT_HIDE)
          .build();
}
