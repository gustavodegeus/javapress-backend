package br.com.javapress.domain.entity.post;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.javapress.application.validation.annotation.AssertIdNotNullForUpdate;
import br.com.javapress.application.validation.groups.PreUpdate;
import br.com.javapress.domain.entity.AbstractEntity;
import br.com.javapress.domain.entity.recipe.Recipe;
import br.com.javapress.domain.entity.user.Admin;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@SequenceGenerator(name = "post_gen", sequenceName = "BLOG_POST_SEQUENCE", allocationSize=1)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,property = "type")  
@JsonSubTypes({@Type(value = BlogPost.class, name = "POST"), @Type(value = Recipe.class, name = "RECIPE")})  
public class Post extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
	@Column(name="post_id")
	@AssertIdNotNullForUpdate(groups={PreUpdate.class})
	private Long id;
	private String title;
	@Column(columnDefinition="TEXT")
	private String content;
	private Boolean published;
	@Temporal(TemporalType.DATE)
	private Calendar publishedDate;
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="post_tags", joinColumns={@JoinColumn(name="post_id")}, inverseJoinColumns={@JoinColumn(name="tag_id")})
	@Cascade(CascadeType.SAVE_UPDATE)
	private Set<Tag> tags;
	@ManyToOne
	private Admin owner;
	@ManyToOne
	private Category category;
	@Transient
	private List<Comment> comments;

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Post(){
		this.tags = new HashSet<Tag>();
	}
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
	public void addTag(Tag tag){
		this.tags.add(tag);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
