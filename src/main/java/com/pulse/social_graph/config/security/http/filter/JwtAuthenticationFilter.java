package com.pulse.social_graph.config.security.http.filter;

import com.pulse.social_graph.config.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * HTTP 요청을 필터링하여 JWT 토큰을 확인하고, 유효한 토큰인 경우 사용자 인증 정보를 설정합니다.
 * gRPC는 HTTP가 아닌 다른 프로토콜을 사용하므로, 이 필터가 동작하지 않습니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    /**
     * HTTP 요청을 필터링하여 JWT 토큰을 확인하고, 유효한 토큰인 경우 사용자 인증 정보를 설정합니다.
     *
     * @param request     HTTP 요청 객체
     * @param response    HTTP 응답 객체
     * @param filterChain 필터 체인 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // HTTP 요청에서 JWT 토큰을 추출합니다.
            String jwt = getJwtFromRequest(request);

            // JWT 토큰이 유효한 경우, 사용자 인증 정보를 설정합니다.
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateJwtToken(jwt)) {
                Authentication authentication = jwtTokenProvider.getAuthenticationFromGrpcToken(jwt);

                // 인증 객체를 설정합니다.
                if (authentication != null) {
                    // 세부 정보를 설정하려면 UsernamePasswordAuthenticationToken으로 캐스팅해야 합니다.
                    UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }


    /**
     * HTTP 요청에서 JWT 토큰을 추출합니다.
     *
     * @param request HTTP 요청 객체
     * @return JWT 토큰
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        // 'Authorization' 헤더에서 JWT 토큰을 추출합니다.
        String bearerToken = request.getHeader("Authorization");

        // 'Bearer '로 시작하는지 확인합니다.
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // 'Bearer ' 부분을 제외한 토큰을 반환합니다.
            String substringToken = bearerToken.substring(7);
            log.info("bearerToken : {}", substringToken);
            return substringToken;
        }

        return null;
    }

}
