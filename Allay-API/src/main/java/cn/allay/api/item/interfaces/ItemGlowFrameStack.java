package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowFrameStack extends ItemStack {
  ItemType<ItemGlowFrameStack> GLOW_FRAME_TYPE = ItemTypeBuilder
          .builder(ItemGlowFrameStack.class)
          .vanillaItem(VanillaItemId.GLOW_FRAME)
          .build();
}
