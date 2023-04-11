package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.model.User;
import dev.shulika.podologia.service.CustomSecurityExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpressionImpl implements CustomSecurityExpression {

    public boolean canAccessUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        Long userId = user.getId();
        return userId.equals(id) || user.getRole().equals("ADMIN");
    }
}
