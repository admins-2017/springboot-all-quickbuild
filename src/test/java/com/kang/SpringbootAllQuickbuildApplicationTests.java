package com.kang;

import com.kang.sys.entity.MerchantCommodity;
import com.kang.sys.service.IMerchantCommodityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAllQuickbuildApplicationTests {

    @Autowired
    IMerchantCommodityService commodityService;

    @Test
    void contextLoads() {
        MerchantCommodity commodity = commodityService.getById(1);
        System.out.println(commodity.toString());
    }

}
