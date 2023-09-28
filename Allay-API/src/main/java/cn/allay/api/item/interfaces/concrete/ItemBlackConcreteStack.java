package cn.allay.api.item.interfaces.concrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackConcreteStack extends ItemStack {
  ItemType<ItemBlackConcreteStack> BLACK_CONCRETE_TYPE = ItemTypeBuilder
          .builder(ItemBlackConcreteStack.class)
          .vanillaItem(VanillaItemId.BLACK_CONCRETE)
          .build();
}
