/**
 * 
 */
package com.ewcms.core.site.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <li>id:站点服务器编号
 * <li>path:发布路径
 * <li>ip:服务器IP
 * <li>port: 端口号
 * <li>user:用户　
 * <li>password:密码
 * </ul>
 * 
 * @author 周冬初
 */
@Entity
@Table(name = "site_siteserver")
@SequenceGenerator(name = "seq_site_siteserver", sequenceName = "seq_site_siteserver_id", allocationSize = 1)
public class SiteServer implements Serializable {
    @Id
    @GeneratedValue(generator = "seq_site_siteserver", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(length = 100)
    private String path;
    @Column(length = 20)
    private String ip;
    @Column(length = 5)
    private String port;
    @Column(length = 30)
    private String user;
    @Column(length = 20)
    private String password;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}