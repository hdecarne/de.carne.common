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
package de.carne.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Utility class providing I/O related functions.
 */
public final class IOUtil {

	private IOUtil() {
		// prevent instantiation
	}

	private static final int STREAM_IO_BUFFER_SIZE = 4096;

	/**
	 * Copy all bytes from one stream to another.
	 *
	 * @param dst The {@link OutputStream} to copy to.
	 * @param src The {@link InputStream} to copy from.
	 * @return The number of copied bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static int copyStream(OutputStream dst, InputStream src) throws IOException {
		return copyStreamStandard(dst, src);
	}

	private static int copyStreamStandard(OutputStream dst, InputStream src) throws IOException {
		byte[] buffer = new byte[STREAM_IO_BUFFER_SIZE];
		int copied = 0;
		int read;

		while ((read = src.read(buffer)) > 0) {
			dst.write(buffer, 0, read);
			copied += read;
		}
		return copied;
	}

	/**
	 * Copy all bytes from an {@link InputStream} to a {@link File}.
	 *
	 * @param dst The {@link File} to copy to.
	 * @param src The {@link InputStream} to copy from.
	 * @return The number of copied bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static int copyStream(File dst, InputStream src) throws IOException {
		int copied;

		try (FileOutputStream dstStream = new FileOutputStream(dst)) {
			copied = copyStream(dstStream, src);
		}
		return copied;
	}

	/**
	 * Copy all bytes from an {@link File} to an {@link OutputStream}.
	 *
	 * @param dst The {@link OutputStream} to copy to.
	 * @param src The {@link File} to copy from.
	 * @return The number of copied bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static int copyFile(OutputStream dst, File src) throws IOException {
		int copied;

		try (FileInputStream srcStream = new FileInputStream(src)) {
			copied = copyStream(dst, srcStream);
		}
		return copied;
	}

	/**
	 * Copy all bytes from an {@link URL} to an {@link OutputStream}.
	 *
	 * @param dst The {@link OutputStream} to copy to.
	 * @param src The {@link URL} to copy from.
	 * @return The number of copied bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static int copyUrl(OutputStream dst, URL src) throws IOException {
		int copied;

		try (InputStream srcStream = src.openStream()) {
			copied = copyStream(dst, srcStream);
		}
		return copied;
	}

	/**
	 * Copy all bytes from an {@link URL} to a {@link File}.
	 *
	 * @param dst The {@link File} to copy to.
	 * @param src The {@link URL} to copy from.
	 * @return The number of copied bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static int copyUrl(File dst, URL src) throws IOException {
		int copied;

		try (InputStream srcStream = src.openStream()) {
			copied = copyStream(dst, srcStream);
		}
		return copied;
	}

}