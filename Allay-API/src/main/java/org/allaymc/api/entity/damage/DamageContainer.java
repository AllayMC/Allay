package org.allaymc.api.entity.damage;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;

import java.util.function.UnaryOperator;

/**
 * Allay Project 2024/1/12
 *
 * @author daoge_cmd
 */
@Getter
public class DamageContainer {
    public static final int DEFAULT_DAMAGE_COOL_DOWN = 10;
    protected Entity attacker;
    protected DamageType damageType;
    protected float sourceDamage;
    @Setter
    protected float finalDamage;
    @Setter
    protected float customKnockback = -1;
    @Setter
    protected int coolDown = DEFAULT_DAMAGE_COOL_DOWN;
    @Setter
    protected boolean critical;

    public DamageContainer(Entity attacker, DamageType damageType, float sourceDamage) {
        // attacker is nullable
        Preconditions.checkNotNull(damageType);
        this.attacker = attacker;
        if (attacker != null) this.critical = attacker.canCriticalAttack();
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

    public boolean hasCustomKnockback() {
        return customKnockback != -1;
    }

    public void updateFinalDamage(UnaryOperator<Float> updater) {
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
