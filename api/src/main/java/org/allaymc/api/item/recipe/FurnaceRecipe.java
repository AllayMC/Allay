package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.Locale;
import java.util.Objects;

/**
 * {@code FurnaceRecipe} describes one ingredient descriptor and output for
 * furnace-style processing. {@code Type.FURNACE} accepts every supplied furnace
 * category, while the other recipe types require an exact category match.
 * Item-stack constructors convert their ingredient into an item-type descriptor
 * with wildcard metadata, and descriptor constructors retain the supplied
 * matching rule.
 *
 * <p>
 * Recipe identifiers combine a normalized descriptor key with the furnace
 * category. Tag descriptors use a {@code "tag_"} prefix so tag lookups and
 * concrete-item lookups can construct their respective keys. Item-type
 * metadata, priority, and output do not participate in these identifiers, so
 * recipes differing only in those properties share an identifier.
 *
 * @author daoge_cmd
 */
@Getter
public class FurnaceRecipe extends Recipe {
    /**
     * {@code ingredient} retains the supplied descriptor and is exposed by
     * {@code getIngredient()}; later descriptor changes therefore affect
     * matching.
     */
    protected final ItemDescriptor ingredient;
    /**
     * Furnace category accepted by this recipe.
     */
    protected final Type type;

    /**
     * Creates a zero-priority recipe matching the ingredient's item type with
     * wildcard metadata. Ingredient count, metadata, and NBT do not become
     * matching constraints; the output stack is retained directly.
     */
    public FurnaceRecipe(ItemStack ingredient, ItemStack output, Type type) {
        this(new ItemTypeDescriptor(ingredient.getItemType()), output, 0, type);
    }

    /**
     * Creates a zero-priority recipe with the supplied ingredient descriptor.
     * The descriptor and output stack are retained directly, and identifier
     * construction accepts only item-type or item-tag descriptors.
     */
    public FurnaceRecipe(ItemDescriptor ingredient, ItemStack output, Type type) {
        this(ingredient, output, 0, type);
    }

    /**
     * Creates a recipe retaining the descriptor, output stack, and explicit
     * priority. Identifier construction uses the descriptor and furnace type,
     * so output, priority, and item-type metadata do not distinguish
     * identifiers.
     *
     * @throws IllegalArgumentException if non-{@code null} {@code ingredient}
     * has an unsupported descriptor implementation
     */
    public FurnaceRecipe(ItemDescriptor ingredient, ItemStack output, int priority, Type type) {
        super(buildIdentifier(ingredient, type), new ItemStack[]{output}, priority);
        this.ingredient = Objects.requireNonNull(ingredient, "ingredient");
        this.type = type;
    }

    /**
     * Builds the furnace recipe identifier from an ingredient's item type and
     * furnace category. The ingredient's metadata, count, and NBT are ignored,
     * matching the item-stack constructor's wildcard descriptor.
     */
    public static Identifier buildIdentifier(ItemStack ingredient, Type type) {
        return buildIdentifier(new ItemTypeDescriptor(ingredient.getItemType()), type);
    }

    /**
     * Builds an identifier from the descriptor key and lowercase furnace
     * category. Item-tag keys receive a {@code "tag_"} prefix, while item-type
     * keys omit metadata; unsupported descriptor implementations throw
     * {@code IllegalArgumentException}.
     */
    public static Identifier buildIdentifier(ItemDescriptor ingredient, Type type) {
        return new Identifier("furnace." + index(ingredient) + "_" + type.name().toLowerCase(Locale.ROOT));
    }

    /**
     * Returns the normalized descriptor key used by furnace recipe registration
     * and lookup. Item-type descriptors use their namespaced item identifier
     * without metadata; item-tag descriptors use their tag name with a
     * {@code "tag_"} prefix.
     *
     * <p>
     * {@code FurnaceRecipeMatcher} builds tag lookup keys through the same
     * path, keeping descriptor category encoding consistent between
     * construction and search. Namespace separators become underscores in
     * either category. Other descriptor implementations throw
     * {@code IllegalArgumentException} because this key builder has no encoding
     * for their matching rules.
     */
    private static String index(ItemDescriptor ingredient) {
        return switch (ingredient) {
            case ItemTypeDescriptor item -> normalize(item.getItemType().getIdentifier().toString());
            case ItemTagDescriptor tag -> "tag_" + normalize(tag.getItemTag().name());
            default -> throw new IllegalArgumentException("Unsupported furnace ingredient descriptor: " + ingredient.getClass().getName());
        };
    }

    /**
     * Replaces namespace separators with underscores for use inside recipe
     * paths.
     */
    private static String normalize(String identifier) {
        // The full source identifier becomes part of one generated recipe path.
        // Replace its namespace separator so it does not split that recipe identifier.
        return identifier.replace(Identifier.NAMESPACE_SEPARATOR, "_");
    }

    /**
     * Returns whether the input furnace category and ingredient satisfy this
     * recipe. {@code Type.FURNACE} accepts any supplied category; every other
     * recipe category requires identity equality before the retained descriptor
     * checks the stack.
     */
    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof FurnaceRecipeInput(
                ItemStack ingredient1, Type type1
        ) && (type == Type.FURNACE || type == type1)) {
            return ingredient.match(ingredient1);
        }

        return false;
    }

    /**
     * {@code Type} identifies the furnace block category used by recipe
     * matching.
     */
    public enum Type {
        /**
         * The recipe can be used in all furnace blocks.
         */
        FURNACE,
        /**
         * The recipe can only be used in a blast furnace.
         */
        BLAST_FURNACE,
        /**
         * The recipe can only be used in a smoker.
         */
        SMOKER,
        /**
         * The recipe can only be used in a campfire.
         */
        CAMPFIRE,
        /**
         * The recipe can only be used in a soul campfire.
         */
        SOUL_CAMPFIRE;
    }
}
