package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNoteblockStack extends ItemStack {
  ItemType<ItemNoteblockStack> NOTEBLOCK_TYPE = ItemTypeBuilder
          .builder(ItemNoteblockStack.class)
          .vanillaItem(VanillaItemId.NOTEBLOCK)
          .build();
}
