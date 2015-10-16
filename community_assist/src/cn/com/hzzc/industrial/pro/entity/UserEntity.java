package cn.com.hzzc.industrial.pro.entity;

/**
 * @todo 普通用户
 * @author pang
 *
 */
public class UserEntity {

	/**
	 * 数据库uuid
	 */
	private String uuid;

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户展示的汉子名字
	 */
	private String userName;
	/**
	 * 标签
	 */
	private String tags;
	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 图像
	 */
	private String img;

	/**
	 * 生日
	 */
	private String birthday;

	/**
	 * Email
	 */
	private String email;

	/**
	 * 个人签名
	 */
	private String sentence;

	/**
	 * 专门给第一次登陆的用户在推荐用户的时候用到的字段
	 */
	private boolean ifAddedInTopList;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public boolean isIfAddedInTopList() {
		return ifAddedInTopList;
	}

	public void setIfAddedInTopList(boolean ifAddedInTopList) {
		this.ifAddedInTopList = ifAddedInTopList;
	}

}
