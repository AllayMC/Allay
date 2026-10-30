package org.allaymc.api.world.particle;

import org.allaymc.api.block.data.Instrument;

/**
 * {@code NoteParticle} describes a note-block visual using an instrument and
 * pitch. The runtime sends these values through a block event, using the
 * caller's effect position as block coordinates; this record retains the
 * supplied pitch without range validation.
 *
 * @param instrument note-block instrument selection
 *
 * @param pitch unvalidated note pitch value
 *
 * @author daoge_cmd
 */
public record NoteParticle(Instrument instrument, int pitch) implements Particle {
}
