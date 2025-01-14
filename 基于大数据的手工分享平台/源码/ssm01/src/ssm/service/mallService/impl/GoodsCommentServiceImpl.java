package ssm.service.mallService.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.mapper.mallMapper.GoodsCommentMapper;
import ssm.mapper.mallMapper.OrderMapper;
import ssm.pojo.Goods.GoodsCommentEntity;
import ssm.pojo.Goods.GoodsCommentImageEntity;
import ssm.service.mallService.GoodsCommentService;

@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {

	@Autowired
	private GoodsCommentMapper goodsCommentMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<GoodsCommentEntity> loadGoodsComment(String goods_id) {
		return goodsCommentMapper.loadGoodsComment(goods_id);
	}

	@Override
	public List<GoodsCommentEntity> loadGoodsCommentByDegree(GoodsCommentEntity commentEntity) {
		// TODO Auto-generated method stub
		return goodsCommentMapper.loadGoodsCommentByDegree(commentEntity);
	}

	@Override
	public List<GoodsCommentImageEntity> loadGoodsCommentImage(String goods_comment_id) {
		// TODO Auto-generated method stub
		return goodsCommentMapper.loadGoodsCommentImage(goods_comment_id);
	}

	/**
	 * 添加评论
	 * @param comment
	 */
	@Override
	public void addGoodsComment(List<GoodsCommentEntity> comments, String order_id) {
		// TODO Auto-generated method stub
		orderMapper.updateIsCommented(order_id);
		GoodsCommentMapper commentMapper = sqlSessionTemplate.getMapper(GoodsCommentMapper.class);
		for (GoodsCommentEntity goodsCommentEntity : comments) {
			commentMapper.addGoodsComment(goodsCommentEntity);
		}
	}

}
