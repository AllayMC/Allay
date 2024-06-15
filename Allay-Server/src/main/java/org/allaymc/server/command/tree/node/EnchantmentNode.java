package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.item.enchantment.EnchantmentRegistry;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.utils.Identifier;

import static org.allaymc.api.utils.Identifier.DEFAULT_NAMESPACE;

/**
 * Allay Project 2024/6/15
 *
 * @author daoge_cmd
 */
public class EnchantmentNode extends EnumNode {

    protected static final String[] ENCHANTMENT_STR_ARRAY =
            EnchantmentRegistry.getRegistry().getContent().m1().values()
                    .stream()
                    .map(type -> type.getIdentifier().path())
                    .toArray(String[]::new);

    public EnchantmentNode(String name, CommandNode parent, EnchantmentType defaultValue) {
        super(name, parent, defaultValue, ENCHANTMENT_STR_ARRAY);
    }

    @Override
    protected Object argToResult(String arg) {
        return EnchantmentRegistry.getRegistry().getByK2(new Identifier(DEFAULT_NAMESPACE, arg));
    }
}
