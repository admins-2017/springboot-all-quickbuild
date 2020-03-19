package com.kang.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.imploded.aspect.SysLog;
import com.kang.imploded.json.JSONResult;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.dto.RecordDto;
import com.kang.sys.entity.OperationRecord;
import com.kang.sys.service.IOperationRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/record")
public class OperationRecordController {


    private IOperationRecordService recordService;

    @GetMapping("/")
    @SysLog(description ="查找操作记录")
    @ApiOperation(value = "获取操作记录",notes = "根据记录id获取对应的操作记录")
    public JSONResult getRecord(RecordDto recordDto){
        IPage<OperationRecord> records = recordService.page(new Page<>(recordDto.getPage(),recordDto.getSize()),new QueryWrapper<OperationRecord>()
                .eq(null != recordDto.getRequestType(), "request_type", recordDto.getRequestType())
                .eq(null != recordDto.getRequestUser(), "request_user", recordDto.getRequestUser())
                .ge(null != recordDto.getStartTime(),"request_time", recordDto.getStartTime())
                .le(null != recordDto.getEndTime(),"request_time",recordDto.getEndTime())
        );
        return JSONResult.ok(records);
    }

    @GetMapping("/{page}/{size}")
    @SysLog(description ="获取所有操作记录")
    @ApiOperation(value = "获取所有操作记录",notes = "获取所有的操作记录")
    public JSONResult getAllRecord(@PathVariable Integer page,@PathVariable Integer size){
        Page<OperationRecord> recordPage = new Page<>(page,size);
        IPage<OperationRecord> recordList = recordService.page(recordPage);
        return JSONResult.ok(recordList);
    }

    @DeleteMapping("/{id}")
    @SysLog(description ="删除操作记录")
    @ApiOperation(value = "删除操作记录",notes = "根据id删除操作记录")
    public JSONResult delRecord(@PathVariable Integer id){
        boolean b = recordService.removeById(id);
        return JSONResult.ok(b);
    }

    @GetMapping("/user/{page}/{size}")
    @SysLog(description ="查找操作记录")
    @ApiOperation(value = "获取操作记录",notes = "根据记录id获取对应的操作记录")
    public JSONResult getRecordByUser(@PathVariable Integer page,@PathVariable Integer size){
        IPage<OperationRecord> records = recordService.page(new Page<>(page,size),new QueryWrapper<OperationRecord>()
                .eq("request_user", SecurityUntil.getUserName()).orderByDesc("request_time")
        );
        return JSONResult.ok(records);
    }


    @Autowired
    public void setRecordService(IOperationRecordService recordService) {
        this.recordService = recordService;
    }
}
