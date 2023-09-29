package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCocoaBeansStack extends ItemStack {
  ItemType<ItemCocoaBeansStack> COCOA_BEANS_TYPE = ItemTypeBuilder
          .builder(ItemCocoaBeansStack.class)
          .vanillaItem(VanillaItemId.COCOA_BEANS)
          .build();
}
