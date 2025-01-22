package org.allaymc.server.extension;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.server.Allay;

import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class ExtensionManager {

    private static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

    private final Path source;
    private final List<Extension> extensionInstances = new ArrayList<>();

    public ExtensionManager(Path source) {
        this.source = source;
    }

    @SneakyThrows
    public void loadExtensions(String[] args) {
        if (!Files.exists(source)) {
            Files.createDirectory(source);
        }

        try (var stream = Files.walk(source)) {
            stream.filter(path -> PATH_MATCHER.matches(path) && Files.isRegularFile(path))
                    .forEach(extensionPath -> loadExtension(extensionPath, args));
        }

        this.extensionInstances.forEach(extension -> extension.main(args));
    }

    private void loadExtension(Path extensionPath, String[] args) {
        log.info(I18n.get().tr(TrKeys.A_EXTENSION_LOADING, extensionPath));
        Allay.EXTRA_RESOURCE_CLASS_LOADER.addJar(extensionPath);

        // Try to load the main class of the extension if it exists
        var mainClass = findMainClass(extensionPath);
        if (mainClass == null) {
            // Main class can be null
            return;
        }

        if (!Extension.class.isAssignableFrom(mainClass)) {
            throw new ExtensionException(I18n.get().tr(TrKeys.A_EXTENSION_MAINCLASS_TYPEINVALID, mainClass.getName()));
        }

        Extension extensionInstance;
        try {
            extensionInstance = (Extension) mainClass.getConstructor().newInstance();
            extensionInstance.main(args);
        } catch (Exception e) {
            throw new ExtensionException(I18n.get().tr(TrKeys.A_EXTENSION_CONSTRUCT_INSTANCE_ERROR, extensionPath, e));
        }

        extensionInstances.add(extensionInstance);
    }

    @SneakyThrows
    private Class<?> findMainClass(Path extensionPath) {
        var jarFileSystem = FileSystems.newFileSystem(extensionPath);
        try {
            var extensionDescriptorPath = jarFileSystem.getPath("extension.json");
            if (!Files.exists(extensionDescriptorPath)) {
                return null;
            }
            var entrance = (String) JSONUtils.fromMap(Files.readString(extensionDescriptorPath)).get("entrance");
            if (entrance == null) {
                throw new ExtensionException("Missing extrance field in extension.json!");
            }
            return Allay.EXTRA_RESOURCE_CLASS_LOADER.loadClass(entrance);
        } catch (ClassNotFoundException e1) {
            throw new ExtensionException(I18n.get().tr(TrKeys.A_EXTENSION_ENTRANCE_MISSING, extensionPath));
        } catch (MalformedURLException e2) {
            throw new ExtensionException("Invalid URL: " + extensionPath.toUri());
        }
    }
}
