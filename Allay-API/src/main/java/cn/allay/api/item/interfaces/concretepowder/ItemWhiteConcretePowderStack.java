package cn.allay.api.item.interfaces.concretepowder;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteConcretePowderStack extends ItemStack {
  ItemType<ItemWhiteConcretePowderStack> WHITE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemWhiteConcretePowderStack.class)
          .vanillaItem(VanillaItemId.WHITE_CONCRETE_POWDER)
          .build();
}
