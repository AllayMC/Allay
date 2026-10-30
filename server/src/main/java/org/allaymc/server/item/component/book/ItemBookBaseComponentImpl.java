package org.allaymc.server.item.component.book;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.server.item.component.ItemBaseComponentImpl;

/**
 * {@code ItemBookBaseComponentImpl} supplies ordinary book stack behavior with
 * broad enchantment-table eligibility. The inherited base component owns stack
 * state, while this specialization allows the enchantment option generator to
 * consider enchantments that do not apply directly to the book item type.
 *
 * @author daoge_cmd
 */
public class ItemBookBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates book stack behavior from the supplied item initialization data.
     */
    public ItemBookBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns whether enchantment-table candidates may bypass item-type
     * applicability checks. This implementation always returns {@code true},
     * while the option generator still applies its other eligibility and level
     * filters.
     */
    @Override
    public boolean isAllEnchantmentsAvailableInEnchantTable() {
        return true;
    }
}
