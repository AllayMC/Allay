package org.allaymc.api.entity.effect;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Setter
@Getter
@AllArgsConstructor
public final class EffectInstance {
    private final EffectType type;
    private int amplifier;
    private int duration;
    private boolean ambient;
    private boolean visible;

    public static EffectInstance fromNBT(NbtMap nbt) {
        var effectType = Registries.EFFECTS.getByK1((int) nbt.getByte("Id"));
        Preconditions.checkNotNull(effectType, "Effect type not found for id: " + nbt.getByte("Id") + "!");
        var amplifier = nbt.getByte("Amplifier");
        var duration = nbt.getInt("Duration");
        var ambient = nbt.getBoolean("Ambient");
        var visible = nbt.getBoolean("ShowParticles");
        return new EffectInstance(effectType, amplifier, duration, ambient, visible);
    }

    public int getLevel() {
        return getAmplifier() + 1;
    }

    public NbtMap saveNBT() {
        return NbtMap.builder()
                .putByte("Id", (byte) type.getId())
                .putByte("Amplifier", (byte) amplifier)
                // TODO: DurationEasy, DurationNormal, DurationHard
                .putInt("Duration", duration)
                .putBoolean("Ambient", ambient)
                // TODO: DisplayOnScreenTextureAnimation
                .putBoolean("ShowParticles", visible)
                .build();
    }
}
