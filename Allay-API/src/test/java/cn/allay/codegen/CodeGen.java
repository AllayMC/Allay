package cn.allay.codegen;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class CodeGen {
    public static void main(String[] args) {
        VanillaBlockIdEnumGen.generate();
        VanillaBlockPropertyTypeGen.generate();
    }
}
