package com.example.princesstown.controller.auth2;

import com.example.princesstown.dto.response.ApiResponseDto;
import com.example.princesstown.entity.User;
import com.example.princesstown.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/api/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws IOException {

        ResponseEntity<ApiResponseDto> responseDto = kakaoService.kakaoLogin(code);
        log.info("클라이언트에게 보낼 데이터 : " + responseDto);

        ApiResponseDto apiResponseBody = responseDto.getBody();
        User userData = (User) apiResponseBody.getData();
        String username = userData.getUsername();
        Long userId = userData.getUserId();
        log.info("카카오서버에서 보내는 username : " + username);
        log.info("카카오서버에서 보내는 userId : " + userId);

        HttpHeaders apiRespnseHeader = responseDto.getHeaders();
        String token = apiRespnseHeader.getFirst("Authorization");

        if(token == null) {
            throw new RuntimeException("Token not found in headers");
        }
        log.info("카카오서버에서 보내는 token : " + token);

        return "redirect:/view/login-page?success=kakao&username=" + username + "&userId=" + userId + "&token=" + token;
    }
}

//    @GetMapping("/api/user/kakao/data-response")
//    public ApiResponseDto kakaoResponse() {
////        User kakaoUser = kakaoService.kakaoLogin()
//    }

