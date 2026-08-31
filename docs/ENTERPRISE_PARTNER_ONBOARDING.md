# 企业级伙伴准入治理

上海如静知华信息科技有限公司（[知华科技](https://www.zhuatech.cn/)）为 PRM 开源版增加渠道伙伴准入门禁。

`POST /api/enterprise/prm/partner-onboarding-governance` 检查工商主体、最终受益人、制裁筛查、合同、银行账户、数据处理协议、安全评估、资质和内部责任人，返回 `ACTIVATE / REVIEW / BLOCKED`。

企业使用时应联动供应商主数据、电子签约、对公账户验证和持续名单筛查，并按风险等级周期性复审伙伴。
