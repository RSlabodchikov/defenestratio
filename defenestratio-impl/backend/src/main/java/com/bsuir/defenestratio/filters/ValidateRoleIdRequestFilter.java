package com.bsuir.defenestratio.filters;

import com.bsuir.defenestratio.entity.Role;
import com.bsuir.defenestratio.exceptions.NotFoundException;
import com.bsuir.defenestratio.utils.RoleUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j

public class ValidateRoleIdRequestFilter implements Filter {

    private static final String INVALID_ROLE_ID = "Invalid roleId %s";

    @Autowired
    private RoleUtils roleUtils;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UUID roleId = getRoleId(request.getRequestURI());
        if (roleId != null) {
            validateRoleId(roleId);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private UUID getRoleId(String requestURI) {
        String[] pathVariables = requestURI.split("/");
        UUID roleId = null;
        try {
            roleId = UUID.fromString(pathVariables[1]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
        }
        return roleId;
    }

    private void validateRoleId(UUID roleId) {
        Map<UUID, Role> roleNames = roleUtils.getAllRoleNames();
        if (!roleNames.containsKey(roleId)) {
            String message = String.format(INVALID_ROLE_ID, roleId);
            throw new NotFoundException(message);
        }
    }

    @Override
    public void destroy() {
    }
}
