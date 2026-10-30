package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockSpreadEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * {@code BlockGrassBlockBaseComponentImpl} implements grass survival, nearby dirt conversion,
 * and bone-meal vegetation placement. Random updates fade grass when the upper block dampens
 * light too strongly, otherwise sufficiently lit grass can propose spreading to one randomly
 * selected dirt block.
 *
 * <p>
 * Bone meal searches nearby columns for an exposed grass surface and selects vegetation using
 * weighted entries. Tall selections require two air blocks and write their lower and upper
 * states separately. Interaction consumption depends on whether any placement attempt reports
 * success, while ordinary spread and fading use cancellable events. The surface search prefers
 * the highest qualifying grass block within its vertical range, so vegetation follows nearby
 * terrain instead of the clicked block's height alone.
 *
 * @author daoge_cmd
 */
public class BlockGrassBlockBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MINIMUM_LIGHT_LEVEL_FOR_SPREADING} sets the source-side internal-light
     * requirement for natural grass spreading during random updates.
     */
    public static final int MINIMUM_LIGHT_LEVEL_FOR_SPREADING = 9;
    /**
     * {@code BONEMEAL_ATTEMPTS} bounds the number of sampled vegetation positions during one
     * bone-meal interaction on grass.
     */
    private static final int BONEMEAL_ATTEMPTS = 128;
    /**
     * {@code BONEMEAL_HORIZONTAL_RANGE} bounds each horizontal coordinate offset from the
     * clicked grass during bone-meal sampling.
     */
    private static final int BONEMEAL_HORIZONTAL_RANGE = 4;
    /**
     * {@code BONEMEAL_VERTICAL_RANGE} bounds the terrain-height search above and below the
     * clicked grass for vegetation placement.
     */
    private static final int BONEMEAL_VERTICAL_RANGE = 2;
    /**
     * {@code BONEMEAL_VEGETATION} defines the weighted plant choices and whether each
     * selection needs two vertical blocks.
     */
    private static final List<VegetationEntry> BONEMEAL_VEGETATION = List.of(
            new VegetationEntry(48, () -> BlockTypes.SHORT_GRASS, false),
            new VegetationEntry(24, () -> BlockTypes.FERN, false),
            new VegetationEntry(12, () -> BlockTypes.TALL_GRASS, true),
            new VegetationEntry(8, () -> BlockTypes.LARGE_FERN, true),
            new VegetationEntry(4, () -> BlockTypes.DANDELION, false),
            new VegetationEntry(4, () -> BlockTypes.POPPY, false)
    );
    /**
     * {@code BONEMEAL_VEGETATION_TOTAL_WEIGHT} supplies the exclusive random-roll bound
     * computed from every configured vegetation entry's weight.
     */
    private static final int BONEMEAL_VEGETATION_TOTAL_WEIGHT = BONEMEAL_VEGETATION.stream()
            .mapToInt(VegetationEntry::weight)
            .sum();

    /**
     * Creates the grass block behavior component for the supplied block type.
     */
    public BlockGrassBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Attempts bone-meal vegetation after inherited interaction handling. The clicked grass
     * must have air immediately above within the dimension's height range; consumption and
     * particles occur only when at least one vegetation attempt reports success.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        if (!dimension.isYInRange(pos.y() + 1)) {
            return false;
        }

        if (dimension.getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        if (!growVegetation(dimension, pos.x(), pos.y(), pos.z())) {
            return false;
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
        return true;
    }

    /**
     * Processes grass fading or one natural spread proposal after the inherited random-update
     * hook. Upper-block light dampening above one proposes dirt immediately and ends this
     * update, regardless of cancellation. Otherwise the source needs sufficient internal light
     * above it before sampling a nearby target.
     *
     * <p>
     * The sampled target must be dirt with internal light at least four above and upper-block
     * light dampening below two. A cancellable spread event supplies the replacement state,
     * but the write retains the sampled coordinates. These source and destination observations
     * jointly decide whether a spread occurs.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        var upperBlockState = block.offsetPos(BlockFace.UP);
        // Survival uses upper-block dampening alone; this branch does not consult the
        // internal-light thresholds used by the later spreading checks.
        if (upperBlockState.getBlockStateData().lightDampening() > 1) {
            var event = new BlockFadeEvent(block, BlockTypes.DIRT.getDefaultState());
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
            }
            return;
        }

        // Source lighting gates sampling; destination lighting is checked separately so
        // a bright source cannot convert dirt whose own upper block is too dark.
        if (dimension.getLightEngine().getInternalLight(pos.x(), pos.y() + 1, pos.z()) >= MINIMUM_LIGHT_LEVEL_FOR_SPREADING) {
            // Sample one target in a three-by-five-by-three region, including three
            // blocks below the source and one above, rather than converting every nearby dirt block.
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int x = random.nextInt(pos.x() - 1, pos.x() + 1 + 1);
            int y = random.nextInt(pos.y() - 3, pos.y() + 1 + 1);
            int z = random.nextInt(pos.z() - 1, pos.z() + 1 + 1);
            var blockState = dimension.getBlockState(x, y, z);
            if (blockState.getBlockType() == BlockTypes.DIRT &&
                dimension.getLightEngine().getInternalLight(x, y + 1, z) >= 4 &&
                dimension.getBlockState(x, y + 1, z).getBlockStateData().lightDampening() < 2) {
                var spreadBlock = new Block(BlockTypes.GRASS_BLOCK.getDefaultState(), new Position3i(x, y, z, dimension), 0);
                var event = new BlockSpreadEvent(block, spreadBlock);
                if (event.call()) {
                    dimension.setBlockState(x, y, z, event.getSpreadBlock().getBlockState());
                }
            }
        }
    }

    /**
     * Returns one dirt item for ordinary grass-block breaking without silk touch.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack(1));
    }

    /**
     * Returns whether grass receives random updates for survival and spreading.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Samples nearby columns and attempts vegetation above their exposed grass surfaces. The
     * result reports whether any attempt succeeds; failed samples leave earlier placements
     * intact, and all configured attempts run even after success.
     */
    private boolean growVegetation(Dimension dimension, int originX, int originY, int originZ) {
        var random = ThreadLocalRandom.current();
        var grown = false;
        for (int i = 0; i < BONEMEAL_ATTEMPTS; i++) {
            int x = originX + random.nextInt(-BONEMEAL_HORIZONTAL_RANGE, BONEMEAL_HORIZONTAL_RANGE + 1);
            int z = originZ + random.nextInt(-BONEMEAL_HORIZONTAL_RANGE, BONEMEAL_HORIZONTAL_RANGE + 1);
            int y = findGrassSurfaceY(dimension, x, originY, z);
            if (y == Integer.MIN_VALUE) {
                continue;
            }

            grown |= placeVegetation(dimension, x, y + 1, z, random);
        }

        return grown;
    }

    /**
     * Returns the highest exposed grass surface within the configured vertical search range.
     * Both the surface and plant position must be inside the dimension; {@code
     * Integer.MIN_VALUE} denotes a column without a qualifying surface.
     */
    private int findGrassSurfaceY(Dimension dimension, int x, int originY, int z) {
        for (int offsetY = BONEMEAL_VERTICAL_RANGE; offsetY >= -BONEMEAL_VERTICAL_RANGE; offsetY--) {
            int y = originY + offsetY;
            if (!dimension.isYInRange(y) || !dimension.isYInRange(y + 1)) {
                continue;
            }

            var surfaceType = dimension.getBlockState(x, y, z).getBlockType();
            var aboveType = dimension.getBlockState(x, y + 1, z).getBlockType();
            if (surfaceType == BlockTypes.GRASS_BLOCK && aboveType == BlockTypes.AIR) {
                return y;
            }
        }

        return Integer.MIN_VALUE;
    }

    /**
     * Selects weighted vegetation and attempts to write it into an air position. A tall
     * selection requires air and height validity for both halves, while an ordinary selection
     * needs only the requested position. The method returns {@code false} when the selected
     * plant cannot fit.
     *
     * <p>
     * Tall plants write the lower state before the upper state, setting their respective
     * {@code UPPER_BLOCK_BIT} values. The helper does not roll back either write or inspect
     * the dimension's write results; its {@code true} result means that a valid selection
     * reached its placement calls, rather than guaranteeing both world writes succeeded.
     */
    private boolean placeVegetation(Dimension dimension, int x, int y, int z, ThreadLocalRandom random) {
        if (!dimension.isYInRange(y)) {
            return false;
        }

        if (dimension.getBlockState(x, y, z).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        int roll = random.nextInt(BONEMEAL_VEGETATION_TOTAL_WEIGHT);
        int cumulativeWeight = 0;
        for (var entry : BONEMEAL_VEGETATION) {
            cumulativeWeight += entry.weight();
            if (roll < cumulativeWeight) {
                var blockType = entry.blockType().get();
                if (entry.tallPlant()) {
                    if (!dimension.isYInRange(y + 1)) {
                        return false;
                    }

                    if (dimension.getBlockState(x, y + 1, z).getBlockType() != BlockTypes.AIR) {
                        return false;
                    }

                    var state = blockType.getDefaultState();
                    // Write matching lower and upper flags after checking both positions.
                    // These separate writes have no rollback if a dimension write is rejected.
                    dimension.setBlockState(x, y, z, state.setPropertyValue(BlockPropertyTypes.UPPER_BLOCK_BIT, false));
                    dimension.setBlockState(x, y + 1, z, state.setPropertyValue(BlockPropertyTypes.UPPER_BLOCK_BIT, true));
                } else {
                    dimension.setBlockState(x, y, z, blockType.getDefaultState());
                }

                return true;
            }
        }

        return false;
    }

    /**
     * {@code VegetationEntry} represents a weighted plant choice for grass bone meal.
     * @param weight relative selection weight
     * @param blockType deferred plant-type supplier
     * @param tallPlant two-block placement flag
     */
    private record VegetationEntry(int weight, Supplier<BlockType<?>> blockType, boolean tallPlant) {}
}
