package Pet.entity;


public class UploadStatus {
    private long bytesRead;//�Ѿ��ϴ����ֽ�������λ���ֽ�
    private long contentLength;//�����ļ��ܳ��ȣ���λ���ֽ�
    private int items;//�����ϴ��ĵڼ����ļ�
    private long startTime = System.currentTimeMillis();//��ʼ�ϴ���ʱ��,���ڼ����ϴ��ٶ�
 
    public long getBytesRead() {
        return bytesRead;
    }
 
    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }
 
    public long getContentLength() {
        return contentLength;
    }
 
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
 
    public int getItems() {
        return items;
    }
 
    public void setItems(int items) {
        this.items = items;
    }
 
    public long getStartTime() {
        return startTime;
    }
 
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}