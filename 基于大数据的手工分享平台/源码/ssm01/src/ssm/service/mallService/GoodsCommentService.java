package ssm.service.mallService;

import java.util.List;

import ssm.pojo.Goods.GoodsCommentEntity;
import ssm.pojo.Goods.GoodsCommentImageEntity;

public interface GoodsCommentService {
	
	/**
	 * 查询指定商品的所有评论
	 * @param goods_id
	 * @return
	 */
	public List<GoodsCommentEntity> loadGoodsComment(String goods_id);
	
	/**
	 * 根据指定商品评价等级（好，中，差评查找评论），按时间排序
	 * @param goods_degree
	 * @param goods_id
	 * @return
	 */
	public List<GoodsCommentEntity> loadGoodsCommentByDegree(GoodsCommentEntity commentEntity);
	
	/**
	 * 根据指定评论，查找用户发布的图片
	 * @param goods_comment_id
	 * @return
	 */
	public List<GoodsCommentImageEntity> loadGoodsCommentImage(String goods_comment_id);
	
	/**
	 * 添加评论
	 * @param comment
	 * @param order_id 
	 */
	public void addGoodsComment(List<GoodsCommentEntity> comment, String order_id);
}
