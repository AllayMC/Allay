package org.allaymc.api.item.data;

import org.allaymc.api.world.sound.SoundNames;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code GoatHornInstrumentTest} verifies horn metadata, sound names, and goat-variant classification.
 *
 * @author Miroshka000
 */
class GoatHornInstrumentTest {

    /**
     * Checks each supported metadata value against the expected instrument order.
     * The mapping must preserve every vanilla horn variant rather than shifting
     * later variants when the enum is edited.
     */
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

    /**
     * Checks every instrument's sound event against its expected horn-call name.
     * The assertions protect variant-specific playback independently of the metadata
     * mapping and translated item display name.
     */
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

    /**
     * Queries negative, first-out-of-range, and very large horn metadata values.
     * Each invalid value must select {@code PONDER}, protecting the usable default
     * instrument for malformed item metadata.
     */
    @Test
    void shouldFallbackToPonderForInvalidMeta() {
        assertEquals(GoatHornInstrument.PONDER, GoatHornInstrument.fromMeta(-1));
        assertEquals(GoatHornInstrument.PONDER, GoatHornInstrument.fromMeta(8));
        assertEquals(GoatHornInstrument.PONDER, GoatHornInstrument.fromMeta(Integer.MAX_VALUE));
    }

    /**
     * Checks the classification flags for regular and screaming-goat horn variants.
     * The first four instruments must remain regular, while the remaining four
     * must retain their screaming-goat classification.
     */
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
