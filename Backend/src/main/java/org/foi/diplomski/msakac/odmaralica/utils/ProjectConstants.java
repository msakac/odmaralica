package org.foi.diplomski.msakac.odmaralica.utils;

import net.bytebuddy.asm.Advice;

import java.util.Locale;


public final class ProjectConstants {

	// FIXME : Customize project constants for your application.ddd

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final Locale TURKISH_LOCALE = new Locale.Builder().setLanguage("tr").setRegion("TR").build();

	public static final Locale DEFAULT_LOCALE = Locale.getDefault();

	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}

}
