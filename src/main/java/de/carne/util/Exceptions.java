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
package de.carne.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import de.carne.check.Nullable;

/**
 * Utility class providing {@link Exception} handling related functions.
 */
public final class Exceptions {

	/**
	 * Make an {@link Exception} unchecked by wrapping it into a {@link RuntimeException}.
	 *
	 * @param exception The {@link Exception} to wrap.
	 * @return The created {@link RuntimeException}.
	 */
	public static RuntimeException toRuntime(Throwable exception) {
		return (exception instanceof RuntimeException ? (RuntimeException) exception
				: new RuntimeException(exception.getLocalizedMessage(), exception));
	}

	/**
	 * Ignore an {@link Exception}.
	 * <p>
	 * This function logs the {@link Exception} using the trace log level and discards it.
	 *
	 * @param exception The {@link Exception} to ignore (may be {@code null}).
	 */
	public static void ignore(@Nullable Throwable exception) {
		if (exception != null) {
			// LOG.trace(exception, "Ignoring exception {0}", exception.getClass());
		}
	}

	/**
	 * Warn about an {@link Exception}.
	 * <p>
	 * This function logs the {@link Exception} using the warning log level and discards it.
	 *
	 * @param exception The {@link Exception} to warn about (may be {@code null}).
	 */
	public static void warn(@Nullable Throwable exception) {
		if (exception != null) {
			// LOG.trace(exception, "Ignoring exception {0}", exception.getClass());
		}
	}

	/**
	 * Get an {@link Exception}'s stack trace.
	 *
	 * @param exception The {@link Exception} to get the stack trace for.
	 * @return The {@link Exception}'s stack trace.
	 */
	public static String getStackTrace(Throwable exception) {
		String stackTrace = null;

		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
			exception.printStackTrace(pw);
			pw.flush();
			stackTrace = sw.toString();
		} catch (IOException e) {
			warn(e);
		}
		return (stackTrace != null ? stackTrace : "<no stack trace>");
	}

}
