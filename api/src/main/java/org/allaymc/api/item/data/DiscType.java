package org.allaymc.api.item.data;

import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;

/**
 * DiscType represents the type of music disc.
 *
 * @author daoge_cmd
 */
public enum DiscType {
    DISC_13(TrKeys.MC_ITEM_RECORD_13_DESC),
    DISC_CAT(TrKeys.MC_ITEM_RECORD_CAT_DESC),
    DISC_BLOCKS(TrKeys.MC_ITEM_RECORD_BLOCKS_DESC),
    DISC_CHIRP(TrKeys.MC_ITEM_RECORD_CHIRP_DESC),
    DISC_FAR(TrKeys.MC_ITEM_RECORD_FAR_DESC),
    DISC_MALL(TrKeys.MC_ITEM_RECORD_MALL_DESC),
    DISC_MELLOHI(TrKeys.MC_ITEM_RECORD_MELLOHI_DESC),
    DISC_STAL(TrKeys.MC_ITEM_RECORD_STAL_DESC),
    DISC_STRAD(TrKeys.MC_ITEM_RECORD_STRAD_DESC),
    DISC_WARD(TrKeys.MC_ITEM_RECORD_WARD_DESC),
    DISC_11(TrKeys.MC_ITEM_RECORD_11_DESC),
    DISC_WAIT(TrKeys.MC_ITEM_RECORD_WAIT_DESC),
    DISC_OTHERSIDE(TrKeys.MC_ITEM_RECORD_OTHERSIDE_DESC),
    DISC_PIGSTEP(TrKeys.MC_ITEM_RECORD_PIGSTEP_DESC),
    DISC_5(TrKeys.MC_ITEM_RECORD_5_DESC),
    DISC_RELIC(TrKeys.MC_ITEM_RECORD_RELIC_DESC),
    DISC_CREATOR(TrKeys.MC_ITEM_RECORD_CREATOR_DESC),
    DISC_CREATOR_MUSIC_BOX(TrKeys.MC_ITEM_RECORD_CREATOR_MUSIC_BOX_DESC),
    DISC_PRECIPICE(TrKeys.MC_ITEM_RECORD_PRECIPICE_DESC),
    DISC_TEARS(TrKeys.MC_ITEM_RECORD_TEARS_DESC),
    DISC_LAVA_CHICKEN(TrKeys.MC_ITEM_RECORD_LAVA_CHICKEN_DESC);

    @Getter
    @MayContainTrKey
    private final String translationKey;

    DiscType(String translationKey) {
        this.translationKey = translationKey;
    }
}
