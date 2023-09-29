package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDetectorRailStack extends ItemStack {
  ItemType<ItemDetectorRailStack> DETECTOR_RAIL_TYPE = ItemTypeBuilder
          .builder(ItemDetectorRailStack.class)
          .vanillaItem(VanillaItemId.DETECTOR_RAIL)
          .build();
}
