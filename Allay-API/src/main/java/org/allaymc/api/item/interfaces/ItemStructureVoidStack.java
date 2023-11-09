package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStructureVoidStack extends ItemStack {
  ItemType<ItemStructureVoidStack> STRUCTURE_VOID_TYPE = ItemTypeBuilder
          .builder(ItemStructureVoidStack.class)
          .vanillaItem(VanillaItemId.STRUCTURE_VOID)
          .build();
}
