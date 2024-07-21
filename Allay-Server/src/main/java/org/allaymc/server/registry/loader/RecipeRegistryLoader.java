package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.DefaultDescriptor;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.recipe.NetworkRecipe;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.recipe.ShapelessRecipe;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.nbt.NbtMap;

import java.io.InputStreamReader;
import java.util.*;

/**
 * Allay Project 2024/7/20
 *
 * @author daoge_cmd
 */
@Slf4j
public class RecipeRegistryLoader implements RegistryLoader<Void, Int2ObjectMap<NetworkRecipe>> {
    @Override
    public Int2ObjectMap<NetworkRecipe> load(Void $) {
        log.info(TrKeys.A_RECIPE_LOADING);
        var recipes = new Int2ObjectOpenHashMap<NetworkRecipe>();
        var stream = Objects.requireNonNull(Utils.getResource("recipes.json"));

        var obj = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonObject();
        var shapedRecipes = obj.getAsJsonArray("shaped");
        var shapelessRecipes = obj.getAsJsonArray("shapeless");
        var initialMax = shapedRecipes.size() + shapelessRecipes.size();
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(initialMax)
                .setTaskName("Loading Vanilla Recipes")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .build()) {
            for (var shapedRecipe : shapedRecipes) {
                var recipe = parseShaped(shapedRecipe.getAsJsonObject());
                recipes.put(recipe.getNetworkId(), recipe);
                pgbar.step();
            }
            for (var shapelessRecipe : shapelessRecipes) {
                var recipe = parseShapeless(shapelessRecipe.getAsJsonObject());
                recipes.put(recipe.getNetworkId(), recipe);
                pgbar.step();
            }
        }
        log.info(TrKeys.A_RECIPE_LOADED, recipes.size());
        return recipes;
    }

    protected ShapelessRecipe parseShapeless(JsonObject obj) {
        // Ingredients
        List<ItemDescriptor> ingredients = new ArrayList<>();
        obj.getAsJsonArray("input").forEach(item -> {
            ingredients.add(parseItemDescriptor(item.getAsJsonObject()));
        });

        return ShapelessRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .ingredients(ingredients.toArray(ItemDescriptor[]::new))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .outputs(parseOutputs(obj).toArray(ItemStack[]::new))
                .tag(obj.get("tag").getAsString())
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
    }

    protected ShapedRecipe parseShaped(JsonObject obj) {
        // Pattern
        List<String> patternList = new ArrayList<>();
        obj.getAsJsonArray("pattern").forEach(line -> {
            patternList.add(line.getAsString());
        });

        // Keys
        Map<Character, ItemDescriptor> keys = new HashMap<>();
        obj.getAsJsonObject("input").entrySet().forEach(k -> {
            // the length of k.getKey() must be 1 so we can safely convert it to a char
            var key = k.getKey().charAt(0);
            var itemDescriptor = parseItemDescriptor(k.getValue().getAsJsonObject());
            keys.put(key, itemDescriptor);
        });

        return ShapedRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .pattern(ShapedRecipe.PatternHelper.build(patternList))
                .keys(keys)
                .outputs(parseOutputs(obj).toArray(ItemStack[]::new))
                .tag(obj.get("tag").getAsString())
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
    }


    protected List<ItemStack> parseOutputs(JsonObject obj) {
        // Multi outputs is possible
        List<ItemStack> outputs = new ArrayList<>();
        var outputJson = obj.get("output");
        if (outputJson.isJsonObject()) {
            outputs.add(parseOutput(outputJson.getAsJsonObject()));
        } else {
            for (var output : outputJson.getAsJsonArray()) {
                outputs.add(parseOutput(output.getAsJsonObject()));
            }
        }
        return outputs;
    }

    protected ItemDescriptor parseItemDescriptor(JsonObject jsonObject) {
        return switch (parseItemDescriptorType(jsonObject)) {
            case DEFAULT -> {
                Identifier itemId = new Identifier(jsonObject.get("item").getAsString());
                var itemType = Registries.ITEMS.get(itemId);
                // "data" field only exists in default item descriptor
                var meta = jsonObject.get("data");
                if (meta != null) {
                    yield new DefaultDescriptor(itemType, meta.getAsInt());
                } else {
                    yield new DefaultDescriptor(itemType);
                }
            }
            case ITEM_TAG -> {
                var tagId = jsonObject.get("tag").getAsString();
                var itemTag = VanillaItemTags.getTagByName(tagId);
                Objects.requireNonNull(itemTag, "Unknown item tag: " + tagId);
                yield new ItemTagDescriptor(itemTag);
            }
        };
    }

    protected ItemDescriptorType parseItemDescriptorType(JsonObject jsonObject) {
        if (jsonObject.has("tag")) return ItemDescriptorType.ITEM_TAG;
        else return ItemDescriptorType.DEFAULT;
    }

    protected enum ItemDescriptorType {
        DEFAULT,
        ITEM_TAG
    }

    protected ItemStack parseOutput(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = Registries.ITEMS.get(itemId);
        Objects.requireNonNull(itemType, "Unknown item type: " + itemId);
        var count = jsonObject.get("count").getAsInt();
        var meta = jsonObject.has("data") ? jsonObject.get("data").getAsInt() : 0;
        var nbtMap = jsonObject.has("nbt") ? AllayNbtUtils.base64ToNbt(jsonObject.get("nbt").getAsString()) : NbtMap.EMPTY;
        return itemType.createItemStack(
                SimpleItemStackInitInfo
                        .builder()
                        .count(count)
                        .meta(meta)
                        .extraTag(nbtMap)
                        .build()
        );
    }
}
