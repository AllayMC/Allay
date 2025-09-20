package org.allaymc.api.world.sound;

/**
 * CustomSound is a sound that accept a string name of the sound. This is useful when
 * the server have custom sound packs installed where you can use custom sound name
 * here. You can also use names in {@link SoundNames} here.
 *
 * @param soundName the name of the sound
 * @param volume    the volume of the sound
 * @param pitch     the pitch of the sound
 * @author daoge_cmd
 */
public record CustomSound(String soundName, float volume, float pitch) implements Sound {
    public CustomSound(String soundName) {
        this(soundName, 1, 1);
    }
}
