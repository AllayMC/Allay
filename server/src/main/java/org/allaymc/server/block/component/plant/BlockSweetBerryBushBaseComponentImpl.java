package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * {@code BlockSweetBerryBushBaseComponentImpl} implements sweet-berry bush support, stage
 * growth, harvesting, and contact damage. Valid dirt-like support permits placement, while
 * lower-support loss breaks the bush. Natural growth requires sufficient light and a random
 * gate; bone meal instead proposes the next stage directly.
 *
 * <p>
 * Ordinary interaction prioritizes bone meal, so a mature bush held against bone meal does not
 * fall through to harvesting. Other interactions at stage two or greater drop berries before
 * resetting growth to one. These harvest writes are separate and unchecked, while stage
 * increases use cancellable growth proposals. Contact damage is sampled only beyond stage zero
 * and applies independently of the harvest and growth mechanisms.
 *
 * @author daoge_cmd
 */
public class BlockSweetBerryBushBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the sweet berry bush behavior component for the supplied block type.
     */
    public BlockSweetBerryBushBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the bush on valid dirt-like ground, requiring the upper face when placement
     * information is supplied. A {@code null} {@code placementInfo} bypasses only that face
     * check, while survival still controls placement.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }
        if (!canSurvive(dimension, placeBlockPos)) {
            return false;
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks a bush after invalid lower support
     * changes. Only the lower face invokes this local survival test, leaving other neighbor
     * notifications to inherited handling.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    /**
     * Returns whether berry bushes receive random updates for natural growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Attempts one growth stage for a bush below stage three after inherited update dispatch.
     * A one-fifth random gate and internal light of at least nine must pass before the
     * cancellable growth proposal can write its state.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var growth = block.getPropertyValue(GROWTH);
        if (growth >= 3) {
            return;
        }

        // Natural growth is sampled independently of bone-meal stage-increase attempts.
        if (ThreadLocalRandom.current().nextInt(5) != 0) {
            return;
        }

        // Natural growth needs light; bone meal bypasses this gate.
        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition()) < 9) {
            return;
        }

        var newState = block.getBlockState().setPropertyValue(GROWTH, growth + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    /**
     * Handles bone-meal growth or berry harvesting after inherited interaction handling. Bone
     * meal proposes one stage below stage three and consumes the item only after acceptance; a
     * mature or cancelled bone-meal action returns {@code false} without harvesting.
     *
     * <p>
     * Other interactions harvest bushes at stage two or above. Stage two yields one or two
     * berries, while later stages yield two or three, and the bush then resets to stage one.
     * Item spawning precedes the reset write, with no growth event or rollback joining those
     * two effects.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        var growth = clickedBlock.getPropertyValue(GROWTH);

        if (itemStack != null && itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            if (growth < 3) {
                var newState = clickedBlock.getBlockState().setPropertyValue(GROWTH, growth + 1);
                var event = new BlockGrowEvent(clickedBlock, newState);
                if (event.call()) {
                    dimension.setBlockState(interactInfo.clickedBlockPos(), event.getNewBlockState());
                    interactInfo.player().tryConsumeItemInHand();
                    dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                    return true;
                }
            }
            return false;
        }

        // Spawn the harvest before resetting growth. A rejected reset write does
        // not reclaim those items, so the two effects are not an atomic harvest transaction.
        if (growth >= 2) {
            var random = ThreadLocalRandom.current();
            int dropCount = growth == 2 ? random.nextInt(1, 3) : random.nextInt(2, 4);
            var dropPos = MathUtils.center(interactInfo.clickedBlockPos());
            dimension.dropItem(ItemTypes.SWEET_BERRIES.createItemStack(dropCount), dropPos);

            var newState = clickedBlock.getBlockState().setPropertyValue(GROWTH, 1);
            dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
            return true;
        }

        return false;
    }

    /**
     * Samples half-point contact damage for living entities inside a bush beyond stage zero.
     * Each callback has a one-in-twenty attack chance; this component does not apply a
     * separate movement requirement or contact cooldown.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        if (growth <= 0) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            // Contact damage uses a per-callback chance rather than a stored cooldown.
            if (ThreadLocalRandom.current().nextInt(20) == 0) {
                living.attack(DamageContainer.contact(0.5f));
            }
        }
    }

    /**
     * Returns berries according to the broken bush's stage. Stages zero and one yield one
     * berry, stage two yields one or two, and later stages yield two or three without a
     * Fortune adjustment.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        var random = ThreadLocalRandom.current();
        int count;
        if (growth <= 1) {
            count = 1;
        } else if (growth == 2) {
            count = random.nextInt(1, 3);
        } else {
            count = random.nextInt(2, 4);
        }
        return Set.of(ItemTypes.SWEET_BERRIES.createItemStack(count));
    }

    /**
     * Returns whether the lower block is dirt-tagged, rooted dirt, or moss.
     */
    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT) ||
               downBlockType == BlockTypes.DIRT_WITH_ROOTS ||
               downBlockType == BlockTypes.MOSS_BLOCK;
    }
}
