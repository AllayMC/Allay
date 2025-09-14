package org.allaymc.api.world.sound;

/**
 * Sound sent when a music disc starts playing in a jukebox.
 *
 * @param discType the type of disc that started playing
 */
public record MusicDiscPlaySound(DiscType discType) implements Sound {
}
