package com.hdc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 拒绝原因
 * @author zhao
 *
 */
@Entity
@Table(name = "REFUSE_REASON")
@DynamicUpdate(true)
@DynamicInsert(true)
public class RefuseReason extends BaseCommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7887276124847651105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 5, nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "TITLE", length = 1000)
	private String reason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TASK_INFO")
	@JsonIgnore
	private TaskInfo taskInfo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public TaskInfo getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}
	
}