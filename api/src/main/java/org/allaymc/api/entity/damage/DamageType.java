package org.allaymc.api.entity.damage;

import com.google.common.collect.Sets;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.utils.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Represents various types of damage that an entity can experience in a game.
 *
 * @author daoge_cmd
 */
public class DamageType {
    public static DamageType ANVIL = fixed(TrKeys.MC_DEATH_ATTACK_ANVIL);
    /**
     * Block explosion damage
     */
    public static DamageType BLOCK_EXPLOSION = dynamic(block -> {
        if (block == BlockTypes.BED) return TrKeys.MC_DEATH_ATTACK_EXPLOSION_BY_BED;
        return TrKeys.MC_DEATH_ATTACK_EXPLOSION;
    });
    public static DamageType CHARGING = defaultFixed(); // TODO
    /**
     * Damage caused by contact with a block such as a Cactus
     */
    public static DamageType CONTACT = dynamic(block -> {
        if (block == BlockTypes.CACTUS) return TrKeys.MC_DEATH_ATTACK_CACTUS;
        if (block == BlockTypes.SWEET_BERRY_BUSH) return TrKeys.MC_DEATH_ATTACK_SWEETBERRY;
        return TrKeys.MC_DEATH_ATTACK_GENERIC;
    });
    /**
     * Damage caused by running out of air underwater
     */
    public static DamageType DROWN = fixed(TrKeys.MC_DEATH_ATTACK_DROWN);
    /**
     * Damage caused by being attacked by another entity
     */
    public static DamageType ENTITY_ATTACK = dynamicWithExtraSingleParam(attacker -> switch (attacker) {
        case EntityPlayer $ -> TrKeys.MC_DEATH_ATTACK_PLAYER;
        case Entity $ -> TrKeys.MC_DEATH_ATTACK_MOB;
        default -> TrKeys.MC_DEATH_ATTACK_GENERIC;
    }, attacker -> switch (attacker) {
        case EntityPlayer player -> player.getDisplayName();
        case Entity entity -> entity.getNameTag() == null ? entity.getDisplayName() : entity.getNameTag();
        default -> null;
    });
    /**
     * Entity explosion damage
     */
    public static DamageType ENTITY_EXPLOSION = dynamicWithExtraSingleParam(attacker -> {
        if (attacker instanceof Entity) return TrKeys.MC_DEATH_ATTACK_EXPLOSION_PLAYER;
        return TrKeys.MC_DEATH_ATTACK_EXPLOSION;
    }, attacker -> switch (attacker) {
        case EntityPlayer player -> player.getDisplayName();
        case Entity entity -> entity.getNameTag() == null ? entity.getDisplayName() : entity.getNameTag();
        default -> null;
    });
    /**
     * Fall damage
     */
    public static DamageType FALL = fixed(TrKeys.MC_DEATH_ATTACK_FALL);
    public static DamageType FALLING_BLOCK = fixed(TrKeys.MC_DEATH_ATTACK_FALLINGBLOCK);
    /**
     * Damage caused by standing in fire
     */
    public static DamageType FIRE = fixed(TrKeys.MC_DEATH_ATTACK_ONFIRE);
    public static DamageType FIREBALL = fixed(TrKeys.MC_DEATH_ATTACK_FIREBALL);
    /**
     * Burn damage
     */
    public static DamageType FIRE_TICK = fixed(TrKeys.MC_DEATH_ATTACK_INFIRE);
    public static DamageType FIREWORKS = fixed(TrKeys.MC_DEATH_ATTACK_FIREWORKS);
    public static DamageType FLY_INTO_WALL = fixed(TrKeys.MC_DEATH_ATTACK_FLYINTOWALL);
    public static DamageType FREEZING = fixed(TrKeys.MC_DEATH_ATTACK_FREEZE);
    /**
     * Damage caused by standing in lava
     */
    public static DamageType LAVA = fixed(TrKeys.MC_DEATH_ATTACK_LAVA);
    /**
     * Damage caused by being struck by lightning
     */
    public static DamageType LIGHTNING = fixed(TrKeys.MC_DEATH_ATTACK_LIGHTNINGBOLT);
    /**
     * Damage caused by standing on a campfire
     */
    public static DamageType CAMPFIRE = fixed(TrKeys.MC_DEATH_ATTACK_INFIRE);
    /**
     * Potion or spell damage
     */
    public static DamageType MAGIC = fixed(TrKeys.MC_DEATH_ATTACK_MAGIC);
    /**
     * Damage caused by standing on magma block
     */
    public static DamageType MAGMA = fixed(TrKeys.MC_DEATH_ATTACK_MAGMA);
    public static DamageType PISTON = defaultFixed(); // TODO
    /**
     * Damage caused by being hit by a projectile such as an arrow
     */
    public static DamageType PROJECTILE = fixedWithExtraSingleParam(TrKeys.MC_DEATH_ATTACK_ARROW, projectile -> {
        var attacker = ((EntityProjectile) projectile).getShooter();
        String name;
        if (attacker instanceof EntityPlayer player) {
            name = player.getDisplayName();
        } else {
            name = attacker.getNameTag() == null ? attacker.getDisplayName() : attacker.getNameTag();
        }
        return name;
    });
    public static DamageType RAM_ATTACK = defaultFixed(); // TODO
    public static DamageType STALACTITE = fixed(TrKeys.MC_DEATH_ATTACK_STALACTITE);
    public static DamageType STALAGMITE = fixed(TrKeys.MC_DEATH_ATTACK_STALAGMITE);
    /**
     * Damage caused by hunger
     */
    public static DamageType STARVE = fixed(TrKeys.MC_DEATH_ATTACK_STARVE);
    /**
     * Damage caused by being put in a block
     */
    public static DamageType SUFFOCATION = fixed(TrKeys.MC_DEATH_ATTACK_INWALL);
    /**
     * Damage caused by submitting /kill command
     */
    public static DamageType COMMAND = defaultFixed();
    public static DamageType SONIC_BOOM = fixed(TrKeys.MC_DEATH_ATTACK_SONICBOOM);
    public static DamageType TEMPERATURE = defaultFixed(); // TODO
    /**
     * Damage caused by thorns enchantment
     */
    public static DamageType THORNS = fixedWithExtraSingleParam(TrKeys.MC_DEATH_ATTACK_THORNS, attacker -> switch (attacker) {
        case EntityPlayer player -> player.getDisplayName();
        case Entity entity -> entity.getNameTag() == null ? entity.getDisplayName() : entity.getNameTag();
        default -> null;
    });
    /**
     * Damage caused by falling into the void
     */
    public static DamageType VOID = fixed(TrKeys.MC_DEATH_ATTACK_OUTOFWORLD);
    public static Set<DamageType> CANNOT_BE_REDUCED_BY_ARMOR_DAMAGE_TYPES = Sets.newHashSet(
            FIRE_TICK, SUFFOCATION, DROWN,
            STARVE, VOID, MAGIC, COMMAND,
            FLY_INTO_WALL, FREEZING, SONIC_BOOM
    );
    public static DamageType WITHER = fixed(TrKeys.MC_DEATH_ATTACK_WITHER);
    /**
     * Plugins
     */
    public static DamageType API = defaultFixed();

