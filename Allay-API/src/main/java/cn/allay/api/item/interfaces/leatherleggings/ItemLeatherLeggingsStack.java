package cn.allay.api.item.interfaces.leatherleggings;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherLeggingsStack extends ItemStack {
  ItemType<ItemLeatherLeggingsStack> LEATHER_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemLeatherLeggingsStack.class)
          .vanillaItem(VanillaItemId.LEATHER_LEGGINGS)
          .build();
}
