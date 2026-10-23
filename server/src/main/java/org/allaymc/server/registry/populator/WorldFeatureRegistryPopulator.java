package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.feature.WorldFeatures;
import org.allaymc.server.world.feature.fungus.HugeFungusFeature;
import org.allaymc.server.world.feature.mushroom.HugeBrownMushroomFeature;
import org.allaymc.server.world.feature.mushroom.HugeRedMushroomFeature;
import org.allaymc.server.world.feature.tree.*;

/**
 * {@code WorldFeatureRegistryPopulator} supplies the built-in tree, huge mushroom, and fungus
 * features exposed through {@code WorldFeatures}. Feature constructors register their
 * identifiers, and block types must already be populated because feature definitions retain
 * the log, foliage, stem, or wart types used during placement.
 *
 * @author daoge_cmd
 */
@Slf4j
public class WorldFeatureRegistryPopulator implements Runnable {
    /**
     * Constructs and assigns built-in vegetation features after block types and the feature
     * registry are available. Registration occurs during each construction, so a later failure
     * does not remove earlier feature registrations or constant assignments.
     */
    @Override
    public void run() {
        // Basic tree types
        WorldFeatures.OAK_TREE = new StraightBlobTreeFeature(StraightBlobTreeFeature.OAK_IDENTIFIER, BlockTypes.OAK_LOG, BlockTypes.OAK_LEAVES, 4, 2, 0);
        WorldFeatures.BIRCH_TREE = new StraightBlobTreeFeature(StraightBlobTreeFeature.BIRCH_IDENTIFIER, BlockTypes.BIRCH_LOG, BlockTypes.BIRCH_LEAVES, 5, 2, 0);
        WorldFeatures.SPRUCE_TREE = new SpruceTreeFeature();
        WorldFeatures.JUNGLE_TREE = new StraightBlobTreeFeature(StraightBlobTreeFeature.JUNGLE_IDENTIFIER, BlockTypes.JUNGLE_LOG, BlockTypes.JUNGLE_LEAVES, 4, 8, 0);
        WorldFeatures.ACACIA_TREE = new AcaciaTreeFeature();
        WorldFeatures.DARK_OAK_TREE = new DoubleTrunkTreeFeature(DoubleTrunkTreeFeature.DARK_OAK_IDENTIFIER, BlockTypes.DARK_OAK_LOG, BlockTypes.DARK_OAK_LEAVES);
        WorldFeatures.CHERRY_TREE = new CherryTreeFeature();
        WorldFeatures.MANGROVE_TREE = new MangroveTreeFeature();
        WorldFeatures.PALE_OAK_TREE = new DoubleTrunkTreeFeature(DoubleTrunkTreeFeature.PALE_OAK_IDENTIFIER, BlockTypes.PALE_OAK_LOG, BlockTypes.PALE_OAK_LEAVES);

        // Azalea tree
        WorldFeatures.AZALEA_TREE = new AzaleaTreeFeature();

        // Large tree variants
        WorldFeatures.MEGA_SPRUCE_TREE = new MegaConicalTreeFeature(MegaConicalTreeFeature.MEGA_SPRUCE_IDENTIFIER, BlockTypes.SPRUCE_LOG, BlockTypes.SPRUCE_LEAVES, 13, 5);
        WorldFeatures.MEGA_PINE_TREE = new MegaConicalTreeFeature(MegaConicalTreeFeature.MEGA_PINE_IDENTIFIER, BlockTypes.SPRUCE_LOG, BlockTypes.SPRUCE_LEAVES, 3, 5);
        WorldFeatures.MEGA_JUNGLE_TREE = new MegaJungleTreeFeature();
        WorldFeatures.TALL_BIRCH_TREE = new StraightBlobTreeFeature(StraightBlobTreeFeature.TALL_BIRCH_IDENTIFIER, BlockTypes.BIRCH_LOG, BlockTypes.BIRCH_LEAVES, 5, 2, 6);
        WorldFeatures.FANCY_OAK_TREE = new FancyOakTreeFeature();

        // Huge mushrooms
        WorldFeatures.HUGE_BROWN_MUSHROOM = new HugeBrownMushroomFeature();
        WorldFeatures.HUGE_RED_MUSHROOM = new HugeRedMushroomFeature();

        // Huge fungi (nether trees)
        WorldFeatures.HUGE_CRIMSON_FUNGUS = new HugeFungusFeature(
                HugeFungusFeature.CRIMSON_IDENTIFIER,
                BlockTypes.CRIMSON_STEM, BlockTypes.NETHER_WART_BLOCK
        );
        WorldFeatures.HUGE_WARPED_FUNGUS = new HugeFungusFeature(
                HugeFungusFeature.WARPED_IDENTIFIER,
                BlockTypes.WARPED_STEM, BlockTypes.WARPED_WART_BLOCK
        );
    }
}
