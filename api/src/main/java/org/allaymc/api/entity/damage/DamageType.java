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
 * {@code DamageType} identifies a damage category and supplies its
 * death-message translation key and parameters. Built-in instances select
 * wording from the attacker's representation, while custom factories accept
 * providers for their own source objects. Each {@code DamageContainer} retains
 * its chosen instance; replacing a public static category does not replace
 * previously retained values.
 *
 * <p>
 * {@code getDeathInfo} reserves the first translation parameter for the
 * victim's display name and appends provider parameters afterward. The two
 * providers must therefore agree on the attacker's representation and the
 * chosen key's parameter order. The public armor-exclusion set is consulted by
 * damage containers at query time, so modifying that set also changes the
 * treatment of existing containers.
 *
 * @author daoge_cmd
 */
public class DamageType {
    /**
     * Falling-anvil damage category.
     */
    public static DamageType ANVIL = fixed(TrKeys.MC_DEATH_ATTACK_ANVIL);
    /**
     * {@code BLOCK_EXPLOSION} supplies explosion death wording. Any non-{@code null}
     * attacker selects the bed-explosion key, while a {@code null} attacker
     * selects the general explosion key.
     */
    public static DamageType BLOCK_EXPLOSION = dynamic(block -> {
        // A retained block source selects the block-specific key; its concrete
        // block type does not change the wording selected by this provider.
        if (block != null) {
            return TrKeys.MC_DEATH_ATTACK_EXPLOSION_BY_BED;
        }

        return TrKeys.MC_DEATH_ATTACK_EXPLOSION;
    });
    /**
     * Charging damage with generic death wording.
     */
    public static DamageType CHARGING = defaultFixed();
    /**
     * {@code CONTACT} selects cactus or sweet-berry wording for the matching
     * block type. Other attacker values, including {@code null}, select the
     * generic death key.
     */
    public static DamageType CONTACT = dynamic(block -> {
        if (block == BlockTypes.CACTUS) return TrKeys.MC_DEATH_ATTACK_CACTUS;
        if (block == BlockTypes.SWEET_BERRY_BUSH) return TrKeys.MC_DEATH_ATTACK_SWEETBERRY;
        return TrKeys.MC_DEATH_ATTACK_GENERIC;
    });
    /**
     * Damage from exhausted underwater air supply.
     */
    public static DamageType DROWN = fixed(TrKeys.MC_DEATH_ATTACK_DROWN);
    /**
     * {@code ENTITY_ATTACK} selects player, mob, or generic attack wording from
     * a non-{@code null} attacker. Entity sources contribute their display name or name
     * tag as the second translation parameter; {@code null} fails in the
     * provider switch.
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
     * {@code ENTITY_EXPLOSION} includes an entity attacker's name in explosion
     * wording. Other non-{@code null} sources use the general explosion key, while
     * {@code null} fails when the additional-parameter provider switches on its
     * source.
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
     * Damage from landing after a fall.
     */
    public static DamageType FALL = fixed(TrKeys.MC_DEATH_ATTACK_FALL);
    /**
     * Damage from a falling block impact.
     */
    public static DamageType FALLING_BLOCK = fixed(TrKeys.MC_DEATH_ATTACK_FALLINGBLOCK);
    /**
     * Damage from direct contact with fire.
     */
    public static DamageType FIRE = fixed(TrKeys.MC_DEATH_ATTACK_ONFIRE);
    /**
     * Damage associated with a fireball impact.
     */
    public static DamageType FIREBALL = fixed(TrKeys.MC_DEATH_ATTACK_FIREBALL);
    /**
     * Damage from an ongoing burning state.
     */
    public static DamageType FIRE_TICK = fixed(TrKeys.MC_DEATH_ATTACK_INFIRE);
    /**
     * Damage associated with a firework explosion.
     */
    public static DamageType FIREWORKS = fixed(TrKeys.MC_DEATH_ATTACK_FIREWORKS);
    /**
     * Damage from flying into a wall.
     */
    public static DamageType FLY_INTO_WALL = fixed(TrKeys.MC_DEATH_ATTACK_FLYINTOWALL);
    /**
     * Damage from prolonged exposure to freezing.
     */
    public static DamageType FREEZING = fixed(TrKeys.MC_DEATH_ATTACK_FREEZE);
    /**
     * Damage from contact with lava.
     */
    public static DamageType LAVA = fixed(TrKeys.MC_DEATH_ATTACK_LAVA);
    /**
     * Damage from a lightning strike.
     */
    public static DamageType LIGHTNING = fixed(TrKeys.MC_DEATH_ATTACK_LIGHTNINGBOLT);
    /**
     * Damage from standing on a lit campfire.
     */
    public static DamageType CAMPFIRE = fixed(TrKeys.MC_DEATH_ATTACK_INFIRE);
    /**
     * Damage from potion or spell effects.
     */
    public static DamageType MAGIC = fixed(TrKeys.MC_DEATH_ATTACK_MAGIC);
    /**
     * Damage from standing on a magma block.
     */
    public static DamageType MAGMA = fixed(TrKeys.MC_DEATH_ATTACK_MAGMA);
    /**
     * Piston damage with generic death wording.
     */
    public static DamageType PISTON = defaultFixed();
    /**
     * {@code PROJECTILE} supplies arrow death wording and names the
     * projectile's shooter. The provider requires an {@code EntityProjectile}
     * attacker with a non-{@code null} shooter; another source representation or an
     * absent shooter fails during death-message generation.
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
    /**
     * Ramming damage with generic death wording.
     */
    public static DamageType RAM_ATTACK = defaultFixed();
    /**
     * Damage from falling pointed dripstone.
     */
    public static DamageType STALACTITE = fixed(TrKeys.MC_DEATH_ATTACK_STALACTITE);
    /**
     * Damage from landing on pointed dripstone.
     */
    public static DamageType STALAGMITE = fixed(TrKeys.MC_DEATH_ATTACK_STALAGMITE);
    /**
     * Damage from an exhausted food supply.
     */
    public static DamageType STARVE = fixed(TrKeys.MC_DEATH_ATTACK_STARVE);
    /**
     * Damage from being trapped inside blocks.
     */
    public static DamageType SUFFOCATION = fixed(TrKeys.MC_DEATH_ATTACK_INWALL);
    /**
     * Command damage with generic death wording.
     */
    public static DamageType COMMAND = defaultFixed();
    /**
     * Damage from a sonic boom attack.
     */
    public static DamageType SONIC_BOOM = fixed(TrKeys.MC_DEATH_ATTACK_SONICBOOM);
    /**
     * Temperature damage with generic death wording.
     */
    public static DamageType TEMPERATURE = defaultFixed();
    /**
     * {@code THORNS} supplies thorns death wording with an entity attacker's
     * name. Other non-{@code null} sources omit the extra parameter, while {@code null}
     * fails in the additional-parameter provider switch.
     */
    public static DamageType THORNS = fixedWithExtraSingleParam(TrKeys.MC_DEATH_ATTACK_THORNS, attacker -> switch (attacker) {
        case EntityPlayer player -> player.getDisplayName();
        case Entity entity -> entity.getNameTag() == null ? entity.getDisplayName() : entity.getNameTag();
        default -> null;
    });
    /**
     * Damage from falling into the void.
     */
    public static DamageType VOID = fixed(TrKeys.MC_DEATH_ATTACK_OUTOFWORLD);
    /**
     * {@code CANNOT_BE_REDUCED_BY_ARMOR_DAMAGE_TYPES} lists categories that
     * bypass armor reduction. The set is mutable and publicly exposed; damage
     * containers consult its current contents whenever
     * {@code canBeReducedByArmor} is called.
     */
    public static Set<DamageType> CANNOT_BE_REDUCED_BY_ARMOR_DAMAGE_TYPES = Sets.newHashSet(
            FIRE_TICK, SUFFOCATION, DROWN,
            STARVE, VOID, MAGIC, COMMAND,
            FLY_INTO_WALL, FREEZING, SONIC_BOOM
    );
    /**
     * Damage associated with the wither effect.
     */
    public static DamageType WITHER = fixed(TrKeys.MC_DEATH_ATTACK_WITHER);
    /**
     * Plugin-supplied damage with generic death wording.
     */
    public static DamageType API = defaultFixed();

