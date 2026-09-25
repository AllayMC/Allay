package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;

import java.util.Map;
import java.util.Set;

public class MinecartDispenseBehavior implements DispenseBehavior {

    private static final Set<BlockType<?>> RAIL_TYPES = Set.of(
            BlockTypes.RAIL,
            BlockTypes.ACTIVATOR_RAIL,
            BlockTypes.DETECTOR_RAIL,
            BlockTypes.GOLDEN_RAIL
    );

    private static final Map<ItemType<?>, EntityType<?>> MINECART_ENTITY_TYPES = Map.of(
            ItemTypes.MINECART, EntityTypes.MINECART,
            ItemTypes.CHEST_MINECART, EntityTypes.CHEST_MINECART,
            ItemTypes.HOPPER_MINECART, EntityTypes.HOPPER_MINECART,
            ItemTypes.TNT_MINECART, EntityTypes.TNT_MINECART,
            ItemTypes.COMMAND_BLOCK_MINECART, EntityTypes.COMMAND_BLOCK_MINECART
    );

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var entityType = MINECART_ENTITY_TYPES.get(item.getItemType());
        if (entityType == null) {
            return DispenseResult.fail();
        }

        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        if (!RAIL_TYPES.contains(dimension.getBlockState(targetPos).getBlockType())) {
            return DispenseResult.fail();
        }

        var entity = entityType.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(MathUtils.center(targetPos))
                        .build()
        );
        if (entity != null) {
            dimension.getEntityManager().addEntity(entity);
        }
        return DispenseResult.success();
    }
}