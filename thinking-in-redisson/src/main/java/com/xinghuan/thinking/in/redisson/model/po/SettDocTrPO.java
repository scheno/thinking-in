package com.xinghuan.thinking.in.redisson.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author shenchen
 * @since 2023/8/21 16:15
 */
@Data
@ToString
@TableName("sett_doc_tr")
public class SettDocTrPO {

    @TableLogic("`id`")
    private Long id;

    @TableField("`sett_doc_code`")
    private String settDocCode;

}
