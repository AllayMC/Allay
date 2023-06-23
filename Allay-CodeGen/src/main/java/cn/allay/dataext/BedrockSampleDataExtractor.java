package cn.allay.dataext;

import java.nio.file.Path;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/20 <br>
 * Allay Project <br>
 */
public class BedrockSampleDataExtractor {

    public static final Path BEDROCK_SAMPLE_FILE_ROOT_PATH = Path.of("Data/bedrock-samples");
    public static final Path BEHAVIOR_PACK_ROOT_PATH = BEDROCK_SAMPLE_FILE_ROOT_PATH.resolve("behavior_pack");
    public static final Path RESOURCE_PACK_ROOT_PATH = BEDROCK_SAMPLE_FILE_ROOT_PATH.resolve("resource_pack");

    public static void main(String[] args) {
//        VanillaEntityDataExtractor.extractDataFromBedrockSample();
    }
}
