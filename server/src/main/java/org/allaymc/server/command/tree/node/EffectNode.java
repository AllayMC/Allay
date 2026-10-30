package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

import static org.allaymc.api.utils.identifier.Identifier.DEFAULT_NAMESPACE;

/**
 * {@code EffectNode} converts a registered effect spelling into its
 * {@code EffectType} for command executors. Accepted spellings are snapshotted
 * from the registry when this class initializes and used by inherited exact
 * enumeration matching. Accepted spellings are bare paths, and result lookup
 * always supplies the default namespace. Later registry changes do not rebuild
 * that shared spelling array.
 *
 * @author IWareQ
 */
public class EffectNode extends EnumNode {

    /**
     * {@code STR_EFFECTS} snapshots effect spellings at class initialization
     * for matching and advertisement. Every node shares the array, while result
     * conversion still reads the current registry.
     */
    protected static final String[] STR_EFFECTS = Registries.EFFECTS.getContent().m1().values()
            .stream()
            .map(type -> type.getIdentifier().path())
            .toArray(String[]::new);

    /**
     * Creates an unattached effect node using the shared registry-derived
     * spelling snapshot. The supplied default is retained without enabling
     * optionality; matching and network suggestions use that existing snapshot
     * rather than rebuilding it.
     */
    public EffectNode(String name, CommandNode parent, EffectType defaultValue) {
        super(name, parent, defaultValue, STR_EFFECTS);
    }

    /**
     * Returns the current registered effect for the supplied matched spelling.
     * Accepted spellings are bare paths, and result lookup always supplies the
     * default namespace. A registry entry removed after the spelling snapshot
     * was created can produce {@code null}.
     */
    @Override
    protected Object argToResult(String arg) {
        return Registries.EFFECTS.getByK2(new Identifier(DEFAULT_NAMESPACE, arg));
    }
}
