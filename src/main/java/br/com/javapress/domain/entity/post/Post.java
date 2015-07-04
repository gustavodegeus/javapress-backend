package br.com.javapress.domain.entity.post;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.javapress.domain.entity.AbstractEntity;
import br.com.javapress.domain.entity.user.Admin;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Post extends AbstractEntity {

	private String title;
	private String content;
	private Boolean published;
	@Temporal(TemporalType.DATE)
	private Calendar publishedDate;
	@ManyToOne
	private Category category;
	@OneToMany
	private Set<Tag> tags;
	@ManyToOne
	private Admin owner;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getPublished() {
		return published;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}
	public Calendar getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Calendar publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public Admin getOwner() {
		return owner;
	}
	public void setOwner(Admin owner) {
		this.owner = owner;
	}
}
