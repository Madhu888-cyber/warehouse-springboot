package com.sathyatech.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="usr_tab")
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@Column(name="u_id")
	@GeneratedValue
	private Long userId;

	@Column(name="u_name")
	private String userName;

	@Column(name="u_email",unique=true)
	private String userEmail;

	@Column(name="u_pwd")
	private String password;

	@Column(name="u_active")
	private Integer active;
	
	@Column(name = "acct_exp", nullable = false)
	public Integer isAcctExp;
	
	@Column(name = "acct_lock", nullable = false)
	public Integer isAcctLock;
	
	@Column(name = "pwd_lock", nullable = false)
	public Integer isPwdExp;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usr_role_tab",
	joinColumns=@JoinColumn(name="u_id"),
	inverseJoinColumns=@JoinColumn(name="r_id"))
	private Set<Role> roles=new HashSet<Role>(0);
	
	@Column(name = "c_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date userCreatedOn;

	@Column(name = "m_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date lastLogin;
	
	

	public User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getIsAcctExp() {
		return isAcctExp;
	}

	public void setIsAcctExp(Integer isAcctExp) {
		this.isAcctExp = isAcctExp;
	}

	public Integer getIsAcctLock() {
		return isAcctLock;
	}

	public void setIsAcctLock(Integer isAcctLock) {
		this.isAcctLock = isAcctLock;
	}

	public Integer getIsPwdExp() {
		return isPwdExp;
	}

	public void setIsPwdExp(Integer isPwdExp) {
		this.isPwdExp = isPwdExp;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Date getUserCreatedOn() {
		return userCreatedOn;
	}

	public void setUserCreatedOn(Date userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public User(Long userId, String userName, String userEmail,
			String password, Integer active, Integer isAcctExp,
			Integer isAcctLock, Integer isPwdExp, Set<Role> roles,
			Date userCreatedOn, Date lastLogin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.active = active;
		this.isAcctExp = isAcctExp;
		this.isAcctLock = isAcctLock;
		this.isPwdExp = isPwdExp;
		this.roles = roles;
		this.userCreatedOn = userCreatedOn;
		this.lastLogin = lastLogin;
	}

	
}