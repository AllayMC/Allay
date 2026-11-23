/**
 * {@code org.allaymc.server.item.impl} hosts the shared item stack base class
 * and compile-time generated delegation implementations for all item stack
 * interfaces in {@code org.allaymc.api.item.interfaces}.
 */
@GeneratePackageImpls(
        apiPackage = "org.allaymc.api.item.interfaces",
        baseClass = "org.allaymc.server.item.impl.ItemStackImpl"
)
package org.allaymc.server.item.impl;

import org.allaymc.api.component.annotation.GeneratePackageImpls;
