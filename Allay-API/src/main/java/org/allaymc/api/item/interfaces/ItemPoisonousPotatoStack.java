package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoisonousPotatoStack extends ItemStack {
  ItemType<ItemPoisonousPotatoStack> POISONOUS_POTATO_TYPE = ItemTypeBuilder
          .builder(ItemPoisonousPotatoStack.class)
          .vanillaItem(VanillaItemId.POISONOUS_POTATO)
          .build();
}
