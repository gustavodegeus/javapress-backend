package br.com.javapress.domain.entity;

import java.util.Calendar;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity{

	@Temporal(value=TemporalType.DATE)
	private Calendar created;
	@Temporal(value=TemporalType.DATE)
	private Calendar updated;
	
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
