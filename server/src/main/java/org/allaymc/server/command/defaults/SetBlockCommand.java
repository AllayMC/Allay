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
 * {@code SetBlockCommand} requests one block-state change in the executing
 * entity's dimension. Its handler resolves optional properties before placement
 * and rejects invalid state combinations; successful feedback describes the
 * request without inspecting whether the dimension accepted the block change.
 *
 * @author daoge_cmd
 */
public class SetBlockCommand extends Command {
    /**
     * Creates the {@code /setblock} command with its built-in authorization permission.
     */
    public SetBlockCommand() {
        super("setblock", TrKeys.MC_COMMANDS_SETBLOCK_DESCRIPTION, Permissions.COMMAND_SETBLOCK);
    }

    /**
     * Installs entity-only block placement with optional property values.
     * Omitted properties use the block type's default state. The handler
     * reports success after requesting placement, including when the dimension
     * returns {@code false} for that request.
     */
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
