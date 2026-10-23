package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
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
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * {@code BlockFungusBaseComponentImpl} implements fungus support and bone-meal feature growth.
 * Placement validates dirt-tagged ground and the additional fungus-compatible soil types and
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
public class BlockFungusBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code nyliumBlockType} resolves the matching nylium required for feature growth.
     * Ordinary survival accepts additional soils, but bone-meal handling compares the lower
     * type with this supplier's result.
     */
    protected final Supplier<BlockType<?>> nyliumBlockType;
    /**
     * {@code fungusFeatureId} selects the registered feature used by growth. A missing
     * registry entry makes the feature attempt return {@code false} before removing the plant.
     */
    protected final Identifier fungusFeatureId;

    /**
     * Creates fungus behavior with a deferred matching-nylium supplier and feature identifier.
     * The supplier is retained and evaluated during bone-meal handling; construction does not
     * validate the feature's presence in the registry.
     */
    public BlockFungusBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> nyliumBlockTypeSupplier, Identifier fungusFeatureId) {
        super(blockType);
        this.nyliumBlockType = nyliumBlockTypeSupplier;
        this.fungusFeatureId = fungusFeatureId;
    }

    /**
     * Creates fungus behavior with an identifier-backed matching-nylium supplier and feature
     * identifier. The method reference defers block registry lookup until bone meal checks
     * whether the current support can grow this fungus.
     */
    public BlockFungusBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId nyliumId, Identifier fungusFeatureId) {
        this(blockType, nyliumId::getBlockType, fungusFeatureId);
    }

    /**
     * Places the fungus when its ground satisfies the survival predicate. A nonnull {@code
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
     * Dispatches the inherited neighbor hook and breaks an unsupported fungus after a
     * lower-face change. Other neighboring changes do not trigger this callback's local
     * ground-survival removal branch.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    /**
     * Handles bone meal only when the lower block matches the configured nylium supplier. The
     * callback consumes the item and emits particles before attempting the feature, then
     * reports the interaction handled regardless of that attempt's boolean result.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(interactInfo.clickedBlockPos())).getBlockType();
        if (downBlockType == nyliumBlockType.get()) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            growFungus(interactInfo.getClickedBlock());
            return true;
        }

        return false;
    }

    /**
     * Attempts the registered fungus feature after temporarily removing the plant. A missing
     * feature returns {@code false} before any removal; a resolved feature receives a
     * chunk-backed context at the plant's coordinates.
     *
     * <p>
     * A {@code false} placement result restores the original state only at that coordinate, without
     * undoing other feature writes. Successful placement retains the feature changes. A thrown
     * exception propagates without entering the restoration branch, so this helper does not
     * promise transactional feature generation.
     */
    protected boolean growFungus(Block block) {
        var feature = Registries.WORLD_FEATURES.get(fungusFeatureId);
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
     * Returns whether the block below {@code pos} supports this fungus. The accepted support
     * set includes dirt-tagged ground and the additional fungus-compatible soil types; this
     * predicate does not itself place or remove the plant.
     */
    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT) ||
               downBlockType == BlockTypes.PODZOL ||
               downBlockType == BlockTypes.FARMLAND ||
               downBlockType == BlockTypes.CRIMSON_NYLIUM ||
               downBlockType == BlockTypes.WARPED_NYLIUM ||
               downBlockType == BlockTypes.SOUL_SOIL ||
               downBlockType == BlockTypes.MYCELIUM;
    }
}
