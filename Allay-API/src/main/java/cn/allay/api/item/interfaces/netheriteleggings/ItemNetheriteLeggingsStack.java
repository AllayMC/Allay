package cn.allay.api.item.interfaces.netheriteleggings;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteLeggingsStack extends ItemStack {
  ItemType<ItemNetheriteLeggingsStack> NETHERITE_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemNetheriteLeggingsStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_LEGGINGS)
          .build();
}
