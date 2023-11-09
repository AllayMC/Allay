package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkCatalystStack extends ItemStack {
  ItemType<ItemSculkCatalystStack> SCULK_CATALYST_TYPE = ItemTypeBuilder
          .builder(ItemSculkCatalystStack.class)
          .vanillaItem(VanillaItemId.SCULK_CATALYST)
          .build();
}
