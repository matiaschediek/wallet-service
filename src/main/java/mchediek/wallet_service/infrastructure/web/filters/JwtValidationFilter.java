package mchediek.wallet_service.infrastructure.web.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {


    @Value("${spring.auth.uri}")
    private String tokenVerifierUrl;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing token");
            return;
        }

        HttpURLConnection conn = (HttpURLConnection) new URL(tokenVerifierUrl).openConnection();
        conn.setRequestProperty("Authorization", authHeader);
        conn.setRequestMethod("GET");

        int status = conn.getResponseCode();
        if (status == 200) {
            chain.doFilter(request, response); // Token OK
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token (checked by Kong)");
        }
    }
}