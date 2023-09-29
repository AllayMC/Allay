package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLapisOreStack extends ItemStack {
  ItemType<ItemLapisOreStack> LAPIS_ORE_TYPE = ItemTypeBuilder
          .builder(ItemLapisOreStack.class)
          .vanillaItem(VanillaItemId.LAPIS_ORE)
          .build();
}
