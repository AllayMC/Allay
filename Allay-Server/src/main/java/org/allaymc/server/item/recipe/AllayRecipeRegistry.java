package org.allaymc.server.item.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ComplexAliasDescriptor;
import org.allaymc.api.item.descriptor.DefaultDescriptor;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.server.item.type.AllayItemType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;

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
        var array = JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(AllayItemType.class.getClassLoader().getResourceAsStream("recipes.json")))).getAsJsonObject().get("recipes").getAsJsonArray();
        try (var pgbar = ProgressBar
                .builder()
                .setInitialMax(array.size())
                .setTaskName("Loading vanilla recipes")
                .setConsumer(new ConsoleProgressBarConsumer(System.out))
                .setUpdateIntervalMillis(100)
                .build()) {
            for (var entry : array) {
                var obj = entry.getAsJsonObject();

                switch (CraftingDataType.byId(obj.get("type").getAsInt())) {
                    case SHAPED -> parseShaped(obj);
                    // TODO: more type
                    default -> {
//                        throw new IllegalStateException("Unexpected value: " + CraftingDataType.byId(obj.get("type").getAsInt()));
                    }
                }

                // furnace recipes don't have both id and uuid
                pgbar.setExtraMessage(
                        obj.has("id") ? obj.get("id").getAsString() :
                                obj.has("uuid") ? obj.get("uuid").getAsString() : "");
                pgbar.step();
            }
        }
    }

    private void parseShaped(JsonObject obj) {
        // Pattern
        List<String> patternList = new ArrayList<>();
        obj.getAsJsonArray("shape").forEach(line -> {
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

        // Outputs
        // Multi output is possible
        List<ItemStack> outputs = new ArrayList<>();
        var outputJson = obj.get("output");
        if (outputJson.isJsonObject()) {
            outputs.add(parseOutput(outputJson.getAsJsonObject()));
        } else {
            for (var output : outputJson.getAsJsonArray()) {
                outputs.add(parseOutput(output.getAsJsonObject()));
            }
        }

        var recipe = ShapedRecipe
                .builder()
                .identifier(new Identifier(obj.get("id").getAsString()))
                .priority(obj.has("priority") ? obj.get("priority").getAsInt() : 0)
                .pattern(ShapedRecipe.PatternHelper.build(patternList))
                .keys(keys)
                .outputs(outputs.toArray(ItemStack[]::new))
                .tags(new String[]{obj.get("block").getAsString()})
                .uuid(UUID.fromString(obj.get("uuid").getAsString()))
                .build();
        registerShaped(recipe);
    }

    private ItemDescriptor parseItemDescriptor(JsonObject jsonObject) {
        return switch(jsonObject.get("type").getAsString()) {
            case "default" -> {
                var itemId = new Identifier(jsonObject.get("itemId").getAsString());
                // meta (data) field is optional
                if (jsonObject.has("auxValue")) {
                    var meta = jsonObject.get("auxValue").getAsInt();
                    yield new DefaultDescriptor(itemId, meta);
                } else {
                    yield new DefaultDescriptor(itemId);
                }
            }
            case "complex_alias" -> {
                var name = new Identifier(jsonObject.get("name").getAsString());
                yield new ComplexAliasDescriptor(name);
            }
            case "item_tag" -> {
                var tagId = jsonObject.get("itemTag").getAsString();
                var itemTag = VanillaItemTags.getTagByName(tagId);
                Objects.requireNonNull(itemTag, "Unknown item tag: " + tagId);
                yield new ItemTagDescriptor(itemTag);
            }
            default -> throw new IllegalStateException("Unexpected value: " + jsonObject.get("type").getAsString());
        };
    }

    private ItemStack parseOutput(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("id").getAsString());
        var itemType = ItemTypeRegistry.getRegistry().get(itemId);
        Objects.requireNonNull(itemType, "Unknown item type: " + itemId);
        var count = jsonObject.get("count").getAsInt();
        var meta = jsonObject.has("damage") ? jsonObject.get("damage").getAsInt() : 0;
        // TODO: nbt_b64
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
