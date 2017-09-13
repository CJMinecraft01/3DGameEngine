package cjminecraft.engine.managers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cjminecraft.engine.Engine;
import cjminecraft.engine.util.IManager;

public class LanguageManager implements IManager {

	private static LanguageManager instance = new LanguageManager();

	public static List<String> SUPPORTED_LANGUAGES = new ArrayList<String>();
	private HashMap<String, String> currentLanguage = new HashMap<String, String>();

	@Override
	public void preInit() throws Exception {
		SUPPORTED_LANGUAGES.add("en_UK");
		loadLanguage("en_UK");
	}

	@Override
	public void init() throws Exception {}

	@Override
	public void postInit() throws Exception {}

	@Override
	public void loop() throws Exception {}

	@Override
	public void cleanUp() throws Exception {
		this.currentLanguage.clear();
	}

	public void loadLanguage(String language) throws Exception {
		if (!SUPPORTED_LANGUAGES.contains(language))
			throw new Exception(String.format("The language %s is not supported!", language));
		this.currentLanguage.clear();
		for (String line : Files.readAllLines(Paths.get(Engine.getInstance().getOption("language_path") + "/" + language
				+ Engine.getInstance().getOption("language_file_type")))) {
			if(!line.startsWith("##") && !line.isEmpty()) {
				String[] details = line.split("=");
				if(details.length < 2)
					continue;
				System.out.println("Adding to current language: " + details[0] + " " + line.substring(details[0].length() + 1));
				this.currentLanguage.put(details[0], line.substring(details[0].length() + 1));
			}
		}
	}

	public String format(String translationKey, Object... args) {
		if (this.currentLanguage.containsKey(translationKey))
			return String.format(this.currentLanguage.get(translationKey), args);
		return translationKey;
	}

	public static LanguageManager getInstance() {
		return instance;
	}

}
