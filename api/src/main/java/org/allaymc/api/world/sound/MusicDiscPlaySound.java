package org.allaymc.api.world.sound;

import org.allaymc.api.item.data.DiscType;

/**
 * {@code MusicDiscPlaySound} describes playback of the selected jukebox music
 * disc. The runtime maps {@code discType} to its record event, and protocol
 * versions can suppress unsupported newer discs; the caller controls the
 * jukebox contents and when playback begins.
 *
 * @param discType disc selecting the record event
 */
public record MusicDiscPlaySound(DiscType discType) implements Sound {
}
