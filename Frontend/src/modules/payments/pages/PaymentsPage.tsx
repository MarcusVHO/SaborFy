import React from 'react';
import { CreditCard } from 'lucide-react';

export const PaymentsPage: React.FC = () => {
  return (
    <div className="h-full flex flex-col items-center justify-center text-center p-8">
      <div className="w-16 h-16 bg-teal-50 rounded-full flex items-center justify-center mb-4">
        <CreditCard size={32} className="text-teal-500" />
      </div>
      <h1 className="text-2xl font-bold text-slate-900 mb-2">Pagamentos</h1>
      <p className="text-slate-500 max-w-md">
        Em breve, acesse o histórico de transações, fechamento de caixa e relatórios financeiros.
      </p>
    </div>
  );
};
