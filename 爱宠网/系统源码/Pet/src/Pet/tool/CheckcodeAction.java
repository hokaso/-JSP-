package Pet.tool;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.http.HttpServletResponse;
 
import org.apache.struts2.ServletActionContext;
 
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
//��֤����
public class CheckcodeAction extends ActionSupport{
 
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private  String checkcode;
public void setCheckcode(String checkcode) {
    this.checkcode = checkcode;
}
 
/**
 * ��֤
 * @throws IOException 
 */
 
public String check() throws IOException {
    //ͼƬ·��
    String tip="images/cuo2.gif";
 
 
 
    //�ӷ�������ȡsession�е���֤��
    String checkcodeServer=(String) ActionContext.getContext().getSession().get("CHECKNUM");
 
    if(checkcode.equals(checkcodeServer)){
        tip="images/dui2.gif";
 
    }
    //��IO���ķ�ʽ��tip���������ajax�첽������
    HttpServletResponse  response=ServletActionContext.getResponse();
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter pw=response.getWriter();
    pw.write(tip);
    pw.flush();
    pw.close();
 
    return null;
 
 
}
}
