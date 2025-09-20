package org.allaymc.api.world.particle;

import org.allaymc.api.block.data.Instrument;

/**
 * NoteParticle is a particle that shows up on note block interactions.
 *
 * @param instrument the instrument of the note block
 * @param pitch      the pitch of the note
 * @author daoge_cmd
 */
public record NoteParticle(Instrument instrument, int pitch) implements Particle {
}
