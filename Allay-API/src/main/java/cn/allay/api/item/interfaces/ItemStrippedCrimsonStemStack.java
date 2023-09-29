package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCrimsonStemStack extends ItemStack {
  ItemType<ItemStrippedCrimsonStemStack> STRIPPED_CRIMSON_STEM_TYPE = ItemTypeBuilder
          .builder(ItemStrippedCrimsonStemStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_CRIMSON_STEM)
          .build();
}
