package org.allaymc.server.block.hash;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.charset.StandardCharsets;

/**
 * {@code MinecraftNamespaceComparator} supplies lexical and FNV ordering helpers
 * for block-identifier experiments.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
@Slf4j
public class MinecraftNamespaceComparator {
    // FNV1_64_INIT supplies the offset basis and FNV1_PRIME_64 supplies the
    // multiplier. The hash helper uses multiplication before XOR, implementing
    // FNV-1 ordering.
    /**
     * FNV-1 offset basis, 64-bit.
     */
    private static final long FNV1_64_INIT = 0xcbf29ce484222325L;
    /**
     * FNV-1 per-byte multiplication factor.
     */
    private static final long FNV1_PRIME_64 = 1099511628211L;


    /**
     * Compares namespaced identifiers by descending path, then descending
     * namespace. Both inputs must contain a colon; the namespace extraction can
     * throw when a path tie reaches an identifier without one.
     */
    public static int compare(String idA, String idB) {
        String childIdA = idA.substring(idA.indexOf(":") + 1);
        String childIdB = idB.substring(idB.indexOf(":") + 1);

        int childIdComparsion = childIdB.compareTo(childIdA);
        if (childIdComparsion != 0) {
            return childIdComparsion;
        }

        String namespaceA = idA.substring(0, idA.indexOf(":"));
        String namespaceB = idB.substring(0, idB.indexOf(":"));
        return namespaceB.compareTo(namespaceA);
    }

    // https://gist.github.com/SupremeMortal/5e09c8b0eb6b3a30439b317b875bc29c
    // Thank you Supreme
    /**
     * Compares the unsigned FNV-1 hashes of both identifiers' UTF-8 bytes. Equal
     * hashes compare equally even when the original identifiers differ, because
     * this helper does not apply a secondary lexical comparison.
     */
    public static int compareFNV(String idA, String idB) {
        byte[] bytes1 = idA.getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = idB.getBytes(StandardCharsets.UTF_8);
        long hash1 = fnv164(bytes1);
        long hash2 = fnv164(bytes2);
        return Long.compareUnsigned(hash1, hash2);
    }

    /**
     * Returns the 64-bit FNV-1 hash of the supplied unsigned byte values.
     */
    private static long fnv164(byte[] data) {
        long hash = FNV1_64_INIT;
        for (byte datum : data) {
            hash *= FNV1_PRIME_64;
            // Treat each UTF-8 byte as unsigned before mixing it into the hash.
            hash ^= (datum & 0xff);
        }

        return hash;
    }
}
