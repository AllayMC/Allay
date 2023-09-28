package cn.allay.api.item.interfaces.wood;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryWoodStack extends ItemStack {
  ItemType<ItemCherryWoodStack> CHERRY_WOOD_TYPE = ItemTypeBuilder
          .builder(ItemCherryWoodStack.class)
          .vanillaItem(VanillaItemId.CHERRY_WOOD)
          .build();
}
