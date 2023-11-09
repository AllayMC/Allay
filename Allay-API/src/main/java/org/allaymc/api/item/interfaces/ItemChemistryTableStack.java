package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemistryTableStack extends ItemStack {
  ItemType<ItemChemistryTableStack> CHEMISTRY_TABLE_TYPE = ItemTypeBuilder
          .builder(ItemChemistryTableStack.class)
          .vanillaItem(VanillaItemId.CHEMISTRY_TABLE)
          .build();
}
