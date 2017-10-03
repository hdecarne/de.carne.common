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
package de.carne.test.util.logging;

import org.junit.Assert;
import org.junit.Test;

import de.carne.util.logging.Log;

/**
 * Test {@linkplain Log} class.
 */
public class LogTest {

	/**
	 * Test logger names.
	 */
	@Test
	public void testLogNames() {
		Log defaultLog = new Log();

		Assert.assertEquals(getClass().getName(), defaultLog.logger().getName());

		Log customLog = new Log(Object.class);

		Assert.assertEquals(Object.class.getName(), customLog.logger().getName());
	}

}
