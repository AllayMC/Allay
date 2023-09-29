package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndPortalFrameStack extends ItemStack {
  ItemType<ItemEndPortalFrameStack> END_PORTAL_FRAME_TYPE = ItemTypeBuilder
          .builder(ItemEndPortalFrameStack.class)
          .vanillaItem(VanillaItemId.END_PORTAL_FRAME)
          .build();
}
