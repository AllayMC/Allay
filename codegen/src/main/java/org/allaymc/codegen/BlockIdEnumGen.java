package org.allaymc.codegen;

import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.Identifier;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import javax.lang.model.element.Modifier;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static org.allaymc.codegen.ClassNames.*;

/**
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class BlockIdEnumGen {
    public static final Map<String, NbtMap> MAPPED_BLOCK_PALETTE_NBT = new HashMap<>();
    static final Path BLOCK_PALETTE_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "unpacked/block_palette.nbt");
    static final List<NbtMap> BLOCK_PALETTE_NBT;
    private static final String PACKAGE_NAME = "org.allaymc.api.block.data";
    public static final Path PACKAGE_PATH = Path.of("api/src/main/java/org/allaymc/api/block/data");

    static {
        try (var nbtReader = new NBTInputStream(new DataInputStream(new GZIPInputStream(Files.newInputStream(BLOCK_PALETTE_FILE_PATH))))) {
            BLOCK_PALETTE_NBT = ((NbtMap) nbtReader.readTag()).getList("blocks", NbtType.COMPOUND);
            for (var entry : BLOCK_PALETTE_NBT) {
                MAPPED_BLOCK_PALETTE_NBT.put(entry.getString("name"), entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
        generateToDependenceModule();
        generateToAPIModule();
    }

    @SneakyThrows
    public static void generateToDependenceModule() {
        TypeSpec.Builder codeBuilder = commonBuilder(DEP_IDENTIFIER);
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("org.allaymc.dependence", codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Files.writeString(Path.of("codegen/src/main/java/org/allaymc/dependence/BlockId.java"), javaFile.toString());
    }

    @SneakyThrows
    public static void generateToAPIModule() {
        var blockTypeClass = ParameterizedTypeName.get(BLOCK_TYPE, WildcardTypeName.subtypeOf(TypeName.OBJECT));
        TypeSpec.Builder codeBuilder = commonBuilder(ClassNames.API_IDENTIFIER)
                .addAnnotation(MINECRAFT_VERSION_SENSITIVE)
                .addMethod(MethodSpec.methodBuilder("getBlockType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.BLOCKS.get(this.getIdentifier())", REGISTRIES)
                        .returns(blockTypeClass)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("fromIdentifier")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ClassNames.API_IDENTIFIER, "identifier")
                        .addCode("""
                                try {
                                    return valueOf(identifier.path().toUpperCase(java.util.Locale.ENGLISH));
                                } catch (IllegalArgumentException ignore) {
                                    return null;
                                }""")
                        .returns(BLOCK_ID)
                        .build()
                );
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder(PACKAGE_NAME, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Files.writeString(PACKAGE_PATH.resolve("BlockId.java"), javaFile.toString());
    }

    private static void addEnums(TypeSpec.Builder codeBuilder) {
        var sortedIdentifier = BLOCK_PALETTE_NBT.stream().map(block -> block.getString("name")).sorted(String::compareTo).map(Identifier::new).toList();
        for (var identifier : sortedIdentifier) {
            codeBuilder.addEnumConstant(
                    identifier.path().toUpperCase(),
                    TypeSpec.anonymousClassBuilder("$S", identifier.toString()).build()
            );
        }
    }

    private static TypeSpec.Builder commonBuilder(ClassName identifierClass) {
        return TypeSpec.enumBuilder("BlockId")
                .addJavadoc("Automatically generated by {@code org.allaymc.codegen.BlockIdEnumGen}")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClass, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(ClassNames.STRING, "identifier")
                        .addStatement("this.identifier = new $T(identifier)", identifierClass)
                        .build()
                );
    }
}