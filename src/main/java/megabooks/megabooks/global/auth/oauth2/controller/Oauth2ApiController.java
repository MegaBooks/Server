package megabooks.megabooks.global.auth.oauth2.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import megabooks.megabooks.global.auth.oauth2.dto.Oauth2ResponseDTO;
import megabooks.megabooks.global.auth.oauth2.service.Oauth2ServiceImpl;
import megabooks.megabooks.global.common.reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Oauth2ApiController {
    private final Oauth2ServiceImpl oauth2Service;
    @GetMapping("/login/oauth2/success")
    public ResponseEntity<?> oauth2Success(HttpServletRequest request) {
        try {
            log.info("[Oauth2ApiController] oauth2Success");

            // 세션에서 토큰 가져오기
            String accessToken = (String) request.getSession().getAttribute("accessToken");
            String refreshToken = (String) request.getSession().getAttribute("refreshToken");

            Oauth2ResponseDTO.Oauth2TokenResponseDTO result = oauth2Service.createOauth2Token(accessToken, refreshToken);
            log.info("Result: {}", result);

            // 세션에서 토큰 정보 제거
            request.getSession().removeAttribute("accessToken");
            request.getSession().removeAttribute("refreshToken");

            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] Oauth2ApiController oauth2Success", result));
        } catch (Exception e) {
            log.error("[Exception] Oauth2ApiController oauth2Success", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
