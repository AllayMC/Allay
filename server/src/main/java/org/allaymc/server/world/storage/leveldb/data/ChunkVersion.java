package org.allaymc.server.world.storage.leveldb.data;

/**
 * {@code ChunkVersion} names
 * chunk-format version slots whose ordinals are persisted in chunk
 * version records. The LevelDB writer selects the current slot by ordinal, so
 * existing constants and unused placeholders must retain their positions when
 * later formats are added.
 *
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkVersion {
    /**
     * Historical format label {@code v9_00}.
     */
    V0_9_0,
    /**
     * Historical format label {@code v9_02}.
     */
    V0_9_2,
    /**
     * Historical format label {@code v9_05}.
     */
    V0_9_5,
    /**
     * Historical format label {@code v17_0}.
     */
    V1_0_0,
    /**
     * Historical format label {@code v18_0}.
     */
    V1_1_0,
    /**
     * Historical format label {@code vConsole1_to_v18_0}.
     */
    V1_1_0_CONVERTED_FROM_CONSOLE,
    /**
     * Historical format label {@code v1_2_0}.
     */
    V1_2_0_2_BETA,
    /**
     * Historical format label {@code v1_2_0_bis}.
     */
    V1_2_0,
    /**
     * Historical format label {@code v1_3_0}.
     */
    V1_2_13,
    /**
     * Historical format label {@code v1_8_0}.
     */
    V1_8_0,
    /**
     * Historical format label {@code v1_9_0}.
     */
    V1_9_0,
    /**
     * Historical format label {@code v1_10_0}.
     */
    V1_11_0_1_BETA,
    /**
     * Historical format label {@code v1_11_0}.
     */
    V1_11_0_3_BETA,
    /**
     * Historical format label {@code v1_11_1}.
     */
    V1_11_0_4_BETA,
    /**
     * Historical format label {@code v1_11_2}.
     */
    V1_11_1,
    /**
     * Historical format label {@code v1_12_0}.
     */
    V1_12_0_4_BETA,
    /**
     * Historical format label {@code v1_14_0}. This placeholder preserves the
     * numeric identity of subsequent chunk formats.
     */
    V1_12_0_UNUSED_1,
    /**
     * Historical format label {@code v1_15_0}. This placeholder preserves the
     * numeric identity of subsequent chunk formats.
     */
    V1_12_0_UNUSED_2,
    /**
     * Historical format label {@code v1_16_0}.
     */
    V1_16_0_51_BETA,
    /**
     * Historical format label {@code v1_16_0_bis}.
     */
    V1_16_0,
    /**
     * Historical format label {@code v1_16_100}.
     */
    V1_16_100_52_BETA,
    /**
     * Historical format label {@code v1_16_100_bis}.
     */
    V1_16_100_57_BETA,
    /**
     * Historical format label {@code v1_16_210}.
     */
    V1_16_210,

    /**
     * Historical format label {@code v1_16_300_caves_cliffs_part_1}.
     */
    V1_16_220_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_internal_v1}. This
     * placeholder preserves the numeric identity of subsequent chunk formats.
     */
    V1_16_220_50_UNUSED,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_part_2}.
     */
    V1_16_230_50_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_internal_v2}. This
     * placeholder preserves the numeric identity of subsequent chunk formats.
     */
    V1_16_230_50_UNUSED,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_part_3}.
     */
    V1_17_30_23_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_internal_v3}. This
     * placeholder preserves the numeric identity of subsequent chunk formats.
     */
    V1_17_30_23_UNUSED,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_part_4}.
     */
    V1_17_30_25_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_internal_v4}. This
     * placeholder preserves the numeric identity of subsequent chunk formats.
     */
    V1_17_30_25_UNUSED,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_part_5}.
     */
    V1_17_40_20_BETA_EXPERIMENTAL_CAVES_CLIFFS,
    /**
     * Historical format label {@code v1_16_300_caves_cliffs_internal_v5}. This
     * placeholder preserves the numeric identity of subsequent chunk formats.
     */
    V1_17_40_UNUSED,
    /**
     * Historical format label {@code v1_18_0}.
     */
    V1_18_0_20_BETA,
    /**
     * Historical format label {@code v1_18_0_internal}. This placeholder
     * preserves the numeric identity of subsequent chunk formats.
     */
    V1_18_0_20_UNUSED,
    /**
     * Historical format label {@code v1_18_1}.
     */
    V1_18_0_22_BETA,
    /**
     * Historical format label {@code v1_18_1_internal}. This placeholder
     * preserves the numeric identity of subsequent chunk formats.
     */
    V1_18_0_22_UNUSED,
    /**
     * Historical format label {@code v1_18_2}.
     */
    V1_18_0_24_BETA,
    /**
     * Historical format label {@code v1_18_2_internal}. This placeholder
     * preserves the numeric identity of subsequent chunk formats.
     */
    V1_18_0_24_UNUSED,
    /**
     * Historical format label {@code v1_18_3}.
     */
    V1_18_0_25_BETA,
    /**
     * Historical format label {@code v1_18_3_individual_actor_storage}.
     */
    V1_18_30,
    /**
     * Historical format label {@code v1_21_4}.
     */
    V1_21_40,
    /**
     * Historical format label {@code v1_21_120}.
     */
    V1_21_120
}
