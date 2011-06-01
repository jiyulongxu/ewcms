/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

package com.ewcms.content.document.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 发布文章
 * 
 * <ul>
 * <li>id:编号</li>
 * <li>title:标题</li>
 * <li>titleStyle:标题样式</li>
 * <li>shortTitle:短标题</li>
 * <li>shortTitleStyle:短标题样式</li>
 * <li>subTitle:副标题</li>
 * <li>subTitlStyle:副标题样式</li>
 * <li>author:作者</li>
 * <li>owner:创建者</li>
 * <li>origin:来源</li>
 * <li>keyword:关键字</li>
 * <li>tag:标签</li>
 * <li>summary:摘要</li>
 * <li>contents:内容集合对象</li>
 * <li>image:文章图片</li>
 * <li>contentHistories:历史内容集合对象</li>
 * <li>topFlag:新闻置顶</li>
 * <li>commentFlag:允许评论</li>
 * <li>imageFlag:图片</li>
 * <li>videoFlag:视频</li>
 * <li>annexFlag:附件</li>
 * <li>hotFlag:热点</li>
 * <li>recommendFlag:推荐</li>
 * <li>copyoutFlag:复制源</li>
 * <li>copyFlag:复制</li>
 * <li>type:文章类型</li>
 * <li>linkAddr:链接地址</li>
 * <li>eauthor:审核人</li>
 * <li>eauthorReal:审核人实名</li>
 * <li>published:发布时间</li>
 * <li>modified:修改时间</li>
 * <li>status:状态</li>
 * <li>url:链接地址</li>
 * <li>channel:频道对象</li>
 * <li>deleteFlag:删除标志</li>
 * <li>deleteAuthor:删除人</li>
 * <li>deleteTime:删除时间</li>
 * <li>refChannel:所引用的频道对象集合</li>
 * <li>relatedArticles:相关文章</li>
 * <li>recommendArticles:推荐文章</li>
 * <li>restoreAuthor:恢复人</li>
 * </ul>
 * 
 * @author 吴智俊
 */
@Entity
@Table(name = "doc_article")
@SequenceGenerator(name = "seq_doc_article", sequenceName = "seq_doc_article_id", allocationSize = 1)
public class Article implements Serializable {

	private static final long serialVersionUID = -5809802652492615658L;

	@Id
    @GeneratedValue(generator = "seq_doc_article",strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	@Column(name = "title_style")
	private String titleStyle;
	@Column(name = "short_title", length = 50)
	private String shortTitle;
	@Column(name = "short_title_style")
	private String shortTitleStyle;
	@Column(name = "sub_title", length = 100)
	private String subTitle;
	@Column(name = "sub_title_style")
	private String subTitleStyle;
	@Column(name = "author")
	private String author;
	@Column(name = "origin")
	private String origin;
	@Column(name = "key_word", columnDefinition = "text")
	private String keyword;
	@Column(name = "tag")
	private String tag;
	@Column(name = "summary", columnDefinition = "text")
	private String summary;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Content.class, orphanRemoval = true)
	@JoinColumn(name = "article_id")
	@OrderBy(value = "page asc")
	private List<Content> contents;
	@Column(name = "image")
	private String image;
	@Column(name = "top_flag")
	private Boolean topFlag;
	@Column(name = "comment_flag")
	private Boolean commentFlag;
	@Column(name = "copy_flag")
	private Boolean copyFlag;
	@Column(name = "copyout_flag")
	private Boolean copyoutFlag;
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ArticleType type;
	@Column(name = "link_addr", columnDefinition = "text")
	private String linkAddr;
	@Column(name = "eauthor")
	private String eauthor;
	@Column(name = "owner")
	private String owner;
	@Column(name = "eauthor_real")
	private String eauthorReal;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "published")
	private Date published;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified", nullable = false)
	private Date modified;
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ArticleStatus status;
	@Column(name = "url", columnDefinition = "text")
	private String url;
	@Column(name = "delete_flag")
	private Boolean deleteFlag;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_time")
	private Date deleteTime;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Related.class)
	@JoinColumn(name = "article_id")
	@OrderBy("sort")
	private List<Related> relateds;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Recommend.class)
	@JoinColumn(name="article_id")
	@OrderBy("sort")
	private List<Recommend> recommends;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", nullable = false)
	private Date createTime;
	@Column(name = "delete_author")
	private String deleteAuthor;
	@Column(name = "restore_author")
	private String restoreAuthor;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, targetEntity = ArticleCategory.class)
	@JoinTable(name = "doc_article_articlecategory", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "articlecategory_id", referencedColumnName = "id"))
	@OrderBy(value = "id")
	private List<ArticleCategory> categories;
	
	public Article() {
		topFlag = false;
		commentFlag = false;
		copyFlag = false;
		copyoutFlag = false;
		type = ArticleType.GENERAL;
		status = ArticleStatus.DRAFT;
		createTime = new Date(Calendar.getInstance().getTime().getTime());
		deleteFlag = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getShortTitleStyle() {
		return shortTitleStyle;
	}

	public void setShortTitleStyle(String shortTitleStyle) {
		this.shortTitleStyle = shortTitleStyle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSubTitleStyle() {
		return subTitleStyle;
	}

	public void setSubTitleStyle(String subTitleStyle) {
		this.subTitleStyle = subTitleStyle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@JsonIgnore
	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(Boolean topFlag) {
		this.topFlag = topFlag;
	}

	public Boolean getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(Boolean commentFlag) {
		this.commentFlag = commentFlag;
	}
	
	public Boolean getCopyFlag() {
		return copyFlag;
	}

	public void setCopyFlag(Boolean copyFlag) {
		this.copyFlag = copyFlag;
	}

	public Boolean getCopyoutFlag() {
		return copyoutFlag;
	}

	public void setCopyoutFlag(Boolean copyoutFlag) {
		this.copyoutFlag = copyoutFlag;
	}

	public ArticleType getType() {
		return type;
	}
	
	public String getTypeDescription(){
		if (type != null){
			return type.getDescription();
		}else{
			return ArticleType.GENERAL.getDescription();
		}
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getEauthor() {
		return eauthor;
	}

	public void setEauthor(String eauthor) {
		this.eauthor = eauthor;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getEauthorReal() {
		return eauthorReal;
	}

	public void setEauthorReal(String eauthorReal) {
		this.eauthorReal = eauthorReal;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getStatusDescription(){
		return status.getDescription();
	}
	
	public ArticleStatus getStatus() {
		return status;
	}

	public void setStatus(ArticleStatus status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	@JsonIgnore
	public List<Related> getRelateds() {
		return relateds;
	}

	public void setRelateds(List<Related> relateds) {
		this.relateds = relateds;
	}

	@JsonIgnore
	public List<Recommend> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<Recommend> recommends) {
		this.recommends = recommends;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeleteAuthor() {
		return deleteAuthor;
	}

	public void setDeleteAuthor(String deleteAuthor) {
		this.deleteAuthor = deleteAuthor;
	}

	public String getRestoreAuthor() {
		return restoreAuthor;
	}

	public void setRestoreAuthor(String restoreAuthor) {
		this.restoreAuthor = restoreAuthor;
	}

	public List<ArticleCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ArticleCategory> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
