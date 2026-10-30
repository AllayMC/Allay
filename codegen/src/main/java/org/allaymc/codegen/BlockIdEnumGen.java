package org.allaymc.codegen;

import com.palantir.javapoet.*;
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
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * {@code BlockIdEnumGen} generates block identifier enums from the unpacked
 * block-state palette. The palette is loaded once, while a separate name map
 * retains the last compound observed for each block for other generators to
 * inspect. Enum generation reads the full palette list, sorts its names, and
 * targets either standalone generator identifiers or runtime API identifiers.
 *
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class BlockIdEnumGen {
    /**
     * {@code MAPPED_BLOCK_PALETTE_NBT} exposes the last palette compound for
     * each block name. Other generators reuse this mutable map when inspecting
     * block properties and defaults.
     */
    public static final Map<String, NbtMap> MAPPED_BLOCK_PALETTE_NBT = new HashMap<>();
    /**
     * {@code PACKAGE_PATH} locates the server source directory receiving the
     * generated runtime block enumeration and its API-based lookup methods.
     */
    public static final Path PACKAGE_PATH = Path.of("server/src/main/java/org/allaymc/server/block/data");
    /**
     * {@code BLOCK_PALETTE_FILE_PATH} selects the compressed palette loaded
     * during class initialization before block identifier generation can
     * proceed.
     */
    static final Path BLOCK_PALETTE_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "unpacked/block_palette.nbt");
    /**
     * {@code BLOCK_PALETTE_NBT} retains the palette's complete entry list,
     * including repeated block names. {@code addEnums} reads this list rather
     * than the collapsed name lookup used by other generators.
     */
    static final List<NbtMap> BLOCK_PALETTE_NBT;
    /**
     * {@code PACKAGE_NAME} selects the runtime block-data package used when
     * JavaPoet emits the server enum and its imports.
     */
    private static final String PACKAGE_NAME = "org.allaymc.server.block.data";

    // Load the palette once and retain its full list alongside the last-entry
    // name index. I/O failure aborts class initialization; generator calls do not
    // refresh either representation from disk.
    static {
        try (var nbtReader = new NBTInputStream(new DataInputStream(new GZIPInputStream(Files.newInputStream(BLOCK_PALETTE_FILE_PATH))))) {
            BLOCK_PALETTE_NBT = ((NbtMap) nbtReader.readTag()).getList("blocks", NbtType.COMPOUND);
            for (var entry : BLOCK_PALETTE_NBT) {
                MAPPED_BLOCK_PALETTE_NBT.put(entry.getString("name"), entry);
            }
        } catch (IOException e) {
            throw new CodeGenException(e);
        }
    }

    /**
     * Generates both block enums from the palette loaded at class
     * initialization. {@code args} is ignored, and input and output paths are
     * resolved from the repository root used as the process working directory.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Replaces the generator-side block enum before writing the server variant.
     * The two outputs are written independently, so a second-output failure
     * does not restore the previous generator-side file after it was replaced.
     */
    @SneakyThrows
    public static void generate() {
        generateToDependenceModule();
        generateToServerModule();
    }

    /**
     * Writes the standalone block enum using generator-side identifier objects.
     * Constants are populated from the full palette list; this variant omits
     * the runtime registry and identifier lookup methods added to the server
     * output.
     */
    @SneakyThrows
    public static void generateToDependenceModule() {
        TypeSpec.Builder codeBuilder = commonBuilder(TypeNames.DEP_IDENTIFIER);
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("org.allaymc.dependence", codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("codegen/src/main/java/org/allaymc/dependence/BlockId.java"), javaFile.toString());
    }

    /**
     * Writes the server block enum with identifier and registry lookup methods.
     * Generated identifier lookup uppercases the path and ignores the
     * namespace, returning {@code null} for unmatched constants. Registry
     * lookup uses each enum constant's complete stored identifier.
     */
    @SneakyThrows
    public static void generateToServerModule() {
        var blockTypeClass = ParameterizedTypeName.get(TypeNames.BLOCK_TYPE, WildcardTypeName.subtypeOf(ClassName.OBJECT));
        // Generated enum lookup reports an unknown path as a null result;
        // this fallback does not change registry resolution of a known constant's identifier.
        TypeSpec.Builder codeBuilder = commonBuilder(TypeNames.API_IDENTIFIER)
                .addMethod(MethodSpec.methodBuilder("fromIdentifier")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(TypeNames.API_IDENTIFIER, "identifier")
                        .beginControlFlow("try")
                        .addStatement("return valueOf(identifier.path().toUpperCase($T.ENGLISH))", Locale.class)
                        .nextControlFlow("catch ($T ignore)", IllegalArgumentException.class)
                        .addStatement("return null")
                        .endControlFlow()
                        .returns(TypeNames.BLOCK_ID)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("getBlockType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.BLOCKS.get(this.getIdentifier())", TypeNames.REGISTRIES)
                        .returns(blockTypeClass)
                        .build()
                );
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder(PACKAGE_NAME, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(PACKAGE_PATH.resolve("BlockId.java"), javaFile.toString());
    }

    /**
     * Appends constants from the palette after sorting complete identifier
     * names. Java constant names use uppercase paths without namespaces, while
     * values retain complete identifiers. Repeated palette names are not
     * deduplicated before being passed to JavaPoet.
     */
    private static void addEnums(TypeSpec.Builder codeBuilder) {
        var sortedIdentifier = BLOCK_PALETTE_NBT.stream().map(block -> block.getString("name")).sorted(String::compareTo).map(Identifier::new).toList();
        for (var identifier : sortedIdentifier) {
            codeBuilder.addEnumConstant(
                    identifier.path().toUpperCase(Locale.ROOT),
                    TypeSpec.anonymousClassBuilder("$S", identifier.toString()).build()
            );
        }
    }

    /**
     * Creates an enum builder storing identifiers through the supplied type.
     * Callers add constants and module-specific methods before building it; the
     * generated constructor parses its string argument when enum constants are
     * initialized.
     */
    private static TypeSpec.Builder commonBuilder(ClassName identifierClass) {
        return TypeSpec.enumBuilder("BlockId")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClass, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(TypeNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(TypeNames.STRING, "identifier")
                        .addStatement("this.identifier = new $T(identifier)", identifierClass)
                        .build()
                );
    }
}
