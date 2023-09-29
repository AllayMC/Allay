package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonStemStack extends ItemStack {
  ItemType<ItemCrimsonStemStack> CRIMSON_STEM_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonStemStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_STEM)
          .build();
}
