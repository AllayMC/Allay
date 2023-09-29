package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledBookshelfStack extends ItemStack {
  ItemType<ItemChiseledBookshelfStack> CHISELED_BOOKSHELF_TYPE = ItemTypeBuilder
          .builder(ItemChiseledBookshelfStack.class)
          .vanillaItem(VanillaItemId.CHISELED_BOOKSHELF)
          .build();
}
