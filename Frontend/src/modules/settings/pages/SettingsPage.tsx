import React from 'react';
import { Settings } from 'lucide-react';

export const SettingsPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col items-center justify-center text-center p-8">
      <div className="w-16 h-16 bg-slate-100 rounded-full flex items-center justify-center mb-4">
        <Settings size={32} className="text-slate-500" />
      </div>
      <h1 className="text-2xl font-bold text-slate-900 mb-2">Configurações</h1>
      <p className="text-slate-500 max-w-md">
        Em breve, ajuste as preferências do sistema, integrações e perfil do estabelecimento.
      </p>
    </div>
  );
};
