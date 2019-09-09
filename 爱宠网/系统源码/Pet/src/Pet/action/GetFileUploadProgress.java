package Pet.action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import Pet.entity.UploadStatus;
import net.sf.json.JSONObject;

@Controller
public class GetFileUploadProgress implements ServletRequestAware,ServletResponseAware{
			
	
	private File resource;
    private String resourceFileName;
    private String resourceContentType;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public File getResource() {
		return resource;
	}
	public void setResource(File resource) {
		this.resource = resource;
	}
	public String getResourceFileName() {
		return resourceFileName;
	}
	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}
	public String getResourceContentType() {
		return resourceContentType;
	}
	public void setResourceContentType(String resourceContentType) {
		this.resourceContentType = resourceContentType;
	}
	/**
     * ��ȡ�ļ��ϴ��Ľ���
     */
    public void getFileUploadProgress() throws Exception{
    	
    	HttpSession session=request.getSession();
    	
        UploadStatus status =(UploadStatus) session.getAttribute("uploadStatus");
 
        if (status == null) {
            System.out.println("uploadStatus is null");
            return;
        }
 
        long startTime = status.getStartTime(); //�ϴ���ʼʱ��
        long currentTime = System.currentTimeMillis(); //����ʱ��
        long time = (currentTime - startTime) / 1000 + 1; //�Ѵ����ʱ�� ��λ:s
 
        //�����ٶȵ�λ:byte/s
        double velocity = ((double) status.getBytesRead()) / (double) time;
        //������ʱ��
        double totalTime = status.getContentLength();
        //����ʣ��ʱ��
        double timeLeft = totalTime - time;
        //�Ѿ���ɵİٷֱ�
        int percent = (int) (100 * (double) status.getBytesRead() / (double) status.getContentLength());
        //�Ѿ��������λ:m
        double length = ((double) status.getBytesRead()) / 1024 / 1024;
        //�ܳ��� ��λ:m
        double totalLength = ((double) status.getContentLength()) / 1024 / 1024;
 
        System.out.println("bytesRead:"+status.getBytesRead());
        System.out.println("ContentLength:"+status.getContentLength());
 
//        System.out.println("percent:"+percent);
//        System.out.println("length:"+length);
//        System.out.println("totalLength:"+totalLength);
//        System.out.println("velocity:"+velocity);
//        System.out.println("time:"+time);
//        System.out.println("totalTime:"+totalTime);
//        System.out.println("timeLeft:"+timeLeft);
//        System.out.println("fileNumber:"+status.getItems());
 
        JSONObject jsonObject = new JSONObject();
 
 
        jsonObject.put("percent", String.valueOf(percent));
        jsonObject.put("length", String.valueOf(length));
        jsonObject.put("totalLength", String.valueOf(totalLength));
        jsonObject.put("velocity", String.valueOf(velocity));
        jsonObject.put("time", String.valueOf(time));
        jsonObject.put("totalTime", String.valueOf(totalTime));
        jsonObject.put("timeLeft", String.valueOf(timeLeft));
        jsonObject.put("fileNumber", String.valueOf(status.getItems()));
 
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonObject.toString());
    }
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

}
