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
 * Represents an enchantment type.
 *
 * @author daoge_cmd
 * @see <a href="https://minecraft.wiki/w/Enchanting#Summary_of_enchantments">Enchantments List<a/>
 */
public abstract class EnchantmentType {

    protected final Identifier identifier;
    protected final int id;
    protected final int maxLevel;
    protected final Rarity rarity;
    protected final ApplicableType applicableType;

    @Getter(AccessLevel.NONE)
    protected final Map<Integer, EnchantmentInstance> instances;

    protected EnchantmentType(Identifier identifier, int id, int maxLevel, Rarity rarity) {
        this(identifier, id, maxLevel, rarity, ApplicableType.ANY);
    }

    protected EnchantmentType(Identifier identifier, int id, int maxLevel, Rarity rarity, ApplicableType applicableType) {
        this.identifier = identifier;
        // Use int types in formal parameters to avoid forcing the nasty int -> short type in subclasses
        this.id = id;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        this.applicableType = applicableType;
        this.instances = new HashMap<>();
        Registries.ENCHANTMENTS.register(this.id, identifier, this);
    }

    /**
     * Create an instance of the enchantment.
     *
     * @param level The level of the instance
     * @return The instance of the enchantment
     */
    public EnchantmentInstance createInstance(int level) {
        return instances.computeIfAbsent(level, l -> new EnchantmentInstance(this, l));
    }

    /**
     * Get the identifier of the enchantment.
     *
     * @return The identifier of the enchantment
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Get the id of the enchantment.
     *
     * @return The id of the enchantment
     */
    public int getId() {
        return id;
    }

    /**
     * Get the max level of the enchantment.
     *
     * @return The max level of the enchantment
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Get the minimum modified level of the enchantment.
     *
     * @param level The level of the enchantment
     * @return The minimum modified level of the enchantment
     * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">Enchanting Levels<a/>.
     */
    public int getMinModifiedLevel(int level) {
        return 1 + level * 10;
    }

    /**
     * Get the maximum modified level of the enchantment.
     *
     * @param level The level of the enchantment
     * @return The maximum modified level of the enchantment
     * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">Enchanting Levels<a/>.
     */
    public int getMaxModifiedLevel(int level) {
        return getMinModifiedLevel(level) + 5;
    }

    /**
     * Check if the enchantment is available in enchantment table.
     *
     * @return {@code true} if the enchantment is available in enchantment table, {@code false} otherwise.
     */
    public boolean isAvailableInEnchantTable() {
        return true;
    }

    /**
     * Check if the enchantment is cursed.
     *
     * @return {@code true} if the enchantment is cursed, {@code false} otherwise.
     */
    public boolean isCursed() {
        return false;
    }

    /**
     * Check if the enchantment can be applied to an item type.
     *
     * @param itemType The item type
     * @return {@code true} if the enchantment can be applied to the item type, {@code false} otherwise.
     */
    public boolean canBeAppliedTo(ItemType<?> itemType) {
        if (itemType == ItemTypes.BOOK) {
            // Book can always be enchanted
            return true;
        }

        return applicableType.canBeAppliedTo(itemType);
    }

    /**
     * Get the applicable type of the enchantment.
     *
     * @return The applicable type of the enchantment
     */
    public ApplicableType getApplicableType() {
        return applicableType;
    }

    /**
     * Get the rarity of the enchantment.
     *
     * @return The rarity of the enchantment
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Get the protection factor of this enchantment type.
     *
     * @return the protection factor of this enchantment type
     * @see <a href="https://minecraft.wiki/w/Armor#Enchantments">Enchantments</a>
     */
    public int getProtectionFactor(DamageType damageType, int level) {
        return 0;
    }

    /**
     * Check if the enchantment is incompatible with another enchantment.
     *
     * @param other The other enchantment
     * @return {@code true} if the enchantments are incompatible, {@code false} otherwise.
     */
    public boolean isIncompatibleWith(EnchantmentType other) {
        return false;
    }

    /**
     * Check if the enchantment can be obtained from fishing.
     * <p>
     * Most enchantments are fishable, except Soul Speed, Swift Sneak, and Wind Burst.
     *
     * @return {@code true} if the enchantment can be obtained from fishing, {@code false} otherwise.
     * @see <a href="https://minecraft.wiki/w/Fishing#Treasure">Fishing Treasure</a>
     */
    public boolean isFishable() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EnchantmentType that)) {
            return false;
        }

        return id == that.id && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, id);
    }
}