    /**
     * {@code deathInfoProvider} selects the death-message key from the supplied
     * attacker. The constructor retains the provider, and {@code getDeathInfo}
     * invokes it for each message after preparing the translation parameters.
     */
    protected Function<Object, String> deathInfoProvider;
    /**
     * {@code deathInfoExtraParamsProvider} supplies parameters following the
     * victim's name. The provider must accept the same attacker representation
     * as {@code deathInfoProvider} and return a non-{@code null} array for message
     * assembly.
     */
    protected Function<Object, String[]> deathInfoExtraParamsProvider;

    /**
     * Creates a category whose key comes from the supplied provider. The
     * additional-parameter provider returns an empty array, leaving the
     * victim's display name as the only translation parameter when a death
     * message is built.
     */
    private DamageType(Function<Object, String> deathInfoProvider) {
        this(deathInfoProvider, $ -> new String[0]);
    }

    /**
     * Creates a category retaining both providers for later message generation.
     * The constructor performs no validation; callers must supply compatible
     * providers whose accepted attacker representation and returned parameter
     * order match the selected death-message key.
     */
    private DamageType(Function<Object, String> deathInfoProvider, Function<Object, String[]> deathInfoExtraParamsProvider) {
        this.deathInfoProvider = deathInfoProvider;
        this.deathInfoExtraParamsProvider = deathInfoExtraParamsProvider;
    }

