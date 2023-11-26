package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/26
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class DefaultDescriptor implements ItemDescriptor {

    public static final int WILDCARD_META = 32767;

    protected Identifier identifier;
    protected int meta;

    public DefaultDescriptor(Identifier identifier) {
        this(identifier, WILDCARD_META);
    }

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().getIdentifier().equals(identifier) &&
               (meta == WILDCARD_META || itemStack.getMeta() == meta);
    }
}
