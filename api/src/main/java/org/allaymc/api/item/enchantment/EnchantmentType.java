package org.allaymc.api.item.enchantment;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;

import java.util.Map;

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
    @Getter(AccessLevel.NONE)
    protected final Map<Integer, EnchantmentInstance> instances = new Int2ObjectOpenHashMap<>();

    protected EnchantmentType(
            Identifier identifier,
            int id,
            int maxLevel,
            Rarity rarity
    ) {
        this.identifier = identifier;
        // Use int types in formal parameters to avoid forcing the nasty int -> short type in subclasses
        this.id = id;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        Registries.ENCHANTMENTS.register(this.id, identifier, this);
    }

    public EnchantmentInstance createInstance(int level) {
        return instances.computeIfAbsent(level, l -> new EnchantmentInstance(this, l));
    }

    /**
     * Get the identifier of the enchantment.
     *
     * @return The identifier of the enchantment.
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Get the id of the enchantment.
     *
     * @return The id of the enchantment.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the max level of the enchantment.
     *
     * @return The max level of the enchantment.
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Get the minimum modified level of the enchantment.
     *
     * @param level The level of the enchantment.
     *
     * @return The minimum modified level of the enchantment.
     * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">Enchanting Levels<a/>.
     */
    public int getMinModifiedLevel(int level) {
        return 1 + level * 10;
    }

    /**
     * Get the maximum modified level of the enchantment.
     *
     * @param level The level of the enchantment.
     *
     * @return The maximum modified level of the enchantment.
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
     * Get the rarity of the enchantment.
     *
     * @return The rarity of the enchantment.
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Check if the enchantment is incompatible with another enchantment.
     *
     * @param other The other enchantment.
     *
     * @return {@code true} if the enchantments are incompatible, {@code false} otherwise.
     */
    public boolean isIncompatibleWith(EnchantmentType other) {
        return false;
    }
}
