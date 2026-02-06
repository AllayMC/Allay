package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityTrampleFarmlandEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MOISTURIZED_AMOUNT;
import org.allaymc.api.block.dto.BlockNeighborUpdateContext;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd
 */
public class BlockFarmLandBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockFarmLandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockNeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();

        if (face == BlockFace.UP && neighbor.getBlockStateData().isSolid()) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.DIRT.getDefaultState());
        }
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var moisture = block.getPropertyValue(MOISTURIZED_AMOUNT);
        if (!hydrated(block)) {
            if (moisture > 0) {
                dimension.setBlockState(pos, block.getBlockState().setPropertyValue(MOISTURIZED_AMOUNT, moisture - 1));
            } else {
                var blockAbove = block.offsetPos(BlockFace.UP);
                if (!blockAbove.getBlockType().hasBlockTag(BlockTags.CROP)) {
                    // Convert farmland to dirt only if there is no crop above.
                    dimension.setBlockState(pos, BlockTypes.DIRT.getDefaultState());
                }
            }
        } else if (moisture != MOISTURIZED_AMOUNT.getMax()) {
            dimension.setBlockState(pos, block.getBlockState().setPropertyValue(MOISTURIZED_AMOUNT, MOISTURIZED_AMOUNT.getMax()));
        }
    }

    /**
     * Check for water within 4 blocks in each direction from the farmland.
     *
     * @param block The farmland block
     * @return {@code true} if the farmland is hydrated, {@code false} otherwise.
     */
    protected boolean hydrated(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        for (var y = 0; y <= 1; y++) {
            for (var x = -4; x <= 4; x++) {
                for (var z = -4; z <= 4; z++) {
                    var liquid = dimension.getLiquid(pos.x() + x, pos.y() + y, pos.z() + z).right();
                    if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void onEntityFallOn(Entity entity, Block block) {
        if (entity instanceof EntityPhysicsComponent physicsComponent &&
            ThreadLocalRandom.current().nextFloat() < physicsComponent.getFallDistance() - 0.5f) {
            var aabb = entity.getAABB();
            var width = aabb.maxX() - aabb.minX();
            var height = aabb.maxY() - aabb.minY();
            if (entity instanceof EntityPlayer || (width * width * height) > 0.512f) {
                var event = new EntityTrampleFarmlandEvent(entity, block);
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), BlockTypes.DIRT.getDefaultState());
                }
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack());
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
