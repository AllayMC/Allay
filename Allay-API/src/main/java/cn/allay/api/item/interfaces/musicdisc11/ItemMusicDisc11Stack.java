package cn.allay.api.item.interfaces.musicdisc11;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc11Stack extends ItemStack {
    ItemType<ItemMusicDisc11Stack> MUSIC_DISC_11_TYPE = ItemTypeBuilder
            .builder(ItemMusicDisc11Stack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_11)
            .build();
}
