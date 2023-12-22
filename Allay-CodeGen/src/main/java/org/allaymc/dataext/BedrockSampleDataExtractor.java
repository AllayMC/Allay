package org.allaymc.dataext;

import java.nio.file.Path;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@Deprecated
public class BedrockSampleDataExtractor {

    public static final Path BEDROCK_SAMPLE_FILE_ROOT_PATH = Path.of("Data/bedrock-samples");
    public static final Path BEHAVIOR_PACK_ROOT_PATH = BEDROCK_SAMPLE_FILE_ROOT_PATH.resolve("behavior_pack");
    public static final Path RESOURCE_PACK_ROOT_PATH = BEDROCK_SAMPLE_FILE_ROOT_PATH.resolve("resource_pack");

    public static void main(String[] args) {
//        VanillaEntityDataExtractor.extractDataFromBedrockSample();
    }
}
