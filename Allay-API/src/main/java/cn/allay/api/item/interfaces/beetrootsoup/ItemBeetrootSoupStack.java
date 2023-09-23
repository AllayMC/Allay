package cn.allay.api.item.interfaces.beetrootsoup;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootSoupStack extends ItemStack {
    ItemType<ItemBeetrootSoupStack> BEETROOT_SOUP_TYPE = ItemTypeBuilder
            .builder(ItemBeetrootSoupStack.class)
            .vanillaItem(VanillaItemId.BEETROOT_SOUP)
            .build();
}
