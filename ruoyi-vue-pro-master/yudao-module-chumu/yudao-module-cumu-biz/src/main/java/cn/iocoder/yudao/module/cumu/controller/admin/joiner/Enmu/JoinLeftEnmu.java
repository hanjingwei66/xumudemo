package cn.iocoder.yudao.module.cumu.controller.admin.joiner.Enmu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JoinLeftEnmu {
    JOIN_FLAG(1,"入场标记"),
    LEFT_FLAG(2,"出场标记");

    private final Integer status;
    /**
     * 状态名
     */
    private final String name;
}
