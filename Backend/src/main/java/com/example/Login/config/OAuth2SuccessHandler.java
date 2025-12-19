package com.example.Login.config;

import com.example.Login.Model.User;
import com.example.Login.repository.UserRepository;
import com.example.Login.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oAuthUser = token.getPrincipal();
        String provider = token.getAuthorizedClientRegistrationId(); // google | github

        Map<String, Object> attrs = oAuthUser.getAttributes();

        // 🔍 DEBUG (YOU WILL SEE THIS IN CONSOLE)
        System.out.println("OAUTH PROVIDER = " + provider);
        System.out.println("OAUTH ATTRIBUTES = " + attrs);

        String email = null;
        String name = null;

        if ("google".equals(provider)) {
            email = (String) attrs.get("email");
            name = (String) attrs.get("name");
        }

        if ("github".equals(provider)) {
            email = (String) attrs.get("email"); // usually null
            name = (String) attrs.get("name");

            if (email == null) {
                String login = (String) attrs.get("login");
                email = login + "@github-oauth.local";
            }

            if (name == null) {
                name = (String) attrs.get("login");
            }
        }

        // ❌ HARD STOP IF STILL NULL (NO SILENT FAILURE)
        if (email == null) {
            throw new RuntimeException(
                    "OAuth login failed: email is null for provider " + provider
            );
        }

        String finalEmail = email;
        String finalName = name;
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User u = new User();
                    u.setEmail(finalEmail);
                    u.setName(finalName);
                    u.setProvider(provider.toUpperCase());
                    return userRepository.save(u);
                });

        String jwt = jwtUtil.generateToken(user.getEmail());

        response.sendRedirect(
                "http://localhost:5173/oauth-success?token=" + jwt
        );
    }
}
