package org.allaymc.api.entity.effect;

import com.google.common.base.Preconditions;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public interface EffectInstance {

    EffectType getType();

    int getAmplifier();

    void setAmplifier(int amplifier);

    boolean isVisible();

    void setVisible(boolean visible);

    int getDuration();

    void setDuration(int duration);

    NbtMap saveNBT();

    static EffectInstance fromNBT(NbtMap nbt) {
        var effectType = EffectRegistry.getRegistry().getByK1((int) nbt.getByte("Id"));
        Preconditions.checkNotNull(effectType, "Effect type not found for id: " + nbt.getByte("Id") + "!");
        int amplifier = nbt.getByte("Amplifier");
        int duration = nbt.getInt("Duration");
        boolean visible = nbt.getBoolean("ShowParticles");
        return new SimpleEffectInstance(effectType, amplifier, duration, visible);
    }
}
