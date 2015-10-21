package cn.com.hzzc.industrial.pro.entity;

/**
 * @todo 首页gridview元素
 * @author pang
 *
 */
public class HomeItem {

	public static String add_act = "0";
	public static String all_act = "1";
	public static String add_topic = "2";
	public static String all_topic = "3";
	// 类型标记
	private String flag;
	// 展示内容
	private String content;
	// 跳转参数
	private String param;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
