package org.allaymc.api.item.enchantment;

import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@code EnchantmentType} describes an enchantment's registered identity,
 * normal level limit, rarity, applicability, and overridable gameplay rules.
 * Its constructors register the new type under both numeric and namespaced
 * identifiers. {@code createInstance} caches instances by the exact requested
 * level without enforcing normal level bounds, while the default rules allow
 * table and fishing acquisition, no protection, and no conflicts.
 *
 * @author daoge_cmd
 */
public abstract class EnchantmentType {

    /**
     * Namespaced registry identity of this enchantment.
     */
    protected final Identifier identifier;
    /**
     * Numeric registry identity used by item NBT.
     */
    protected final int id;
    /**
     * Normal highest level considered during table selection.
     */
    protected final int maxLevel;
    /**
     * Selection weight and anvil cost category.
     */
    protected final Rarity rarity;
    /**
     * Item category checked after the ordinary-book exception.
     */
    protected final ApplicableType applicableType;

    /**
     * {@code instances} retains one instance for each requested integer level.
     * {@code createInstance} mutates this ordinary {@code HashMap}, so
     * concurrent callers require external synchronization.
     */
    @Getter(AccessLevel.NONE)
    protected final Map<Integer, EnchantmentInstance> instances;

    /**
     * Creates and registers an enchantment accepting the
     * {@code ApplicableType.ANY} category. The full constructor overwrites
     * existing registry associations under the supplied keys and rejects
     * registration when the registry is frozen.
     */
    protected EnchantmentType(Identifier identifier, int id, int maxLevel, Rarity rarity) {
        this(identifier, id, maxLevel, rarity, ApplicableType.ANY);
    }

    /**
     * Creates an enchantment with the supplied identity and gameplay metadata.
     * Construction immediately registers this instance under {@code id} and
     * {@code identifier}, replacing existing associations under those keys; a
     * frozen registry throws {@code IllegalStateException}.
     */
    protected EnchantmentType(Identifier identifier, int id, int maxLevel, Rarity rarity, ApplicableType applicableType) {
        this.identifier = identifier;
        this.id = id;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        this.applicableType = applicableType;
        this.instances = new HashMap<>();
        Registries.ENCHANTMENTS.register(this.id, identifier, this);
    }

    /**
     * Returns the cached instance for exactly the supplied integer level. This
     * method accepts levels outside {@code 1} through {@code maxLevel}, and
     * callers must synchronize concurrent access to the instance cache.
     */
    public EnchantmentInstance createInstance(int level) {
        return instances.computeIfAbsent(level, l -> new EnchantmentInstance(this, l));
    }

    /**
     * Returns the namespaced identifier assigned when this type was
     * constructed.
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Returns the numeric identifier assigned when this type was constructed.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the normal maximum level used by enchantment selection.
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Returns the default lower table-power bound, {@code 1 + level * 10}.
     */
    public int getMinModifiedLevel(int level) {
        return 1 + level * 10;
    }

    /**
     * Returns the default upper table-power bound, five above
     * {@code getMinModifiedLevel(level)}.
     */
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 5;
    }

    /**
     * Returns whether table selection may include this type, defaulting to
     * {@code true}.
     */
    public boolean isAvailableInEnchantTable() {
        return true;
    }

    /**
     * Returns whether this enchantment is a curse, defaulting to {@code false}.
     */
    public boolean isCursed() {
        return false;
    }

    /**
     * Returns whether this enchantment accepts the supplied item type. Ordinary
     * {@code ItemTypes.BOOK} always passes; every other type is checked by the
     * configured {@code applicableType} category, which may inspect a newly
     * created stack.
     */
    public boolean canBeAppliedTo(ItemType<?> itemType) {
        if (itemType == ItemTypes.BOOK) {
            return true;
        }

        return applicableType.canBeAppliedTo(itemType);
    }

    /**
     * Returns the item category configured for this enchantment type.
     */
    public ApplicableType getApplicableType() {
        return applicableType;
    }

    /**
     * Returns the rarity providing this enchantment's selection weight and
     * anvil cost.
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Returns this type's protection contribution, which defaults to zero for
     * every damage type.
     */
    public int getProtectionFactor(DamageType damageType, int level) {
        return 0;
    }

    /**
     * Returns whether this type rejects the other enchantment, defaulting to
     * {@code false}.
     */
    public boolean isIncompatibleWith(EnchantmentType other) {
        return false;
    }

    /**
     * Returns whether fishing may yield this enchantment, defaulting to
     * {@code true}.
     */
    public boolean isFishable() {
        return true;
    }

    /**
     * Returns whether another enchantment type shares this numeric and
     * namespaced identity.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EnchantmentType that)) {
            return false;
        }

        return id == that.id && Objects.equals(identifier, that.identifier);
    }

    /**
     * Returns a hash combining the numeric and namespaced registry identities.
     */
    @Override
    public int hashCode() {
        return Objects.hash(identifier, id);
    }
}
