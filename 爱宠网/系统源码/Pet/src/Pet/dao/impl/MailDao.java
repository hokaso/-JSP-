package Pet.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import Pet.dao.Mail;
@Repository
@SuppressWarnings("all")
public class MailDao extends HibernateDaoSupport implements Mail{
	@Autowired
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void handelMail(String mail,String url) {
		InputStream is = this.getClass().getResourceAsStream("/mailInfo.properties");
    	Properties prop = new Properties();
    	try {
			prop.load(is);//������Դ�ļ�
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
        String msgText = "������������Ӽ����û���������ܵ�����ֶ����Ƶ���ַ����ִ��\n" + "http://localhost:8080/WEB7/Active_process.action"+url;
        String smtpHost = prop.get("smtpHost").toString();//SMTP��������
        String from = prop.get("mailName").toString();//�����˵�ַ
        String pwd = prop.get("pwd").toString();//����
        String to = mail;//�����˵�ַ
        // ����properties����
        Properties props = new Properties();
        //�����ʼ�������
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth","true");
        //ȡ��Ĭ�ϵ�Session
        Session session = Session.getDefaultInstance(props, null);

        // ����һ����Ϣ�������巢���˵�ַ�������˵�ַ
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));

            InternetAddress[] address = {new InternetAddress(to)};

            message.setRecipients(Message.RecipientType.TO, address);

            message.setSubject("����ע���û�");//�趨����

            message.setSentDate(new Date());//�趨����ʱ��

            message.setText(msgText);//��ǰ�涨���msgText�е������趨Ϊ�ʼ����ĵ�����

            message.saveChanges(); // implicit with send()
            //Э��
            Transport transport = session.getTransport("smtp");
            //�����˵�ַ���û���������
            transport.connect(smtpHost, from, pwd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }}
	}

