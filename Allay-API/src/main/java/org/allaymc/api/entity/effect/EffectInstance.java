package org.allaymc.api.entity.effect;

import com.google.common.base.Preconditions;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public interface EffectInstance {

    static EffectInstance fromNBT(NbtMap nbt) {
        var effectType = Registries.EFFECTS.getByK1((int) nbt.getByte("Id"));
        Preconditions.checkNotNull(effectType, "Effect type not found for id: " + nbt.getByte("Id") + "!");
        int amplifier = nbt.getByte("Amplifier");
        int duration = nbt.getInt("Duration");
        boolean visible = nbt.getBoolean("ShowParticles");
        return new SimpleEffectInstance(effectType, amplifier, duration, visible);
    }

    EffectType getType();

    int getAmplifier();

    void setAmplifier(int amplifier);

    default int getLevel() {
        return getAmplifier() + 1;
    }

    boolean isVisible();

    void setVisible(boolean visible);

    int getDuration();

    void setDuration(int duration);

    NbtMap saveNBT();
}
