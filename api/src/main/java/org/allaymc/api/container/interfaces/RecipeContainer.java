package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * {@code RecipeContainer} exposes container contents as recipe inputs for matching and
 * crafting actions. Each implementation selects the input representation appropriate to
 * its workstation; callers must use that representation without assuming that every
 * implementation copies stored item stacks.
 *
 * @author IWareQ
 */
public interface RecipeContainer extends Container {
    /**
     * Creates the recipe-input representation of this container's current contents. The
     * concrete workstation determines its shape and whether input stacks are copied or
     * remain shared with the container.
     */
    RecipeInput createRecipeInput();
}
