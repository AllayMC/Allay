package org.allaymc.codegen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * {@code CodeGenConstants} supplies the resource location and formatting
 * settings shared by generators. Resource paths are relative to the process
 * working directory, so generator entry points expect the repository root. JSON
 * output uses pretty printing, while JavaPoet receives the shared indentation
 * string.
 *
 * @author daoge_cmd
 */
public interface CodeGenConstants {
    /**
     * {@code DATA_PATH} locates generator input resources relative to the
     * repository root used as the process working directory.
     */
    String DATA_PATH = "data/resources/";
    /**
     * {@code GSON} parses generator inputs and formats emitted JSON with
     * indentation using the same shared {@code Gson} configuration.
     */
    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    /**
     * {@code INDENT} sets four-space indentation for JavaPoet builders so
     * generated source follows the repository's Java layout.
     */
    String INDENT = "    ";
}
