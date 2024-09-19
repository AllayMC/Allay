package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.LinkedHashMap;

/**
 * @author daoge_cmd
 */
public class ItemTypeNode extends BaseNode {

    public ItemTypeNode(String name, CommandNode parent, ItemType<?> defaultValue) {
        super(name, parent, defaultValue);
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        if (arg.indexOf(':') == -1) {
            arg = Identifier.DEFAULT_NAMESPACE + ":" + arg;
        }
        var itemType = Registries.ITEMS.get(new Identifier(arg));
        if (itemType == null) {
            context.addError("%" + TrKeys.M_COMMANDS_GIVE_ITEM_NOTFOUND, arg);
            return false;
        }
        context.putResult(itemType);
        context.popArg();
        return true;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        // NOTICE: The name must be "itemName", so that the client will show item list
        // There is need to send the full item list to the client
        data.setName("itemName");
        // Also, there must be "Item"
        data.setEnumData(new CommandEnumData("Item", new LinkedHashMap<>(), false));
        return data;
    }
}
