package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailBootsStack extends ItemStack {
  ItemType<ItemChainmailBootsStack> CHAINMAIL_BOOTS_TYPE = ItemTypeBuilder
          .builder(ItemChainmailBootsStack.class)
          .vanillaItem(VanillaItemId.CHAINMAIL_BOOTS)
          .build();
}
