package org.allaymc.server.network.protocol;

import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemGroup;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

import java.util.ArrayList;
import java.util.List;

final class ProtocolPayloadCopier {
    private ProtocolPayloadCopier() {
    }

    static List<ItemDefinition> copyItemDefinitions(List<ItemDefinition> definitions) {
        return definitions.stream()
                .map(ProtocolPayloadCopier::copyItemDefinition)
                .toList();
    }

    static List<CreativeItemGroup> copyCreativeGroups(List<CreativeItemGroup> groups) {
        return groups.stream()
                .map(group -> new CreativeItemGroup(
                        group.category(),
                        group.name(),
                        copyItemData(group.icon())
                ))
                .toList();
    }

    static List<CreativeItemData> copyCreativeItems(List<CreativeItemData> items) {
        return items.stream()
                .map(item -> new CreativeItemData(
                        copyItemData(item.item()),
                        item.netId(),
                        item.groupId()
                ))
                .toList();
    }

    static List<BlockPropertyData> copyBlockProperties(List<BlockPropertyData> properties) {
        return properties.stream()
                .map(property -> new BlockPropertyData(
                        property.name(),
                        copyNbtMap(property.properties())
                ))
                .toList();
    }

    static List<RecipeData> copyRecipes(List<RecipeData> recipes) {
        return recipes.stream()
                .map(ProtocolPayloadCopier::copyRecipe)
                .toList();
    }

    static ItemData copyItemData(ItemData item) {
        var canPlace = item.getCanPlace();
        var canBreak = item.getCanBreak();
        var blockDefinition = item.getBlockDefinition();
        Integer blockRuntimeId = blockDefinition == null ? null : blockDefinition.runtimeId();
        return ItemData.builder()
                .definition(copyItemDefinition(item.getDefinition()))
                .damage(item.getDamage())
                .count(item.getCount())
                .tag(copyNbtMap(item.getTag()))
                .canPlace(canPlace == null ? null : canPlace.clone())
                .canBreak(canBreak == null ? null : canBreak.clone())
                .blockingTicks(item.getBlockingTicks())
                .blockDefinition(blockRuntimeId == null ? null : () -> blockRuntimeId)
                .usingNetId(item.isUsingNetId())
                .netId(item.getNetId())
                .build();
    }

    static NbtMap copyNbtMap(NbtMap source) {
        if (source == null || source.isEmpty()) {
            return source;
        }

        var copy = NbtMap.builder();
        for (var key : source.keySet()) {
            copy.put(key, copyNbtValue(source.get(key)));
        }
        return copy.build();
    }

    private static ItemDefinition copyItemDefinition(ItemDefinition definition) {
        if (definition == null || definition == ItemDefinition.AIR || definition == ItemDefinition.LEGACY_FIREWORK) {
            return definition;
        }
        return new SimpleItemDefinition(
                definition.identifier(),
                definition.runtimeId(),
                definition.version(),
                definition.componentBased(),
                copyNbtMap(definition.componentData())
        );
    }

    private static RecipeData copyRecipe(RecipeData recipe) {
        return switch (recipe) {
            case ShapedRecipeData shaped -> ShapedRecipeData.of(
                    shaped.getType(), shaped.getId(), shaped.getWidth(), shaped.getHeight(),
                    copyIngredients(shaped.getIngredients()), copyItems(shaped.getResults()), shaped.getUuid(),
                    shaped.getTag(), shaped.getPriority(), shaped.getNetId(), shaped.isAssumeSymetry(),
                    copyRequirement(shaped.getRequirement())
            );
            case ShapelessRecipeData shapeless -> ShapelessRecipeData.of(
                    shapeless.getType(), shapeless.getId(), copyIngredients(shapeless.getIngredients()),
                    copyItems(shapeless.getResults()), shapeless.getUuid(), shapeless.getTag(),
                    shapeless.getPriority(), shapeless.getNetId(), copyRequirement(shapeless.getRequirement())
            );
            case SmithingTransformRecipeData smithing -> SmithingTransformRecipeData.of(
                    smithing.getId(), copyIngredient(smithing.getTemplate()), copyIngredient(smithing.getBase()),
                    copyIngredient(smithing.getAddition()), copyItemData(smithing.getResult()),
                    smithing.getTag(), smithing.getNetId()
            );
            case SmithingTrimRecipeData smithing -> SmithingTrimRecipeData.of(
                    smithing.getId(), copyIngredient(smithing.getBase()), copyIngredient(smithing.getAddition()),
                    copyIngredient(smithing.getTemplate()), smithing.getTag(), smithing.getNetId()
            );
            case MultiRecipeData multi -> MultiRecipeData.of(multi.getUuid(), multi.getNetId());
            case FurnaceRecipeData furnace -> FurnaceRecipeData.of(
                    furnace.getType(), furnace.getInputId(), furnace.getInputData(),
                    copyItemData(furnace.getResult()), furnace.getTag()
            );
            default -> throw new IllegalStateException(
                    "Unsupported cached recipe payload: " + recipe.getClass().getName()
            );
        };
    }

    private static List<ItemData> copyItems(List<ItemData> items) {
        return items.stream()
                .map(ProtocolPayloadCopier::copyItemData)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    private static List<ItemDescriptorWithCount> copyIngredients(List<ItemDescriptorWithCount> ingredients) {
        return ingredients.stream()
                .map(ProtocolPayloadCopier::copyIngredient)
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    private static ItemDescriptorWithCount copyIngredient(ItemDescriptorWithCount ingredient) {
        if (ingredient == null) {
            return null;
        }

        var descriptor = ingredient.descriptor();
        if (descriptor instanceof DefaultDescriptor item) {
            descriptor = new DefaultDescriptor(copyItemDefinition(item.itemId()), item.auxValue());
        }
        return new ItemDescriptorWithCount(descriptor, ingredient.count());
    }

    private static RecipeUnlockingRequirement copyRequirement(RecipeUnlockingRequirement requirement) {
        if (requirement == null) {
            return null;
        }
        return new RecipeUnlockingRequirement(
                requirement.context(),
                copyIngredients(requirement.ingredients())
        );
    }

    private static Object copyNbtValue(Object value) {
        return switch (value) {
            case NbtMap map -> copyNbtMap(map);
            case NbtList<?> list -> copyNbtList(list);
            case byte[] bytes -> bytes.clone();
            case int[] ints -> ints.clone();
            case long[] longs -> longs.clone();
            default -> value;
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static NbtList<?> copyNbtList(NbtList<?> source) {
        var values = new ArrayList<>(source.size());
        for (var value : source) {
            values.add(copyNbtValue(value));
        }
        return new NbtList(source.getType(), values);
    }
}
