package cjminecraft.engine.loaders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Utility class to easily use a language file for text.
 * 
 * To load a language file, use {@link #loadLanguage(String)}. To format a
 * translation key, use {@link #format(String, Object...)}
 * 
 * @author CJMinecraft
 *
 */
public class LanguageLoader {

	/**
	 * The lift of supported languages
	 */
	public static final List<String> SUPPORTED_LANGUAGES = new ArrayList<String>();

	/**
	 * The location of the folder containing all of the language files
	 */
	public static String LANGUAGE_FILE_PATH = "assets/lang";

	/**
	 * The file type of the language files
	 */
	public static String LANGUAGE_FILE_TYPE = ".txt";

	/**
	 * The current language with all of the translation keys and values stored
	 */
	private static HashMap<String, String> currentLanguage = new HashMap<String, String>();

	/**
	 * Load a language using the {@link #LANGUAGE_FILE_PATH} + {@code language}
	 * + {@link #LANGUAGE_FILE_TYPE}
	 * 
	 * @param language
	 *            The language to load
	 * @throws Exception
	 *             Throws an exception when using
	 *             {@link Files#readAllLines(java.nio.file.Path, java.nio.charset.Charset)}
	 */
	public static void loadLanguage(String language) throws Exception {
		if (!SUPPORTED_LANGUAGES.contains(language))
			SUPPORTED_LANGUAGES.add(language);
		currentLanguage.clear();
		for (String line : Files.readAllLines(Paths.get(LANGUAGE_FILE_PATH + "/" + language + LANGUAGE_FILE_TYPE))) {
			if (!line.startsWith("##") && !line.isEmpty()) {
				String[] details = line.split("=");
				if (details.length < 2)
					continue;
				System.out.println("Adding to current language: key:" + details[0] + " value:"
						+ line.substring(details[0].length() + 1));
				currentLanguage.put(details[0], line.substring(details[0].length() + 1));
			}
		}
	}

	/**
	 * Format the given translation key using the language file
	 * 
	 * @param translationKey
	 *            The location of the key in the language file
	 * @param args
	 *            The arguments for use with
	 *            {@link String#format(String, Object...)}
	 * @return The localised value of the translation key
	 */
	public static String format(String translationKey, Object... args) {
		if (currentLanguage.containsKey(translationKey))
			return String.format(currentLanguage.get(translationKey), args);
		return translationKey;
	}

}
