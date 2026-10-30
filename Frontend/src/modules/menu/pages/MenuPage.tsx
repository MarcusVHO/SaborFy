import React from 'react';
import { Utensils } from 'lucide-react';

export const MenuPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col items-center justify-center text-center p-8">
      <div className="w-16 h-16 bg-rose-50 rounded-full flex items-center justify-center mb-4">
        <Utensils size={32} className="text-rose-500" />
      </div>
      <h1 className="text-2xl font-bold text-slate-900 mb-2">Cardápio</h1>
      <p className="text-slate-500 max-w-md">
        Em breve, gerencie os produtos, categorias e preços do seu restaurante.
      </p>
    </div>
  );
};
