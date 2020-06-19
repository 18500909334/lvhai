package com.ucap.zfw.entity;
/**
 * 稿件表
 * @author
 *
 */
public class Manuscript {
	//稿件ID
	public String manuscriptId;
	//标题
	public String title;
	//标题
	public String subTitle;
	//关键字
	public String keyword;
	//用户id
	public String userId;
	//栏目id
	public String channelId;
	//站点
	public String websiteId;
	//URL
	public String publishTime;
	//
	public String createdTime;
	//正文
	public String content;
	//稿件url路径
	public String url;

	public String  urlRule;
	public String  relevanceIds;
	//false：暂存 true:提交发布
	public String  isPublish;
	public String  redirectUrl;
	public String  manuscriptType;
	public String  spiderSourceId;
	public String  spiderSourceName;
	public String  meta_author;
	public String  picmt_;
	public String  editmt_;
	public String  syh_;
	public String getManuscriptId() {
		return manuscriptId;
	}
	public void setManuscriptId(String manuscriptId) {
		this.manuscriptId = manuscriptId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(String websiteId) {
		this.websiteId = websiteId;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlRule() {
		return urlRule;
	}
	public void setUrlRule(String urlRule) {
		this.urlRule = urlRule;
	}
	public String getRelevanceIds() {
		return relevanceIds;
	}
	public void setRelevanceIds(String relevanceIds) {
		this.relevanceIds = relevanceIds;
	}
	public String getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getManuscriptType() {
		return manuscriptType;
	}
	public void setManuscriptType(String manuscriptType) {
		this.manuscriptType = manuscriptType;
	}
	public String getSpiderSourceId() {
		return spiderSourceId;
	}
	public void setSpiderSourceId(String spiderSourceId) {
		this.spiderSourceId = spiderSourceId;
	}
	public String getSpiderSourceName() {
		return spiderSourceName;
	}
	public void setSpiderSourceName(String spiderSourceName) {
		this.spiderSourceName = spiderSourceName;
	}

	public String getMeta_author() {
		return meta_author;
	}
	public void setMeta_author(String meta_author) {
		this.meta_author = meta_author;
	}
	public String getPicmt_() {
		return picmt_;
	}
	public void setPicmt_(String picmt_) {
		this.picmt_ = picmt_;
	}
	public String getEditmt_() {
		return editmt_;
	}
	public void setEditmt_(String editmt_) {
		this.editmt_ = editmt_;
	}
	public String getSyh_() {
		return syh_;
	}
	public void setSyh_(String syh_) {
		this.syh_ = syh_;
	}

	
}