    protected Function<Object, String> deathInfoProvider;
    protected Function<Object, String[]> deathInfoExtraParamsProvider;

    private DamageType(Function<Object, String> deathInfoProvider) {
        this(deathInfoProvider, $ -> new String[0]);
    }

    private DamageType(Function<Object, String> deathInfoProvider, Function<Object, String[]> deathInfoExtraParamsProvider) {
        this.deathInfoProvider = deathInfoProvider;
        this.deathInfoExtraParamsProvider = deathInfoExtraParamsProvider;
    }

    public static DamageType fixed(@MayContainTrKey String deathInfo) {
        return new DamageType($ -> deathInfo);
    }

    public static DamageType dynamic(Function<Object, String> deathInfoProvider) {
        return new DamageType(deathInfoProvider);
    }

    public static DamageType dynamicWithExtraParams(Function<Object, String> deathInfoProvider, Function<Object, String[]> deathInfoExtraParamsProvider) {
        return new DamageType(deathInfoProvider, deathInfoExtraParamsProvider);
    }

    public static DamageType dynamicWithExtraSingleParam(Function<Object, String> deathInfoProvider, Function<Object, String> deathInfoExtraSingleParamProvider) {
        return new DamageType(deathInfoProvider, attacker -> {
            var singleParam = deathInfoExtraSingleParamProvider.apply(attacker);
            return singleParam != null ? new String[]{singleParam} : new String[0];
        });
    }

    public static DamageType fixedWithExtraSingleParam(@MayContainTrKey String deathInfo, Function<Object, String> deathInfoExtraSingleParamProvider) {
        return new DamageType($ -> deathInfo, attacker -> {
            var singleParam = deathInfoExtraSingleParamProvider.apply(attacker);
            return singleParam != null ? new String[]{singleParam} : new String[0];
        });
    }

    public static DamageType defaultFixed() {
        return new DamageType($ -> TrKeys.MC_DEATH_ATTACK_GENERIC);
    }

    public Pair<String, String[]> getDeathInfo(Entity victim, Object attacker) {
        // Put victim name to the first place
        List<String> params = new ArrayList<>();
        params.add(victim.getDisplayName());
        params.addAll(Arrays.asList(deathInfoExtraParamsProvider.apply(attacker)));
        return new Pair<>(deathInfoProvider.apply(attacker), params.toArray(String[]::new));
    }
}
