package org.allaymc.api.item.interfaces.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaPlanksStack extends ItemStack {
  ItemType<ItemAcaciaPlanksStack> ACACIA_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaPlanksStack.class)
          .vanillaItem(VanillaItemId.ACACIA_PLANKS)
          .build();
}
