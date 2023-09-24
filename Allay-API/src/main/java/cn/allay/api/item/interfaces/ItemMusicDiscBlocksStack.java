package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscBlocksStack extends ItemStack {
    ItemType<ItemMusicDiscBlocksStack> MUSIC_DISC_BLOCKS_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscBlocksStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_BLOCKS)
            .build();
}
