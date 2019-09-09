package Pet.utils;

import java.io.IOException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionListenerͳ�������
 */
public class ApplicationListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {;
        MyFileUtils.createFile(Constant.countFilePath);
        try {
            MyFileUtils.writeTxtFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }


}
