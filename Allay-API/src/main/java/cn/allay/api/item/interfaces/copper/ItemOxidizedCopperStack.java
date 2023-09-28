package cn.allay.api.item.interfaces.copper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCopperStack extends ItemStack {
  ItemType<ItemOxidizedCopperStack> OXIDIZED_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemOxidizedCopperStack.class)
          .vanillaItem(VanillaItemId.OXIDIZED_COPPER)
          .build();
}
