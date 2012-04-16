package de.grundid.twiki.parser;

import org.junit.Test;

public class PatternTest {

	private String dataOld = "* [[Zabaglione]]\r\n" + "* [[Zabaione]]\r\n" + "* [[Zackenbarsch]]\r\n"
			+ "* [[Zampone]]\r\n" + "* [[Zander]]\r\n" + "* [[Zanderfilet]]\r\n" + "* [[Zapfen]]";

	private String data = "* [[Zabaglione]]   [[Zabaione]]";

	@Test
	public void testPattern() throws Exception {

		//		Pattern linkPattern = Pattern.compile("\\[\\[(.+?)\\]\\]", Pattern.DOTALL);
		//
		//		Matcher matcher = linkPattern.matcher(dataOld);
		//		while (matcher.find()) {
		//			System.out.println(matcher.group(1));
		//		}
		//
		//		assertTrue(matcher.matches());
		//		assertEquals(7, matcher.groupCount());
		//
		//		assertEquals("Zabaglione", matcher.group(1));
	}
}
