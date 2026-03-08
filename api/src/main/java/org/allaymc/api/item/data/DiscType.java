package org.allaymc.api.item.data;

import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;

/**
 * DiscType represents the type of music disc.
 * <p>
 * Each disc type has a unique comparator signal output (1-15) when placed in a jukebox.
 *
 * @author daoge_cmd
 */
public enum DiscType {
    // The comparator signal values are based on Minecraft Wiki:
    // https://minecraft.wiki/w/Jukebox#Redstone_component
    DISC_13(TrKeys.MC_ITEM_RECORD_13_DESC, 1),
    DISC_CAT(TrKeys.MC_ITEM_RECORD_CAT_DESC, 2),
    DISC_BLOCKS(TrKeys.MC_ITEM_RECORD_BLOCKS_DESC, 3),
    DISC_CHIRP(TrKeys.MC_ITEM_RECORD_CHIRP_DESC, 4),
    DISC_FAR(TrKeys.MC_ITEM_RECORD_FAR_DESC, 5),
    DISC_MALL(TrKeys.MC_ITEM_RECORD_MALL_DESC, 6),
    DISC_MELLOHI(TrKeys.MC_ITEM_RECORD_MELLOHI_DESC, 7),
    DISC_STAL(TrKeys.MC_ITEM_RECORD_STAL_DESC, 8),
    DISC_STRAD(TrKeys.MC_ITEM_RECORD_STRAD_DESC, 9),
    DISC_WARD(TrKeys.MC_ITEM_RECORD_WARD_DESC, 10),
    DISC_11(TrKeys.MC_ITEM_RECORD_11_DESC, 11),
    DISC_WAIT(TrKeys.MC_ITEM_RECORD_WAIT_DESC, 12),
    DISC_OTHERSIDE(TrKeys.MC_ITEM_RECORD_OTHERSIDE_DESC, 14),
    DISC_PIGSTEP(TrKeys.MC_ITEM_RECORD_PIGSTEP_DESC, 13),
    DISC_5(TrKeys.MC_ITEM_RECORD_5_DESC, 15),
    DISC_RELIC(TrKeys.MC_ITEM_RECORD_RELIC_DESC, 14),
    DISC_CREATOR(TrKeys.MC_ITEM_RECORD_CREATOR_DESC, 12),
    DISC_CREATOR_MUSIC_BOX(TrKeys.MC_ITEM_RECORD_CREATOR_MUSIC_BOX_DESC, 11),
    DISC_PRECIPICE(TrKeys.MC_ITEM_RECORD_PRECIPICE_DESC, 13),
    DISC_TEARS(TrKeys.MC_ITEM_RECORD_TEARS_DESC, 15),
    DISC_LAVA_CHICKEN(TrKeys.MC_ITEM_RECORD_LAVA_CHICKEN_DESC, 15);

    @Getter
    @MayContainTrKey
    private final String translationKey;

    @Getter
    private final int comparatorSignal;

    DiscType(String translationKey, int comparatorSignal) {
        this.translationKey = translationKey;
        this.comparatorSignal = comparatorSignal;
    }
}
