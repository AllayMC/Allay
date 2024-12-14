package org.allaymc.server.world.storage;

/**
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkVersion {
    // v9_00 = 0
    V0_9_0,
    // v9_02 = 1
    V0_9_2,
    // v9_05 = 2
    V0_9_5,
    // v17_0 = 3
    V1_0_0,
    // v18_0 = 4
    V1_1_0,
    // vConsole1_to_v18_0 = 5
    V1_1_0_CONVERTED_FROM_CONSOLE,
    // v1_2_0 = 6
    V1_2_0_2_BETA,
    // v1_2_0_bis = 7
    V1_2_0,
    // v1_3_0 = 8
    V1_2_13,
    // v1_8_0 = 9
    V1_8_0,
    // v1_9_0 = 10
    V1_9_0,
    // v1_10_0 = 11
    V1_11_0_1_BETA,
    // v1_11_0 = 12
    V1_11_0_3_BETA,
    // v1_11_1 = 13
    V1_11_0_4_BETA,
    // v1_11_2 = 14
    V1_11_1,
    // v1_12_0 = 15
    V1_12_0_4_BETA,
    // v1_14_0 = 16
    V1_12_0_UNUSED_1, //possibly some beta version?
    // v1_15_0 = 17
    V1_12_0_UNUSED_2, //possibly some beta version?
    // v1_16_0 = 18
    V1_16_0_51_BETA,
    // v1_16_0_bis = 19
    V1_16_0,
    // v1_16_100 = 20
    V1_16_100_52_BETA,
    // v1_16_100_bis = 21
    V1_16_100_57_BETA,
    // v1_16_210 = 22
    V1_16_210,

    // Since this version they seem to skip every other version. Possibly the skipped ones are internal use.
    // v1_16_300_caves_cliffs_part_1 = 23
    V1_16_220_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    // v1_16_300_caves_cliffs_internal_v1 = 24
    V1_16_220_50_UNUSED,
    // v1_16_300_caves_cliffs_part_2 = 25
    V1_16_230_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    // v1_16_300_caves_cliffs_internal_v2 = 26
    V1_16_230_50_UNUSED,
    // v1_16_300_caves_cliffs_part_3 = 27
    V1_17_30_23_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    // v1_16_300_caves_cliffs_internal_v3 = 28
    V1_17_30_23_UNUSED,
    // v1_16_300_caves_cliffs_part_4 = 29
    V1_17_30_25_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    // v1_16_300_caves_cliffs_internal_v4 = 30
    V1_17_30_25_UNUSED,
    // v1_16_300_caves_cliffs_part_5 = 31
    V1_17_40_20_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    // v1_16_300_caves_cliffs_internal_v5 = 32
    V1_17_40_UNUSED,
    // v1_18_0 = 33
    V1_18_0_20_BETA,
    // v1_18_0_internal = 34
    V1_18_0_20_UNUSED,
    // v1_18_1 = 35
    V1_18_0_22_BETA,
    // v1_18_1_internal = 36
    V1_18_0_22_UNUSED,
    // v1_18_2 = 37
    V1_18_0_24_BETA,
    // v1_18_2_internal = 38
    V1_18_0_24_UNUSED,
    // v1_18_3 = 39
    V1_18_0_25_BETA,
    // v1_18_3_individual_actor_storage = 40
    V1_18_30,
    // v1_21_4 = 41
    V1_21_40; // Biome id become short instead of byte
}
