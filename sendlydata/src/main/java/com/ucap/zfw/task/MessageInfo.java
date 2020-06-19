package com.ucap.zfw.task;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 推送数据
 * @author lzd
 *
 */
public class MessageInfo  implements RowMapper<MessageInfo> {

	private static final long serialVersionUID = 1L;
	public String serialNumber;
	public String batchNumber;
	public String timestamp;
	public String msgID;
	public String createTime;
	public String topic;
	public String provincialCode;
	public String cityCode;
	public String districtCode;
	public String provincialName;
	public String cityName;
	public String districtName;;
	public String address;
	public String userID;
	public String companyName;
	public String fullName;
	public String nickname;
	public String occupation;
	public String idCardPro;
	public String idCardCity;
	public String idCardDis;
	public String nation;
	public String cerType;
	public String cerCode;
	public String cerValidity;
	public String tel;
	public String telAttribution;
	public String email;
	public String msgIP;
	public String ipAddress;
	public String msgTitle;
	public String msgContent;
	public String msgClassify;
	public String msgType;
	public String isPublic;
	public String msgChannel;
	public String deptType;
	public String deptName;
	public String deptCode;
	public String area;
	public String census;
	public String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getProvincialCode() {
		return provincialCode;
	}
	public void setProvincialCode(String provincialCode) {
		this.provincialCode = provincialCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getProvincialName() {
		return provincialName;
	}
	public void setProvincialName(String provincialName) {
		this.provincialName = provincialName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIdCardPro() {
		return idCardPro;
	}
	public void setIdCardPro(String idCardPro) {
		this.idCardPro = idCardPro;
	}
	public String getIdCardCity() {
		return idCardCity;
	}
	public void setIdCardCity(String idCardCity) {
		this.idCardCity = idCardCity;
	}
	public String getIdCardDis() {
		return idCardDis;
	}
	public void setIdCardDis(String idCardDis) {
		this.idCardDis = idCardDis;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCerType() {
		return cerType;
	}
	public void setCerType(String cerType) {
		this.cerType = cerType;
	}
	public String getCerCode() {
		return cerCode;
	}
	public void setCerCode(String cerCode) {
		this.cerCode = cerCode;
	}
	public String getCerValidity() {
		return cerValidity;
	}
	public void setCerValidity(String cerValidity) {
		this.cerValidity = cerValidity;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTelAttribution() {
		return telAttribution;
	}
	public void setTelAttribution(String telAttribution) {
		this.telAttribution = telAttribution;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMsgIP() {
		return msgIP;
	}
	public void setMsgIP(String msgIP) {
		this.msgIP = msgIP;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgClassify() {
		return msgClassify;
	}
	public void setMsgClassify(String msgClassify) {
		this.msgClassify = msgClassify;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getMsgChannel() {
		return msgChannel;
	}
	public void setMsgChannel(String msgChannel) {
		this.msgChannel = msgChannel;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCensus() {
		return census;
	}
	public void setCensus(String census) {
		this.census = census;
	}
	@Override
	public MessageInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		MessageInfo data=new MessageInfo();
		data.setCreateTime(rs.getNString("createTime"));
		data.setArea(rs.getNString("area"));
		data.setFullName(rs.getNString("fullName"));
		data.setMsgContent(rs.getNString("msgContent"));
		data.setMsgID(rs.getNString("msgID"));
		data.setTel(rs.getNString("tel"));
		return data;
	}

}
