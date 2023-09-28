package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateSlabStack extends ItemStack {
  ItemType<ItemCobbledDeepslateSlabStack> COBBLED_DEEPSLATE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemCobbledDeepslateSlabStack.class)
          .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_SLAB)
          .build();
}
