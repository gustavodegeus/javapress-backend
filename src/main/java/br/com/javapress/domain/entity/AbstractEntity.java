package br.com.javapress.domain.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class AbstractEntity{

	@Temporal(value=TemporalType.DATE)
	@Column(updatable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm")
	private Calendar created;
	@Temporal(value=TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm")
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
	@PrePersist
	protected void onCreate() {
	    created = Calendar.getInstance();
	}

	@PreUpdate
	protected void onUpdate() {
	    updated = Calendar.getInstance();
	}
}
