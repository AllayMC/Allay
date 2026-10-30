package org.allaymc.api.item.recipe.descriptor;

import org.allaymc.api.item.ItemStack;

/**
 * {@code ItemDescriptor} defines an ingredient predicate used by recipe
 * matching. Implementations choose which item properties matter, allowing
 * recipes to accept a particular type and metadata or a broader item tag; a
 * successful match does not itself consume the candidate stack.
 *
 * @author daoge_cmd
 */
public interface ItemDescriptor {
    /**
     * Returns whether the candidate stack satisfies this descriptor's
     * ingredient rule.
     */
    boolean match(ItemStack itemStack);
}
