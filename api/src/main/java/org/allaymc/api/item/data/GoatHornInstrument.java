package org.allaymc.api.item.data;

import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.sound.SoundNames;

/**
 * {@code GoatHornInstrument} identifies a goat horn's display key, sound event,
 * and screaming-goat classification. The enum ordinal is the item metadata value;
 * metadata lookup maps an out-of-range value to {@code PONDER} so callers always
 * receive a supported instrument.
 *
 * @author Miroshka000
 */
@Getter
public enum GoatHornInstrument {
    /**
     * Ponder goat-horn instrument.
     */
    PONDER(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_0, SoundNames.HORN_CALL_0, false),
    /**
     * Sing goat-horn instrument.
     */
    SING(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_1, SoundNames.HORN_CALL_1, false),
    /**
     * Seek goat-horn instrument.
     */
    SEEK(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_2, SoundNames.HORN_CALL_2, false),
    /**
     * Feel goat-horn instrument.
     */
    FEEL(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_3, SoundNames.HORN_CALL_3, false),
    /**
     * Admire goat-horn instrument.
     */
    ADMIRE(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_4, SoundNames.HORN_CALL_4, true),
    /**
     * Call goat-horn instrument.
     */
    CALL(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_5, SoundNames.HORN_CALL_5, true),
    /**
     * Yearn goat-horn instrument.
     */
    YEARN(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_6, SoundNames.HORN_CALL_6, true),
    /**
     * Dream goat-horn instrument.
     */
    DREAM(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_7, SoundNames.HORN_CALL_7, true);

    /**
     * {@code VALUES} caches instruments in metadata order for lookup. Its length
     * defines the accepted metadata range checked by {@code fromMeta}.
     */
    private static final GoatHornInstrument[] VALUES = values();

    /**
     * Instrument display translation key.
     */
    @MayContainTrKey
    private final String translationKey;
    /**
     * Horn-call sound event name.
     */
    private final String soundName;
    /**
     * Screaming-goat instrument variant flag.
     */
    private final boolean screamingGoatVariant;

    /**
     * Creates an instrument with its display, sound, and goat-variant data.
     */
    GoatHornInstrument(String translationKey, String soundName, boolean screamingGoatVariant) {
        this.translationKey = translationKey;
        this.soundName = soundName;
        this.screamingGoatVariant = screamingGoatVariant;
    }

    /**
     * Returns this instrument's ordinal as the goat-horn metadata value.
     */
    public int getMeta() {
        return ordinal();
    }

    /**
     * Returns the instrument represented by the supplied goat-horn metadata.
     * Negative values and values beyond the instrument array select {@code PONDER};
     * valid metadata follows the enum declaration order.
     */
    public static GoatHornInstrument fromMeta(int meta) {
        // Invalid metadata has no corresponding horn sound, so choose PONDER
        // before callers obtain the instrument's translation key or sound event.
        return meta >= 0 && meta < VALUES.length ? VALUES[meta] : PONDER;
    }
}
