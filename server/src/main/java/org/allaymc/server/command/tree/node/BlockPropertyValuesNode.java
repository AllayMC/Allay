package org.allaymc.server.command.tree.node;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.tree.BaseNode;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.AllayStringUtils;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockPropertyValuesNode extends BaseNode {
    public BlockPropertyValuesNode(String name, CommandNode parent, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        super(name, parent, defaultValue);
        if (!(parent instanceof BlockTypeNode)) {
            throw new IllegalArgumentException("BlockPropertyValuesNode must be a child of BlockTypeNode");
        }
    }

    @Override
    public boolean match(CommandContext context) {
        var arg = context.queryArg();
        if (!(arg.startsWith("[") && arg.endsWith("]"))) {
            return false;
        }

        var substring = arg.substring(1, arg.length() - 1);
        if (substring.isBlank()) {
            context.putResult(List.of());
            context.popArg();
            return true;
        }

        BlockType<?> blockType = context.getResult(context.getCurrentResultIndex() - 1);
        var propertyValues = new HashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        for (var propertyStr : AllayStringUtils.fastSplit(substring, ",")) {
            var split = AllayStringUtils.fastTwoPartSplit(propertyStr, "=", "");
            var key = removeQuote(split[0]);
            var value = removeQuote(split[1]);
            var propertyType = blockType.getProperties().get(key);
            if (propertyType == null) {
                context.addError("%" + TrKeys.MC_COMMANDS_BLOCKSTATE_TYPEERROR, key);
                return false;
            }
            BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue;
            try {
                propertyValue = propertyType.tryCreateValue(value);
            } catch (IllegalArgumentException e) {
                context.addError("%" + TrKeys.MC_COMMANDS_BLOCKSTATE_VALUEERROR, key + "=" + value);
                return false;
            }
            propertyValues.put(propertyType, propertyValue);
        }

        for (var propertyValue : blockType.getDefaultState().getPropertyValues().values()) {
            propertyValues.putIfAbsent(propertyValue.getPropertyType(), propertyValue);
        }

        context.putResult(new ArrayList<>(propertyValues.values()));
        context.popArg();
        return true;
    }

    protected String removeQuote(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.BLOCK_STATES);
        return data;
    }
}
