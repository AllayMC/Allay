package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

import static org.allaymc.api.utils.identifier.Identifier.DEFAULT_NAMESPACE;

/**
 * {@code EnchantmentNode} converts a registered enchantment spelling into its
 * {@code EnchantmentType} for command executors. Accepted spellings are
 * snapshotted from the registry when this class initializes and used by
 * inherited exact enumeration matching. Accepted spellings include full
 * identifiers and bare paths; bare paths resolve in the default namespace.
 * Later registry changes do not rebuild that shared spelling array.
 *
 * @author daoge_cmd
 */
public class EnchantmentNode extends EnumNode {

    /**
     * {@code ENCHANTMENT_STR_ARRAY} snapshots enchantment spellings at class
     * initialization for matching and advertisement. Every node shares the
     * array, while result conversion still reads the current registry.
     */
    protected static final String[] ENCHANTMENT_STR_ARRAY =
            Registries.ENCHANTMENTS.getContent().m1().values()
                    .stream()
                    .<String>mapMulti((type, consumer) -> {
                        consumer.accept(type.getIdentifier().toString());
                        consumer.accept(type.getIdentifier().path());
                    })
                    .toArray(String[]::new);

    /**
     * Creates an unattached enchantment node using the shared registry-derived
     * spelling snapshot. The supplied default is retained without enabling
     * optionality; matching and network suggestions use that existing snapshot
     * rather than rebuilding it.
     */
    public EnchantmentNode(String name, CommandNode parent, EnchantmentType defaultValue) {
        super(name, parent, defaultValue, ENCHANTMENT_STR_ARRAY);
    }

    /**
     * Returns the current registered enchantment for the supplied matched
     * spelling. Accepted spellings include full identifiers and bare paths;
     * bare paths resolve in the default namespace. A registry entry removed
     * after the spelling snapshot was created can produce {@code null}.
     */
    @Override
    protected Object argToResult(String arg) {
        return Registries.ENCHANTMENTS.getByK2(arg.contains(":") ? new Identifier(arg) : new Identifier(DEFAULT_NAMESPACE, arg));
    }
}
