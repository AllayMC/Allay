package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

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
