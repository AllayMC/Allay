package org.allaymc.api.item.enchantment;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;

import java.util.Map;

/**
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

    public Identifier getIdentifier() {
        return identifier;
    }

    public int getId() {
        return id;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public boolean checkIncompatible(EnchantmentType other) {
        return false;
    }
}
