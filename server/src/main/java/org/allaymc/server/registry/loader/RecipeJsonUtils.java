package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.nbt.NbtMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
@UtilityClass
public final class RecipeJsonUtils {

    public static ItemStack parseOutput(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = Registries.ITEMS.get(itemId);
        Objects.requireNonNull(itemType, "Unknown item type: " + itemId);
        var count = jsonObject.get("count").getAsInt();
        var meta = jsonObject.has("data") ? jsonObject.get("data").getAsInt() : 0;
        var nbtMap = jsonObject.has("nbt") ? AllayNBTUtils.base64ToNbt(jsonObject.get("nbt").getAsString()) : NbtMap.EMPTY;
        return itemType.createItemStack(
                ItemStackInitInfo
                        .builder()
                        .count(count)
                        .meta(meta)
                        .extraTag(nbtMap)
                        .build()
        );
    }

    public static List<ItemStack> parseOutputs(JsonObject obj) {
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

    public static ItemDescriptor parseItemDescriptor(JsonObject jsonObject) {
        return switch (parseItemDescriptorType(jsonObject)) {
            case ITEM_TYPE -> parseItemTypeDescriptor(jsonObject);
            case ITEM_TAG -> parseItemTagDescriptor(jsonObject);
        };
    }

    public static ItemTagDescriptor parseItemTagDescriptor(JsonObject jsonObject) {
        var tagId = jsonObject.get("tag").getAsString();
        var itemTag = ItemTags.getTagByName(tagId);
        Objects.requireNonNull(itemTag, "Unknown item tag: " + tagId);
        return new ItemTagDescriptor(itemTag);
    }

    public static ItemTypeDescriptor parseItemTypeDescriptor(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = Registries.ITEMS.get(itemId);
        Objects.requireNonNull(itemType, "Unknown item type: " + itemId);
        var meta = jsonObject.get("data");
        if (meta != null) {
            return new ItemTypeDescriptor(itemType, meta.getAsInt());
        } else {
            // When meta is null, it means that any meta values are allowed
            return new ItemTypeDescriptor(itemType);
        }
    }

    public static ItemStack parseItemStack(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = Registries.ITEMS.get(itemId);
        Objects.requireNonNull(itemType, "Unknown item type: " + itemId);
        var meta = jsonObject.get("data");
        if (meta != null) {
            return itemType.createItemStack(1, meta.getAsInt());
        } else {
            return itemType.createItemStack(1);
        }
    }

    public static ItemDescriptorType parseItemDescriptorType(JsonObject jsonObject) {
        if (jsonObject.has("tag")) return ItemDescriptorType.ITEM_TAG;
        else return ItemDescriptorType.ITEM_TYPE;
    }

    public enum ItemDescriptorType {
        ITEM_TYPE,
        ITEM_TAG
    }
}
