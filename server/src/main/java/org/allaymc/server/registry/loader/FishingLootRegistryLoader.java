package org.allaymc.server.registry.loader;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.fishing.FishingLoot;
import org.allaymc.api.item.fishing.FishingLootCategory;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.fishing.FishingEnchantmentHelper;
import org.allaymc.server.item.fishing.SimpleFishingLoot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Registry loader for fishing loot items.
 * <p>
 * Vanilla loot distribution:
 * <ul>
 *   <li>Fish: 85% base chance</li>
 *   <li>Treasure: 5% base chance (requires open water)</li>
 *   <li>Junk: 10% base chance</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public class FishingLootRegistryLoader implements RegistryLoader<Void, Map<Identifier, FishingLoot>> {

    @Override
    public Map<Identifier, FishingLoot> load(Void $) {
        var map = new HashMap<Identifier, FishingLoot>();

        // === FISH (85% base chance) ===
        // Distribution within fish category: Cod 60%, Salmon 25%, Tropical Fish 2%, Pufferfish 13%
        registerLoot(map, "minecraft:cod", FishingLootCategory.FISH, 0.60f, () -> ItemTypes.COD.createItemStack());
        registerLoot(map, "minecraft:salmon", FishingLootCategory.FISH, 0.25f, () -> ItemTypes.SALMON.createItemStack());
        registerLoot(map, "minecraft:tropical_fish", FishingLootCategory.FISH, 0.02f, () -> ItemTypes.TROPICAL_FISH.createItemStack());
        registerLoot(map, "minecraft:pufferfish", FishingLootCategory.FISH, 0.13f, () -> ItemTypes.PUFFERFISH.createItemStack());

        // === TREASURE (5% base chance, requires open water) ===
        // Weights: Enchanted Book 6, others 5 (total 36)
        registerTreasureLoot(map, "minecraft:bow", FishingLootCategory.TREASURE, 5f,
                () -> {
                    var bow = ItemTypes.BOW.createItemStack();
                    // Treasures have some damage in vanilla
                    bow.setDamage(ThreadLocalRandom.current().nextInt(bow.getMaxDamage() / 4));
                    // Apply enchantments using level 22-30 algorithm
                    FishingEnchantmentHelper.applyTreasureEnchantments(bow);
                    return bow;
                });
        registerTreasureLoot(map, "minecraft:enchanted_book", FishingLootCategory.TREASURE, 6f,
                () -> {
                    var book = ItemTypes.ENCHANTED_BOOK.createItemStack();
                    // Apply enchantments using level-30 algorithm (chance not reduced for multiple)
                    FishingEnchantmentHelper.applyBookEnchantments(book);
                    return book;
                });
        registerTreasureLoot(map, "minecraft:fishing_rod", FishingLootCategory.TREASURE, 5f,
                () -> {
                    var rod = ItemTypes.FISHING_ROD.createItemStack();
                    rod.setDamage(ThreadLocalRandom.current().nextInt(rod.getMaxDamage() / 4));
                    // Apply enchantments using level 22-30 algorithm
                    FishingEnchantmentHelper.applyTreasureEnchantments(rod);
                    return rod;
                });
        registerTreasureLoot(map, "minecraft:name_tag", FishingLootCategory.TREASURE, 5f, () -> ItemTypes.NAME_TAG.createItemStack());
        registerTreasureLoot(map, "minecraft:nautilus_shell", FishingLootCategory.TREASURE, 5f, () -> ItemTypes.NAUTILUS_SHELL.createItemStack());
        registerTreasureLoot(map, "minecraft:saddle", FishingLootCategory.TREASURE, 5f, () -> ItemTypes.SADDLE.createItemStack());
        // Waterlily is treasure in Bedrock Edition (junk in Java Edition)
        registerTreasureLoot(map, "minecraft:waterlily", FishingLootCategory.TREASURE, 5f, () -> ItemTypes.WATERLILY.createItemStack());

        // === JUNK (10% base chance) ===
        // Weights based on vanilla distribution
        registerLoot(map, "minecraft:bowl", FishingLootCategory.JUNK, 10f, () -> ItemTypes.BOWL.createItemStack());
        registerLoot(map, "minecraft:fishing_rod_junk", FishingLootCategory.JUNK, 2f,
                () -> {
                    var rod = ItemTypes.FISHING_ROD.createItemStack();
                    // Junk fishing rods have random damage
                    rod.setDamage((int) (rod.getMaxDamage() * 0.1f + Math.random() * rod.getMaxDamage() * 0.8f));
                    return rod;
                });
        registerLoot(map, "minecraft:leather", FishingLootCategory.JUNK, 10f, () -> ItemTypes.LEATHER.createItemStack());
        registerLoot(map, "minecraft:leather_boots", FishingLootCategory.JUNK, 10f,
                () -> {
                    var boots = ItemTypes.LEATHER_BOOTS.createItemStack();
                    // Junk leather boots have random damage
                    boots.setDamage((int) (boots.getMaxDamage() * 0.1f + Math.random() * boots.getMaxDamage() * 0.8f));
                    return boots;
                });
        registerLoot(map, "minecraft:rotten_flesh", FishingLootCategory.JUNK, 10f, () -> ItemTypes.ROTTEN_FLESH.createItemStack());
        registerLoot(map, "minecraft:stick", FishingLootCategory.JUNK, 5f, () -> ItemTypes.STICK.createItemStack());
        registerLoot(map, "minecraft:string", FishingLootCategory.JUNK, 5f, () -> ItemTypes.STRING.createItemStack());
        registerLoot(map, "minecraft:potion", FishingLootCategory.JUNK, 10f, () -> ItemTypes.POTION.createItemStack());
        registerLoot(map, "minecraft:bone", FishingLootCategory.JUNK, 10f, () -> ItemTypes.BONE.createItemStack());
        registerLoot(map, "minecraft:ink_sac", FishingLootCategory.JUNK, 1f,
                () -> {
                    var inkSac = ItemTypes.INK_SAC.createItemStack();
                    inkSac.setCount(10);
                    return inkSac;
                });
        registerLoot(map, "minecraft:tripwire_hook", FishingLootCategory.JUNK, 10f, () -> ItemTypes.TRIPWIRE_HOOK.createItemStack());

        return map;
    }

    private void registerLoot(Map<Identifier, FishingLoot> map, String id, FishingLootCategory category, float weight, Supplier<ItemStack> supplier) {
        var identifier = new Identifier(id);
        map.put(identifier, new SimpleFishingLoot(identifier, category, weight, supplier, false));
    }

    private void registerTreasureLoot(Map<Identifier, FishingLoot> map, String id, FishingLootCategory category, float weight, Supplier<ItemStack> supplier) {
        var identifier = new Identifier(id);
        map.put(identifier, new SimpleFishingLoot(identifier, category, weight, supplier, true));
    }
}
