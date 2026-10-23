package org.allaymc.api.entity.effect;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Range;

/**
 * {@code EffectInstance} holds mutable amplifier, remaining duration, and
 * particle flags for one effect type. Living entities retain the instance
 * supplied during accepted addition, so later mutations affect active behavior;
 * the generated constructor and setters do not enforce the documented amplifier
 * range. NBT persistence narrows the numeric ID and amplifier to signed bytes.
 *
 * @author daoge_cmd
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public final class EffectInstance {
    /**
     * Registered effect type reference.
     */
    private final EffectType type;
    /**
     * {@code amplifier} is the zero-based strength used by {@code getLevel}.
     * The range annotation documents expected values but does not validate
     * constructor or setter input.
     */
    @Range(from = 0, to = Short.MAX_VALUE)
    private int amplifier;
    /**
     * {@code duration} counts remaining game ticks. The living component
     * decrements it before {@code onTick}, then requests removal when the
     * resulting value is nonpositive.
     */
    private int duration;
    /**
     * Reduced-particle ambient presentation flag.
     */
    private boolean ambient;
    /**
     * Effect particle visibility flag.
     */
    private boolean visible;

    /**
     * Creates another instance with copied scalar settings and the same effect
     * type reference. Later mutations to either instance's amplifier, duration,
     * or flags do not change the other's copied settings.
     */
    public EffectInstance(EffectInstance effect) {
        this.type = effect.type;
        this.amplifier = effect.amplifier;
        this.duration = effect.duration;
        this.ambient = effect.ambient;
        this.visible = effect.visible;
    }

    /**
     * Creates an instance from numeric effect metadata in NBT. {@code "Id"} and
     * {@code "Amplifier"} are read as signed bytes, and the effect registry
     * must already contain the decoded ID; an unknown ID throws {@code
     * NullPointerException}.
     */
    public static EffectInstance fromNBT(NbtMap nbt) {
        var effectType = Registries.EFFECTS.getByK1((int) nbt.getByte("Id"));
        Preconditions.checkNotNull(effectType, "Effect type not found for id: " + nbt.getByte("Id") + "!");
        var amplifier = nbt.getByte("Amplifier");
        var duration = nbt.getInt("Duration");
        var ambient = nbt.getBoolean("Ambient");
        var visible = nbt.getBoolean("ShowParticles");
        return new EffectInstance(effectType, amplifier, duration, ambient, visible);
    }

    /**
     * Returns the one-based effect level by adding one to the amplifier.
     */
    @Range(from = 1, to = Short.MAX_VALUE + 1)
    public int getLevel() {
        return getAmplifier() + 1;
    }

    /**
     * Serializes the effect settings using byte ID and amplifier fields. Values
     * outside signed-byte range are narrowed and may not round-trip through
     * {@code fromNBT}; duration remains an integer game-tick count.
     */
    public NbtMap saveNBT() {
        return NbtMap.builder()
                // NBT stores these fields as bytes, so IDs and amplifiers outside
                // the signed-byte range lose high bits before a later fromNBT read.
                .putByte("Id", (byte) type.getId())
                .putByte("Amplifier", (byte) amplifier)
                // TODO: Add difficulty-specific DurationEasy, DurationNormal, and
                // DurationHard NBT fields when effect loading supports them.
                .putInt("Duration", duration)
                .putBoolean("Ambient", ambient)
                // TODO: Persist DisplayOnScreenTextureAnimation when effect
                // instances expose that presentation flag.
                .putBoolean("ShowParticles", visible)
                .build();
    }
}
