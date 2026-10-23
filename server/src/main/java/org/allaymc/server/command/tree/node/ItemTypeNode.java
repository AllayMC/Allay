package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.LinkedHashMap;

/**
 * {@code ItemTypeNode} resolves one command token to a registered item type,
 * supplying the default namespace for a bare path. A missing registry entry
 * adds item-not-found feedback and leaves the token unconsumed. Client metadata
 * uses fixed item-name and enumeration identifiers with an empty value map
 * instead of transmitting the entire registry.
 *
 * @author daoge_cmd
 */
public class ItemTypeNode extends BaseNode {

    /**
     * Creates an unattached item-type node retaining the supplied parent and
     * omission default. Matching uses the live {@code Registries.ITEMS}
     * registry; callers attach the node and enable optionality separately.
     */
    public ItemTypeNode(String name, CommandNode parent, ItemType<?> defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Resolves the current token through {@code Registries.ITEMS}, adding the
     * default namespace when no colon is present. A found type is appended
     * before consuming one token; a missing type records feedback and returns
     * {@code false} without advancing either cursor. Invalid identifier syntax
     * propagates rather than becoming a mismatch.
     */
    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        // Bare command identifiers use the default namespace, allowing ordinary
        // vanilla paths to resolve through the same registry keys as explicit names.
        if (arg.indexOf(':') == -1) {
            arg = Identifier.DEFAULT_NAMESPACE + ":" + arg;
        }
        var itemType = Registries.ITEMS.get(new Identifier(arg));
        if (itemType == null) {
            context.addError("%" + TrKeys.MC_COMMANDS_GIVE_ITEM_NOTFOUND, arg);
            return false;
        }
        context.putResult(itemType);
        context.popArg();
        return true;
    }

    /**
     * Creates metadata named {@code "itemName"} with the fixed {@code "Item"}
     * enumeration identifier and an empty values map. These names replace the
     * configured parameter name, while optionality still comes from the base
     * metadata.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        // Keep the fixed item-parameter names used by client command metadata.
        // The empty enumeration avoids repeating the registry's complete value list
        // for every command parameter that advertises this item syntax.
        data.setName("itemName");
        data.setEnumData(new CommandEnumData("Item", new LinkedHashMap<>(), false));
        return data;
    }
}
