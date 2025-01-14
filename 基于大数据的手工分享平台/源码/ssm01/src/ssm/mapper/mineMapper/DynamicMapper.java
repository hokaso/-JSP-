package ssm.mapper.mineMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ssm.pojo.Mine.Customer;
import ssm.pojo.Mine.CustomerExample;
import ssm.pojo.Mine.Dynamic;

public interface DynamicMapper {
 
	List<Dynamic> selectMyDynamic(String u_id);

	int selectDynamicLikeCount(String d_id);

	int selectDynamicCommentCount(String d_id);
	
	
	List<Dynamic> ProductUpSuggestion();
	
	List<Dynamic> ProductDownSuggestion();

	List<Map<String,Object>> findLatestPubDynamic(String category);

	List<Map<String, Object>> findLatestRespDynamic(String category);

	List<Map<String, Object>> findHostDynamic(String category);
}