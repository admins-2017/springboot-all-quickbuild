package com.kang.sys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/17 15:13
 */
@Data
public class RecordDto {

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 操作用户
     */
    private String requestUser;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String endTime;

    /**
     * 分页页数
     */
    private Integer page;

    /**
     * 分页条数
     */
    private Integer size;
}
