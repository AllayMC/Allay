package cn.allay.api.item.interfaces.concretepowder;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleConcretePowderStack extends ItemStack {
  ItemType<ItemPurpleConcretePowderStack> PURPLE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemPurpleConcretePowderStack.class)
          .vanillaItem(VanillaItemId.PURPLE_CONCRETE_POWDER)
          .build();
}
