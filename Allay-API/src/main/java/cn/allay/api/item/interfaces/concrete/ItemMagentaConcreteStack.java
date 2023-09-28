package cn.allay.api.item.interfaces.concrete;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaConcreteStack extends ItemStack {
  ItemType<ItemMagentaConcreteStack> MAGENTA_CONCRETE_TYPE = ItemTypeBuilder
          .builder(ItemMagentaConcreteStack.class)
          .vanillaItem(VanillaItemId.MAGENTA_CONCRETE)
          .build();
}
