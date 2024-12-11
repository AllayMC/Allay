package org.allaymc.server.world.storage;

/**
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkVersion {
    V0_9_0,
    V0_9_2,
    V0_9_5,
    V1_0_0,
    V1_1_0,
    V1_1_0_CONVERTED_FROM_CONSOLE,
    V1_2_0_2_BETA,
    V1_2_0,
    V1_2_13,
    V1_8_0,
    V1_9_0,
    V1_11_0_1_BETA,
    V1_11_0_3_BETA,
    V1_11_0_4_BETA,
    V1_11_1,
    V1_12_0_4_BETA,
    V1_12_0_UNUSED_1, //possibly some beta version?
    V1_12_0_UNUSED_2, //possibly some beta version?
    V1_16_0_51_BETA,
    V1_16_0,
    V1_16_100_52_BETA,
    V1_16_100_57_BETA,
    V1_16_210,

    //Since this version they seem to skip every other version. Possibly the skipped ones are internal use.
    V1_16_220_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    V1_16_220_50_UNUSED,
    V1_16_230_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    V1_16_230_50_UNUSED,
    V1_17_30_23_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    V1_17_30_23_UNUSED,
    V1_17_30_25_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    V1_17_30_25_UNUSED,
    V1_17_40_20_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    V1_17_40_UNUSED,
    V1_18_0_20_BETA,
    V1_18_0_20_UNUSED,
    V1_18_0_22_BETA,
    V1_18_0_22_UNUSED,
    V1_18_0_24_BETA,
    V1_18_0_24_UNUSED,
    V1_18_0_25_BETA,
    V1_18_30,
    V1_21_40; // Biome id become short instead of byte
}