    /**
     * Creates a category using the same death-message key for every attacker.
     * The supplied {@code deathInfo} is retained without validation, and
     * generated messages contain only the victim's display name as a
     * translation parameter.
     */
    public static DamageType fixed(@MayContainTrKey String deathInfo) {
        return new DamageType($ -> deathInfo);
    }

    /**
     * Creates a category selecting its death-message key from the attacker. The
     * provider is retained without validation and receives the original
     * attacker object; generated messages contain only the victim's display
     * name as a parameter.
     */
    public static DamageType dynamic(Function<Object, String> deathInfoProvider) {
        return new DamageType(deathInfoProvider);
    }

    /**
     * Creates a category using attacker-dependent key and additional-parameter
     * providers. The providers are retained directly and must accept the same
     * source representation; the parameter provider must return a non-{@code null}
     * array ordered for the selected key.
     */
    public static DamageType dynamicWithExtraParams(Function<Object, String> deathInfoProvider, Function<Object, String[]> deathInfoExtraParamsProvider) {
        return new DamageType(deathInfoProvider, deathInfoExtraParamsProvider);
    }

    /**
     * Creates a category with an attacker-dependent key and optional additional
     * parameter. A {@code null} result from
     * {@code deathInfoExtraSingleParamProvider} omits that parameter, while any
     * other result follows the victim's display name in the translation
     * arguments.
     */
    public static DamageType dynamicWithExtraSingleParam(Function<Object, String> deathInfoProvider, Function<Object, String> deathInfoExtraSingleParamProvider) {
        return new DamageType(deathInfoProvider, attacker -> {
            // A missing optional name contributes no argument; retaining a null
            // array entry would still occupy a translation-parameter position.
            var singleParam = deathInfoExtraSingleParamProvider.apply(attacker);
            return singleParam != null ? new String[]{singleParam} : new String[0];
        });
    }

    /**
     * Creates a category with a fixed key and optional attacker-dependent
     * parameter. A {@code null} result from
     * {@code deathInfoExtraSingleParamProvider} omits that parameter, while any
     * other result follows the victim's display name in the translation
     * arguments.
     */
    public static DamageType fixedWithExtraSingleParam(@MayContainTrKey String deathInfo, Function<Object, String> deathInfoExtraSingleParamProvider) {
        return new DamageType($ -> deathInfo, attacker -> {
            // A missing optional name contributes no argument; retaining a null
            // array entry would still occupy a translation-parameter position.
            var singleParam = deathInfoExtraSingleParamProvider.apply(attacker);
            return singleParam != null ? new String[]{singleParam} : new String[0];
        });
    }

    /**
     * Creates a category using the generic death-message translation key. The
     * attacker contributes no parameters, so each generated message contains
     * only the victim's display name regardless of the source object supplied
     * later.
     */
    public static DamageType defaultFixed() {
        return new DamageType($ -> TrKeys.MC_DEATH_ATTACK_GENERIC);
    }

    /**
     * Builds a death-message key and ordered translation arguments for the
     * victim. The victim's current display name occupies the first argument,
     * and the additional-parameter provider supplies all following arguments.
     * Both providers receive the original {@code attacker}, so callers must
     * supply the representation required by this category rather than the
     * display name alone.
     *
     * <p>
     * The method creates a new argument array for the returned pair. Provider
     * failures propagate, including a {@code null} additional-parameter array
     * or a source that does not satisfy a built-in category's requirements.
     */
    public Pair<String, String[]> getDeathInfo(Entity victim, Object attacker) {
        // Translation keys reserve their first argument for the victim, so
        // append provider arguments only after that display name has been added.
        List<String> params = new ArrayList<>();
        params.add(victim.getDisplayName());
        params.addAll(Arrays.asList(deathInfoExtraParamsProvider.apply(attacker)));
        return new Pair<>(deathInfoProvider.apply(attacker), params.toArray(String[]::new));
    }
}
