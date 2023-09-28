package cn.allay.api.item.interfaces.copper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCopperStack extends ItemStack {
  ItemType<ItemWaxedOxidizedCopperStack> WAXED_OXIDIZED_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemWaxedOxidizedCopperStack.class)
          .vanillaItem(VanillaItemId.WAXED_OXIDIZED_COPPER)
          .build();
}
