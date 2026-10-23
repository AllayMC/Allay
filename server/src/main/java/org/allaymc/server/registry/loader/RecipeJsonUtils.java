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
 * {@code RecipeJsonUtils} decodes recipe item stacks and ingredient descriptors from bundled
 * JSON data. Registry lookups resolve item and tag identities, while output defaults and
 * ingredient wildcard metadata differ: omitted output metadata selects zero, but omitted
 * ingredient metadata accepts every metadata value.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class RecipeJsonUtils {

    /**
     * Creates an output stack from its item identity and required count. Missing {@code "data"}
     * selects metadata zero and missing {@code "nbt"} selects an empty compound; unknown item
     * identities and malformed supplied fields fail parsing.
     */
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

    /**
     * Decodes {@code "output"} as either one object or an array of objects. The returned list is
     * mutable and preserves array order, and each entry uses the same output-count, metadata,
     * and NBT rules.
     */
    public static List<ItemStack> parseOutputs(JsonObject obj) {
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

    /**
     * Decodes a tag or item-type ingredient descriptor from one object. Presence of {@code "tag"}
     * takes precedence over {@code "item"}, and the selected parser resolves its identity through
     * the appropriate runtime registry.
     */
    public static ItemDescriptor parseItemDescriptor(JsonObject jsonObject) {
        return switch (parseItemDescriptorType(jsonObject)) {
            case ITEM_TYPE -> parseItemTypeDescriptor(jsonObject);
            case ITEM_TAG -> parseItemTagDescriptor(jsonObject);
        };
    }

    /**
     * Creates a descriptor for the named item tag. The tag must already exist in {@code ItemTags};
     * an unresolved name fails immediately rather than producing a descriptor that can match
     * an unknown tag.
     */
    public static ItemTagDescriptor parseItemTagDescriptor(JsonObject jsonObject) {
        var tagId = jsonObject.get("tag").getAsString();
        var itemTag = ItemTags.getTagByName(tagId);
        Objects.requireNonNull(itemTag, "Unknown item tag: " + tagId);
        return new ItemTagDescriptor(itemTag);
    }

    /**
     * Creates an ingredient descriptor for a registered item type. Present {@code "data"} requires
     * that metadata value, while an omitted field permits every metadata value; unknown item
     * identities fail before descriptor construction.
     */
    public static ItemTypeDescriptor parseItemTypeDescriptor(JsonObject jsonObject) {
        var itemId = new Identifier(jsonObject.get("item").getAsString());
        var itemType = Registries.ITEMS.get(itemId);
        Objects.requireNonNull(itemType, "Unknown item type: " + itemId);
        var meta = jsonObject.get("data");
        if (meta != null) {
            return new ItemTypeDescriptor(itemType, meta.getAsInt());
        } else {
            return new ItemTypeDescriptor(itemType);
        }
    }

    /**
     * Creates a one-item stack for a registered recipe input, output, or reagent identity. Present
     * {@code "data"} supplies metadata; omission uses the item type's default stack metadata,
     * and unknown item identities fail parsing.
     */
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

    /**
     * Returns {@code ITEM_TAG} when {@code "tag"} is present and {@code ITEM_TYPE} otherwise.
     * This classification does not validate the chosen field's contents, and tag presence wins
     * even when an item field is also supplied.
     */
    public static ItemDescriptorType parseItemDescriptorType(JsonObject jsonObject) {
        if (jsonObject.has("tag")) return ItemDescriptorType.ITEM_TAG;
        else return ItemDescriptorType.ITEM_TYPE;
    }

    /**
     * {@code ItemDescriptorType} identifies the recipe ingredient parser selected by JSON field
     * presence.
     */
    public enum ItemDescriptorType {
        /**
         * Registered item type ingredient.
         */
        ITEM_TYPE,
        /**
         * Registered item tag ingredient.
         */
        ITEM_TAG
    }
}
