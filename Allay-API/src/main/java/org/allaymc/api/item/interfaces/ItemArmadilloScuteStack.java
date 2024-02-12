package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmadilloScuteStack extends ItemStack {
  ItemType<ItemArmadilloScuteStack> ARMADILLO_SCUTE_TYPE = ItemTypeBuilder
          .builder(ItemArmadilloScuteStack.class)
          .vanillaItem(VanillaItemId.ARMADILLO_SCUTE)
          .build();
}
