package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBakedPotatoStack extends ItemStack {
  ItemType<ItemBakedPotatoStack> BAKED_POTATO_TYPE = ItemTypeBuilder
          .builder(ItemBakedPotatoStack.class)
          .vanillaItem(VanillaItemId.BAKED_POTATO)
          .build();
}
