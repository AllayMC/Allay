package org.allaymc.api.entity.effect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public final class SimpleEffectInstance implements EffectInstance {
    private final EffectType type;
    private int amplifier;
    private int duration;
    private boolean visible;

    @Override
    public NbtMap saveNBT() {
        return NbtMap.builder()
                .putByte("Id", (byte) type.getId())
                .putByte("Amplifier", (byte) amplifier)
                // TODO: DurationEasy, DurationNormal, DurationHard
                .putInt("Duration", duration)
                // TODO: Ambient
                // TODO: DisplayOnScreenTextureAnimation
                .putBoolean("ShowParticles", visible)
                .build();
    }
}
