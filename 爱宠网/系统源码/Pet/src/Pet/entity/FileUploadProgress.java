package Pet.entity;

public class FileUploadProgress {
		
	 private long startTime = System.currentTimeMillis();//��ʼʱ��
	    private long totalLength=1;//�ļ��ϴ����ܳ���
	    private long currentLength=0;//��ǰ�ļ��ϴ��ĳ���
	    private boolean flag;//�Ƿ��ϴ����

	    public long getTotalLength() {
	        return totalLength;
	    }

	    public void setTotalLength(long totalLength) {
	        this.totalLength = totalLength;
	    }

	    public long getCurrentLength() {
	        return currentLength;
	    }

	    public void setCurrentLength(long currentLength) {
	        this.currentLength = currentLength;
	    }

	    public boolean isFlag() {
	        return flag;
	    }

	    public void setFlag(boolean flag) {
	        this.flag = flag;
	    }

	    public long getStartTime() {
	        return startTime;
	    }

	    public void setStartTime(long startTime) {
	        this.startTime = startTime;
	    }

	    public FileUploadProgress(){
	        super();
	    }
}
