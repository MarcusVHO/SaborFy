export interface LoginCredentials {
  username: string;
  password: string;
  rememberMe?: boolean;
}

export interface RefreshTokenPayload {
  refreshToken: string;
}

export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
}

export interface JwtPayload {
  id?: number | string;
  restaurantId?: number | string;
  role?: string;
  sub?: string;
  exp?: number;
  iat?: number;
  [key: string]: any;
}

export interface UserProfile {
  id: string;
  username: string;
  role?: string;
  restaurantId?: number | string;
}

export interface AuthState {
  user: UserProfile | null;
  accessToken: string | null;
  refreshToken: string | null;
  isAuthenticated: boolean;
  isLoading: boolean;
  error: string | null;
}
