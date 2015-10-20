package cn.com.hzzc.industrial.pro.entity;

/**
 * @todo 活动entity
 * @author pang
 *
 */
public class ActivityEntity {

	private String id;
	private String title;
	private String imgId;
	private String introduction;
	private String status;
	private String readNum;
	private String type;
	private String createDate;
	private String beginDate;
	private String endDate;
	// 具体类型下活动的ID
	private String detailId;
	private String societyId;

	public boolean isIfNeedSelfSociety() {
		return ifNeedSelfSociety;
	}

	public void setIfNeedSelfSociety(boolean ifNeedSelfSociety) {
		this.ifNeedSelfSociety = ifNeedSelfSociety;
	}

	private boolean ifNeedSelfSociety;

	public String getSocietyId() {
		return societyId;
	}

	public void setSocietyId(String societyId) {
		this.societyId = societyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

}
