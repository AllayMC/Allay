package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShieldStack extends ItemStack {
  ItemType<ItemShieldStack> SHIELD_TYPE = ItemTypeBuilder
          .builder(ItemShieldStack.class)
          .vanillaItem(VanillaItemId.SHIELD)
          .build();
}
