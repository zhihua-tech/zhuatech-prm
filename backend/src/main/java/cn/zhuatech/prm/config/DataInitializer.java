/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.prm.config;

import cn.zhuatech.prm.model.*;
import cn.zhuatech.prm.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(OperatingUnitRepository operatingUnits, WorkRecordRepository orders,
                           ResourceRegisterRepository resources, ReviewRecordRepository reviewRecords,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (operatingUnits.count() > 0) return;
            OperatingUnit primaryUnit = operatingUnits.save(new OperatingUnit("PRM-EAST", "启程数字科技", "华东生态大区", 180));
            OperatingUnit secondaryUnit = operatingUnits.save(new OperatingUnit("PRM-RETAIL", "远景信息服务", "远景信息服务", 120));
            OperatingUnit tertiaryUnit = operatingUnits.save(new OperatingUnit("PRM-PUBLIC", "鼎新系统集成", "公共事业部", 96));

            WorkRecord t1 = orders.save(new WorkRecord("FC-260801-018", "CUS-EAST-DATA", "华东渠道收入商机推进", tertiaryUnit, 24, 16, 1, LocalDate.now().plusDays(1), WorkRecord.Status.RUNNING, "GW-Q3"));
            WorkRecord t2 = orders.save(new WorkRecord("FC-260801-021", "CUS-RETAIL-AI", "连锁零售智能补货项目", primaryUnit, 18, 8, 0, LocalDate.now().plusDays(1), WorkRecord.Status.RUNNING, "TERM-12"));
            WorkRecord t3 = orders.save(new WorkRecord("BUD-260802-006", "CUS-MED-DQM", "研发费用年度资源", secondaryUnit, 12, 0, 0, LocalDate.now().plusDays(3), WorkRecord.Status.RELEASED, "SP-2026"));
            WorkRecord t4 = orders.save(new WorkRecord("FC-260728-015", "CUS-ENERGY-OA", "海外现金流商机", primaryUnit, 20, 20, 1, LocalDate.now(), WorkRecord.Status.COMPLETED, "SEA-09"));

            resources.saveAll(List.of(
                new ResourceRegister("CAT-HPLC-03", "伙伴能力画像", primaryUnit, ResourceRegister.Status.RUNNING, 88),
                new ResourceRegister("CAT-ICP-02", "利润伙伴能力", primaryUnit, ResourceRegister.Status.IDLE, 76),
                new ResourceRegister("CAT-UTM-05", "现金流伙伴能力", tertiaryUnit, ResourceRegister.Status.RUNNING, 91),
                new ResourceRegister("CAT-INC-08", "伙伴评分模型", secondaryUnit, ResourceRegister.Status.ALARM, 62)
            ));
            reviewRecords.saveAll(List.of(
                new ReviewRecord("ISS-260801-032", t1, "伙伴能力复核", 6, 0, ReviewRecord.Result.PASSED, "顾言"),
                new ReviewRecord("ISS-260801-011", t2, "伙伴能力校验", 3, 0, ReviewRecord.Result.PASSED, "周惟"),
                new ReviewRecord("ISS-260801-018", t4, "赢单归档复核", 5, 1, ReviewRecord.Result.FAILED, "顾言"),
                new ReviewRecord("ISS-260802-003", t3, "报备归属确认", 4, 0, ReviewRecord.Result.PENDING, "周惟")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "周惟", UserAccount.Role.DOMAIN_USER, "PRM-EAST"),
                new UserAccount("planner", demo, "顾言", UserAccount.Role.DOMAIN_OPERATOR, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
