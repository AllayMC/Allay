package org.allaymc.server.world.storage;

/**
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkSectionVersion {
    /**
     * Original subchunk format as of MCPE 1.0
     */
    CLASSIC,

    /**
     * First paletted version, seen in 1.2.13
     */
    PALETTED_SINGLE,

    //the following are not used by vanilla, but treated the same as version 0 due to a legacy converter which
    //erroneously used the version byte as subchunk height
    CLASSIC_BUG_2,
    CLASSIC_BUG_3,
    CLASSIC_BUG_4,
    CLASSIC_BUG_5,
    CLASSIC_BUG_6,
    CLASSIC_BUG_7,

    /**
     * Paletted with layers, almost identical to v1, but includes a length prefix and 0 or more storages.
     * First seen in 1.4 Update Aquatic to support water inside other blocks.
     */
    PALETTED_MULTI,

    /**
     * Paletted with layers, identical to v8 except for a height byte after the layercount byte.
     * This seems like a pointless change, but newest versions of the game use it.
     * First seen in 1.18 for Caves and Cliffs update (and some experimental versions prior to 1.18).
     */
    PALETTED_MULTI_WITH_OFFSET
}
