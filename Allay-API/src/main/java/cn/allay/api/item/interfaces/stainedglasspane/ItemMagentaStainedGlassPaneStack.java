package cn.allay.api.item.interfaces.stainedglasspane;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaStainedGlassPaneStack extends ItemStack {
  ItemType<ItemMagentaStainedGlassPaneStack> MAGENTA_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemMagentaStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.MAGENTA_STAINED_GLASS_PANE)
          .build();
}
