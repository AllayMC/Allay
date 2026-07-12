package org.allaymc.api.item.data;

import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.sound.SoundNames;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
@Getter
public enum GoatHornInstrument {
    PONDER(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_0, SoundNames.HORN_CALL_0, false),
    SING(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_1, SoundNames.HORN_CALL_1, false),
    SEEK(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_2, SoundNames.HORN_CALL_2, false),
    FEEL(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_3, SoundNames.HORN_CALL_3, false),
    ADMIRE(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_4, SoundNames.HORN_CALL_4, true),
    CALL(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_5, SoundNames.HORN_CALL_5, true),
    YEARN(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_6, SoundNames.HORN_CALL_6, true),
    DREAM(TrKeys.MC_ITEM_MINECRAFT_GOAT_HORN_SOUND_7, SoundNames.HORN_CALL_7, true);

    private static final GoatHornInstrument[] VALUES = values();

    @MayContainTrKey
    private final String translationKey;
    private final String soundName;
    private final boolean screamingGoatVariant;

    GoatHornInstrument(String translationKey, String soundName, boolean screamingGoatVariant) {
        this.translationKey = translationKey;
        this.soundName = soundName;
        this.screamingGoatVariant = screamingGoatVariant;
    }

    public int getMeta() {
        return ordinal();
    }

    public static GoatHornInstrument fromMeta(int meta) {
        return meta >= 0 && meta < VALUES.length ? VALUES[meta] : PONDER;
    }
}
