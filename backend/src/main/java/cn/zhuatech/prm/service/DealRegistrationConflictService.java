/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.prm.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
@Service public class DealRegistrationConflictService {
 public Result evaluate(Request r){int best=0;List<String> conflicts=new ArrayList<>();for(ExistingDeal d:r.existingDeals()){if(d.active()&&d.accountId().equalsIgnoreCase(r.accountId())&&!d.partnerId().equalsIgnoreCase(r.partnerId())&&d.registeredDaysAgo()<=90){int score=d.registeredDaysAgo()<=30?90:65;if(d.value().compareTo(r.value().multiply(new BigDecimal("0.8")))>=0)score+=10;best=Math.max(best,Math.min(score,100));conflicts.add(d.dealId());}}String status=best>=90?"BLOCK":best>=60?"REVIEW":"CLEAR";return new Result(best,status,conflicts,status.equals("CLEAR")?"可进入渠道商机登记审批":"核查客户归属、保护期和合作伙伴贡献证据");}
 public record Request(@NotBlank String registrationId,@NotBlank String partnerId,@NotBlank String accountId,@NotNull @DecimalMin("0.01") BigDecimal value,@NotNull List<@Valid ExistingDeal> existingDeals){}
 public record ExistingDeal(@NotBlank String dealId,@NotBlank String partnerId,@NotBlank String accountId,@NotNull @DecimalMin("0.01") BigDecimal value,@Min(0) int registeredDaysAgo,@NotNull Boolean active){}
 public record Result(int conflictScore,String status,List<String> conflictingDeals,String action){}
}
