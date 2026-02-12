package org.allaymc.server.world.storage.leveldb.data;

/**
 * Enum that represents the versions of chunk formats used in the system.
 *
 * <p>
 * This enum tracks the different chunk versions over time, with specific references to betas, internal versions,
 * and experimental features.
 * Skipped versions may indicate internal use or unpublicized beta versions.
 * </p>
 *
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkVersion {
    /**
     * Version v9_00
     */
    V0_9_0,
    /**
     * Version v9_02
     */
    V0_9_2,
    /**
     * Version v9_05
     */
    V0_9_5,
    /**
     * Version v17_0
     */
    V1_0_0,
    /**
     * Version v18_0
     */
    V1_1_0,
    /**
     * Version vConsole1_to_v18_0
     */
    V1_1_0_CONVERTED_FROM_CONSOLE,
    /**
     * Version v1_2_0
     */
    V1_2_0_2_BETA,
    /**
     * Version v1_2_0_bis
     */
    V1_2_0,
    /**
     * Version v1_3_0
     */
    V1_2_13,
    /**
     * Version v1_8_0
     */
    V1_8_0,
    /**
     * Version v1_9_0
     */
    V1_9_0,
    /**
     * Version v1_10_0
     */
    V1_11_0_1_BETA,
    /**
     * Version v1_11_0
     */
    V1_11_0_3_BETA,
    /**
     * Version v1_11_1
     */
    V1_11_0_4_BETA,
    /**
     * Version v1_11_2
     */
    V1_11_1,
    /**
     * Version v1_12_0
     */
    V1_12_0_4_BETA,
    /**
     * Possibly some beta version?
     *
     * <p>
     * Version v1_14_0
     * </p>
     */
    V1_12_0_UNUSED_1,
    /**
     * Possibly some beta version?
     *
     * <p>
     * Version v1_15_0
     * </p>
     */
    V1_12_0_UNUSED_2,
    /**
     * Version v1_16_0
     */
    V1_16_0_51_BETA,
    /**
     * Version v1_16_0_bis
     */
    V1_16_0,
    /**
     * Version v1_16_100
     */
    V1_16_100_52_BETA,
    /**
     * Version v1_16_100_bis
     */
    V1_16_100_57_BETA,
    /**
     * Version v1_16_210
     */
    V1_16_210,

    /**
     * Since this version, they seem to skip every other version. Possibly the skipped ones are internal use.
     * <p>
     * Version v1_16_300_caves_cliffs_part_1
     * </p>
     */
    V1_16_220_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Version v1_16_300_caves_cliffs_internal_v1
     */
    V1_16_220_50_UNUSED,
    /**
     * Version v1_16_300_caves_cliffs_part_2
     */
    V1_16_230_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Version v1_16_300_caves_cliffs_internal_v2
     */
    V1_16_230_50_UNUSED,
    /**
     * Version v1_16_300_caves_cliffs_part_3
     */
    V1_17_30_23_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Version v1_16_300_caves_cliffs_internal_v3
     */
    V1_17_30_23_UNUSED,
    /**
     * Version v1_16_300_caves_cliffs_part_4
     */
    V1_17_30_25_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Version v1_16_300_caves_cliffs_internal_v4
     */
    V1_17_30_25_UNUSED,
    /**
     * Version v1_16_300_caves_cliffs_part_5
     */
    V1_17_40_20_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Version v1_16_300_caves_cliffs_internal_v5
     */
    V1_17_40_UNUSED,
    /**
     * Version v1_18_0
     */
    V1_18_0_20_BETA,
    /**
     * Version v1_18_0_internal
     */
    V1_18_0_20_UNUSED,
    /**
     * Version v1_18_1
     */
    V1_18_0_22_BETA,
    /**
     * Version v1_18_1_internal
     */
    V1_18_0_22_UNUSED,
    /**
     * Version v1_18_2
     */
    V1_18_0_24_BETA,
    /**
     * Version v1_18_2_internal
     */
    V1_18_0_24_UNUSED,
    /**
     * Version v1_18_3
     */
    V1_18_0_25_BETA,
    /**
     * Version v1_18_3_individual_actor_storage
     */
    V1_18_30,
    /**
     * Biome id becomes short instead of byte
     * <p>
     * Version v1_21_4
     * </p>
     */
    V1_21_40
}
