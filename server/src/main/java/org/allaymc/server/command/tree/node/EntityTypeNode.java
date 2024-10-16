package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;

import static org.allaymc.api.utils.Identifier.DEFAULT_NAMESPACE;

/**
 * @author daoge_cmd
 */
public class EntityTypeNode extends EnumNode {

    protected static final String[] ENTITY_STR_ARRAY =
            Registries.ENTITIES.getContent().keySet()
                    .stream()
                    .<String>mapMulti((identifier, consumer) -> {
                        consumer.accept(identifier.toString());
                        consumer.accept(identifier.path());
                        return;
                    }).toArray(String[]::new);

    public EntityTypeNode(String name, CommandNode parent, EntityType<?> defaultValue) {
        super(name, parent, defaultValue, ENTITY_STR_ARRAY);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        if (arg.indexOf(':') == -1) {
            arg = Identifier.DEFAULT_NAMESPACE + ":" + arg;
        }
        var itemType = Registries.ENTITIES.get(new Identifier(arg));
        if (itemType == null) {
            context.addError("%" + TrKeys.M_COMMANDS_GENERIC_ENTITY_NOTFOUND, arg);
            return false;
        }
        context.putResult(itemType);
        context.popArg();
        return true;
    }

    @Override
    protected Object argToResult(String arg) {
        return Registries.ENTITIES.get(arg.contains(":") ? new Identifier(arg) : new Identifier(DEFAULT_NAMESPACE, arg));

    }
}
