/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package org.allaymc.api.utils.identifier;

import lombok.experimental.StandardException;

/**
 * {@code InvalidIdentifierException} represents rejection of identifier characters during
 * namespace or path validation. The identifier constructor and assertion helper use this exception
 * for invalid characters, while {@code null} inputs generally fail through ordinary {@code null} dereferences. The
 * generated
 * constructors follow the standard runtime-exception message and cause forms.
 *
 * @author Cool_Loong
 */
@StandardException
public class InvalidIdentifierException extends RuntimeException {
}

