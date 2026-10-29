import React from 'react';
import { ShoppingBag } from 'lucide-react';

export const OrdersPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col p-6 animate-fade-in">
      <div className="flex items-center gap-3 mb-6">
        <div className="p-3 bg-indigo-50 text-indigo-600 rounded-xl">
          <ShoppingBag size={24} />
        </div>
        <div>
          <h1 className="text-2xl font-bold text-slate-900 tracking-tight">Histórico de Pedidos</h1>
          <p className="text-sm text-slate-500 mt-1">Visualize todos os pedidos processados no sistema.</p>
        </div>
      </div>

      <div className="flex-1 bg-white rounded-2xl shadow-sm border border-slate-200 flex flex-col items-center justify-center p-8 text-center">
        <div className="w-16 h-16 bg-slate-50 rounded-full flex items-center justify-center mb-4">
          <ShoppingBag size={32} className="text-slate-300" />
        </div>
        <h3 className="text-lg font-semibold text-slate-800 mb-1">Nenhum pedido ainda</h3>
        <p className="text-sm text-slate-500 max-w-sm">
          A lista de pedidos processados aparecerá aqui. Esta tela será implementada nas próximas etapas.
        </p>
      </div>
    </div>
  );
};
