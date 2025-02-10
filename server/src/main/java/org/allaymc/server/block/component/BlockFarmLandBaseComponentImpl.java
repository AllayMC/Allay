package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.eventbus.event.entity.EntityTrampleFarmlandEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockFarmLandBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockFarmLandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
        if (face != BlockFace.UP) {
            return;
        }

        if (neighbor.blockState().getBlockStateData().isSolid()) {
            current.dimension().setBlockState(current.pos(), BlockTypes.DIRT.getDefaultState());
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        super.onRandomUpdate(current);

        var dimension = current.dimension();
        var pos = current.pos();
        var moisture = current.blockState().getPropertyValue(BlockPropertyTypes.MOISTURIZED_AMOUNT);
        if (!hydrated(current)) {
            if (moisture > 0) {
                dimension.setBlockState(pos, current.blockState().setPropertyValue(BlockPropertyTypes.MOISTURIZED_AMOUNT, moisture - 1));
            } else {
                var blockAbove = current.offsetPos(BlockFace.UP);
                if (!blockAbove.blockState().getBlockType().hasBlockTag(BlockTags.CROP)) {
                    // Convert farmland to dirt only if there is no crop above.
                    dimension.setBlockState(pos, BlockTypes.DIRT.getDefaultState());
                }
            }
        } else if (moisture != 7) {
            dimension.setBlockState(pos, current.blockState().setPropertyValue(BlockPropertyTypes.MOISTURIZED_AMOUNT, 7));
        }
    }

    /**
     * Check for water within 4 blocks in each direction from the farmland.
     *
     * @param block The farmland block.
     *
     * @return {@code true} if the farmland is hydrated, {@code false} otherwise.
     */
    protected boolean hydrated(BlockStateWithPos block) {
        var dimension = block.dimension();
        var pos = block.pos();
        for (var y = 0; y <= 1; y++) {
            for (var x = -4; x <= 4; x++) {
                for (var z = -4; z <= 4; z++) {
                    var liquid = dimension.getLiquid(pos.x() + x, pos.y() + y, pos.z() + z).right();
                    if (liquid != null && liquid.getBlockType().hasBlockTag(BlockCustomTags.WATER)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // TODO: add more entities or find a better way
    protected static final Set<Identifier> VALID_ENTITIES = Set.of(EntityId.PLAYER.getIdentifier());

    @Override
    public void onEntityFallOn(Entity entity, BlockStateWithPos block) {
        if (ThreadLocalRandom.current().nextFloat() < entity.getFallDistance() - 0.5f) {
            if (!VALID_ENTITIES.contains(entity.getEntityType().getIdentifier()) ||
                entity.getMetadata().get(EntityFlag.BABY)) {
                return;
            }

            var event = new EntityTrampleFarmlandEvent(entity, block);
            if (event.call()) {
                block.dimension().setBlockState(block.pos(), BlockTypes.DIRT.getDefaultState());
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack());
    }
}
