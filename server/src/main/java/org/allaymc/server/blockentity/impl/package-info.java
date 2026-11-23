/**
 * {@code org.allaymc.server.blockentity.impl} hosts the shared block-entity base
 * class and compile-time generated delegation implementations for all block-entity
 * interfaces in {@code org.allaymc.api.blockentity.interfaces}.
 */
@GeneratePackageImpls(
        apiPackage = "org.allaymc.api.blockentity.interfaces",
        baseClass = "org.allaymc.server.blockentity.impl.BlockEntityImpl"
)
package org.allaymc.server.blockentity.impl;

import org.allaymc.api.component.annotation.GeneratePackageImpls;
