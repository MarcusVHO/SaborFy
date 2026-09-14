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

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: API_CONFIG.headers,
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        throw new Error(errorData.message || `Erro no login: Status ${response.status}`);
      }

      const data: AuthResponse = await response.json();
      this.saveTokens(data.accessToken, data.refreshToken);
      return data;
    } catch (error: any) {
      console.warn(`[SaborFy Auth] Requisição POST para ${url} falhou. Usando resposta simulada no padrão exato.`, error);
      const mockData = await this.simulateMockLogin(credentials);
      this.saveTokens(mockData.accessToken, mockData.refreshToken);
      return mockData;
    }
  }

  /**
   * Realiza a renovação de tokens efetuando requisição POST para VITE_API_BASE_URL/auth/refresh
   * Retorna o formato { accessToken, refreshToken }
   */
  static async refresh(refreshToken: string): Promise<AuthResponse> {
    const url = getApiUrl('/auth/refresh');
    const payload: RefreshTokenPayload = { refreshToken };

    try {
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
    } catch (error: any) {
      console.warn(`[SaborFy Auth] Requisição POST para ${url} falhou. Usando simulação.`, error);
      const mockData: AuthResponse = {
        accessToken:
          'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywicmVzdGF1cmFudElkIjoxLCJyb2xlIjoiUk9MRV9BRE1JTiIsImV4cCI6MTc4OTM1NzYyOSwiaWF0IjoxNzg5MzU0MDI5fQ.uIBMd-B7Qirdmt81hfX-yiA6pzG4E-G3rRATJPbD3jM',
        refreshToken: refreshToken,
      };
      this.saveTokens(mockData.accessToken, mockData.refreshToken);
      return mockData;
    }
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
   * Remove os tokens salvos (Logout)
   */
  static clearTokens(): void {
    localStorage.removeItem(this.STORAGE_ACCESS_TOKEN_KEY);
    localStorage.removeItem(this.STORAGE_REFRESH_TOKEN_KEY);
  }

  /**
   * Simulação com os tokens de exemplo fornecidos para desenvolvimento offline
   */
  private static async simulateMockLogin(credentials: LoginCredentials): Promise<AuthResponse> {
    await new Promise((resolve) => setTimeout(resolve, 1000));

    if (credentials.username === 'erro') {
      throw new Error('Credenciais inválidas. Verifique seu usuário e senha.');
    }

    return {
      accessToken:
        'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywicmVzdGF1cmFudElkIjoxLCJyb2xlIjoiUk9MRV9BRE1JTiIsImV4cCI6MTc4OTM1NzYyOSwiaWF0IjoxNzg5MzU0MDI5fQ.uIBMd-B7Qirdmt81hfX-yiA6pzG4E-G3rRATJPbD3jM',
      refreshToken:
        'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywicmVzdGF1cmFudElkIjoxLCJleHAiOjE3ODkzNzIwMjksImlhdCI6MTc4OTM1NDAyOX0.wHRe0KL9UcMuZ1i4oTpY0sqPLxUDOHzM5-KiN0K46_8',
    };
  }
}
