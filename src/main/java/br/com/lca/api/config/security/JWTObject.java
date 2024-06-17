package br.com.lca.api.config.security;

import br.com.lca.api.config.SecutiryRoles;

import java.util.Date;
import java.util.List;

public record JWTObject(
        String subject,
        Date issuedAt,
        Date expiresAt,
        List<SecutiryRoles> roles
) {
}
