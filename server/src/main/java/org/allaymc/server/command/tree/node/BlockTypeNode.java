package org.allaymc.server.command.tree.node;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.LinkedHashMap;

/**
 * {@code BlockTypeNode} resolves one command token to a registered block type,
 * supplying the default namespace for a bare path. A missing registry entry
 * adds block-not-found feedback and leaves the token unconsumed. Client
 * metadata uses fixed block-name and enumeration identifiers with an empty
 * value map instead of transmitting the entire registry.
 *
 * @author daoge_cmd
 */
public class BlockTypeNode extends BaseNode {
    /**
     * Creates an unattached block-type node retaining the supplied parent and
     * omission default. Matching uses the live {@code Registries.BLOCKS}
     * registry; callers attach the node and enable optionality separately.
     */
    public BlockTypeNode(String name, CommandNode parent, BlockType<?> defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Resolves the current token through {@code Registries.BLOCKS}, adding the
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
        var itemType = Registries.BLOCKS.get(new Identifier(arg));
        if (itemType == null) {
            context.addError("%" + TrKeys.MC_COMMANDS_GIVE_BLOCK_NOTFOUND, arg);
            return false;
        }
        context.putResult(itemType);
        context.popArg();
        return true;
    }

    /**
     * Creates metadata named {@code "blockName"} with the fixed {@code "Block"}
     * enumeration identifier and an empty values map. These names replace the
     * configured parameter name, while optionality still comes from the base
     * metadata.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        // Keep the fixed block-parameter names used by client command metadata.
        // The empty enumeration avoids repeating the registry's complete value list
        // for every command parameter that advertises this block syntax.
        data.setName("blockName");
        data.setEnumData(new CommandEnumData("Block", new LinkedHashMap<>(), false));
        return data;
    }
}
