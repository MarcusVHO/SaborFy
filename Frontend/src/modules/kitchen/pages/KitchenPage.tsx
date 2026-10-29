import React from 'react';
import { ChefHat } from 'lucide-react';

export const KitchenPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col items-center justify-center text-center p-8">
      <div className="w-16 h-16 bg-orange-50 rounded-full flex items-center justify-center mb-4">
        <ChefHat size={32} className="text-orange-500" />
      </div>
      <h1 className="text-2xl font-bold text-slate-900 mb-2">Painel da Cozinha</h1>
      <p className="text-slate-500 max-w-md">
        Aqui serão exibidos os pedidos em tempo real para a equipe da cozinha preparar, organizados por tempo de espera.
      </p>
    </div>
  );
};
