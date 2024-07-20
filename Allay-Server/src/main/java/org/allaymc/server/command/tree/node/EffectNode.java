package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;

import static org.allaymc.api.utils.Identifier.DEFAULT_NAMESPACE;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
public class EffectNode extends EnumNode {

    protected static final String[] STR_EFFECTS = Registries.EFFECTS.getContent().m1().values()
            .stream()
            .map(type -> type.getIdentifier().path())
            .toArray(String[]::new);

    public EffectNode(String name, CommandNode parent, EffectType defaultValue) {
        super(name, parent, defaultValue, STR_EFFECTS);
    }

    @Override
    protected Object argToResult(String arg) {
        return Registries.EFFECTS.getByK2(new Identifier(DEFAULT_NAMESPACE, arg));
    }
}
