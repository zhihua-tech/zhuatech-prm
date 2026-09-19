/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.prm.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class PartnerOnboardingGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.businessRegistrationVerified()) blockers.add("伙伴工商主体未核验");
        if (!request.beneficialOwnerVerified()) blockers.add("最终受益所有人未核验");
        if (!request.sanctionsScreeningPassed()) blockers.add("制裁与限制名单筛查未通过");
        if (!request.contractSigned()) blockers.add("伙伴协议尚未签署");
        if (!request.bankAccountVerified()) blockers.add("结算账户未完成对公核验");
        if (request.personalDataShared() && !request.dataProcessingAgreementSigned()) blockers.add("涉及个人信息但未签署数据处理协议");
        if (!blockers.isEmpty()) {
            actions.add("禁止激活伙伴，完成尽职调查、合同和结算控制");
            return new Assessment(Decision.BLOCKED, false, blockers, actions);
        }
        if (!request.securityAssessmentPassed() || !request.requiredCertificationValid()
            || !request.channelOwnerAssigned()) {
            if (!request.securityAssessmentPassed()) actions.add("完成伙伴信息安全评估和整改");
            if (!request.requiredCertificationValid()) actions.add("补充有效的行业或交付资质");
            if (!request.channelOwnerAssigned()) actions.add("指定内部渠道责任人");
            return new Assessment(Decision.REVIEW, false, blockers, actions);
        }
        actions.add("激活伙伴账户并记录尽调、合同、资质与责任快照");
        return new Assessment(Decision.ACTIVATE, true, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String partnerCode, boolean businessRegistrationVerified,
                          boolean beneficialOwnerVerified, boolean sanctionsScreeningPassed,
                          boolean contractSigned, boolean bankAccountVerified,
                          boolean personalDataShared, boolean dataProcessingAgreementSigned,
                          boolean securityAssessmentPassed, boolean requiredCertificationValid,
                          boolean channelOwnerAssigned) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, boolean active, List<String> blockers,
                             List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { ACTIVATE, REVIEW, BLOCKED }
}
