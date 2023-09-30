package cn.allay.api.item.interfaces.concretepowder;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeConcretePowderStack extends ItemStack {
  ItemType<ItemOrangeConcretePowderStack> ORANGE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemOrangeConcretePowderStack.class)
          .vanillaItem(VanillaItemId.ORANGE_CONCRETE_POWDER)
          .build();
}
