package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code ShapedRecipe} matches crafting ingredients against character positions
 * in a stored pattern. Empty outer rows and columns are removed from the input
 * before positional comparison, allowing the same arrangement at different grid
 * offsets. A space in the pattern requires air, while every other character
 * selects a descriptor from {@code keys}; matching neither mirrors nor rotates
 * the pattern.
 *
 * <p>
 * The constructor retains {@code pattern}, {@code keys}, and the inherited
 * output storage. Callers supply a nonempty rectangular pattern, descriptors
 * for every nonspace key, and rectangular nonempty input arrays. {@code match}
 * rejects oversized trimmed inputs but still indexes every pattern position, so
 * a smaller nonempty trimmed input can fail with
 * {@code ArrayIndexOutOfBoundsException} rather than return {@code false}.
 *
 * @author daoge_cmd
 */
@Getter
public class ShapedRecipe extends Recipe {

    /**
     * {@code EMPTY_KEY_CHAR} marks pattern positions that must contain air
     * after trimming the input grid.
     */
    public static final char EMPTY_KEY_CHAR = ' ';
    /**
     * {@code EMPTY_ITEM_STACK_ARRAY} represents an all-air grid after trimming,
     * allowing {@code match} to reject inputs without any occupied positions.
     */
    protected static final ItemStack[][] EMPTY_ITEM_STACK_ARRAY = new ItemStack[0][0];

    /**
     * {@code keys} retains the supplied descriptor map. {@code getKeys()}
     * exposes it directly, so map changes alter subsequent matching.
     */
    protected Map<Character, ItemDescriptor> keys;
    /**
     * {@code pattern} retains the supplied rows. {@code getPattern()} exposes
     * the same arrays, so callers must coordinate changes with recipe
     * consumers.
     */
    protected char[][] pattern;

    /**
     * Creates a shaped recipe retaining the supplied pattern, descriptor map,
     * and outputs. Callers provide a nonempty rectangular pattern and a
     * descriptor for every character other than {@code EMPTY_KEY_CHAR};
     * construction performs no validation or copying.
     */
    public ShapedRecipe(Identifier identifier, ItemStack[] outputs, int priority, char[][] pattern, Map<Character, ItemDescriptor> keys) {
        super(identifier, outputs, priority);
        this.pattern = pattern;
        this.keys = keys;
    }

