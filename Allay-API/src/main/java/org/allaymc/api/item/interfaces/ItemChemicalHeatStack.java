package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemicalHeatStack extends ItemStack {
  ItemType<ItemChemicalHeatStack> CHEMICAL_HEAT_TYPE = ItemTypeBuilder
          .builder(ItemChemicalHeatStack.class)
          .vanillaItem(VanillaItemId.CHEMICAL_HEAT)
          .build();
}
