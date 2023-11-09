package org.allaymc.api.item.interfaces.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchFenceGateStack extends ItemStack {
  ItemType<ItemBirchFenceGateStack> BIRCH_FENCE_GATE_TYPE = ItemTypeBuilder
          .builder(ItemBirchFenceGateStack.class)
          .vanillaItem(VanillaItemId.BIRCH_FENCE_GATE)
          .build();
}
