/*
 * Copyright (c) 2016-2017 Holger de Carne and contributors, All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.carne.util.logging;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Utility class providing {@link Log} related functions.
 */
public final class Logs {

	private static final Logger LOGGER = Logger.getLogger(Logs.class.getName());

	// Touch our custom level class to make sure the level names are registered
	static {
		LogLevel.LEVEL_NOTICE.getName();
	}

	private Logs() {
		// prevent instantiation
	}

	/**
	 * Standard name for default logging config.
	 */
	public static final String CONFIG_DEFAULT = "logging-default.properties";

	/**
	 * Standard name for verbose logging config.
	 */
	public static final String CONFIG_VERBOSE = "logging-verbose.properties";

	/**
	 * Standard name for debug logging config.
	 */
	public static final String CONFIG_DEBUG = "logging-debug.properties";

	/**
	 * Read and apply {@link LogManager} configuration.
	 *
	 * @param config The name of the configuration to read.
	 * @throws IOException if an I/O error occurs while reading the configuration.
	 */
	public static void readConfig(String config) throws IOException {
		try (InputStream configInputStream = openConfig(config)) {
			LogManager.getLogManager().readConfiguration(configInputStream);
		}
	}

	private static InputStream openConfig(String config) throws FileNotFoundException {
		InputStream configInputStream;

		try {
			configInputStream = new FileInputStream(config);
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.FINE, e, () -> "Unable to load logging config from file: " + config);
			configInputStream = null;
		}
		if (configInputStream == null) {
			configInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
			if (configInputStream == null) {
				LOGGER.warning(() -> "Unable to load logging config from resource: " + config);
				throw new FileNotFoundException("Unable to load logging config: " + config);
			}
		}
		return configInputStream;
	}

}
