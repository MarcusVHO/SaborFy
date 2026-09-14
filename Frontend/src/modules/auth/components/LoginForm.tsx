import React, { useState } from 'react';
import { User, Lock, Eye, EyeOff, ArrowRight, AlertCircle, CheckCircle2 } from 'lucide-react';
import { Button } from '../../../components/ui/Button';
import { Input } from '../../../components/ui/Input';
import { Card } from '../../../components/ui/Card';
import { AuthService } from '../services/auth.service';
import type { AuthResponse } from '../types/auth.types';

interface LoginFormProps {
  onSuccess?: (authData: AuthResponse) => void;
}

export const LoginForm: React.FC<LoginFormProps> = ({ onSuccess }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [isSuccess, setIsSuccess] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    if (!username.trim()) {
      setError('Por favor, informe seu usuário.');
      return;
    }
    if (!password || password.length < 4) {
      setError('A senha deve ter pelo menos 4 caracteres.');
      return;
    }

    setIsLoading(true);

    try {
      // Realiza o login: os tokens (accessToken e refreshToken) serão armazenados automaticamente
      const response = await AuthService.login({ username, password, rememberMe });
      
      setIsSuccess(true);
      if (onSuccess) {
        onSuccess(response);
      }
    } catch (err: any) {
      setError(err.message || 'Falha ao autenticar. Tente novamente.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Card glass className="w-full max-w-md bg-transparent border-0 shadow-none p-0">
      {/* Form Header */}
      <div className="mb-7">
        <h2 className="text-2xl font-extrabold text-white tracking-tight mb-1.5">Acessar Conta</h2>
        <p className="text-sm text-gray-400">Informe seu usuário e senha para continuar.</p>
      </div>

      {/* Error Alert */}
      {error && (
        <div
          className="flex items-center gap-3 p-3.5 rounded-xl bg-red-500/10 border border-red-500/30 text-red-300 text-sm mb-5 animate-fade-in"
          role="alert"
        >
          <AlertCircle size={18} className="shrink-0 text-red-400" />
          <span>{error}</span>
        </div>
      )}

      {/* Success View */}
      {isSuccess ? (
        <div className="flex flex-col items-center text-center py-8 gap-4 animate-fade-in">
          <CheckCircle2 size={52} className="text-emerald-400 animate-bounce" />
          <h3 className="text-xl font-bold text-white">Login realizado com sucesso!</h3>
          <p className="text-sm text-gray-400">Redirecionando para o painel...</p>
        </div>
      ) : (
        /* Login Form */
        <form onSubmit={handleSubmit} className="flex flex-col gap-5">
          <Input
            label="Usuário"
            type="text"
            placeholder="Digite seu usuário"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            leftIcon={<User size={18} />}
            required
            autoComplete="username"
          />

          <Input
            label="Senha"
            type={showPassword ? 'text' : 'password'}
            placeholder="••••••••"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            leftIcon={<Lock size={18} />}
            rightIcon={
              <button
                type="button"
                className="bg-transparent text-gray-400 hover:text-white transition-colors p-1"
                onClick={() => setShowPassword(!showPassword)}
                tabIndex={-1}
                aria-label={showPassword ? 'Ocultar senha' : 'Exibir senha'}
              >
                {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
              </button>
            }
            required
            autoComplete="current-password"
          />

          {/* Form Options */}
          <div className="flex items-center justify-between text-xs sm:text-sm">
            <label className="flex items-center gap-2 text-gray-300 cursor-pointer select-none">
              <input
                type="checkbox"
                checked={rememberMe}
                onChange={(e) => setRememberMe(e.target.checked)}
                className="w-4 h-4 rounded border-gray-600 bg-dark-800 text-sabor-500 focus:ring-sabor-500/30 accent-sabor-500"
              />
              <span>Lembrar de mim</span>
            </label>
            <a
              href="#forgot-password"
              className="text-sabor-400 hover:text-sabor-300 transition-colors font-medium"
            >
              Esqueceu a senha?
            </a>
          </div>

          {/* Submit Button */}
          <Button
            type="submit"
            variant="primary"
            size="lg"
            fullWidth
            isLoading={isLoading}
            rightIcon={<ArrowRight size={18} />}
          >
            {isLoading ? 'Autenticando...' : 'Entrar'}
          </Button>
        </form>
      )}
    </Card>
  );
};
