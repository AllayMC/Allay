package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailLeggingsStack extends ItemStack {
  ItemType<ItemChainmailLeggingsStack> CHAINMAIL_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemChainmailLeggingsStack.class)
          .vanillaItem(VanillaItemId.CHAINMAIL_LEGGINGS)
          .build();
}
