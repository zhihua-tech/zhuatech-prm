/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.prm.controller;

import cn.zhuatech.prm.common.ApiResponse;
import cn.zhuatech.prm.service.PartnerOnboardingGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/prm")
public class PartnerOnboardingGovernanceController {
    private final PartnerOnboardingGovernanceService service;
    public PartnerOnboardingGovernanceController(PartnerOnboardingGovernanceService service) { this.service = service; }

    @PostMapping("/partner-onboarding-governance")
    public ApiResponse<PartnerOnboardingGovernanceService.Assessment> assess(
        @Valid @RequestBody PartnerOnboardingGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
