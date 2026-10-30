export type RoleName = 'COOK' | 'WAITER' | 'TELLER' | 'ADMIN' | 'OWNER';

export interface User {
  id: number;
  restaurantId: number;
  registration: string;
  name: string;
  role: RoleName;
  active: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface PageResponse<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  numberOfElements: number;
  first: boolean;
  last: boolean;
  empty: boolean;
}

export interface CreateUserPayload {
  name: string;
  password?: string;
  registration: string;
  role: RoleName;
}

// Para uso nos selects
export const ROLE_LABELS: Record<RoleName, string> = {
  COOK: 'Cozinha',
  WAITER: 'Garçom',
  TELLER: 'Caixa',
  ADMIN: 'Administrador',
  OWNER: 'Proprietário'
};
