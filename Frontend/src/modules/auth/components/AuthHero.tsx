import React from 'react';
import { UtensilsCrossed, Shield, Clock, LayoutDashboard } from 'lucide-react';

export const AuthHero: React.FC = () => {
  return (
    <div className="relative flex flex-col justify-between p-10 lg:p-12 bg-gradient-to-br from-dark-800/90 to-dark-900/95 border-r border-white/10 overflow-hidden">
      {/* Background Subtle Glows */}
      <div className="absolute -top-12 -left-12 w-64 h-64 bg-sabor-500/20 rounded-full blur-3xl pointer-events-none animate-[pulseGlow_4s_ease-in-out_infinite]" />
      <div className="absolute -bottom-12 -right-12 w-64 h-64 bg-sabor-500/10 rounded-full blur-3xl pointer-events-none animate-[pulseGlow_4s_ease-in-out_infinite]" />

      {/* Brand Header */}
      <div className="relative z-10 flex items-center gap-3.5">
        <div className="flex items-center justify-center w-12 h-12 rounded-xl bg-gradient-to-br from-sabor-500 to-sabor-600 text-white shadow-lg shadow-sabor-500/25">
          <UtensilsCrossed size={26} />
        </div>
        <div className="flex flex-col">
          <span className="text-2xl font-bold tracking-tight text-white">SaborFy</span>
          <span className="text-xs text-gray-400 font-medium">Gestão Gastronômica</span>
        </div>
      </div>

      {/* Hero Content - Clean & Direct */}
      <div className="relative z-10 my-auto py-8">
        <h1 className="text-3xl font-extrabold leading-tight text-white mb-3">
          Plataforma de Gestão SaborFy
        </h1>
        <p className="text-sm leading-relaxed text-gray-400 mb-8 max-w-md">
          Acesse o sistema para acompanhar pedidos, gerenciar a operação e controlar os processos do seu estabelecimento.
        </p>

        {/* Minimal Feature Indicators */}
        <div className="flex flex-col gap-3">
          <div className="flex items-center gap-3 p-3 bg-white/[0.02] border border-white/5 rounded-xl text-gray-300 text-sm">
            <LayoutDashboard size={18} className="text-sabor-400 shrink-0" />
            <span>Painel operacional integrado</span>
          </div>

          <div className="flex items-center gap-3 p-3 bg-white/[0.02] border border-white/5 rounded-xl text-gray-300 text-sm">
            <Clock size={18} className="text-sabor-400 shrink-0" />
            <span>Acompanhamento em tempo real</span>
          </div>

          <div className="flex items-center gap-3 p-3 bg-white/[0.02] border border-white/5 rounded-xl text-gray-300 text-sm">
            <Shield size={18} className="text-sabor-400 shrink-0" />
            <span>Acesso seguro por perfil de usuário</span>
          </div>
        </div>
      </div>

      {/* Clean Footer */}
      <div className="relative z-10 text-xs text-gray-500">
        <span>SaborFy System © {new Date().getFullYear()}</span>
      </div>
    </div>
  );
};
