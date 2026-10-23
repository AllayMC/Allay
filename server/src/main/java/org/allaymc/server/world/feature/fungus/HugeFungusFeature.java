package org.allaymc.server.world.feature.fungus;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.server.world.feature.AbstractWorldFeature;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code HugeFungusFeature} places configurable crimson or warped huge fungi
 * using a central stem, wart canopy, and occasional shroomlights. Both
 * variants accept either nylium substrate and use the same replacement
 * predicate for stems, canopy blocks, and downward wart strands. The
 * constructor registers the feature before retaining its selected materials.
 *
 * <p>
 * Placement validates the substrate and trunk column before writing, but it
 * does not reserve the surrounding canopy or validate dimension height. Canopy
 * positions are tested individually, so obstacles can leave gaps without
 * rejecting the feature. Hollow lower layers surround the stem, and a smaller
 * top cap closes the canopy. Random corner omissions and downward strands vary
 * that outline independently for each eligible position.
 *
 * @author daoge_cmd
 */
public class HugeFungusFeature extends AbstractWorldFeature {

    /**
     * Crimson huge-fungus registry identifier. Runtime initialization uses
     * this key when constructing and registering its corresponding feature
     * instance.
     */
    public static final Identifier CRIMSON_IDENTIFIER = new Identifier("minecraft:huge_crimson_fungus");
    /**
     * Warped huge-fungus registry identifier. Runtime initialization uses this
     * key when constructing and registering its corresponding feature
     * instance.
     */
    public static final Identifier WARPED_IDENTIFIER = new Identifier("minecraft:huge_warped_fungus");

    /**
     * Stem trunk block type.
     */
    private final BlockType<?> stemType;
    /**
     * Canopy and downward-strand block type.
     */
    private final BlockType<?> wartBlockType;

    /**
     * Creates and registers a huge-fungus feature with the supplied stem and
     * canopy materials. Both materials are retained after base registration,
     * and either nylium variant is accepted by the shared placement check.
     */
    public HugeFungusFeature(Identifier identifier, BlockType<?> stemType, BlockType<?> wartBlockType) {
        super(identifier);
        this.stemType = stemType;
        this.wartBlockType = wartBlockType;
    }

    /**
     * Attempts a fungus with a randomly selected stem height from four through
     * twelve blocks. An unsupported substrate or obstructed trunk returns
     * {@code false} before mutation; canopy obstacles are skipped after the
     * trunk is written.
     *
     * <p>
     * The ground check accepts both crimson and warped nylium independently of
     * the configured stem material. The trunk is placed before hollow canopy
     * layers and their cap, and each canopy position rechecks replaceability.
     * A {@code true} result means these passes completed, while inaccessible
     * chunks can still discard writes and exceptions do not roll back earlier
     * changes.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        // The sampled stem height also anchors the lower canopy layers and cap.
        int height = 4 + random.nextInt(9);

        if (!canGenerate(context, x, y, z, height)) {
            return false;
        }

        placeTrunk(context, x, y, z, height);
        placeCanopy(context, x, y, z, height, random);

        return true;
    }

    /**
     * Returns whether the state is air, replaceable, or tagged as leaves.
     */
    private boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.AIR ||
               blockType.hasBlockTag(BlockTags.REPLACEABLE) ||
               blockType.hasBlockTag(BlockTags.LEAVES);
    }

    /**
     * Returns whether either nylium substrate supports an unobstructed stem
     * column. Only the central column is checked; canopy space and dimension
     * bounds remain subject to later context access and per-position
     * replacement checks.
     */
    private boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        // Either nylium substrate accepts both configured fungus variants.
        var groundType = context.getBlockState(x, y - 1, z).getBlockType();
        if (groundType != BlockTypes.CRIMSON_NYLIUM && groundType != BlockTypes.WARPED_NYLIUM) {
            return false;
        }

        // Only the stem column is a prerequisite; canopy obstacles are handled
        // individually during placement rather than rejecting this growth attempt.
        for (int dy = 0; dy < height; dy++) {
            if (!canReplace(context.getBlockState(x, y + dy, z))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Places default stem blocks through eligible positions in the selected
     * column. The replacement predicate is checked again during writing, so
     * changed or inaccessible terrain can prevent individual requested stem
     * blocks.
     */
    private void placeTrunk(WorldFeatureContext context, int x, int y, int z, int height) {
        var stemState = stemType.getDefaultState();
        for (int dy = 0; dy < height; dy++) {
            if (canReplace(context.getBlockState(x, y + dy, z))) {
                context.setBlockState(x, y + dy, z, stemState);
            }
        }
    }

    /**
     * Places three hollow radius-two layers below a smaller radius-one top
     * cap. Each layer delegates its random omissions, shroomlights, and
     * downward strands, with the caller-supplied height measured from the stem
     * base.
     */
    private void placeCanopy(WorldFeatureContext context, int x, int y, int z, int height, ThreadLocalRandom random) {
        // Hollow lower layers surround the stem without filling their interior;
        // the smaller final cap closes the top independently of that cavity.
        for (int layer = height - 3; layer < height; layer++) {
            int canopyY = y + layer;
            placeCanopyLayer(context, x, canopyY, z, 2, true, random);
        }

                int topY = y + height;
        placeCanopyLayer(context, x, topY, z, 1, false, random);
    }

    /**
     * Places eligible wart or shroomlight blocks in a square canopy layer,
     * optionally excluding its interior. Corners have independent omission
     * chances, and nonreplaceable positions receive neither a canopy block nor
     * a downward strand.
     *
     * <p>
     * A shroomlight replaces the wart choice at one in twenty accepted
     * positions. Hollow-layer edges can start downward strands with one-third
     * probability, and each strand uses wart blocks for up to four lower
     * positions until replacement fails. The random length can also be zero,
     * so selecting a strand does not imply that any lower block is written.
     */
    private void placeCanopyLayer(WorldFeatureContext context, int x, int y, int z, int radius, boolean hollow, ThreadLocalRandom random) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                // Hollow layers leave the interior available for the central stem.
                if (hollow && Math.abs(dx) < radius && Math.abs(dz) < radius) {
                    continue;
                }

                // Corner omissions vary the outline while leaving straight edges eligible.
                if (Math.abs(dx) == radius && Math.abs(dz) == radius) {
                    if (random.nextBoolean()) {
                        continue;
                    }
                }

                if (!canReplace(context.getBlockState(x + dx, y, z + dz))) {
                    continue;
                }

                // One in twenty eligible canopy blocks becomes a light source.
                if (random.nextInt(20) == 0) {
                    context.setBlockState(x + dx, y, z + dz, BlockTypes.SHROOMLIGHT.getDefaultState());
                } else {
                    context.setBlockState(x + dx, y, z + dz, wartBlockType.getDefaultState());
                }

                // Hollow edges can grow downward wart strands; the selected material
                // remains wart blocks rather than a separate vine block type.
                if (hollow && isEdge(dx, dz, radius) && random.nextInt(3) == 0) {
                    int vineLength = random.nextInt(5); // zero through four downward wart blocks
                    for (int vy = 1; vy <= vineLength; vy++) {
                        if (canReplace(context.getBlockState(x + dx, y - vy, z + dz))) {
                            context.setBlockState(x + dx, y - vy, z + dz, wartBlockType.getDefaultState());
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns whether either horizontal offset reaches the square layer
     * boundary.
     */
    private boolean isEdge(int dx, int dz, int radius) {
        return Math.abs(dx) == radius || Math.abs(dz) == radius;
    }
}
