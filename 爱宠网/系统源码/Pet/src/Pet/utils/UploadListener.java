package Pet.utils;

import org.apache.commons.fileupload.ProgressListener;

import Pet.entity.UploadStatus;

/**
 * Application Lifecycle Listener implementation class UploadListener
 *
 */

public class UploadListener implements ProgressListener{
	 
	private UploadStatus status;
    /**
     * Default constructor. 
     */
	public UploadListener(UploadStatus status) {
        this.status = status;
 
    }

	 public void update(long bytesRead, long contentLength, int items) {
	        // �ϴ��������ø÷���
	        status.setBytesRead(bytesRead); // �Ѷ�ȡ�����ݳ���
	        status.setContentLength(contentLength); // �ļ��ܳ���
	        status.setItems(items); // ���ڱ���ڼ����ļ�
	 
	    }
}
