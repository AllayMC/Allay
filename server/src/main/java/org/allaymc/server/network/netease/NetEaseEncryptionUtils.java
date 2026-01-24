package org.allaymc.server.network.netease;

import org.cloudburstmc.protocol.bedrock.data.auth.CertificateChainPayload;
import org.cloudburstmc.protocol.bedrock.util.ChainValidationResult;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.JsonWebSignature;

import java.security.interfaces.ECPublicKey;
import java.util.List;
import java.util.Map;

/**
 * Utility class for NetEase (China) Minecraft client encryption and chain validation.
 * <p>
 * NetEase clients use a different public key for chain validation instead of Mojang's key.
 * This implementation is based on WaterdogPE's NeteaseEncryptionUtils.
 *
 * @author daoge_cmd
 */
public final class NetEaseEncryptionUtils {

    /// NetEase public key used to verify the login chain for NetEase clients.
    private static final ECPublicKey NETEASE_PUBLIC_KEY;
    private static final AlgorithmConstraints ALGORITHM_CONSTRAINTS;

    static {
        ALGORITHM_CONSTRAINTS = new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, "ES384");
        try {
            NETEASE_PUBLIC_KEY = EncryptionUtils.parseKey(
                    "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAEEsmU+IF/XeAF3yiqJ7Ko36btx6JtdB26wV9Eyw4AYR/nmesznkfXxwQ4B0NkSnGIZccbb2f3nFUYughKSoAcNHx+lQm8F9h9RwhrNgeN907z06LUA2AqWcwqasxyaU0E"
            );
        } catch (Exception e) {
            throw new AssertionError("Unable to initialize NetEase public key", e);
        }
    }

    private NetEaseEncryptionUtils() {
        // Utility class
    }

    /**
     * Validates the login chain for NetEase clients using NetEase's public key instead of Mojang's.
     * Based on WaterdogPE's NeteaseEncryptionUtils implementation.
     *
     * @param chainPayload the certificate chain payload from login packet
     * @return the chain validation result
     * @throws Exception if validation fails
     */
    public static ChainValidationResult validateChain(CertificateChainPayload chainPayload) throws Exception {
        List<String> chain = chainPayload.getChain();
        if (chain == null || chain.isEmpty()) {
            throw new IllegalStateException("Certificate chain is empty");
        }

        return switch (chain.size()) {
            case 1 -> {
                // Single certificate chain - return unverified payload
                JsonWebSignature identity = new JsonWebSignature();
                identity.setCompactSerialization(chain.get(0));
                yield new ChainValidationResult(false, identity.getUnverifiedPayload());
            }
            case 3 -> {
                // Three certificate chain - validate with NetEase public key
                ECPublicKey currentKey = null;
                Map<String, Object> parsedPayload = null;

                for (int i = 0; i < 3; i++) {
                    JsonWebSignature signature = new JsonWebSignature();
                    signature.setCompactSerialization(chain.get(i));
                    ECPublicKey expectedKey = EncryptionUtils.parseKey(signature.getHeader("x5u"));

                    if (currentKey == null) {
                        currentKey = expectedKey;
                    } else if (!currentKey.equals(expectedKey)) {
                        throw new IllegalStateException("Received broken chain");
                    }

                    signature.setAlgorithmConstraints(ALGORITHM_CONSTRAINTS);
                    signature.setKey(currentKey);
                    if (!signature.verifySignature()) {
                        throw new IllegalStateException("Chain signature doesn't match content");
                    }

                    // Check if the second entry is signed by NetEase
                    if (i == 1 && !currentKey.equals(NETEASE_PUBLIC_KEY)) {
                        throw new IllegalStateException("The chain isn't signed by NetEase!");
                    }

                    @SuppressWarnings("unchecked")
                    Map<String, Object> payload = JsonUtil.parseJson(signature.getUnverifiedPayload());
                    parsedPayload = payload;
                    String identityPublicKey = (String) payload.get("identityPublicKey");
                    currentKey = EncryptionUtils.parseKey(identityPublicKey);
                }

                yield new ChainValidationResult(true, parsedPayload);
            }
            default -> throw new IllegalStateException("Unexpected login chain length: " + chain.size());
        };
    }
}
