package de.grundid.twiki.parser;

import java.util.HashSet;
import java.util.Set;

public class WiktionaryData {

	public static String[] partsOfSpeech = { "Substantiv", "KonjugierteForm", "DeklinierteForm", "PartizipI",
			"Adjektiv", "Verb", "Abkürzung", "Redewendung", "Adverb", "PartizipII", "Wortverbindung",
			"ErweiterterInfinitiv", "Numerale", "Toponym", "Nachname", "Personalpronomen" };

	public static String[] partsOfSpeechAll = { "Substantiv", "KonjugierteForm", "DeklinierteForm", "PartizipI",
			"Adjektiv", "Verb", "Abkürzung", "Redewendung", "Adverb", "PartizipII", "Wortverbindung",
			"ErweiterterInfinitiv", "Numerale", "Toponym", "Nachname", "Personalpronomen", "Interjektion",
			"Präposition", "Sprichwort", "Konjunktion", "GebundenesLexem", "Präfix", "Gerundium", "Eigenname",
			"Suffix", "Vorname", "Ortsnamen-Grundwort", "Hiragana", "Indefinitpronomen", "Grußformel", "Pronomen",
			"Demonstrativpronomen", "Zahlzeichen", "Possessivpronomen", "Artikel", "Partikel", "Buchstabe",
			"Interrogativpronomen", "Kontraktion", "Subjunktion", "Symbol", "Reflexivpronomen", "Komparativ",
			"Onomatopoetikum", "Umschrift", "Hilfsverb", "Interrogativadverb", "Relativpronomen", "Zahl",
			"Gradpartikel", "Pronominaladverb", "Merkspruch", "Affix", "Antwortpartikel", "Konjunktionaladverb",
			"Superlativ", "Modalpartikel", "Negationspartikel", "Postposition", "Präfixoid",
			"ReflexivesPersonalpronomen", "Satzzeichen", "Fokuspartikel", "Hanzi", "Katakana",
			"KontraktionausPräpositionundArtikel", "ReflexivesPossessivpronomen", "Reziprokpronomen",
			"Singularetantum", "Suffixoid", "Zahladjektiv", "Zahlklassifikator" };

	public static Set<String> structurePrefixes = new HashSet<String>();

	public static Set<String> ignorePrefixes = new HashSet<String>();
	static {
		ignorePrefixes.add("MediaWiki:");
		ignorePrefixes.add("Wiktionary:");
		ignorePrefixes.add("Datei:");
		ignorePrefixes.add("Hilfe:");
		ignorePrefixes.add("Vorlage:");

		structurePrefixes.add("Kategorie:");
		structurePrefixes.add("Thesaurus:");
		structurePrefixes.add("Verzeichnis:");

		ignorePrefixes.add("Appendix:");
		ignorePrefixes.add("Category:");
		ignorePrefixes.add("Help:");
		ignorePrefixes.add("Template:");
		ignorePrefixes.add("Rhymes:");
		ignorePrefixes.add("Rhmyes:");
		ignorePrefixes.add("Sign gloss:");
		ignorePrefixes.add("Summary:");
		ignorePrefixes.add("Thread:");
		ignorePrefixes.add("Transwiki:");
		ignorePrefixes.add("Unsupported titles/");
		ignorePrefixes.add("Wikisaurus:");
		ignorePrefixes.add("Citations:");
		ignorePrefixes.add("Concordance:");
		ignorePrefixes.add("File:");
		ignorePrefixes.add("Glossary:");
		ignorePrefixes.add("Index:");
		ignorePrefixes.add("Wiktionary talk:");
	}

	public static boolean isPrefixed(String title) {
		return getPrefixIfAny(title) != null;
	}

	public static String getPrefixIfAny(String title) {
		for (String prefix : WiktionaryData.ignorePrefixes) {
			if (title.startsWith(prefix)) {
				return prefix;
			}
		}
		return null;
	}

}
