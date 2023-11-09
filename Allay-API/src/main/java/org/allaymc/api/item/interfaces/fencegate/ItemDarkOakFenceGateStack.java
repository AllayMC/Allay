package org.allaymc.api.item.interfaces.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakFenceGateStack extends ItemStack {
  ItemType<ItemDarkOakFenceGateStack> DARK_OAK_FENCE_GATE_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakFenceGateStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_FENCE_GATE)
          .build();
}
