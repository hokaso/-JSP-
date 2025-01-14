package ssm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ssm.pojo.Mine.Customer;
import ssm.pojo.Mine.Dynamic;
import ssm.service.CustomerService;
import ssm.service.impl.CustomerServiceImpl;

@Controller
public class CustomerHandler {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/login.action")
	@ResponseBody
	public void login(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		
		resp.setContentType("text/html;charset=utf-8");
		InputStream is = req.getInputStream();
		byte[] b = new byte[1024];
		int len = is.read(b);
		String str = new String(b,0,len);
		System.out.println(str);
		
		Gson gson = new Gson();
		
		Customer customer = gson.fromJson(str, Customer.class);
		System.out.println(customer);
		customer = customerService.selectByUsernameAndPassword(customer);
		System.out.println(customer);
		
		if(customer!=null) {
			OutputStream os = resp.getOutputStream();
			String json = gson.toJson(customer);
			System.out.println(json);
			os.write(json.getBytes("UTF-8"));
			os.flush();
		}else {
			OutputStream os = resp.getOutputStream();
			os.write("".getBytes());
		}

		return;
		
	}
	@RequestMapping("/findFollow_Fan.action")
	@ResponseBody
	public void findFollow_Fan(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		String u_id = req.getParameter("u_id");
		String data = customerService.findFollow_Fan(u_id);
		OutputStream os = resp.getOutputStream();
		os.write(data.getBytes("UTF-8"));
		os.flush();
		return;
	}
	@RequestMapping("/findFollowDetail.action")
	@ResponseBody
	public void findFollowDetail(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		String u_id = req.getParameter("u_id");
		List<Customer> list = customerService.selectFollowDetail(u_id);
		Gson gson = new Gson();
		String data = gson.toJson(list);
		OutputStream os = resp.getOutputStream();
		//处理乱码问题
		os.write(data.getBytes("UTF-8"));
		os.flush();
		return ;
	}
	@RequestMapping("/findFanDetail.action")
	@ResponseBody
	public void findFanDetail(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		String u_id = req.getParameter("u_id");
		List<Customer> list = customerService.selectFanDetail(u_id);
		for(Customer c: list) {
			System.out.println(c);
		}
		Gson gson = new Gson();
		String data = gson.toJson(list);
		OutputStream os = resp.getOutputStream();
		//处理乱码问题
		os.write(data.getBytes("UTF-8"));
		os.flush();
		return ;
	}
	
	@RequestMapping("/updateCustomerPwd.action")
	@ResponseBody
	public String updateCustomerPwd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServletInputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			System.out.println("len=" + len);
			baos.write(buffer, 0, len);
		}
		String json = baos.toString();
		
		Customer customer = new Gson().fromJson(json, Customer.class);
		System.out.println("customer = " + customer);
		customerService.updateCustomerPwd(customer);
		
		String res = "success";
		try {
			OutputStream os = response.getOutputStream();
			os.write(res.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	@RequestMapping("/updateCustomerInfo.action")
	@ResponseBody
	public String updateCustomerInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServletInputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			System.out.println("len=" + len);
			baos.write(buffer, 0, len);
		}
		String json = baos.toString();
		
		Customer customer = new Gson().fromJson(json, Customer.class);
		System.out.println("customer = " + customer);
		customerService.updateCustomerInfo(customer);
		
		String res = "success";
		try {
			OutputStream os = response.getOutputStream();
			os.write(res.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	//后台管理
	@RequestMapping("/findallCustomer.action")
	public ModelAndView findallCustomer() {
		
		ModelAndView modelAndView = new ModelAndView();
		List<Customer> customerList = customerService.findallCustomer();
		modelAndView.addObject("customerList",customerList);
		for (Customer customer : customerList) {
			System.out.println(customer);
		}
		
		modelAndView.setViewName("jsps/customer.jsp");
		
		return modelAndView;
		
	}
	@RequestMapping("/editCustomerPre.action")
	public ModelAndView editCustomerPre(String u_id) {
		
		ModelAndView modelAndView = new ModelAndView();
		Customer customer = customerService.findCustomer(u_id);
		modelAndView.addObject(customer);
		System.out.println(customer);
		
		modelAndView.setViewName("jsps/editCustomer.jsp");
		
		return modelAndView;
		
	}
	@RequestMapping("/editCustomer.action")
	public ModelAndView editCustomer(Customer customer,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		System.out.println(customer);
		ModelAndView modelAndView = new ModelAndView();
		customerService.editCustomer(customer);
		modelAndView.setViewName("findallCustomer.action");
		
		return modelAndView;
		
	}
	@RequestMapping("/deleteCustomer.action")
	public ModelAndView deleteCustomer(String u_id,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		ModelAndView modelAndView = new ModelAndView();
		customerService.deleteCustomer(u_id);
		modelAndView.setViewName("findallCustomer.action");
		
		return modelAndView;
		
	}
	@RequestMapping("/addCustomer.action")
	public ModelAndView addCustomer(Customer customer,HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		ModelAndView modelAndView = new ModelAndView();
		customer.setU_id(System.currentTimeMillis()+"");
		customerService.addCustomer(customer);
		modelAndView.setViewName("findallCustomer.action");
		
		return modelAndView;
		
	}
	
	
	@RequestMapping("/SelectHighRisk.action")
	public ModelAndView SelectHighRisk() {
		
		ModelAndView modelAndView = new ModelAndView();
		List<Customer> customerList = customerService.SelectHighRisk();
		modelAndView.addObject("customerList",customerList);
		for (Customer customer : customerList) {
			System.out.println(customer);
		}
		
		modelAndView.setViewName("jsps/SelectHighRisk.jsp");
		
		return modelAndView;
		
	}

}
