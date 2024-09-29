package org.allaymc.server.command.defaults;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.joml.Vector3f;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class SetBlockCommand extends SimpleCommand {
    public SetBlockCommand() {
        super("setblock", TrKeys.M_COMMANDS_SETBLOCK_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .pos("position")
                .blockType("blockType")
                .blockPropertyValues("blockPropertyValues")
                .exec((context, player) -> {
                    Vector3f pos = context.getResult(0);
                    BlockType<?> blockType = context.getResult(1);
                    List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> blockPropertyValues = context.getResult(2);

                    var blockState = blockType.ofState(blockPropertyValues);
                    if (blockState == null) {
                        context.addError("%" + TrKeys.M_COMMANDS_BLOCKSTATE_INVALIDSTATE, blockType.getIdentifier() + blockPropertyValues.toString());
                        return context.fail();
                    }
                    player.getDimension().setBlockState(pos, blockState);
                    context.addOutput(TrKeys.M_COMMANDS_SETBLOCK_SUCCESS);
                    return context.success();
                }, SenderType.PLAYER);
    }
}
