/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.prm.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class PartnerOnboardingGovernanceServiceTest {
    private final PartnerOnboardingGovernanceService service = new PartnerOnboardingGovernanceService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void activatesDueDiligenceApprovedPartner() {
        var result = service.assess(new PartnerOnboardingGovernanceService.Request(
            "P-001", true, true, true, true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(PartnerOnboardingGovernanceService.Decision.ACTIVATE);
        assertThat(result.active()).isTrue();
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksKycContractAndPrivacyFailures() {
        var result = service.assess(new PartnerOnboardingGovernanceService.Request(
            "P-002", false, false, false, false, false, true, false, true, true, true));
        assertThat(result.decision()).isEqualTo(PartnerOnboardingGovernanceService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(6);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void reviewsSecurityQualificationAndOwnership() {
        var result = service.assess(new PartnerOnboardingGovernanceService.Request(
            "P-003", true, true, true, true, true, false, false, false, false, false));
        assertThat(result.decision()).isEqualTo(PartnerOnboardingGovernanceService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(3);
    }
}
