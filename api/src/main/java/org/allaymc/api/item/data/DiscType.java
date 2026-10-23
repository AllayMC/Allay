package org.allaymc.api.item.data;

import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;

/**
 * {@code DiscType} represents a music disc's translation key and jukebox comparator
 * signal. Each constant stores its signal explicitly rather than deriving it
 * from enum order; several disc kinds share the same signal strength, so that
 * value does not uniquely identify a disc.
 *
 * @author daoge_cmd
 */
public enum DiscType {
    /**
     * 13 music disc.
     */
    DISC_13(TrKeys.MC_ITEM_RECORD_13_DESC, 1),
    /**
     * Cat music disc.
     */
    DISC_CAT(TrKeys.MC_ITEM_RECORD_CAT_DESC, 2),
    /**
     * Blocks music disc.
     */
    DISC_BLOCKS(TrKeys.MC_ITEM_RECORD_BLOCKS_DESC, 3),
    /**
     * Chirp music disc.
     */
    DISC_CHIRP(TrKeys.MC_ITEM_RECORD_CHIRP_DESC, 4),
    /**
     * Far music disc.
     */
    DISC_FAR(TrKeys.MC_ITEM_RECORD_FAR_DESC, 5),
    /**
     * Mall music disc.
     */
    DISC_MALL(TrKeys.MC_ITEM_RECORD_MALL_DESC, 6),
    /**
     * Mellohi music disc.
     */
    DISC_MELLOHI(TrKeys.MC_ITEM_RECORD_MELLOHI_DESC, 7),
    /**
     * Stal music disc.
     */
    DISC_STAL(TrKeys.MC_ITEM_RECORD_STAL_DESC, 8),
    /**
     * Strad music disc.
     */
    DISC_STRAD(TrKeys.MC_ITEM_RECORD_STRAD_DESC, 9),
    /**
     * Ward music disc.
     */
    DISC_WARD(TrKeys.MC_ITEM_RECORD_WARD_DESC, 10),
    /**
     * 11 music disc.
     */
    DISC_11(TrKeys.MC_ITEM_RECORD_11_DESC, 11),
    /**
     * Wait music disc.
     */
    DISC_WAIT(TrKeys.MC_ITEM_RECORD_WAIT_DESC, 12),
    /**
     * Otherside music disc.
     */
    DISC_OTHERSIDE(TrKeys.MC_ITEM_RECORD_OTHERSIDE_DESC, 14),
    /**
     * Pigstep music disc.
     */
    DISC_PIGSTEP(TrKeys.MC_ITEM_RECORD_PIGSTEP_DESC, 13),
    /**
     * 5 music disc.
     */
    DISC_5(TrKeys.MC_ITEM_RECORD_5_DESC, 15),
    /**
     * Relic music disc.
     */
    DISC_RELIC(TrKeys.MC_ITEM_RECORD_RELIC_DESC, 14),
    /**
     * Creator music disc.
     */
    DISC_CREATOR(TrKeys.MC_ITEM_RECORD_CREATOR_DESC, 12),
    /**
     * Creator music-box disc.
     */
    DISC_CREATOR_MUSIC_BOX(TrKeys.MC_ITEM_RECORD_CREATOR_MUSIC_BOX_DESC, 11),
    /**
     * Precipice music disc.
     */
    DISC_PRECIPICE(TrKeys.MC_ITEM_RECORD_PRECIPICE_DESC, 13),
    /**
     * Tears music disc.
     */
    DISC_TEARS(TrKeys.MC_ITEM_RECORD_TEARS_DESC, 15),
    /**
     * Lava Chicken disc.
     */
    DISC_LAVA_CHICKEN(TrKeys.MC_ITEM_RECORD_LAVA_CHICKEN_DESC, 15),
    /**
     * Bounce music disc.
     */
    DISC_BOUNCE(TrKeys.MC_ITEM_RECORD_NAME, 15);

    /**
     * Music-disc display translation key.
     */
    @Getter
    @MayContainTrKey
    private final String translationKey;

    /**
     * Jukebox comparator signal strength.
     */
    @Getter
    private final int comparatorSignal;

    /**
     * Creates a disc kind with its display key and comparator signal.
     */
    DiscType(String translationKey, int comparatorSignal) {
        this.translationKey = translationKey;
        this.comparatorSignal = comparatorSignal;
    }
}
