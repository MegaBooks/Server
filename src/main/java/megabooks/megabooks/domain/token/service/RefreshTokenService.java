package megabooks.megabooks.domain.token.service;

import megabooks.megabooks.domain.token.dto.RefreshTokenRequestDTO;
import megabooks.megabooks.domain.token.dto.RefreshTokenResponseDTO;

public interface RefreshTokenService {
    RefreshTokenResponseDTO.RefreshTokenGetAccessTokenDTO getAccessToken(RefreshTokenRequestDTO.RefreshTokenGetAccessTokenDTO tokenGetAccessTokenDTO);
}