package org.allaymc.server.command.defaults;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.joml.Vector3d;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class FillCommand extends Command {
    public FillCommand() {
        super("fill", TrKeys.MC_COMMANDS_FILL_DESCRIPTION, Permissions.COMMAND_FILL);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("from")
                .pos("to")
                .blockType("blockType")
                .blockPropertyValues("blockPropertyValues").optional()
                .exec(context -> {
                    var from = context.<Vector3d>getResult(0).floor();
                    var to = context.<Vector3d>getResult(1).floor();

                    var dim = context.getSender().getCommandExecuteLocation().dimension();
                    if (!dim.isInWorld(from.x(), from.y(), from.z()) || !dim.isInWorld(to.x(), to.y(), to.z())) {
                        context.addError("%" + TrKeys.MC_COMMANDS_FILL_OUTOFWORLD);
                        return context.fail();
                    }

                    BlockType<?> blockType = context.getResult(2);
                    List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> blockPropertyValues = context.getResult(3);

                    var blockState = blockPropertyValues.isEmpty() ? blockType.getDefaultState() : blockType.ofState(blockPropertyValues);
                    if (blockState == null) {
                        context.addError("%" + TrKeys.MC_COMMANDS_BLOCKSTATE_INVALIDSTATE, blockType.getIdentifier() + blockPropertyValues.toString());
                        return context.fail();
                    }

                    var minX = Math.min(from.x, to.x);
                    var maxX = Math.max(from.x, to.x);
                    var minY = Math.min(from.y, to.y);
                    var maxY = Math.max(from.y, to.y);
                    var minZ = Math.min(from.z, to.z);
                    var maxZ = Math.max(from.z, to.z);

                    int count = 0;
                    for (var x = minX; x <= maxX; x++) {
                        for (var y = minY; y <= maxY; y++) {
                            for (var z = minZ; z <= maxZ; z++) {
                                dim.setBlockState(x, y, z, blockState);
                                count++;
                            }
                        }
                    }

                    context.addOutput(TrKeys.MC_COMMANDS_FILL_SUCCESS, count);
                    return context.success();
                });
    }
}
