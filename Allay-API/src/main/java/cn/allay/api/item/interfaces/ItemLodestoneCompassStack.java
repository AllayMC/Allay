package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLodestoneCompassStack extends ItemStack {
  ItemType<ItemLodestoneCompassStack> LODESTONE_COMPASS_TYPE = ItemTypeBuilder
          .builder(ItemLodestoneCompassStack.class)
          .vanillaItem(VanillaItemId.LODESTONE_COMPASS)
          .build();
}
