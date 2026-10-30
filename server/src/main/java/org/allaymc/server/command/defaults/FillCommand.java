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
 * {@code FillCommand} places one block state throughout an inclusive cuboid in
 * the sender's execution dimension. The handler validates the corners and
 * requested state before visiting positions; its reported count includes every
 * placement attempt, even when a block-change event prevents an individual edit.
 *
 * @author daoge_cmd
 */
public class FillCommand extends Command {
    /**
     * Creates the {@code /fill} command with its built-in authorization permission.
     */
    public FillCommand() {
        super("fill", TrKeys.MC_COMMANDS_FILL_DESCRIPTION, Permissions.COMMAND_FILL);
    }

    /**
     * Installs cuboid filling with optional block property values.
     * Fractional corners select containing blocks, reversed corners are
     * normalized, and omitted properties select the block's default state.
     * Placement attempts occur independently and contribute to feedback without
     * checking whether each edit was accepted.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("from")
                .pos("to")
                .blockType("blockType")
                .blockPropertyValues("blockPropertyValues").optional()
                .exec(context -> {
                    // Flooring selects containing blocks before inclusive cuboid bounds are computed.
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
