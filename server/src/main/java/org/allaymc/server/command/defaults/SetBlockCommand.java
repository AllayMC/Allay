package org.allaymc.server.command.defaults;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.joml.Vector3d;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class SetBlockCommand extends Command {
    public SetBlockCommand() {
        super("setblock", TrKeys.MC_COMMANDS_SETBLOCK_DESCRIPTION, Permissions.COMMAND_SETBLOCK);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("position")
                .blockType("blockType")
                .blockPropertyValues("blockPropertyValues").optional()
                .exec((context, entity) -> {
                    Vector3d pos = context.getResult(0);
                    BlockType<?> blockType = context.getResult(1);
                    List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> blockPropertyValues = context.getResult(2);

                    var blockState = blockPropertyValues.isEmpty() ? blockType.getDefaultState() : blockType.ofState(blockPropertyValues);
                    if (blockState == null) {
                        context.addError("%" + TrKeys.MC_COMMANDS_BLOCKSTATE_INVALIDSTATE, blockType.getIdentifier() + blockPropertyValues.toString());
                        return context.fail();
                    }

                    entity.getDimension().setBlockState(pos, blockState);
                    context.addOutput(TrKeys.MC_COMMANDS_SETBLOCK_SUCCESS);
                    return context.success();
                }, SenderType.ENTITY);
    }
}
