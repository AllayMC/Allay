package cn.allay.codegen;

import java.nio.file.Path;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class CodeGen {
    static final Path BLOCK_PALETTE_FILE_PATH = Path.of("Allay-Server/src/main/resources/block_palette.nbt");

    public static void main(String[] args) {
        VanillaBlockIdEnumGen.generate();
        VanillaBlockPropertyTypeGen.generate();
    }
}
