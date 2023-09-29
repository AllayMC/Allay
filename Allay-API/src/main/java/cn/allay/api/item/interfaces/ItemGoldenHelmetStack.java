package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHelmetStack extends ItemStack {
  ItemType<ItemGoldenHelmetStack> GOLDEN_HELMET_TYPE = ItemTypeBuilder
          .builder(ItemGoldenHelmetStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_HELMET)
          .build();
}
