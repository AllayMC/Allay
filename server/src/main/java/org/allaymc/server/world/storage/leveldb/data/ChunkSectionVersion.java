package org.allaymc.server.world.storage.leveldb.data;

/**
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkSectionVersion {
    /**
     * Original subchunk format as of MCPE 1.0
     */
    // v17_0 = 0
    CLASSIC,

    /**
     * First paletted version, seen in 1.2.13
     */
    // v1_3_0 = 1
    PALETTED_SINGLE,

    // The following are not used by vanilla, but treated the same as version 0 due to a legacy converter which
    // erroneously used the version byte as subchunk height
    // v17_0_badly_converted1 = 2
    CLASSIC_BUG_2,
    // v17_0_badly_converted2 = 3
    CLASSIC_BUG_3,
    // v17_0_badly_converted3 = 4
    CLASSIC_BUG_4,
    // v17_0_badly_converted4 = 5
    CLASSIC_BUG_5,
    // v17_0_badly_converted5 = 6
    CLASSIC_BUG_6,
    // v17_0_badly_converted6 = 7
    CLASSIC_BUG_7,

    /**
     * Paletted with layers, almost identical to v1, but includes a length prefix and 0 or more storages.
     * First seen in 1.4 Update Aquatic to support water inside other blocks.
     */
    // v1_3_0_2 = 8
    PALETTED_MULTI,

    /**
     * Paletted with layers, identical to v8 except for a height byte after the layercount byte.
     * This seems like a pointless change, but newest versions of the game use it.
     * First seen in 1.18 for Caves and Cliffs update (and some experimental versions prior to 1.18).
     */
    // v1_3_0_3 = 9
    PALETTED_MULTI_WITH_OFFSET
}
