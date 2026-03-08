package org.allaymc.server.item.enchantment;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.server.utils.AllayRandom;
import org.jctools.maps.NonBlockingHashMapLong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class EnchantmentOptionGenerator {

    // TODO: this should be based on the total number of crafting recipes - if there are ever 100k recipes, this will conflict with regular recipes
    public static final int NETWORK_ID_COUNTER_INITIAL_VALUE = 100000;

    private static final NonBlockingHashMapLong<EnchantOption> ENCHANT_OPTIONS = new NonBlockingHashMapLong<>();
    private static final AtomicInteger NETWORK_ID_COUNTER = new AtomicInteger(NETWORK_ID_COUNTER_INITIAL_VALUE);
    private static final int MAX_BOOKSHELF_COUNT = 15;

    private static final List<String> WORDS = List.of(
            "air", "animal", "ball", "beast", "berata",
            "bless", "cold", "creature", "cthulhu", "cube",
            "curse", "darkness", "demon", "destroy", "dry",
            "earth", "elder", "elemental", "embiggen", "enchant",
            "fhtagnbaguette", "fiddle", "fire", "free", "fresh",
            "galvanize", "grow", "hot", "humanoid", "ignite",
            "imbue", "inside", "klaatu", "light", "limited",
            "xyzzy", "mental", "mglwnafh", "niktu", "of",
            "other", "phnglui", "physical", "range", "rlyeh",
            "scrolls", "self", "shorten", "shrink", "snuff",
            "sphere", "spirit", "stale", "stretch", "the",
            "towards", "twist", "undead", "water", "wet",
            "wgahnagl", "cool_loong", "allay", "daoge", "atri",
            "mdx", "iwareq", "liulihaocai" // QAQ
    );

    public static List<Pair<Integer, EnchantOption>> generateEnchantOptions(Position3ic enchantTablePos, ItemStack input, int seed) {
        if (input == null || input.hasEnchantments() || input.getItemType().getItemData().enchantValue() == 0) {
            return Collections.emptyList();
        }

        AllayRandom random = new AllayRandom(seed);

        int bookshelfCount = countBookshelves(enchantTablePos);
        int baseRequiredLevel = random.nextRange(1, 8) + (bookshelfCount >> 1) + random.nextRange(0, bookshelfCount);

        return List.of(
                createEnchantOption(random, input, 1, (int) Math.floor(Math.max(baseRequiredLevel / 3D, 1))),
                createEnchantOption(random, input, 2, (int) Math.floor(baseRequiredLevel * 2D / 3 + 1)),
                createEnchantOption(random, input, 3, Math.max(baseRequiredLevel, bookshelfCount * 2))
        );
    }

    public EnchantOption removeEnchantOption(int networkId) {
        return ENCHANT_OPTIONS.remove(networkId);
    }

    public void removeEnchantOptions(Collection<Long> options) {
        for (var option : options) {
            ENCHANT_OPTIONS.remove(option);
        }
    }

    private static Pair<Integer, EnchantOption> createEnchantOption(AllayRandom random, ItemStack inputItem, int requiredLapisLazuliCount, int requiredXpLevel) {
        int modifiedLevel = requiredXpLevel;

        int enchantValue = inputItem.getItemType().getItemData().enchantValue();
        modifiedLevel = modifiedLevel + random.nextInt(enchantValue / 4) + random.nextInt(enchantValue / 2) + 1;

        // Random bonus for enchanting power between 0.85 and 1.15
        double bonus = 1 + (random.nextFloat() + random.nextFloat() - 1) * 0.15;
        modifiedLevel = (int) Math.round(modifiedLevel * bonus);
        if (modifiedLevel < 1) modifiedLevel = 1;

        List<EnchantmentInstance> resultEnchantments = new ArrayList<>();
        List<EnchantmentInstance> availableEnchantments = getAvailableEnchantments(modifiedLevel, inputItem);
        if (!availableEnchantments.isEmpty()) {
            final AtomicReference<EnchantmentInstance> lastEnchantment = new AtomicReference<>(getRandomWeightedEnchantment(random, availableEnchantments));
            if (lastEnchantment.get() != null) {
                resultEnchantments.add(lastEnchantment.get());
            }

            while (random.nextInt(1, 50) <= modifiedLevel) {
                if (!resultEnchantments.isEmpty()) {
                    availableEnchantments = availableEnchantments.stream()
                            .filter(e -> e.getType().getId() != lastEnchantment.get().getType().getId() && !e.getType().isIncompatibleWith(lastEnchantment.get().getType()))
                            .collect(Collectors.toList());
                }
                if (availableEnchantments.isEmpty()) {
                    break;
                }
                var enchantment = getRandomWeightedEnchantment(random, availableEnchantments);
                if (enchantment != null) {
                    resultEnchantments.add(enchantment);
                    lastEnchantment.set(enchantment);
                }
                modifiedLevel /= 2;
            }
        }

        var networkId = NETWORK_ID_COUNTER.getAndIncrement();
        var option = new EnchantOption(generateRandomOptionName(random), requiredLapisLazuliCount, requiredXpLevel, resultEnchantments);
        ENCHANT_OPTIONS.put(networkId, option);
        return new Pair<>(networkId, option);
    }

    private static int countBookshelves(Position3ic enchantTablePos) {
        int bookshelfCount = 0;
        var dimension = enchantTablePos.dimension();

        for (int x = -2; x <= 2; x++) {
            outer:
            for (int z = -2; z <= 2; z++) {
                // We only check blocks at a distance of 2 blocks from the enchanting table
                if (Math.abs(x) != 2 && Math.abs(z) != 2) {
                    continue;
                }

                // Ensure the space between the bookshelf stack at this X/Z and the enchanting table is empty
                for (int y = 0; y <= 1; y++) {
                    // Calculate the coordinates of the space between the bookshelf and the enchanting table
                    if (dimension.getBlockState(
                            enchantTablePos.x() + Math.max(Math.min(x, 1), -1),
                            enchantTablePos.y() + y,
                            enchantTablePos.z() + Math.max(Math.min(z, 1), -1)
                    ).getBlockType() != BlockTypes.AIR) {
                        continue outer;
                    }
                }

                // Finally, check the number of bookshelves at the current position
                for (int y = 0; y <= 1; y++) {
                    if (dimension.getBlockState(enchantTablePos.x() + x, enchantTablePos.y() + y, enchantTablePos.z() + z).getBlockType() == BlockTypes.BOOKSHELF) {
                        bookshelfCount++;
                        if (bookshelfCount == MAX_BOOKSHELF_COUNT) {
                            return bookshelfCount;
                        }
                    }
                }
            }
        }

        return bookshelfCount;
    }

    private static EnchantmentInstance getRandomWeightedEnchantment(AllayRandom random, List<EnchantmentInstance> enchantments) {
        if (enchantments.isEmpty()) {
            return null;
        }

        int totalWeight = 0;
        for (var enchantment : enchantments) {
            totalWeight += enchantment.getType().getRarity().getWeight();
        }

        EnchantmentInstance result = null;
        int randomWeight = random.nextRange(1, totalWeight);

        for (var enchantment : enchantments) {
            randomWeight -= enchantment.getType().getRarity().getWeight();
            if (randomWeight < 0) {
                result = enchantment;
                break;
            }
        }
        return result;
    }

    private static String generateRandomOptionName(AllayRandom random) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3 + random.nextInt(2); i++) {
            builder.append(" ").append(WORDS.get(random.nextInt(WORDS.size() - 1)));
        }
        return builder.toString();
    }

    private static List<EnchantmentInstance> getAvailableEnchantments(int modifiedLevel, ItemStack item) {
        List<EnchantmentInstance> list = new ArrayList<>();
        for (var enchantment : getPrimaryEnchantmentsForItem(item)) {
            for (int lvl = enchantment.getMaxLevel(); lvl > 0; lvl--) {
                if (modifiedLevel >= enchantment.getMinModifiedLevel(lvl) && modifiedLevel <= enchantment.getMaxModifiedLevel(lvl)) {
                    list.add(enchantment.createInstance(lvl));
                    break;
                }
            }
        }
        return list;
    }

    private static List<EnchantmentType> getPrimaryEnchantmentsForItem(ItemStack item) {
        return Registries.ENCHANTMENTS
                .getContent()
                .m1().values()
                .stream()
                .filter(item::checkEnchantmentCompatibility)
                .filter(EnchantmentType::isAvailableInEnchantTable)
                .filter(type -> item.isAllEnchantmentsAvailableInEnchantTable() || type.canBeAppliedTo(item.getItemType()))
                .toList();
    }

}
