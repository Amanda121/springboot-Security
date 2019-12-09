package com.example.security.Service.Impl;

import com.example.security.Service.SecurityService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    @Secured("ROLE_ADMIN")
    public String admin() {
        String string = "hello";
        return string;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','DBA')")
    @Override
    public String user() {
        String string = "hello";
        return string;
    }

    @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
    @Override
    public String dba() {
        return "hello";
    }
}
