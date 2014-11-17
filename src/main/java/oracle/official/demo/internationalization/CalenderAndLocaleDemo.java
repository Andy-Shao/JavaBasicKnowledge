package oracle.official.demo.internationalization;

import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class CalenderAndLocaleDemo {

	/**
	 * The DecimalFormatSymbols.getInstance(Locale) static method is now able to recognize 
	 * Locale.UNICODE_LOCALE_EXTENSION for the numbering system.
	 */
	@Test
	public void getInstance(){
	}
	
	/**
	 * The new method, LocaleServiceProvider.html#isSupportedLocale(Locale), returns true 
	 * if the specified locale is supported by the locale service provider.
	 */
	@Test
	public void isSupportedLocale(){
		LocaleServiceProvider localeServiceProvider = new LocaleServiceProvider() {
			
			@Override
			public Locale[] getAvailableLocales() {
				return new Locale[]{Locale.US};
			}
		};
		
		Assert.assertThat(localeServiceProvider.isSupportedLocale(Locale.US), Matchers.is(true));
		Assert.assertThat(localeServiceProvider.isSupportedLocale(Locale.CHINA), Matchers.is(false));
	}
	
	@Test
	public void extensios(){
		Locale us = Locale.US;
		Assert.assertThat(us.hasExtensions(), Matchers.is(false));
		Assert.assertThat(us.stripExtensions(), Matchers.is(Locale.US));
		
		Locale china = Locale.CHINA;
		Assert.assertThat(china.hasExtensions(), Matchers.is(false));
		Assert.assertThat(china.stripExtensions(), Matchers.is(Locale.CHINA));
	}
}
