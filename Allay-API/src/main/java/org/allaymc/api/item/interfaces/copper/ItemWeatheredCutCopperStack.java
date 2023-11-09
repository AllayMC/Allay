package org.allaymc.api.item.interfaces.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperStack extends ItemStack {
  ItemType<ItemWeatheredCutCopperStack> WEATHERED_CUT_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemWeatheredCutCopperStack.class)
          .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER)
          .build();
}
