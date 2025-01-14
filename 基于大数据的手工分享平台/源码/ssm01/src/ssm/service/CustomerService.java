package ssm.service;

import java.util.List;

import ssm.pojo.Mine.Customer;
import ssm.pojo.Mine.Dynamic;

public interface CustomerService {
	
	public Customer selectByUsernameAndPassword(Customer customer);
	public void updateCustomer(Customer customer);
	public String findFollow_Fan(String u_id);
	public List<Customer> selectFollowDetail(String u_id);
	public List<Customer> selectFanDetail(String u_id);
	
	//后台管理
	public List<Customer> findallCustomer();
	public Customer findCustomer(String u_id);
	public void editCustomer(Customer customer);
	public void deleteCustomer(String u_id);
	public void addCustomer(Customer customer);
	
	
	public List<Customer> SelectHighRisk();
	public List<Dynamic> ProductDownSuggestion();
	public List<Dynamic> ProductUpSuggestion();
	public Customer findCustomerById(String u_id);
	void updataCustomerCancellationCount(Customer customer);
	
	/**
	 * 修改个人信息
	 * @param customer
	 */
	public void updateCustomerInfo(Customer customer);
	
	/**
	 * 修改密码
	 * @param customer
	 */
	public void updateCustomerPwd(Customer customer);
}
