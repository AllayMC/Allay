package org.allaymc.api.item.enchantment;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.utils.Identifier;

import java.util.Map;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
@Getter
public abstract class AbstractEnchantmentType implements EnchantmentType {

    protected final Identifier identifier;
    protected final int id;
    protected final int maxLevel;
    protected final Rarity rarity;
    @Getter(AccessLevel.NONE)
    protected final Map<Integer, EnchantmentInstance> instances = new Int2ObjectOpenHashMap<>();

    protected AbstractEnchantmentType(
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
        EnchantmentRegistry.getRegistry().register(this.id, identifier, this);
    }

    @Override
    public EnchantmentInstance createInstance(int level) {
        return instances.computeIfAbsent(level, l -> new SimpleEnchantmentInstance(this, l));
    }
}
