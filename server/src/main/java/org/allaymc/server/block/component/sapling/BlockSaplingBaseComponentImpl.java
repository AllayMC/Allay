package org.allaymc.server.block.component.sapling;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.feature.WorldFeature;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockSaplingBaseComponentImpl} implements sapling survival, staged natural growth,
 * bone meal, and selection of ordinary or large tree features. Dirt-tagged support permits
 * placement, while sufficiently lit random updates first set the age flag and later attempt a
 * tree.
 *
 * <p>
 * Feature selection can choose a secondary ordinary tree or a secondary large tree, and a
 * matching two-by-two sapling square enables the large-tree path. Growth removes the
 * participating saplings before invoking the feature. A {@code false} result restores the starting
 * position or square, but other feature changes are not rolled back and thrown failures bypass
 * restoration. Large-tree failure restores every position from one supplied sapling snapshot
 * rather than preserving each original state's properties.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockSaplingBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code treeFeatureId} selects the primary ordinary-tree feature. {@code null} leaves the
     * primary selection unavailable, and registry lookup is deferred until a growth attempt.
     */
    protected final Identifier treeFeatureId;
    /**
     * {@code secondaryTreeFeatureId} optionally selects an alternate ordinary tree with a
     * ten-percent roll. A missing selected entry returns {@code null} without retrying the
     * primary feature.
     */
    protected final Identifier secondaryTreeFeatureId;
    /**
     * {@code megaTreeFeatureId} enables two-by-two tree detection when nonnull. Its registry
     * entry supplies the primary large-tree feature, while no usable selected entry falls back
     * to ordinary-tree selection.
     */
    protected final Identifier megaTreeFeatureId;
    /**
     * {@code secondaryMegaTreeFeatureId} optionally selects the alternate large-tree registry
     * entry. {@code secondaryMegaTreeChance} controls that selection before the primary
     * large-tree entry is considered.
     */
    protected final Identifier secondaryMegaTreeFeatureId;
    /**
     * {@code secondaryMegaTreeChance} is the unvalidated probability threshold for selecting
     * the secondary large-tree identifier. Values outside the usual probability range are
     * compared directly with the random sample.
     */
    protected final float secondaryMegaTreeChance;

    /**
     * Creates sapling behavior with ordinary and optional large-tree identifiers. This
     * overload disables the secondary large-tree selection by supplying a {@code null}
     * secondary identifier and a zero selection chance.
     */
    public BlockSaplingBaseComponentImpl(
            BlockType<? extends BlockBehavior> blockType,
            Identifier treeFeatureId,
            Identifier secondaryTreeFeatureId,
            Identifier megaTreeFeatureId
    ) {
        this(blockType, treeFeatureId, secondaryTreeFeatureId, megaTreeFeatureId, null, 0.0f);
    }

    /**
     * Creates sapling behavior with all feature-selection identifiers and an alternate
     * large-tree chance. Identifiers may be {@code null} to omit those choices, and the chance
     * is retained without range validation or eager registry lookup.
     */
    public BlockSaplingBaseComponentImpl(
            BlockType<? extends BlockBehavior> blockType,
            Identifier treeFeatureId,
            Identifier secondaryTreeFeatureId,
            Identifier megaTreeFeatureId,
            Identifier secondaryMegaTreeFeatureId,
            float secondaryMegaTreeChance
    ) {
        super(blockType);
        this.treeFeatureId = treeFeatureId;
        this.secondaryTreeFeatureId = secondaryTreeFeatureId;
        this.megaTreeFeatureId = megaTreeFeatureId;
        this.secondaryMegaTreeFeatureId = secondaryMegaTreeFeatureId;
        this.secondaryMegaTreeChance = secondaryMegaTreeChance;
    }

    /**
     * Places a sapling only when its lower state satisfies {@code canPlaceOn}. The predicate
     * runs for both player and programmatic placement, after which inherited property
     * processing supplies the final write.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var blockBelow = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        if (!canPlaceOn(blockBelow)) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and rechecks support after a lower-face change. A
     * failing support predicate breaks the sapling; changes from other faces leave this local
     * rule untouched.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN) {
            var blockBelow = block.offsetPos(BlockFace.DOWN);
            if (!canPlaceOn(blockBelow.getBlockState())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Returns whether the supporting state carries the dirt block tag.
     */
    protected boolean canPlaceOn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.DIRT);
    }

    /**
     * Returns whether saplings receive random updates for staged tree growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Dispatches the inherited update and requires internal light of at least nine. An unaged
     * sapling first gains {@code AGE_BIT}; a later eligible update attempts tree growth using
     * the current feature configuration.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var lightLevel = block.getDimension().getLightEngine().getInternalLight(block.getPosition());
        // Natural staging needs sufficient light, while bone meal bypasses this gate.
        if (lightLevel < 9) {
            return;
        }

        boolean aged = block.getPropertyValue(BlockPropertyTypes.AGE_BIT);

        if (!aged) {
            block.getDimension().setBlockState(
                    block.getPosition(),
                    block.getBlockState().setPropertyValue(BlockPropertyTypes.AGE_BIT, true)
            );
        } else {
            growTree(block);
        }
    }

    /**
     * Handles bone meal after inherited interaction handling with a forty-five-percent
     * no-growth roll. Every normally completed bone-meal path emits particles and consumes the
     * held item, including a missing feature or a tree attempt returning {@code false}; a
     * thrown growth failure escapes before those later effects.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            if (ThreadLocalRandom.current().nextFloat() < 0.45f) {
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            var block = interactInfo.getClickedBlock();
            if (growTree(block)) {
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        }

        return false;
    }

    /**
     * Attempts a large tree from an eligible two-by-two arrangement before ordinary feature
     * selection. A usable selected large-tree feature returns its placement result directly,
     * so a {@code false} large-tree result does not retry an ordinary tree. An unavailable large-tree
     * selection allows the ordinary path.
     *
     * <p>
     * The ordinary path removes this sapling before feature placement and restores its
     * supplied snapshot only when placement returns {@code false}. Missing ordinary features
     * return {@code false} without removal. Exceptions propagate without restoration, and no
     * path tracks arbitrary feature changes for complete rollback.
     */
    protected boolean growTree(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        if (megaTreeFeatureId != null) {
            var corner = findSaplingCorner(block);
            if (corner != null) {
                var megaFeature = selectMegaTreeFeature();
                if (megaFeature != null) {
                    return tryGrowMegaTree(block, corner, megaFeature);
                }
            }
        }

        WorldFeature feature = selectTreeFeature();

        if (feature == null) {
            return false;
        }

        var context = new WorldFeatureContext(dimension.getChunkManager());

        // Remove the originating sapling before feature placement so its state does
        // not obstruct generation; only a false result restores this one coordinate afterward.
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.AIR.getDefaultState());

        boolean success = feature.place(context, pos.x(), pos.y(), pos.z());

        if (!success) {
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), block.getBlockState());
        }

        return success;
    }

    /**
     * Selects the alternate ordinary-tree registry entry with ten-percent probability when
     * configured, otherwise the primary. A selected missing entry returns {@code null}; this
     * method does not fall back after that lookup fails.
     */
    protected WorldFeature selectTreeFeature() {
        // A selected alternate remains the choice even when its registry entry is
        // absent, so the caller can observe an unavailable feature rather than silently substituting primary.
        if (secondaryTreeFeatureId != null && ThreadLocalRandom.current().nextFloat() < 0.1f) {
            return Registries.WORLD_FEATURES.get(secondaryTreeFeatureId);
        }
        return treeFeatureId != null ? Registries.WORLD_FEATURES.get(treeFeatureId) : null;
    }

    /**
     * Selects an optional alternate large-tree entry using its configured chance, otherwise
     * the primary. A selected missing entry returns {@code null}; {@code growTree} then
     * continues to ordinary-tree selection rather than retrying another large-tree identifier.
     */
    protected WorldFeature selectMegaTreeFeature() {
        if (secondaryMegaTreeFeatureId != null && ThreadLocalRandom.current().nextFloat() < secondaryMegaTreeChance) {
            return Registries.WORLD_FEATURES.get(secondaryMegaTreeFeatureId);
        }
        return megaTreeFeatureId != null ? Registries.WORLD_FEATURES.get(megaTreeFeatureId) : null;
    }

    /**
     * Attempts the supplied feature after clearing the two-by-two square at {@code corner}.
     * Callers establish that the square contains matching sapling types; this helper does not
     * independently repeat that validation.
     *
     * <p>
     * When feature placement returns {@code false}, all four positions receive {@code block}'s
     * single state snapshot. Distinct original age flags or properties are therefore not
     * preserved. Feature writes outside the square are not tracked, and a thrown exception
     * escapes before restoration. The returned boolean is the feature's result rather than a
     * combined success check for every removal or restoration write.
     */
    protected boolean tryGrowMegaTree(Block block, Vector3ic corner, WorldFeature megaFeature) {
        var dimension = block.getDimension();
        var saplingState = block.getBlockState();

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                dimension.setBlockState(corner.x() + dx, corner.y(), corner.z() + dz, BlockTypes.AIR.getDefaultState());
            }
        }

        var context = new WorldFeatureContext(dimension.getChunkManager());

        boolean success = megaFeature.place(context, corner.x(), corner.y(), corner.z());

        if (!success) {
            // Restore the square from one snapshot after a false result. Original
            // per-position properties and feature writes outside these four coordinates are not recovered.
            for (int dx = 0; dx <= 1; dx++) {
                for (int dz = 0; dz <= 1; dz++) {
                    dimension.setBlockState(corner.x() + dx, corner.y(), corner.z() + dz, saplingState);
                }
            }
        }

        return success;
    }

    /**
     * Returns a newly allocated northwest corner of a matching two-by-two sapling square
     * containing {@code block}. The search compares block types at four possible corners and
     * ignores age or other properties; {@code null} means no square qualifies.
     */
    protected Vector3ic findSaplingCorner(Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();
        var saplingType = block.getBlockType();

        int[][] offsets = {{0, 0}, {-1, 0}, {0, -1}, {-1, -1}};

        for (int[] offset : offsets) {
            int cornerX = pos.x() + offset[0];
            int cornerZ = pos.z() + offset[1];

            boolean valid = true;
            for (int dx = 0; dx <= 1 && valid; dx++) {
                for (int dz = 0; dz <= 1 && valid; dz++) {
                    var checkBlock = dimension.getBlockState(cornerX + dx, pos.y(), cornerZ + dz);
                    if (checkBlock.getBlockType() != saplingType) {
                        valid = false;
                    }
                }
            }

            if (valid) {
                return new org.joml.Vector3i(cornerX, pos.y(), cornerZ);
            }
        }

        return null;
    }
}
