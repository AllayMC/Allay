/**
 * {@code org.allaymc.server.block.impl} hosts the shared block behavior base class
 * and compile-time generated delegation implementations for all block behavior
 * interfaces in {@code org.allaymc.api.block.interfaces}.
 */
@GeneratePackageImpls(
        apiPackage = "org.allaymc.api.block.interfaces",
        baseClass = "org.allaymc.server.block.impl.BlockBehaviorImpl"
)
package org.allaymc.server.block.impl;

import org.allaymc.api.component.annotation.GeneratePackageImpls;
