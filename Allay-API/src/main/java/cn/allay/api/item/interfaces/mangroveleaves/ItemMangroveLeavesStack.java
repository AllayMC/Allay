package cn.allay.api.item.interfaces.mangroveleaves;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveLeavesStack extends ItemStack {
    ItemType<ItemMangroveLeavesStack> MANGROVE_LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemMangroveLeavesStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_LEAVES)
            .build();
}
