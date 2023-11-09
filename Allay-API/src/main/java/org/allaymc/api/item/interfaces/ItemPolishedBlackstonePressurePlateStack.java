package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstonePressurePlateStack extends ItemStack {
  ItemType<ItemPolishedBlackstonePressurePlateStack> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstonePressurePlateStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
          .build();
}
