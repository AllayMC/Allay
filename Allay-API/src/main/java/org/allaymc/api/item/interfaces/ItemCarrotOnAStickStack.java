package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotOnAStickStack extends ItemStack {
  ItemType<ItemCarrotOnAStickStack> CARROT_ON_A_STICK_TYPE = ItemTypeBuilder
          .builder(ItemCarrotOnAStickStack.class)
          .vanillaItem(VanillaItemId.CARROT_ON_A_STICK)
          .build();
}
