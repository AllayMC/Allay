package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockMushroomBaseComponentImpl} implements mushroom support and bone-meal feature
 * growth. Placement validates mycelium, podzol, nylium, or sufficiently dark solid ground and
 * restricts nonnull player placement information to an upper-face interaction.
 *
 * <p>
 * Feature growth resolves the configured registry entry, removes the original plant, and
 * invokes feature placement through a chunk-backed context. A {@code false} placement result restores
 * only the original block state at its starting position; other feature writes are not tracked
 * for rollback. An exception escapes before that restoration path runs. Bone-meal consumption
 * belongs to interaction handling and does not depend on the feature's success result.
 *
 * @author daoge_cmd
 */
public class BlockMushroomBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code mushroomFeatureId} selects the registered feature used by growth. A missing
     * registry entry makes the feature attempt return {@code false} before removing the plant.
     */
    protected final Identifier mushroomFeatureId;

    /**
     * Creates mushroom behavior with its configured mushroom feature identifier. Construction
     * retains the identifier without checking the registry; feature availability is resolved
     * only when a growth attempt begins.
     */
    public BlockMushroomBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Identifier mushroomFeatureId) {
        super(blockType);
        this.mushroomFeatureId = mushroomFeatureId;
    }

    /**
     * Places the mushroom when its ground satisfies the survival predicate. A nonnull {@code
     * placementInfo} must identify the upper face, while programmatic placement still applies
     * the same survival checks.
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
     * Dispatches the inherited neighbor hook and breaks an unsupported mushroom. Every
     * neighbor face can trigger survival revalidation, including changes affecting the
     * light-sensitive rule for ordinary solid supporting ground.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (!canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    /**
     * Handles bone meal after inherited interaction handling. The callback consumes the item
     * and emits particles before a forty-percent chance of attempting feature growth; either
     * random outcome reports the interaction handled even if feature placement fails.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);

        // Consume bone meal before sampling feature growth, so an unsuccessful roll still uses it.
        if (ThreadLocalRandom.current().nextInt(5) < 2) {
            growMushroom(interactInfo.getClickedBlock());
        }

        return true;
    }

    /**
     * Attempts the registered mushroom feature after temporarily removing the plant. A missing
     * feature returns {@code false} before any removal; a resolved feature receives a
     * chunk-backed context at the plant's coordinates.
     *
     * <p>
     * A {@code false} placement result restores the original state only at that coordinate, without
     * undoing other feature writes. Successful placement retains the feature changes. A thrown
     * exception propagates without entering the restoration branch, so this helper does not
     * promise transactional feature generation.
     */
    protected boolean growMushroom(Block block) {
        var feature = Registries.WORLD_FEATURES.get(mushroomFeatureId);
        if (feature == null) {
            return false;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var originalState = block.getBlockState();

        var context = new WorldFeatureContext(dimension.getChunkManager());
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.AIR.getDefaultState());

        // Restore only the starting plant on a false result; feature writes elsewhere
        // are not tracked here, and an exception escapes before this restoration branch.
        boolean success = feature.place(context, pos.x(), pos.y(), pos.z());
        if (!success) {
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), originalState);
        }
        return success;
    }

    /**
     * Returns whether soil and light permit this mushroom to survive. Mycelium, podzol, and
     * either nylium type qualify regardless of light. Every other qualifying surface must be
     * solid and have internal light below thirteen at the mushroom.
     *
     * <p>
     * The special-soil branch runs before the light check, so bright special ground remains
     * valid while equally bright ordinary solid ground fails. The predicate checks current
     * world state and does not itself break the mushroom; placement and neighbor callbacks
     * apply its decision.
     */
    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var downPos = BlockFace.DOWN.offsetPos(pos);
        var downState = dimension.getBlockState(downPos);
        var downBlockType = downState.getBlockType();

        if (downBlockType == BlockTypes.MYCELIUM ||
            downBlockType == BlockTypes.PODZOL ||
            downBlockType == BlockTypes.CRIMSON_NYLIUM ||
            downBlockType == BlockTypes.WARPED_NYLIUM) {
            return true;
        }

        // Special soils bypass the light threshold applied to other ground.
        if (downState.getBlockStateData().isSolid()) {
            return dimension.getLightEngine().getInternalLight(pos) < 13;
        }

        return false;
    }
}
