import { apiFetch } from '../../../config/api.config';
import type { User, RoleName, PageResponse, CreateUserPayload } from '../types/user.types';

export class UserService {
  static async getUsers(
    searchText?: string, 
    roleName?: RoleName | '', 
    page: number = 0, 
    size: number = 20
  ): Promise<PageResponse<User>> {
    const params = new URLSearchParams();
    
    if (searchText && searchText.trim().length > 0) {
      params.append('searchText', searchText.trim());
    }
    
    if (roleName && roleName.trim().length > 0) {
      params.append('roleName', roleName);
    }

    params.append('page', page.toString());
    params.append('size', size.toString());

    const queryString = params.toString();
    const endpoint = `/user${queryString ? `?${queryString}` : ''}`;
    
    const response = await apiFetch(endpoint, {
      method: 'GET',
    });

    if (!response.ok) {
      throw new Error('Falha ao buscar usuários da API');
    }

    return await response.json() as PageResponse<User>;
  }

  static async createUser(payload: CreateUserPayload): Promise<User> {
    const response = await apiFetch('/user/register', {
      method: 'POST',
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      
      if (response.status === 400 && Array.isArray(errorData.fieldErrors)) {
        const passwordError = errorData.fieldErrors.find((err: any) => err.field === 'password');
        if (passwordError) {
          throw new Error('A senha deve ter pelo menos 8 caracteres, letras maiúsculas, minúsculas, números e caracteres especiais.');
        }
        
        // Fallback genérico para outros erros de campo
        const firstError = errorData.fieldErrors[0];
        if (firstError) {
          throw new Error(firstError.message);
        }
      }

      throw new Error(errorData.message || 'Falha ao criar usuário.');
    }

    return await response.json() as User;
  }
}
