import { API_CONFIG, getApiUrl } from '../../../config/api.config';
import type { AuthResponse, JwtPayload, LoginCredentials, RefreshTokenPayload } from '../types/auth.types';

export class AuthService {
  private static STORAGE_ACCESS_TOKEN_KEY = 'saborfy_access_token';
  private static STORAGE_REFRESH_TOKEN_KEY = 'saborfy_refresh_token';

  /**
   * Decodifica o payload de um JWT Token sem dependências externas
   */
  static parseJwt(token: string): JwtPayload | null {
    try {
      const base64Url = token.split('.')[1];
      if (!base64Url) return null;
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(
        atob(base64)
          .split('')
          .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
          .join('')
      );
      return JSON.parse(jsonPayload);
    } catch {
      return null;
    }
  }

  /**
   * Realiza o Login efetuando requisição POST para VITE_API_BASE_URL/auth/login
   * Retorna o formato { accessToken, refreshToken }
   */
  static async login(credentials: LoginCredentials): Promise<AuthResponse> {
    const url = getApiUrl('/auth/login');
    const payload = {
      username: credentials.username,
      password: credentials.password,
    };

    const response = await fetch(url, {
      method: 'POST',
      headers: API_CONFIG.headers,
      body: JSON.stringify(payload),
    });

    if (response.status === 401) {
      throw new Error('Login ou senha incorretos.');
    }

    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      throw new Error(errorData.message || `Erro no login: Status ${response.status}`);
    }

    const data: AuthResponse = await response.json();
    this.saveTokens(data.accessToken, data.refreshToken);
    return data;
  }

  /**
   * Realiza a renovação de tokens efetuando requisição POST para VITE_API_BASE_URL/auth/refresh
   * Retorna o formato { accessToken, refreshToken }
   */
  static async refresh(refreshToken: string): Promise<AuthResponse> {
    const url = getApiUrl('/auth/refresh');
    const payload: RefreshTokenPayload = { refreshToken };

    const response = await fetch(url, {
      method: 'POST',
      headers: API_CONFIG.headers,
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      throw new Error(errorData.message || `Erro ao atualizar token: Status ${response.status}`);
    }

    const data: AuthResponse = await response.json();
    this.saveTokens(data.accessToken, data.refreshToken);
    return data;
  }

  /**
   * Salva os tokens no localStorage
   */
  static saveTokens(accessToken: string, refreshToken: string): void {
    localStorage.setItem(this.STORAGE_ACCESS_TOKEN_KEY, accessToken);
    localStorage.setItem(this.STORAGE_REFRESH_TOKEN_KEY, refreshToken);
  }

  /**
   * Obtém o accessToken salvo
   */
  static getAccessToken(): string | null {
    return localStorage.getItem(this.STORAGE_ACCESS_TOKEN_KEY);
  }

  /**
   * Obtém o refreshToken salvo
   */
  static getRefreshToken(): string | null {
    return localStorage.getItem(this.STORAGE_REFRESH_TOKEN_KEY);
  }

  /**
   * Remove os tokens salvos (Logout local)
   */
  static clearTokens(): void {
    localStorage.removeItem(this.STORAGE_ACCESS_TOKEN_KEY);
    localStorage.removeItem(this.STORAGE_REFRESH_TOKEN_KEY);
  }

  /**
   * Realiza o Logout no backend enviando o refreshToken e limpa localmente
   */
  static async logout(): Promise<void> {
    const refreshToken = this.getRefreshToken();
    const accessToken = this.getAccessToken();
    
    if (refreshToken) {
      const url = getApiUrl('/auth/logout');
      
      const headers: Record<string, string> = {
        ...API_CONFIG.headers,
      };
      
      if (accessToken) {
        headers['Authorization'] = `Bearer ${accessToken}`;
      }

      try {
        await fetch(url, {
          method: 'POST',
          headers,
          body: JSON.stringify({ refreshToken }),
        });
      } catch (error) {
        console.warn('[SaborFy Auth] Erro ao realizar logout no servidor.', error);
      }
    }
    this.clearTokens();
  }


}
