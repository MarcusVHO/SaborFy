import { AuthService } from '../modules/auth/services/auth.service';

/**
 * Application API Configuration
 */
export const API_CONFIG = {
  baseUrl: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  },
};

/**
 * Funçao para construir a URL completa de um endpoint da API
 */
export const getApiUrl = (endpoint: string): string => {
  const cleanBase = API_CONFIG.baseUrl.replace(/\/+$/, '');
  const cleanEndpoint = endpoint.replace(/^\/+/, '');
  return `${cleanBase}/${cleanEndpoint}`;
};

/**
 * HTTP Client seguro (apiFetch) que:
 * 1. Anexa automaticamente o 'Authorization: Bearer <accessToken>' nas requisições
 * 2. Em caso de resposta 401 (token expirado), tenta renovar silenciosamente usando o refreshToken
 * 3. Repete a requisição original com o novo token obtido.
 */
export const apiFetch = async (
  endpoint: string,
  options: RequestInit = {},
  isRetry = false
): Promise<Response> => {
  const url = getApiUrl(endpoint);
  const accessToken = AuthService.getAccessToken();

  const headers: Record<string, string> = {
    ...API_CONFIG.headers,
    ...(options.headers as Record<string, string>),
  };

  if (accessToken) {
    headers['Authorization'] = `Bearer ${accessToken}`;
  }

  const response = await fetch(url, {
    ...options,
    headers,
  });

  // Se receber 401 (Não autorizado/Token expirado) e ainda não tiver tentado retry
  if (response.status === 401 && !isRetry) {
    const refreshToken = AuthService.getRefreshToken();

    if (refreshToken) {
      try {
        // Tenta renovar o token com o refreshToken armazenado
        const refreshedTokens = await AuthService.refresh(refreshToken);
        
        // Re-executa a requisição original com o novo accessToken
        const newHeaders: Record<string, string> = {
          ...headers,
          Authorization: `Bearer ${refreshedTokens.accessToken}`,
        };

        return await fetch(url, {
          ...options,
          headers: newHeaders,
        });
      } catch (refreshError) {
        // Se a renovação falhar (refreshToken também expirado ou inválido)
        AuthService.clearTokens();
        throw new Error('Sua sessão expirou. Por favor, faça login novamente.');
      }
    }
  }

  return response;
};
