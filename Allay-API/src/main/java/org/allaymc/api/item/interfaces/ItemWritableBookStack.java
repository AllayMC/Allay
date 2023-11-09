package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWritableBookStack extends ItemStack {
  ItemType<ItemWritableBookStack> WRITABLE_BOOK_TYPE = ItemTypeBuilder
          .builder(ItemWritableBookStack.class)
          .vanillaItem(VanillaItemId.WRITABLE_BOOK)
          .build();
}
