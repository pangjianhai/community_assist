package cn.com.hzzc.industrial.pro.entity;

/**
 * @todo ��������entity
 * @author pang
 *
 */
public class TaskTypeEntity {
	public static final int TYPE_MINE = 0;// �ҵ�
	public static final int TYPE_ASSIGNE = 1;// �ҷ����

	public static final int PROGRESS_DONE = 0;// �Ѿ���ɵ�
	public static final int PROGRESS_WILL_DO = 1;// ��Ҫ����
	public static final int PROGRESS_RUNNING = 2;// ��������
	public static final int PROGRESS_NOT_DONE = 4;// ���ڵ�

	/**
	 * ����
	 */
	private int type;

	/**
	 * ���
	 */
	private int progress;
	/**
	 * ����
	 */
	private String title;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
