package org.allaymc.server.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.UUID;

/**
 * ComplexRecipe represents a special type of recipe whose output is dynamic based on the input.
 * The matching logic for these recipes needs to be written manually and cannot be data-driven.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class ComplexRecipe extends Recipe {

    /**
     * The unique identifier ({@link UUID}) associated with this complex recipe.
     * <p>
     * The UUID for each complex recipe is hard-coded and cannot be changed or be generated
     * randomly since the client relies on the UUID to identify the recipe. All available
     * complex recipe UUIDs can be found in {@link ComplexRecipeIds}.
     */
    protected final UUID uuid;

    protected ComplexRecipe(UUID uuid) {
        super(new Identifier("complex." + uuid), null, 0);
        this.uuid = uuid;
    }

    /**
     * Retrieves the outputs of the recipe based on the input. If the input does not match, this method should
     * return {@code null}.
     *
     * @return the output {@link ItemStack}s based on the input, or {@code null} if the input does not match
     */
    public abstract ItemStack[] getOutputs(RecipeInput input);

    @Override
    public ItemStack[] getOutputs() {
        throw new UnsupportedOperationException("Cannot get outputs of a complex recipe without input!");
    }

    @Override
    public ItemStack getOutput() {
        throw new UnsupportedOperationException("Cannot get outputs of a complex recipe without input!");
    }
}
