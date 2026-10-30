package org.allaymc.api.world.sound;

import org.allaymc.api.block.data.Instrument;

/**
 * {@code NoteSound} describes a note-block sound with an instrument and pitch.
 * The runtime places the instrument ordinal in the high byte and the pitch in
 * the low byte without masking, so callers provide pitch values suitable for
 * that encoding.
 *
 * @param instrument note-block instrument selection
 *
 * @param pitch unvalidated note pitch value
 */
public record NoteSound(Instrument instrument, int pitch) implements Sound {
}
