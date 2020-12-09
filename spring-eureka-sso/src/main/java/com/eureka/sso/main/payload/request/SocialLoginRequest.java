package com.eureka.sso.main.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SocialLoginRequest {
    private String accessToken;
//    private String provider;
}
