import React, { useState, useEffect } from 'react';
import { Search, Filter, Plus, UserCircle, BadgeCheck, XCircle, ChevronLeft, ChevronRight } from 'lucide-react';
import { UserService } from '../services/user.service';
import { ROLE_LABELS } from '../types/user.types';
import type { User, RoleName, PageResponse } from '../types/user.types';
import { UserFormModal } from '../components/UserFormModal';

export const StaffPage: React.FC = () => {
  const [pageData, setPageData] = useState<PageResponse<User> | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // Filtros
  const [searchText, setSearchText] = useState('');
  const [selectedRole, setSelectedRole] = useState<RoleName | ''>('');
  
  // Paginação
  const [page, setPage] = useState(0);
  const pageSize = 12;

  // Modal State
  const [isUserModalOpen, setIsUserModalOpen] = useState(false);

  // Debounce effect para o searchText e dependências de filtro
  useEffect(() => {
    // Reset page to 0 when filters change
    setPage(0);
  }, [searchText, selectedRole]);

  const fetchUsers = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await UserService.getUsers(searchText, selectedRole, page, pageSize);
      setPageData(data);
    } catch (err) {
      console.error(err);
      setError('Não foi possível carregar a lista de equipe. Tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const timerId = setTimeout(() => {
      fetchUsers();
    }, 500); // 500ms debounce

    return () => clearTimeout(timerId);
  }, [searchText, selectedRole, page]);

  const handlePreviousPage = () => {
    if (page > 0) setPage(p => p - 1);
  };

  const handleNextPage = () => {
    if (pageData && !pageData.last) setPage(p => p + 1);
  };

  const handleUserCreated = () => {
    setIsUserModalOpen(false);
    // Se estivemos na página 0, apenas atualiza. Se não, volta para 0
    if (page === 0) {
      fetchUsers();
    } else {
      setPage(0);
    }
  };

  const users = pageData?.content || [];

  return (
    <div className="h-full flex flex-col">
      <div className="flex flex-col sm:flex-row sm:items-center justify-between mb-6 gap-4">
        <div>
          <h1 className="text-2xl font-bold text-slate-900 tracking-tight hidden">Equipe</h1>
          <p className="text-sm text-slate-500">Gerencie garçons, caixas e administradores do sistema.</p>
        </div>
      </div>

      <div className="bg-white p-3 md:p-4 rounded-xl shadow-sm border border-slate-200 mb-6 flex flex-col lg:flex-row gap-3 items-stretch lg:items-center">
        <div className="relative flex-1">
          <Search size={18} className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" />
          <input 
            type="text" 
            placeholder="Buscar por nome ou matrícula..." 
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
            className="w-full pl-9 pr-4 py-2.5 bg-slate-50 border border-slate-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all text-sm"
          />
        </div>
        
        <div className="flex flex-col sm:flex-row gap-3 items-center w-full lg:w-auto">
          <div className="relative w-full sm:w-auto sm:min-w-[160px] md:min-w-[180px]">
            <Filter size={18} className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" />
            <select 
              value={selectedRole}
              onChange={(e) => setSelectedRole(e.target.value as RoleName | '')}
              className="w-full pl-9 pr-8 py-2.5 bg-slate-50 border border-slate-200 rounded-lg appearance-none focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all text-sm text-slate-700 cursor-pointer"
            >
              <option value="">Todos os Cargos</option>
              {Object.entries(ROLE_LABELS).map(([key, label]) => (
                <option key={key} value={key}>{label}</option>
              ))}
            </select>
          </div>

          <button 
            onClick={() => setIsUserModalOpen(true)}
            className="flex w-full sm:w-auto items-center justify-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-5 py-2.5 rounded-lg font-medium transition-colors shadow-sm shrink-0 text-sm"
          >
            <Plus size={18} />
            <span>Adicionar Usuário</span>
          </button>
        </div>
      </div>

      <div className="flex-1 flex flex-col">
        {loading && !pageData ? (
          <div className="flex justify-center items-center h-48">
            <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-indigo-600"></div>
          </div>
        ) : error ? (
          <div className="bg-red-50 text-red-600 p-4 rounded-lg text-center border border-red-100">
            {error}
          </div>
        ) : users.length === 0 ? (
          <div className="flex flex-col items-center justify-center text-center p-8 bg-white border border-slate-200 border-dashed rounded-xl h-64">
            <UserCircle size={48} className="text-slate-300 mb-4" />
            <h3 className="text-lg font-semibold text-slate-900 mb-1">Nenhum usuário encontrado</h3>
            <p className="text-slate-500 max-w-sm">
              Não encontramos nenhum funcionário com os filtros selecionados.
            </p>
          </div>
        ) : (
          <>
            <div className={`bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden transition-opacity duration-200 ${loading ? 'opacity-50' : 'opacity-100'}`}>
              <div className="overflow-x-auto">
                <table className="w-full text-left border-collapse">
                  <thead>
                    <tr className="bg-slate-50 border-b border-slate-200 text-xs uppercase tracking-wider text-slate-500 font-semibold">
                      <th className="px-6 py-4">Funcionário</th>
                      <th className="px-6 py-4">Matrícula</th>
                      <th className="px-6 py-4">Cargo</th>
                      <th className="px-6 py-4">Status</th>
                      <th className="px-6 py-4 text-right">Ações</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-200">
                    {users.map((user) => (
                      <tr key={user.id} className="hover:bg-slate-50/50 transition-colors group">
                        <td className="px-6 py-4 whitespace-nowrap">
                          <div className="flex items-center gap-3">
                            <div className="w-10 h-10 rounded-full bg-indigo-50 flex items-center justify-center shrink-0">
                              <span className="text-sm font-bold text-indigo-600">
                                {user.name.charAt(0).toUpperCase()}
                              </span>
                            </div>
                            <span className="font-semibold text-slate-900">{user.name}</span>
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-600 font-mono">
                          {user.registration}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          <span className="inline-flex items-center px-2.5 py-1 rounded-full text-xs font-medium bg-indigo-50 text-indigo-700 border border-indigo-100">
                            {ROLE_LABELS[user.role] || user.role}
                          </span>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          {user.active ? (
                            <span className="inline-flex items-center gap-1.5 text-sm text-emerald-600 font-medium">
                              <BadgeCheck size={16} />
                              Ativo
                            </span>
                          ) : (
                            <span className="inline-flex items-center gap-1.5 text-sm text-slate-400 font-medium">
                              <XCircle size={16} />
                              Inativo
                            </span>
                          )}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap text-right">
                          <button className="text-sm text-indigo-600 font-medium hover:text-indigo-800 transition-colors opacity-0 group-hover:opacity-100 focus:opacity-100">
                            Editar
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>

            {/* Controles de Paginação */}
            {pageData && pageData.totalPages > 1 && (
              <div className="flex items-center justify-between mt-auto pt-4 border-t border-slate-200">
                <span className="text-sm text-slate-500">
                  Mostrando {pageData.number * pageData.size + 1} a {Math.min((pageData.number + 1) * pageData.size, pageData.totalElements)} de {pageData.totalElements}
                </span>
                <div className="flex items-center gap-2">
                  <button 
                    onClick={handlePreviousPage} 
                    disabled={pageData.first || loading}
                    className="p-2 rounded-lg border border-slate-200 text-slate-600 hover:bg-slate-50 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
                  >
                    <ChevronLeft size={18} />
                  </button>
                  <span className="text-sm font-medium text-slate-700 px-2">
                    Página {pageData.number + 1} de {pageData.totalPages}
                  </span>
                  <button 
                    onClick={handleNextPage} 
                    disabled={pageData.last || loading}
                    className="p-2 rounded-lg border border-slate-200 text-slate-600 hover:bg-slate-50 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
                  >
                    <ChevronRight size={18} />
                  </button>
                </div>
              </div>
            )}
          </>
        )}
      </div>

      <UserFormModal 
        isOpen={isUserModalOpen}
        onClose={() => setIsUserModalOpen(false)}
        onSuccess={handleUserCreated}
      />
    </div>
  );
};
