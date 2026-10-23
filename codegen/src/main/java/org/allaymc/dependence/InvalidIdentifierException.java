/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package org.allaymc.dependence;

import lombok.experimental.StandardException;

/**
 * {@code InvalidIdentifierException} reports rejected namespace or path
 * characters in generator-side identifiers. Strict constructors and validation
 * helpers propagate this runtime failure, while optional parsing helpers
 * convert it to an absent result. Lombok supplies constructors for diagnostic
 * messages and underlying causes.
 *
 * @author Cool_Loong
 */
@StandardException
public class InvalidIdentifierException extends RuntimeException {
}

