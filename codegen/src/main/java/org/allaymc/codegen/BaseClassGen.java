package org.allaymc.codegen;

import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.List;

/**
 * {@code BaseClassGen} provides shared code-generation helpers for Allay's
 * type catalogs and default type initializers. Interface and implementation
 * delegation classes are no longer pre-generated as empty skeletons; instead,
 * specialized interfaces live explicitly in {@code api} and their corresponding
 * server {@code *Impl} classes are generated at compile time by the
 * {@code :processor} annotation processor.
 *
 * @author daoge_cmd | IWareQ
 */
public abstract class BaseClassGen {

    /**
     * {@code DEFAULT_INIT_CHUNK_SIZE} limits how many guarded default
     * registrations appear in one generated helper method so neither the JVM
     * bytecode limit nor JaCoCo instrumentation hits oversized methods.
     */
    protected static final int DEFAULT_INIT_CHUNK_SIZE = 200;

    /**
     * Emits a public static {@code init()} entry point and chunked private helper
     * methods executing {@code statements} in order. When {@code statements} fits
     * within {@code chunkSize}, all statements are emitted directly in
     * {@code init()}.
     *
     * @param classBuilder target initializer type builder
     * @param statements   guarded registration blocks in deterministic order
     * @param chunkSize    maximum number of blocks per generated helper method
     */
    protected static void addChunkedInitMethods(TypeSpec.Builder classBuilder, List<CodeBlock> statements, int chunkSize) {
        var initMethod = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
        if (statements.size() <= chunkSize) {
            for (var statement : statements) {
                initMethod.addCode(statement);
            }
            classBuilder.addMethod(initMethod.build());
            return;
        }

        int partIndex = 0;
        for (int start = 0; start < statements.size(); start += chunkSize) {
            int end = Math.min(start + chunkSize, statements.size());
            var partName = "initPart" + partIndex++;
            initMethod.addStatement("$N()", partName);

            var partMethod = MethodSpec.methodBuilder(partName)
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC);
            for (int i = start; i < end; i++) {
                partMethod.addCode(statements.get(i));
            }
            classBuilder.addMethod(partMethod.build());
        }
        classBuilder.addMethod(initMethod.build());
    }
}
