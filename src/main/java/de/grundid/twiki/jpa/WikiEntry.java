package de.grundid.twiki.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WikiEntry {

	@Id
	@GeneratedValue
	private Integer wikiEntryId;
	private String source;
	private String title;
	private String category;
	private String entry;

	public Integer getWikiEntryId() {
		return wikiEntryId;
	}

	public void setWikiEntryId(Integer wikiEntryId) {
		this.wikiEntryId = wikiEntryId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

}
