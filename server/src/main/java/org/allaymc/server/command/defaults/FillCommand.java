package org.allaymc.server.command.defaults;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.joml.Vector3f;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class FillCommand extends SimpleCommand {
    public FillCommand() {
        super("fill", TrKeys.M_COMMANDS_FILL_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("from")
                .pos("to")
                .blockType("blockType")
                .blockPropertyValues("blockPropertyValues")
                .optional()
                .exec(context -> {
                    var from = context.<Vector3f>getResult(0).floor();
                    var to = context.<Vector3f>getResult(1).ceil();
                    var dim = context.getSender().getCmdExecuteLocation().dimension();
                    if (!dim.isInWorld(from.x(), from.y(), from.z()) || !dim.isInWorld(to.x(), to.y(), to.z())) {
                        context.addError("%" + TrKeys.M_COMMANDS_FILL_OUTOFWORLD);
                        return context.fail();
                    }

                    BlockType<?> blockType = context.getResult(2);
                    List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> blockPropertyValues = context.getResult(3);

                    var blockState = blockPropertyValues.isEmpty() ? blockType.getDefaultState() : blockType.ofState(blockPropertyValues);
                    if (blockState == null) {
                        context.addError("%" + TrKeys.M_COMMANDS_BLOCKSTATE_INVALIDSTATE, blockType.getIdentifier() + blockPropertyValues.toString());
                        return context.fail();
                    }

                    var count = 0;
                    for (var x = from.x; x <= to.x; x++) {
                        for (var y = from.y; y <= to.y; y++) {
                            for (var z = from.z; z <= to.z; z++) {
                                dim.setBlockState((int) x, (int) y, (int) z, blockState);
                                count++;
                            }
                        }
                    }
                    context.addOutput(TrKeys.M_COMMANDS_FILL_SUCCESS, count);

                    return context.success();
                });
    }
}
