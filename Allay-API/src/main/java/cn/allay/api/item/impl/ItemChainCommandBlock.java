package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainCommandBlock extends ItemStack {
  ItemType<ItemChainCommandBlock> TYPE = ItemTypeBuilder
          .builder(ItemChainCommandBlock.class)
          .vanillaItem(VanillaItemId.CHAIN_COMMAND_BLOCK, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
