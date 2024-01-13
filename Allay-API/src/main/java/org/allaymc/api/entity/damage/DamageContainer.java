package org.allaymc.api.entity.damage;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;

import java.util.function.Function;

/**
 * Allay Project 2024/1/12
 *
 * @author daoge_cmd
 */
@Getter
public class DamageContainer {
    public static final float DEFAULT_KNOCK_BACK = 0.3f;
    public static final int DEFAULT_DAMAGE_COOLDOWN = 10;
    protected Entity attacker;
    protected DamageType damageType;
    protected float sourceDamage;
    @Setter
    protected float finalDamage;
    @Setter
    protected float knockback = DEFAULT_KNOCK_BACK;
    @Setter
    protected int cooldown = DEFAULT_DAMAGE_COOLDOWN;

    public DamageContainer(Entity attacker, DamageType damageType, float sourceDamage) {
        // attacker is nullable
        Preconditions.checkNotNull(damageType);
        this.attacker = attacker;
        this.damageType = damageType;
        this.sourceDamage = sourceDamage;
        this.finalDamage = sourceDamage;
    }

    public static DamageContainer simpleAttack(float sourceDamage) {
        return new DamageContainer(null, DamageType.API, sourceDamage);
    }

    public static DamageContainer entityAttack(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, DamageType.ENTITY_ATTACK, sourceDamage);
    }

    public void updateFinalDamage(Function<Float, Float> updater) {
        this.finalDamage = updater.apply(this.finalDamage);
    }

    public enum DamageType {
        ENTITY_ATTACK,
        FALL,
        VOID,
        PROJECTILE,
        DROWNING,
        CACTUS,
        LAVA,
        ON_FIRE,
        FIRE,
        ENTITY_EXPLODE,
        MAGIC_EFFECT,
        STARVE,
        API,
        COMMAND,
    }
}
