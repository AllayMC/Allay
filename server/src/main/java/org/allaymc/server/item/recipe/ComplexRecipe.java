package org.allaymc.server.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.UUID;

/**
 * {@code ComplexRecipe} represents a recipe whose outputs depend on the
 * supplied input rather than a fixed output array. A protocol-defined UUID
 * identifies the special recipe to clients, while server implementations
 * compute outputs explicitly; output access without an input is unsupported.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class ComplexRecipe extends Recipe {

    /**
     * {@code uuid} identifies the client's special recipe behavior. Callers
     * supply the protocol-defined value from {@link ComplexRecipeIds} rather
     * than generating an arbitrary UUID.
     */
    protected final UUID uuid;

    /**
     * Creates a dynamic recipe using the supplied client recipe UUID. The
     * inherited identifier embeds that UUID, priority is zero, and fixed
     * outputs are {@code null}; callers use input-dependent output access.
     */
    protected ComplexRecipe(UUID uuid) {
        super(new Identifier("complex." + uuid), null, 0);
        this.uuid = uuid;
    }

    /**
     * Computes the outputs for a supported input, returning {@code null} when
     * it does not match. Implementations define output ownership and any input
     * side effects, so callers consult the concrete recipe before retaining or
     * reusing stacks.
     */
    public abstract ItemStack[] getOutputs(RecipeInput input);

    /**
     * Rejects output access that does not supply recipe input. Dynamic recipes
     * require {@code getOutputs(RecipeInput)} to compute their result, so this
     * method always throws {@code UnsupportedOperationException} instead of
     * exposing fixed output storage.
     */
    @Override
    public ItemStack[] getOutputs() {
        throw new UnsupportedOperationException("Cannot get outputs of a complex recipe without input!");
    }

    /**
     * Rejects output access that does not supply recipe input. Dynamic recipes
     * require {@code getOutputs(RecipeInput)} to compute their result, so this
     * method always throws {@code UnsupportedOperationException} instead of
     * exposing fixed output storage.
     */
    @Override
    public ItemStack getOutput() {
        throw new UnsupportedOperationException("Cannot get outputs of a complex recipe without input!");
    }
}
