package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonNyliumStack extends ItemStack {
  ItemType<ItemCrimsonNyliumStack> CRIMSON_NYLIUM_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonNyliumStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_NYLIUM)
          .build();
}
