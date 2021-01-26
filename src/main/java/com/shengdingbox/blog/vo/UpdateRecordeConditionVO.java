package com.shengdingbox.blog.vo;


import com.shengdingbox.blog.entity.UpdateRecorde;
import com.zhouzifei.tool.dto.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRecordeConditionVO extends BaseConditionVO {
	private UpdateRecorde updateRecorde;
}

