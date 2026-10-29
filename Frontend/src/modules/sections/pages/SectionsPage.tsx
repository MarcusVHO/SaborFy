import React from 'react';
import { Layers } from 'lucide-react';

export const SectionsPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col items-center justify-center text-center p-8">
      <div className="w-16 h-16 bg-purple-50 rounded-full flex items-center justify-center mb-4">
        <Layers size={32} className="text-purple-500" />
      </div>
      <h1 className="text-2xl font-bold text-slate-900 mb-2">Gerenciar Seções</h1>
      <p className="text-slate-500 max-w-md">
        Em breve, crie e organize o mapa das mesas, divididas por seções (Salão, Deck, Balcão).
      </p>
    </div>
  );
};
