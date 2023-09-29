package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherCropStack extends ItemStack {
  ItemType<ItemPitcherCropStack> PITCHER_CROP_TYPE = ItemTypeBuilder
          .builder(ItemPitcherCropStack.class)
          .vanillaItem(VanillaItemId.PITCHER_CROP)
          .build();
}
