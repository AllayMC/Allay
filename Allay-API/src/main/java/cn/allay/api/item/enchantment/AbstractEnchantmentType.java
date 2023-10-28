package cn.allay.api.item.enchantment;

import cn.allay.api.identifier.Identifier;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;

import java.util.Map;

/**
 * Allay Project 2023/10/21
 *
 * @author daoge_cmd
 */
public class AbstractEnchantmentType implements EnchantmentType {

    protected final Identifier identifier;
    protected final short id;
    protected final short maxLevel;
    protected final Rarity rarity;
    protected final Map<Short, EnchantmentInstance> instances = new Short2ObjectOpenHashMap<>();

    protected AbstractEnchantmentType(
            Identifier identifier,
            int id, int maxLevel,
            Rarity rarity) {
        this.identifier = identifier;
        // Use int types in formal parameters to avoid forcing the nasty int -> short type in subclasses
        this.id = (short) id;
        this.maxLevel = (short) maxLevel;
        this.rarity = rarity;
        EnchantmentRegistry.getRegistry().register(this.id, identifier, this);
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public short getMaxLevel() {
        return maxLevel;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public EnchantmentInstance createInstance(short level) {
        return instances.computeIfAbsent(level, l -> new SimpleEnchantmentInstance(this, l));
    }
}
