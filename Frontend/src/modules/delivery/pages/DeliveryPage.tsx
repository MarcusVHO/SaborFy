import React from 'react';
import { Bike } from 'lucide-react';

export const DeliveryPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col items-center justify-center text-center p-8">
      <div className="w-16 h-16 bg-blue-50 rounded-full flex items-center justify-center mb-4">
        <Bike size={32} className="text-blue-500" />
      </div>
      <h1 className="text-2xl font-bold text-slate-900 mb-2">Pedidos do Delivery</h1>
      <p className="text-slate-500 max-w-md">
        Em breve, esta tela exibirá os pedidos de delivery, integração com iFood e acompanhamento de entregadores.
      </p>
    </div>
  );
};
