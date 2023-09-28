package cn.allay.api.item.interfaces.wool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueWoolStack extends ItemStack {
  ItemType<ItemLightBlueWoolStack> LIGHT_BLUE_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueWoolStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_WOOL)
          .build();
}
