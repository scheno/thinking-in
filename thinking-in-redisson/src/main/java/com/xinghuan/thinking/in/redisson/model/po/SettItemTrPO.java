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
@TableName("sett_item_tr")
public class SettItemTrPO {

    @TableLogic("`id`")
    private Long id;

    @TableField("`sett_item_code`")
    private String settItemCode;

    @TableField("`pur_sls_org`")
    private Long purSlsOrg;

    @TableField("`sett_doc_id`")
    private Long settDocId;

}
