package br.com.javapress.domain.entity;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass	
public abstract class AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Temporal(value=TemporalType.DATE)
	private Calendar created;
	@Temporal(value=TemporalType.DATE)
	private Calendar updated;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getCreated() {
		return created;
	}
	public void setCreated(Calendar created) {
		this.created = created;
	}
	public Calendar getUpdated() {
		return updated;
	}
	public void setUpdated(Calendar updated) {
		this.updated = updated;
	}
}
