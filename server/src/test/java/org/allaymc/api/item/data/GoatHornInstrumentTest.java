package org.allaymc.api.item.data;

import org.allaymc.api.world.sound.SoundNames;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
class GoatHornInstrumentTest {

    @Test
    void shouldMapAllVanillaVariantsByMeta() {
        var expected = List.of(
                GoatHornInstrument.PONDER,
                GoatHornInstrument.SING,
                GoatHornInstrument.SEEK,
                GoatHornInstrument.FEEL,
                GoatHornInstrument.ADMIRE,
                GoatHornInstrument.CALL,
                GoatHornInstrument.YEARN,
                GoatHornInstrument.DREAM
        );

        for (int meta = 0; meta < expected.size(); meta++) {
            assertEquals(expected.get(meta), GoatHornInstrument.fromMeta(meta));
        }
    }

    @Test
    void shouldExposeMatchingSoundNames() {
        assertEquals(SoundNames.HORN_CALL_0, GoatHornInstrument.PONDER.getSoundName());
        assertEquals(SoundNames.HORN_CALL_1, GoatHornInstrument.SING.getSoundName());
        assertEquals(SoundNames.HORN_CALL_2, GoatHornInstrument.SEEK.getSoundName());
        assertEquals(SoundNames.HORN_CALL_3, GoatHornInstrument.FEEL.getSoundName());
        assertEquals(SoundNames.HORN_CALL_4, GoatHornInstrument.ADMIRE.getSoundName());
        assertEquals(SoundNames.HORN_CALL_5, GoatHornInstrument.CALL.getSoundName());
        assertEquals(SoundNames.HORN_CALL_6, GoatHornInstrument.YEARN.getSoundName());
        assertEquals(SoundNames.HORN_CALL_7, GoatHornInstrument.DREAM.getSoundName());
    }

    @Test
    void shouldFallbackToPonderForInvalidMeta() {
        assertEquals(GoatHornInstrument.PONDER, GoatHornInstrument.fromMeta(-1));
        assertEquals(GoatHornInstrument.PONDER, GoatHornInstrument.fromMeta(8));
        assertEquals(GoatHornInstrument.PONDER, GoatHornInstrument.fromMeta(Integer.MAX_VALUE));
    }

    @Test
    void shouldSeparateRegularAndScreamingGoatVariants() {
        assertFalse(GoatHornInstrument.PONDER.isScreamingGoatVariant());
        assertFalse(GoatHornInstrument.SING.isScreamingGoatVariant());
        assertFalse(GoatHornInstrument.SEEK.isScreamingGoatVariant());
        assertFalse(GoatHornInstrument.FEEL.isScreamingGoatVariant());
        assertTrue(GoatHornInstrument.ADMIRE.isScreamingGoatVariant());
        assertTrue(GoatHornInstrument.CALL.isScreamingGoatVariant());
        assertTrue(GoatHornInstrument.YEARN.isScreamingGoatVariant());
        assertTrue(GoatHornInstrument.DREAM.isScreamingGoatVariant());
    }
}
