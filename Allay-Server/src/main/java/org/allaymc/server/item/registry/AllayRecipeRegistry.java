package org.allaymc.server.item.registry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ConsoleProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.DefaultDescriptor;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.utils.AllayNbtUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.item.type.AllayItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;

import java.io.InputStreamReader;
import java.util.*;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayRecipeRegistry implements RecipeRegistry {
    private final Map<Integer, NetworkRecipe> networkRecipes = new Int2ObjectOpenHashMap<>();
    private final Map<Identifier, ShapedRecipe> shapedRecipes = new HashMap<>();
    private final Map<Identifier, ShapelessRecipe> shapelessRecipes = new HashMap<>();
    private final Set<Recipe> recipes = new HashSet<>();
    private List<RecipeData> networkRecipeData = null;
    private boolean shouldUpdateNetworkRecipeDataCache = true;

    public void registerVanillaRecipes() {
        var stream = AllayItemType.class.getClassLoader().getResourceAsStream("recipes.json");
        if (stream == null) return;

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
                registerShaped(parseShaped(shapedRecipe.getAsJsonObject()));
                pgbar.step();
            }

            for (var shapelessRecipe : shapelessRecipes) {
                registerShapeless(parseShapeless(shapelessRecipe.getAsJsonObject()));
                pgbar.step();
            }
//            for (var entry : array) {
//                var obj = entry.getAsJsonObject();
//
//                switch (CraftingDataType.byId(obj.get("type").getAsInt())) {
//                    case SHAPELESS -> registerShapeless(parseShapeless(obj));
//                    case SHAPED -> registerShaped(parseShaped(obj));
//                    case FURNACE, FURNACE_DATA, MULTI, SHULKER_BOX, SHAPELESS_CHEMISTRY, SHAPED_CHEMISTRY,
//                         SMITHING_TRANSFORM, SMITHING_TRIM -> {
//                        // TODO
//                    }
//                    default ->
//                            throw new IllegalStateException("Unexpected value: " + CraftingDataType.byId(obj.get("type").getAsInt()));
//                }
//
//                // furnace recipes don't have both id and uuid
//                pgbar.setExtraMessage(
//                        obj.has("id") ? obj.get("id").getAsString() :
//                                obj.has("uuid") ? obj.get("uuid").getAsString() : "");
//                pgbar.step();
//            }
        }
    }

    private ShapelessRecipe parseShapeless(JsonObject obj) {
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

    private ShapedRecipe parseShaped(JsonObject obj) {
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


    private List<ItemStack> parseOutputs(JsonObject obj) {
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

    private ItemDescriptor parseItemDescriptor(JsonObject jsonObject) {
        return switch (parseItemDescriptorType(jsonObject)) {
            case DEFAULT -> {
                Identifier itemId = new Identifier(jsonObject.get("item").getAsString());
                var itemType = ItemTypeRegistry.getRegistry().get(itemId);
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

    private ItemDescriptorType parseItemDescriptorType(JsonObject jsonObject) {
        if (jsonObject.has("tag")) return ItemDescriptorType.ITEM_TAG;
        else return ItemDescriptorType.DEFAULT;
    }

    private enum ItemDescriptorType {
        DEFAULT,
        ITEM_TAG
    }

    private ItemStack parseOutput(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = ItemTypeRegistry.getRegistry().get(itemId);
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

    @Override
    public NetworkRecipe getRecipeByNetworkId(int networkId) {
        return networkRecipes.get(networkId);
    }

    @Override
    public void registerShaped(ShapedRecipe recipe) {
        networkRecipes.put(recipe.getNetworkId(), recipe);
        shapedRecipes.put(recipe.getIdentifier(), recipe);
        recipes.add(recipe);
        shouldUpdateNetworkRecipeDataCache = true;
    }

    @Override
    public ShapedRecipe getShapedRecipe(Identifier identifier) {
        return shapedRecipes.get(identifier);
    }

    @Override
    public void registerShapeless(ShapelessRecipe recipe) {
        networkRecipes.put(recipe.getNetworkId(), recipe);
        shapelessRecipes.put(recipe.getIdentifier(), recipe);
        recipes.add(recipe);
        shouldUpdateNetworkRecipeDataCache = true;
    }

    @Override
    public ShapelessRecipe getShapelessRecipe(Identifier identifier) {
        return shapelessRecipes.get(identifier);
    }

    @Override
    public CraftingDataPacket getCraftingDataPacket() {
        var packet = new CraftingDataPacket();
        packet.getCraftingData().addAll(getNetworkRecipeData());
        // TODO packet.getPotionMixData().addAll();
        // TODO packet.getContainerMixData().addAll();
        packet.setCleanRecipes(true);
        return packet;
    }

    @Override
    public Set<Recipe> getRecipes() {
        return Collections.unmodifiableSet(recipes);
    }

    protected List<RecipeData> getNetworkRecipeData() {
        if (shouldUpdateNetworkRecipeDataCache) {
            networkRecipeData = buildNetworkRecipeData();
            shouldUpdateNetworkRecipeDataCache = false;
        }
        return networkRecipeData;
    }

    protected List<RecipeData> buildNetworkRecipeData() {
        var result = new ArrayList<RecipeData>();
        for (var recipe : recipes) {
            result.add(recipe.toNetworkRecipeData());
        }
        return result;
    }
}
