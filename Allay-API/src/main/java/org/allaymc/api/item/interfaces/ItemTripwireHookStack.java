package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTripwireHookStack extends ItemStack {
  ItemType<ItemTripwireHookStack> TRIPWIRE_HOOK_TYPE = ItemTypeBuilder
          .builder(ItemTripwireHookStack.class)
          .vanillaItem(VanillaItemId.TRIPWIRE_HOOK)
          .build();
}
