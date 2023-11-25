package org.allaymc.server.item.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.descriptor.ItemIdentifierAndMetaDescriptor;
import org.allaymc.api.item.descriptor.ItemIdentifierDescriptor;
import org.allaymc.api.item.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemUnknownStack;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.server.item.type.AllayItemType;

import java.io.InputStreamReader;
import java.util.*;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayRecipeRegistry implements RecipeRegistry {
    private final Map<Identifier, ShapedRecipe> shapedRecipes = new HashMap<>();

    public void registerVanillaRecipes() {
        registerVanillaShapedRecipes();
        // TODO
    }

    private void registerVanillaShapedRecipes() {
        var array = JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(AllayItemType.class.getClassLoader().getResourceAsStream("recipes/recipe_shaped.json")))).getAsJsonArray();
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(array.size())
                .setTaskName("Loading vanilla shaped recipes")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var entry : array) {
                var obj = entry.getAsJsonObject();

                // Pattern
                List<String> patternList = new ArrayList<>();
                obj.getAsJsonArray("pattern").forEach(line -> {
                    patternList.add(line.getAsString());
                });

                // Keys
                Map<Character, ItemDescriptor> keys = new HashMap<>();
                obj.getAsJsonObject("key").entrySet().forEach(k -> {
                    // the length of k.getKey() must be 1 so we can safely convert it to a char
                    var key = k.getKey().charAt(0);
                    var itemDescriptor = parseItemDescriptor(k.getValue().getAsJsonObject());
                    keys.put(key, itemDescriptor);
                });

                // Outputs
                // Multi output is possible
                List<ItemStack> outputs = new ArrayList<>();
                var result = obj.get("result");
                if (result.isJsonObject()) {
                    outputs.add(parseItemStack(result.getAsJsonObject()));
                } else {
                    for (var output : result.getAsJsonArray()) {
                        outputs.add(parseItemStack(output.getAsJsonObject()));
                    }
                }

                // Tags
                var tags = new ArrayList<String>();
                obj.getAsJsonArray("tags").forEach(tag -> {
                    tags.add(tag.getAsString());
                });

                // Unlock
                var unlockItems = new ArrayList<ItemDescriptor>();
                String unlockCondition = null;
                if (obj.has("unlock")) {
                    var unlock = obj.get("unlock");
                    if (unlock.isJsonArray()) {
                        unlock.getAsJsonArray().forEach(item -> {
                            unlockItems.add(parseItemDescriptor(item.getAsJsonObject()));
                        });
                    } else {
                        unlockCondition = unlock.getAsJsonObject().get("context").getAsString();
                    }
                }

                var recipeId = obj.getAsJsonObject("description").get("identifier").getAsString();
                var recipe = ShapedRecipe
                        .builder()
                        .identifier(new Identifier(recipeId))
                        .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                        .group(obj.has("group") ? obj.get("group").getAsString() : "")
                        .pattern(patternList)
                        .keys(keys)
                        .outputs(outputs.toArray(ItemStack[]::new))
                        .tags(tags.toArray(String[]::new))
                        .unlockItems(unlockItems.toArray(ItemDescriptor[]::new))
                        .unlockCondition(unlockCondition)
                        .build();
                registerShaped(recipe);

                pgbar.setExtraMessage(recipeId);
                pgbar.step();
            }
        }
    }

    private ItemDescriptor parseItemDescriptor(JsonObject jsonObject) {
        // There won't be "count" field in item descriptor
        if (jsonObject.has("tag")) {
            // item tag descriptor
            var tagId = jsonObject.get("tag").getAsString();
            var itemTag = VanillaItemTags.getTagByName(tagId);
            Objects.requireNonNull(itemTag, "Unknown item tag: " + tagId);
            return new ItemTagDescriptor(itemTag);
        } else {
            // jsonObject.has("item") == true
            // common item descriptor
            var itemId = new Identifier(jsonObject.get("item").getAsString());
            ItemDescriptor descriptor;
            // meta (data) field is optional
            if (jsonObject.has("data")) {
                var data = jsonObject.get("data").getAsInt();
                descriptor = new ItemIdentifierAndMetaDescriptor(itemId, data);
            } else {
                descriptor = new ItemIdentifierDescriptor(itemId);
            }
            return descriptor;
        }
    }

    private ItemStack parseItemStack(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = ItemTypeRegistry.getRegistry().get(itemId);
        if (itemType == null) {
            log.warn("Unknown item type: {}", itemId);
            return ItemUnknownStack.UNKNOWN_TYPE.createItemStack(SimpleItemStackInitInfo.builder().count(1).build());
        }
        var count = jsonObject.has("count") ? jsonObject.get("count").getAsInt() : 1;
        var meta = jsonObject.has("data") ? jsonObject.get("data").getAsInt() : 0;
        return itemType.createItemStack(
                SimpleItemStackInitInfo
                        .builder()
                        .count(count)
                        .meta(meta)
                        .build()
        );
    }

    @Override
    public void registerShaped(ShapedRecipe recipe) {
        shapedRecipes.put(recipe.getIdentifier(), recipe);
    }

    @Override
    public ShapedRecipe getShapedRecipe(Identifier identifier) {
        return shapedRecipes.get(identifier);
    }
}
