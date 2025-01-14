package ssm.controller.mallController;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ssm.pojo.Goods.GoodsEntity;
import ssm.pojo.Goods.GoodsIntroduceImage;
import ssm.pojo.Goods.GoodsSpecs;
import ssm.service.mallService.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/findGoodsByCategory.action")
	@ResponseBody
	public String findGoodsByCategory(Integer category, HttpServletResponse response, HttpServletRequest request) {

		//System.out.println("category = " + category);

		List<GoodsEntity> list = goodsService.findGoodsByCategory(category);

		String json = new Gson().toJson(list);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/getTroduceImage.action")
	@ResponseBody
	public String getTroduceImage(String goods_id, HttpServletResponse response, HttpServletRequest request) {
		
		System.out.println("goods_id = " + goods_id);
		
		List<GoodsIntroduceImage> list = goodsService.getTroduceImage(goods_id);
		
		String json = new Gson().toJson(list);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/findGoodsBySearchBar.action")
	@ResponseBody
	public String findGoodsBySearchBar(String search, HttpServletResponse response, HttpServletRequest request) {
		System.out.println("value = " + search);
		List<GoodsEntity> list = goodsService.findGoodsBySearchBar(search);
		String json = new Gson().toJson(list);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		return json;
	}
	
	@RequestMapping(value = "/getGodosSpecs.action")
	@ResponseBody
	public String getGodosSpecs(String goods_id, HttpServletResponse response, HttpServletRequest request) throws Exception {
		GoodsEntity goods = goodsService.getGodosSpecs(goods_id);
		
		String json = new Gson().toJson(goods);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.setContentType("text/json;charset=utf-8");
		// response.getWriter().write(json);
		return json;
	}
	
	@RequestMapping(value = "/findGoodsById.action")
	@ResponseBody
	public String findGoodsById(String goods_id, HttpServletResponse response, HttpServletRequest request) throws Exception {
		System.out.println(goods_id);
		GoodsEntity goods = goodsService.findGoodsById(goods_id);
		String json = new Gson().toJson(goods);
		try {
			OutputStream os = response.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return json;
	}
	
	//////////////////////////////////////////////////////////后台代码//////////////////////////////////////////////////////////////////
	
	//这里开始时后台
	@RequestMapping(value = "/findAllGoods.action")
	@ResponseBody
	public ModelAndView findAllGoods(HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<GoodsEntity> goodsList = goodsService.findAllGoods();
		ModelAndView mAndView = new ModelAndView();
		for (GoodsEntity goodsEntity : goodsList) {
			List<GoodsSpecs> specsList = goodsEntity.getGoodsSpecs();
			int length = specsList.size();
			if(length==0) {
				goodsEntity.setGoods_price("0.0");
			}else if(length==1){
				double min = specsList.get(0).getSpecs_price();
				goodsEntity.setGoods_price(min+"");
			}else {
				double min = specsList.get(0).getSpecs_price();
				double max = specsList.get(length-1).getSpecs_price();
				goodsEntity.setGoods_price(min+"-"+max);
			}
		}
		List<Map<String,Object>> categoryList = goodsService.findAllCategory();
		//List<Map<String,Object>> categoryList = (List<Map<String,Object>>)request.getAttribute("categoryList");
		int len = categoryList.size()+1;
		String[] categoryArr = new String[len];
		for(Map<String,Object> category:categoryList){
			categoryArr[(Integer)category.get("ca_id")] = (String)category.get("ca_name");
		}
		mAndView.addObject("goodsList",goodsList);
		mAndView.addObject("categoryArr",categoryArr);
		mAndView.setViewName("jsps/goods.jsp");
		return mAndView;
	}
	@RequestMapping(value = "/findGoodsDetail.action")
	@ResponseBody
	public ModelAndView findGoodsDetail(String goods_id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		GoodsEntity goods = goodsService.getGodosSpecs(goods_id);
		System.out.println(goods);
		mAndView.addObject("goods",goods);
		mAndView.setViewName("jsps/goodsDetail.jsp");
		return mAndView;
	}
	@RequestMapping(value = "/deleteGoods.action")
	@ResponseBody
	public ModelAndView deleteGoods(String goods_id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		goodsService.deleteGoods(goods_id);
		System.out.println();
		mAndView.setViewName("findAllGoods.action");
		return mAndView;
	}
	@RequestMapping(value = "/addGoods.action")
	public ModelAndView addGoods(GoodsEntity goods,MultipartFile file , HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		if(file!=null) {
			System.out.println(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			String extendName = name.substring(name.lastIndexOf("."), name.length());
			String picturepath = "testImages/"+System.currentTimeMillis()+extendName;
			String filepath = request.getServletContext().getRealPath("/") + picturepath;
			File picFile = new File(filepath);
			System.out.println(filepath);
			if(!picFile.exists()) {
				picFile.mkdirs();
			}
			file.transferTo(picFile);
			goods.setGoods_coverPic(picturepath);
			
		}else {
			System.out.println("false");
		}
		goods.setGoods_id(System.currentTimeMillis()+""+(int)Math.random()*10+"");
		String[] attrs = request.getParameterValues("specs_attrs");
		String[] stock = request.getParameterValues("specs_stock");
		String[] price = request.getParameterValues("specs_price");
		if(attrs.length>0) {
			List<GoodsSpecs> specsList = new ArrayList<>();
			for (int i = 0; i < attrs.length; i++) {
				GoodsSpecs specs = new GoodsSpecs();
				specs.setGoods_id(goods.getGoods_id());
				specs.setSpecs_id(System.currentTimeMillis()+""+(int)Math.random()*10+"");
				specs.setSpecs_attrs(attrs[i]);
				specs.setSpecs_stock(Integer.parseInt(stock[i]));
				specs.setSpecs_price(Double.parseDouble(price[i]));
				specsList.add(specs);
			}
			goods.setGoodsSpecs(specsList);
		}			
		System.out.println(goods);
		goodsService.addGoods(goods);
		mAndView.setViewName("findAllGoods.action");
		return mAndView;
	}
	@RequestMapping(value = "/editGoodsPre.action")
	@ResponseBody
	public ModelAndView editGoodsPre(String goods_id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		GoodsEntity goods = goodsService.getGodosSpecs(goods_id);
		System.out.println(goods);
		List<Map<String,Object>> categoryList = goodsService.findAllCategory();
		//List<Map<String,Object>> categoryList = (List<Map<String,Object>>)request.getAttribute("categoryList");
		int len = categoryList.size()+1;
		String[] categoryArr = new String[len];
		for(Map<String,Object> category:categoryList){
			categoryArr[(Integer)category.get("ca_id")] = (String)category.get("ca_name");
		}
		mAndView.addObject("goods",goods);
		mAndView.addObject("categoryArr",categoryArr);
		mAndView.setViewName("jsps/editGoods.jsp");
		return mAndView;
	}
	@RequestMapping(value = "/updateGoods.action")
	public ModelAndView updateGoods(GoodsEntity goods,MultipartFile file , HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mAndView = new ModelAndView();
		if(file!=null) {
			System.out.println(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			String extendName = name.substring(name.lastIndexOf("."), name.length());
			String picturepath = "testImages/"+System.currentTimeMillis()+extendName;
			String filepath = request.getServletContext().getRealPath("/") + picturepath;
			File picFile = new File(filepath);
			System.out.println(filepath);
			if(!picFile.exists()) {
				picFile.mkdirs();
			}
			file.transferTo(picFile);
			goods.setGoods_coverPic(picturepath);
			
		}else {
			System.out.println("false");
		}		
		String[] id = request.getParameterValues("specs_id");
		String[] attrs = request.getParameterValues("specs_attrs");
		String[] stock = request.getParameterValues("specs_stock");
		String[] price = request.getParameterValues("specs_price");
		if(attrs.length>0) {
			List<GoodsSpecs> specsList = new ArrayList<>();
			for (int i = 0; i < attrs.length; i++) {
				GoodsSpecs specs = new GoodsSpecs();
				specs.setGoods_id(goods.getGoods_id());
				specs.setSpecs_id(id[i]);
				specs.setSpecs_attrs(attrs[i]);
				specs.setSpecs_stock(Integer.parseInt(stock[i]));
				specs.setSpecs_price(Double.parseDouble(price[i]));
				specsList.add(specs);
			}
			goods.setGoodsSpecs(specsList);
		}			
		System.out.println(goods);
		goodsService.updateGoods(goods);
		mAndView.setViewName("findAllGoods.action");
		return mAndView;
	}
	
}
