package org.allaymc.server.command.tree.node;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.utils.AllayStringUtils;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * {@code BlockPropertyValuesNode} parses a bracketed property assignment token
 * for the block type produced by its immediate parent. The constructor requires
 * a {@code BlockTypeNode}, and matching reads the immediately preceding result
 * as that block type. Property names and values may each have surrounding
 * double quotes, which the parser removes before lookup and conversion.
 *
 * <p>
 * A blank bracket body produces an empty list for executors selecting a default
 * state. A nonempty body keeps the last assignment per property type and fills
 * omitted properties from the block's default state. Unknown names and caught
 * {@code IllegalArgumentException} conversions add feedback and fail before
 * token consumption. Other conversion failures, including an integer property's
 * out-of-range cached-array access, propagate while the argument and result
 * cursors remain unchanged.
 *
 * @author daoge_cmd
 */
public class BlockPropertyValuesNode extends BaseNode {
    /**
     * Creates an unattached property node retaining its default list and
     * parent. A parent outside {@code BlockTypeNode} raises
     * {@code IllegalArgumentException}, because matching needs the preceding
     * block-type result to interpret property names.
     */
    public BlockPropertyValuesNode(String name, CommandNode parent, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> defaultValue) {
        super(name, parent, defaultValue);
        if (!(parent instanceof BlockTypeNode)) {
            throw new IllegalArgumentException("BlockPropertyValuesNode must be a child of BlockTypeNode");
        }
    }

    /**
     * Parses one bracketed token into property values for the preceding
     * block-type result. Missing brackets fail without output; a blank body
     * appends an empty list and consumes the token. Nonempty input resolves
     * each property and converts its value before changing the argument or
     * result cursors.
     *
     * <p>
     * Later assignments for the same property replace earlier ones, and
     * unspecified properties come from the block's default state. Unknown names
     * or {@code IllegalArgumentException} conversions append feedback and
     * return {@code false}; other failures, such as an integer property's
     * {@code ArrayIndexOutOfBoundsException}, propagate. Feedback is not rolled
     * back, and the result follows map iteration order rather than textual
     * assignment order.
     */
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
                // Report the rejected property assignment without consuming its token,
                // so traversal still identifies this input position when matching fails.
                context.addError("%" + TrKeys.MC_COMMANDS_BLOCKSTATE_VALUEERROR, key + "=" + value);
                return false;
            }
            propertyValues.put(propertyType, propertyValue);
        }

        // Nonempty assignments describe a complete candidate state. Preserve each
        // explicit choice and supply omitted properties from this block's default state
        // before the executor requests a block-state lookup.
        for (var propertyValue : blockType.getDefaultState().getPropertyValues().values()) {
            propertyValues.putIfAbsent(propertyValue.getPropertyType(), propertyValue);
        }

        context.putResult(new ArrayList<>(propertyValues.values()));
        context.popArg();
        return true;
    }

    /**
     * Returns the interior of a string enclosed by double quotes, otherwise the
     * original string. The method performs no escaping or trimming; a
     * one-character quote string fails substring bounds because it is not a
     * valid quoted pair.
     */
    protected String removeQuote(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    /**
     * Creates client metadata advertising {@code CommandParam.BLOCK_STATES} for
     * property assignment syntax.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.BLOCK_STATES);
        return data;
    }
}
