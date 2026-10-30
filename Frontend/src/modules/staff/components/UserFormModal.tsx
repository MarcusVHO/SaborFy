import React, { useState } from 'react';
import { X, Save, AlertCircle, CheckCircle2 } from 'lucide-react';
import { Button } from '../../../components/ui/Button';
import { Input } from '../../../components/ui/Input';
import { UserService } from '../services/user.service';
import { ROLE_LABELS, type CreateUserPayload, type User } from '../types/user.types';

interface UserFormModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSuccess: () => void;
}

export const UserFormModal: React.FC<UserFormModalProps> = ({ isOpen, onClose, onSuccess }) => {
  const [formData, setFormData] = useState<CreateUserPayload>({
    name: '',
    registration: '',
    password: '',
    role: 'WAITER'
  });

  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [createdUser, setCreatedUser] = useState<User | null>(null);

  if (!isOpen) return null;

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleClose = () => {
    if (createdUser) {
      onSuccess(); // atualiza a listagem ao fechar a tela de sucesso
      setCreatedUser(null);
    }
    setFormData({ name: '', registration: '', password: '', role: 'WAITER' });
    setError(null);
    onClose();
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setIsLoading(true);

    try {
      const newUser = await UserService.createUser(formData);
      setCreatedUser(newUser);
    } catch (err: any) {
      setError(err.message || 'Ocorreu um erro ao cadastrar o funcionário.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4 sm:p-6 bg-black/60 backdrop-blur-md animate-fade-in">
      <div className="bg-slate-900/90 border border-white/10 rounded-2xl shadow-2xl w-full max-w-md overflow-hidden animate-slide-up backdrop-blur-xl">
        {createdUser ? (
          /* Tela de Sucesso */
          <div className="p-8 flex flex-col items-center text-center">
            <div className="w-16 h-16 bg-emerald-500/20 rounded-full flex items-center justify-center mb-6">
              <CheckCircle2 size={32} className="text-emerald-400" />
            </div>
            <h2 className="text-2xl font-bold text-white mb-2">Usuário Criado!</h2>
            <p className="text-sm text-slate-400 mb-8">
              O funcionário foi cadastrado com sucesso e já tem acesso ao sistema.
            </p>

            <div className="w-full bg-slate-800/50 rounded-xl p-5 mb-8 border border-white/5 text-left space-y-3">
              <div className="flex flex-col">
                <span className="text-xs text-slate-500 font-medium uppercase tracking-wider">Nome</span>
                <span className="text-sm text-white font-medium">{createdUser.name}</span>
              </div>
              <div className="flex flex-col">
                <span className="text-xs text-slate-500 font-medium uppercase tracking-wider">Matrícula</span>
                <div className="flex items-center justify-between">
                  <span className="text-sm font-mono text-indigo-400">{createdUser.registration}</span>
                </div>
              </div>
              <div className="flex flex-col">
                <span className="text-xs text-slate-500 font-medium uppercase tracking-wider">Cargo</span>
                <span className="text-sm text-white">{ROLE_LABELS[createdUser.role]}</span>
              </div>
            </div>

            <Button
              type="button"
              variant="primary"
              fullWidth
              onClick={handleClose}
            >
              Concluir
            </Button>
          </div>
        ) : (
          /* Formulário de Cadastro */
          <>
            {/* Header */}
            <div className="flex items-center justify-between px-6 py-5 border-b border-white/5">
              <div>
                <h2 className="text-xl font-bold text-white">Novo Funcionário</h2>
                <p className="text-sm text-slate-400 mt-0.5">Cadastre um novo membro na equipe</p>
              </div>
              <button 
                onClick={handleClose}
                className="p-2 text-slate-400 hover:text-white hover:bg-white/10 rounded-xl transition-all"
              >
                <X size={20} />
              </button>
            </div>

            {/* Body */}
            <form onSubmit={handleSubmit} className="p-6">
              {error && (
                <div className="flex items-start gap-3 p-3.5 mb-5 rounded-xl bg-red-500/10 border border-red-500/20 text-red-300 text-sm">
                  <AlertCircle size={18} className="shrink-0 mt-0.5 text-red-400" />
                  <span>{error}</span>
                </div>
              )}

              <div className="space-y-4">
                <Input
                  label="Nome Completo"
                  name="name"
                  placeholder="Ex: João da Silva"
                  value={formData.name}
                  onChange={handleChange}
                  required
                />
                
                <Input
                  label="Matrícula"
                  name="registration"
                  placeholder="Ex: 12345"
                  value={formData.registration}
                  onChange={handleChange}
                  required
                />

                <div className="flex flex-col gap-1.5 w-full">
                  <label className="text-sm font-medium text-gray-200">Cargo</label>
                  <div className="relative">
                    <select 
                      name="role"
                      value={formData.role}
                      onChange={handleChange}
                      className="w-full py-3 px-4 text-sm text-gray-100 bg-slate-800/80 border border-white/10 rounded-xl appearance-none focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all cursor-pointer"
                      required
                    >
                      {Object.entries(ROLE_LABELS).map(([key, label]) => (
                        <option key={key} value={key} className="bg-slate-900">{label}</option>
                      ))}
                    </select>
                    <div className="absolute inset-y-0 right-4 flex items-center pointer-events-none text-slate-400">
                      <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="6 9 12 15 18 9"></polyline></svg>
                    </div>
                  </div>
                </div>

                <Input
                  label="Senha de Acesso"
                  type="password"
                  name="password"
                  placeholder="••••••••"
                  value={formData.password}
                  onChange={handleChange}
                  required
                  autoComplete="new-password"
                />
              </div>

              {/* Footer */}
              <div className="flex items-center justify-end gap-3 mt-8 pt-5 border-t border-white/5">
                <Button
                  type="button"
                  variant="outline"
                  onClick={handleClose}
                  disabled={isLoading}
                  className="border-white/10 text-slate-300 hover:bg-white/5 hover:text-white"
                >
                  Cancelar
                </Button>
                <Button
                  type="submit"
                  variant="primary"
                  isLoading={isLoading}
                  leftIcon={<Save size={18} />}
                >
                  Cadastrar Usuário
                </Button>
              </div>
            </form>
          </>
        )}
      </div>
    </div>
  );
};
