package org.allaymc.api.world.sound;

import org.allaymc.api.block.data.Instrument;

/**
 * Sound produced by a note block.
 *
 * @param instrument the instrument used by the note block
 * @param pitch      the pitch of the note (implementation-specific interpretation)
 */
public record NoteSound(Instrument instrument, int pitch) implements Sound {
}
