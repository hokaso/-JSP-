package ssm.controller.mallController;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ssm.pojo.Goods.GoodsSpecs;
import ssm.pojo.Goods.OrderEntity;
import ssm.pojo.Goods.OrderItemEntity;
import ssm.pojo.Mine.Customer;
import ssm.service.CustomerService;
import ssm.service.mallService.GoodsService;
import ssm.service.mallService.OrderService;
import ssm.utils.FormatUtil;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderSerivce;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CustomerService customerService;

	/**
	 * 确认订单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/confirmOrder.action")
	@ResponseBody
	public String confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletInputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			// System.out.println("len=" + len);
			baos.write(buffer, 0, len);
		}
		String json = baos.toString();
		System.out.println(json);
		OrderEntity order = new Gson().fromJson(json, OrderEntity.class);
		List<OrderItemEntity> orderItemList = order.getItemList();
		List<GoodsSpecs> specsList = new ArrayList<GoodsSpecs>();
		
		// order.setOrder_id(FormatUtil.createUUID());
		order.setOrder_create_time(FormatUtil.getNowTime());
		order.setOrder_id(FormatUtil.createUUID());
		order.setOrder_state(OrderEntity.STATE_UNPAID);
		for (OrderItemEntity orderItemEntity : orderItemList) {
			
			String cartItemId = orderItemEntity.getOrder_item_id();
			GoodsSpecs ispecs = orderItemEntity.getSpecs();
			
			GoodsSpecs dspecs = goodsService.findSpecsById(ispecs.getSpecs_id());
			// 判断库存数是否小于要购买的数量的数量
			if (dspecs == null) {
				String tips = orderItemEntity.getGoods().getGoods_name() + "/" + ispecs.getSpecs_attrs() + ":所选数量大于库存数";
				OutputStream os = response.getOutputStream();
				os.write(tips.getBytes("utf-8"));
				os.flush();
				os.close();
				return tips;
			}
			int count  = dspecs.getSpecs_stock() - orderItemEntity.getOrder_item_count();
			
			if (count < 0) {
				String tips = orderItemEntity.getGoods().getGoods_name() + "/" + ispecs.getSpecs_attrs() + ":所选数量大于库存数";
				OutputStream os = response.getOutputStream();
				os.write(tips.getBytes("utf-8"));
				os.flush();
				os.close();
				return tips;
			}
			
			if (cartItemId == null) {
				orderItemEntity.setOrder_item_id(FormatUtil.createUUID());
			}
			
			dspecs.setSpecs_stock(count);
			orderItemEntity.setOrder(order);
			specsList.add(dspecs);
		}
		
		orderSerivce.creatOrder(order, orderItemList, specsList);
		//orderSerivce.creatOrder(order, orderItemList);
		return "success";
	}
	
	/**
	 * 判断库存数是否小于要购买的数量的数量
	 * 	库存数小于返回false，否则返回true
	 * @param item
	 * @return
	 */
	/*private boolean compareStock(OrderItemEntity item, GoodsSpecs specs) {
		String specs_id = specs.getSpecs_id();
		Integer stock = goodsService.getSpecsStock(specs_id);
		if(stock == null) {
			return false;
		} else if (stock < item.getOrder_item_count()) {
			return false;
		} else {
			return true;
		}
	}*/
	
	/**
	 * 查找用户的id
	 * @param u_id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findOrdersByUid.action")
	@ResponseBody
	public String findOrdersByUid(String u_id, HttpServletRequest request, HttpServletResponse response) {
		List<OrderEntity> orderList = orderSerivce.findOrdersByUid(u_id);
		String json = new Gson().toJson(orderList);
		System.out.println("id = " + u_id);
		System.out.println("json = " + json);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 修改订单状态
	 * @param order_id
	 * @param order_state
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateOrderState.action")
	@ResponseBody
	public String updateOrderState(String order_id, String order_state, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("order_id = " + order_id);
		System.out.println("order_state = " + order_state);
		
		OrderEntity order = new OrderEntity();
		order.setOrder_id(order_id);
		order.setOrder_state(order_state);
		
		if (order_state.equals(OrderEntity.STATE_UNDELIVER)) {
			order.setOrder_paytime(FormatUtil.getNowTime());
		}

		orderSerivce.updateOrderState(order);
		
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
	
	/**
	 * “删除”订单
	 * @param order_id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteOrder.action")
	@ResponseBody
	public String deleteOrder(String order_id, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("order_id = " + order_id);
		
		OrderEntity order = new OrderEntity();
		order.setOrder_id(order_id);
		// order.setOrder_state(OrderEntity.STATE_DELETE);
		// order.setOrder_paytime(FormatUtil.getNowTime());
		order.setOrder_delete(0);
		orderSerivce.deleteOrder(order);
		
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
	
	/**
	 * 用户申请退款
	 * @param order_id
	 * @param u_id
	 * @param order_state
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/refundOrder.action")
	@ResponseBody
	public String refundOrder(String order_id, String u_id, HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("order_id = " + order_id);
		// System.out.println("u_id = " + u_id);
		
		Customer customer = customerService.findCustomerById(u_id);
		customer.setCancellationCount(customer.getCancellationCount() + 1);
		
		OrderEntity order = new OrderEntity();
		order.setOrder_id(order_id);
		order.setOrder_state(OrderEntity.STATE_REFUND);
		orderSerivce.refundOrder(order, customer);
		
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

	/**
	 * 查询订单详细
	 * @param order_id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findOrderDetail.action")
	@ResponseBody
	public String findOrderDetail(String order_id, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("order_id = " + order_id);
		OrderEntity order = orderSerivce.findOrderDetail(order_id);
		String json = new Gson().toJson(order);
		// System.out.println("json = " + json);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