    /**
     * Returns whether a crafting grid matches the stored pattern after removal
     * of outer air rows and columns. The comparison preserves interior air
     * positions and checks each nonspace key through its descriptor; inputs are
     * not consumed.
     *
     * <p>
     * Other input representations, all-air grids, and oversized trimmed grids
     * return {@code false}. The implementation indexes the entire pattern after
     * those checks, so smaller nonempty trimmed grids may throw
     * {@code ArrayIndexOutOfBoundsException}; callers must not rely on a clean
     * mismatch for that case.
     */
    @Override
    public boolean match(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput(ItemStack[][] items))) return false;

        var inputs = removeUselessRowAndColumn(items);
        if (inputs.length == 0 ||
            inputs.length > pattern.length ||
            inputs[0].length > pattern[0].length) {
            return false;
        }

        for (int i = 0, patternLength = pattern.length; i < patternLength; i++) {
            var row = pattern[i];
            for (int j = 0, rowLength = row.length; j < rowLength; j++) {
                var key = row[j];
                var item = inputs[i][j];
                if (key == EMPTY_KEY_CHAR) {
                    if (!item.getItemType().equals(AIR)) {
                        return false;
                    }
                } else {
                    var descriptor = keys.get(key);
                    if (!descriptor.match(item)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // @formatter:off
    /**
     * Returns the occupied rectangular region after discarding outer air rows
     * and columns. Callers supply a nonempty rectangular array containing
     * non-{@code null} stacks; an all-air grid returns
     * {@code EMPTY_ITEM_STACK_ARRAY}.
     *
     * <p>
     * The boundary searches preserve every interior position, including air
     * gaps, so later pattern matching can still distinguish required empty
     * cells from ingredient cells. An already bounded grid returns
     * {@code inputs} itself, while trimming allocates row arrays that retain
     * the original stack references rather than copying item data.
     */
    // @formatter:on
    protected ItemStack[][] removeUselessRowAndColumn(ItemStack[][] inputs) {
        int
                startRow = 0,
                endRow = inputs.length - 1;
        for (int row = 0; row < inputs.length; row++) {
            if (notAllEmptyRow(inputs[row])) {
                startRow = row;
                break;
            }

            if (row == inputs.length - 1) return EMPTY_ITEM_STACK_ARRAY;
        }

        for (int row = inputs.length - 1; row >= 0; row--) {
            if (notAllEmptyRow(inputs[row])) {
                endRow = row;
                break;
            }
        }

        int
                startColumn = 0,
                endColumn = inputs[0].length - 1;
        for (int column = 0; column < inputs[0].length; column++) {
            if (notAllEmptyColumn(inputs, column)) {
                startColumn = column;
                break;
            }
        }

        for (int column = inputs[0].length - 1; column >= 0; column--) {
            if (notAllEmptyColumn(inputs, column)) {
                endColumn = column;
                break;
            }
        }

        if (startRow == 0 && endRow == inputs.length - 1 &&
            startColumn == 0 && endColumn == inputs[0].length - 1
        ) return inputs;

        var result = new ItemStack[endRow - startRow + 1][endColumn - startColumn + 1];
        for (int row = startRow; row <= endRow; row++) {
            if (endColumn + 1 - startColumn >= 0)
                System.arraycopy(inputs[row], startColumn, result[row - startRow], 0, endColumn + 1 - startColumn);
        }

        return result;
    }

    /**
     * Returns whether the supplied row contains an item type other than air.
     */
    protected boolean notAllEmptyRow(ItemStack[] inputs) {
        return Arrays.stream(inputs).anyMatch(item -> item.getItemType() != AIR);
    }

    /**
     * Returns whether the selected column contains an item type other than air.
     */
    protected boolean notAllEmptyColumn(ItemStack[][] inputs, int column) {
        return Arrays.stream(inputs).anyMatch(row -> row[column].getItemType() != AIR);
    }

    /**
     * {@code PatternHelper} constructs character matrices used as shaped recipe
     * patterns.
     */
    public interface PatternHelper {
        /**
         * Creates a three-by-three pattern from the supplied characters in row
         * order.
         */
        static char[][] build(
                char a, char b, char c,
                char d, char e, char f,
                char g, char h, char i
        ) {
            return new char[][]{
                    {a, b, c},
                    {d, e, f},
                    {g, h, i}
            };
        }

        /**
         * Creates a two-by-two pattern from the supplied characters in row
         * order.
         */
        static char[][] build(
                char a, char b,
                char c, char d
        ) {
            return new char[][]{
                    {a, b},
                    {c, d}
            };
        }

        /**
         * Creates a single-position pattern containing the supplied key
         * character.
         */
        static char[][] build(char a) {
            return new char[][]{{a}};
        }

        /**
         * Creates a three-row pattern by copying each supplied string into a
         * character array. Row lengths are preserved independently, so callers
         * provide equal lengths when a rectangular recipe pattern is required.
         */
        static char[][] build(String l1, String l2, String l3) {
            return new char[][]{
                    l1.toCharArray(),
                    l2.toCharArray(),
                    l3.toCharArray()
            };
        }

        /**
         * Creates a two-row pattern by copying each supplied string into a
         * character array. Row lengths are preserved independently, so callers
         * provide equal lengths when a rectangular recipe pattern is required.
         */
        static char[][] build(String l1, String l2) {
            return new char[][]{
                    l1.toCharArray(),
                    l2.toCharArray()
            };
        }

        /**
         * Creates a rectangular pattern from a nonempty list of equal-length
         * strings. Unequal row lengths throw {@code IllegalArgumentException},
         * and an empty list fails when the first row is requested; the
         * resulting arrays are newly allocated.
         */
        static char[][] build(List<String> stringList) {
            var commonLength = stringList.getFirst().length();
            var pattern = new char[stringList.size()][commonLength];
            for (int row = 0; row < stringList.size(); row++) {
                var line = stringList.get(row);
                if (line.length() != commonLength)
                    throw new IllegalArgumentException("All row in pattern must have the same length");

                var charArray = line.toCharArray();
                System.arraycopy(charArray, 0, pattern[row], 0, charArray.length);
            }

            return pattern;
        }
    }
}
