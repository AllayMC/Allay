package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRapidFertilizerStack extends ItemStack {
  ItemType<ItemRapidFertilizerStack> RAPID_FERTILIZER_TYPE = ItemTypeBuilder
          .builder(ItemRapidFertilizerStack.class)
          .vanillaItem(VanillaItemId.RAPID_FERTILIZER)
          .build();
}
