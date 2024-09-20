package org.allaymc.api.entity.effect;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public final class EffectInstance {
    private final EffectType type;
    private int amplifier;
    private int duration;
    private boolean visible;

    public static EffectInstance fromNBT(NbtMap nbt) {
        var effectType = Registries.EFFECTS.getByK1((int) nbt.getByte("Id"));
        Preconditions.checkNotNull(effectType, "Effect type not found for id: " + nbt.getByte("Id") + "!");
        int amplifier = nbt.getByte("Amplifier");
        int duration = nbt.getInt("Duration");
        boolean visible = nbt.getBoolean("ShowParticles");
        return new EffectInstance(effectType, amplifier, duration, visible);
    }

    public EffectType getType() {
        return null;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public int getLevel() {
        return getAmplifier() + 1;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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
