package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitDeepslateRedstoneOreStack extends ItemStack {
  ItemType<ItemLitDeepslateRedstoneOreStack> LIT_DEEPSLATE_REDSTONE_ORE_TYPE = ItemTypeBuilder
          .builder(ItemLitDeepslateRedstoneOreStack.class)
          .vanillaItem(VanillaItemId.LIT_DEEPSLATE_REDSTONE_ORE)
          .build();
}
