package com.assu.study.chap09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BillingController {

  @GetMapping("/billing-codes")
  public ApiResponse<List<BillingCodeResponse>> getBillingCodes(
      @RequestParam(required = false) String codeName
  ) {
    List<BillingCodeResponse> response = List.of(
        BillingCodeResponse.of("CODE-111111"),
        BillingCodeResponse.of("CODE-222222")
    );
    return ApiResponse.ok(response);
  }

  @PostMapping(path = "/billing-codes")
  public ApiResponse<CreateCodeResponse> createBillingCodes(
      @RequestBody CreateCodeRequest request
  ) {
    return ApiResponse.ok(CreateCodeResponse.of(request.getIds()));
  }
}
