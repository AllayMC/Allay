package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscChirpStack extends ItemStack {
    ItemType<ItemMusicDiscChirpStack> MUSIC_DISC_CHIRP_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscChirpStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_CHIRP)
            .build();
}
