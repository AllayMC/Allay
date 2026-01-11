package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.world.feature.WorldFeatures;
import org.allaymc.server.world.feature.tree.*;

/**
 * Populates the WorldFeatures class with tree feature instances.
 *
 * @author daoge_cmd
 */
@Slf4j
public class WorldFeatureRegistryPopulator implements Runnable {
    @Override
    public void run() {
        // Basic tree types
        WorldFeatures.OAK_TREE = new OakTreeFeature();
        WorldFeatures.BIRCH_TREE = new BirchTreeFeature();
        WorldFeatures.SPRUCE_TREE = new SpruceTreeFeature();
        WorldFeatures.JUNGLE_TREE = new JungleTreeFeature();
        WorldFeatures.ACACIA_TREE = new AcaciaTreeFeature();
        WorldFeatures.DARK_OAK_TREE = new DarkOakTreeFeature();
        WorldFeatures.CHERRY_TREE = new CherryTreeFeature();
        WorldFeatures.MANGROVE_TREE = new MangroveTreeFeature();
        WorldFeatures.PALE_OAK_TREE = new PaleOakTreeFeature();

        // Large tree variants
        WorldFeatures.MEGA_SPRUCE_TREE = new MegaSpruceTreeFeature();
        WorldFeatures.MEGA_PINE_TREE = new MegaPineTreeFeature();
        WorldFeatures.MEGA_JUNGLE_TREE = new MegaJungleTreeFeature();
        WorldFeatures.TALL_BIRCH_TREE = new TallBirchTreeFeature();
        WorldFeatures.FANCY_OAK_TREE = new FancyOakTreeFeature();
    }
}
