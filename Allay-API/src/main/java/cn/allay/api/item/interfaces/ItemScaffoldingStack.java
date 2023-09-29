package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemScaffoldingStack extends ItemStack {
  ItemType<ItemScaffoldingStack> SCAFFOLDING_TYPE = ItemTypeBuilder
          .builder(ItemScaffoldingStack.class)
          .vanillaItem(VanillaItemId.SCAFFOLDING)
          .build();
}
