/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.prm;
import cn.zhuatech.prm.service.DealRegistrationConflictService;import org.junit.jupiter.api.Test;import java.math.*;import java.util.*;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class DealRegistrationConflictServiceTests {private final DealRegistrationConflictService service=new DealRegistrationConflictService();
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void blocksProtectedAccountConflict(){var d=new DealRegistrationConflictService.ExistingDeal("D1","P2","A1",new BigDecimal("100000"),10,true);var r=service.evaluate(new DealRegistrationConflictService.Request("R1","P1","A1",new BigDecimal("90000"),List.of(d)));assertEquals("BLOCK",r.status());}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void clearsDifferentAccount(){var d=new DealRegistrationConflictService.ExistingDeal("D1","P2","A2",new BigDecimal("100000"),10,true);var r=service.evaluate(new DealRegistrationConflictService.Request("R1","P1","A1",new BigDecimal("90000"),List.of(d)));assertEquals("CLEAR",r.status());}}
