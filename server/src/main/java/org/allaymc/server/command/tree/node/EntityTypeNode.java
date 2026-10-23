package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code EntityTypeNode} resolves command tokens to current registered entity
 * types while advertising a shared identifier snapshot. Bare paths receive the
 * default namespace for lookup. Unlike inherited enumeration matching, its
 * matcher reads the registry directly, so newly registered types can execute
 * even when their names were absent from the class-initialization advertisement
 * snapshot.
 *
 * @author daoge_cmd
 */
public class EntityTypeNode extends EnumNode {

    /**
     * {@code ENTITY_STR_ARRAY} snapshots full entity identifiers and bare paths
     * at class initialization. Nodes share this advertisement array, while
     * {@code match} independently resolves current registry contents.
     */
    protected static final String[] ENTITY_STR_ARRAY =
            Registries.ENTITIES.getContent().keySet()
                    .stream()
                    .<String>mapMulti((identifier, consumer) -> {
                        consumer.accept(identifier.toString());
                        consumer.accept(identifier.path());
                    }).toArray(String[]::new);

    /**
     * Creates an unattached entity-type node retaining the supplied default and
     * parent. Inherited network metadata uses the shared identifier snapshot,
     * while the overridden matcher consults the live entity registry.
     */
    public EntityTypeNode(String name, CommandNode parent, EntityType<?> defaultValue) {
        super(name, parent, defaultValue, ENTITY_STR_ARRAY);
    }

    /**
     * Resolves the current token from the entity registry after adding the
     * default namespace to a bare path. A missing entry adds feedback and
     * returns {@code false} without cursor changes; malformed identifiers
     * propagate instead of becoming a mismatch.
     */
    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        // Default-namespace expansion gives bare paths the same lookup identity as
        // explicit vanilla identifiers while leaving custom namespaced input intact.
        if (arg.indexOf(':') == -1) {
            arg = Identifier.DEFAULT_NAMESPACE + ":" + arg;
        }
        var itemType = Registries.ENTITIES.get(new Identifier(arg));
        if (itemType == null) {
            context.addError("%" + TrKeys.MC_COMMANDS_GENERIC_ENTITY_NOTFOUND, arg);
            return false;
        }
        context.putResult(itemType);
        context.popArg();
        return true;
    }

    /**
     * Returns the current entity type for a full identifier or
     * default-namespace bare path. This inherited conversion hook can return
     * {@code null}; the overridden {@code match} performs its own lookup and
     * does not call it.
     */
    @Override
    protected Object argToResult(String arg) {
        return Registries.ENTITIES.get(arg.contains(":") ? new Identifier(arg) : new Identifier(Identifier.DEFAULT_NAMESPACE, arg));
    }
}
