package org.allaymc.api.world.sound;

/**
 * {@code CustomSound} describes a named sound with explicit volume and pitch.
 * The runtime forwards these values to the client without range validation
 * here; names may come from {@link SoundNames} or from sound resources
 * available to that client.
 *
 * @param soundName client sound resource name
 *
 * @param volume unvalidated playback volume multiplier
 *
 * @param pitch unvalidated playback pitch multiplier
 *
 * @author daoge_cmd
 */
public record CustomSound(String soundName, float volume, float pitch) implements Sound {
    /**
     * Creates a named sound with volume and pitch both set to one. The
     * supplied name is forwarded unchanged, and the client must have a
     * matching sound resource.
     */
    public CustomSound(String soundName) {
        this(soundName, 1, 1);
    }
}
