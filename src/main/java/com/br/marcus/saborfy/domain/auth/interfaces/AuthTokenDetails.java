package com.br.marcus.saborfy.domain.auth.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthTokenDetails extends UserDetails {
    String getId();
}
