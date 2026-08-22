/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.prm.controller;
import cn.zhuatech.prm.common.ApiResponse;import cn.zhuatech.prm.service.DealRegistrationConflictService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/prm/insights/deal-conflict") public class DealRegistrationConflictController {private final DealRegistrationConflictService service;public DealRegistrationConflictController(DealRegistrationConflictService service){this.service=service;}@PostMapping ApiResponse<DealRegistrationConflictService.Result> evaluate(@Valid @RequestBody DealRegistrationConflictService.Request request){return ApiResponse.ok(service.evaluate(request));}}
