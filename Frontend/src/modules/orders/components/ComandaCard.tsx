import React from 'react';
import { Clock, User } from 'lucide-react';

export interface Comanda {
  id: string;
  clientName: string;
  section: string;
  tableNumber?: string;
  status: 'active' | 'closing' | 'delayed';
  total: number;
  timeOpen: string; // e.g. "01:25"
}

interface ComandaCardProps {
  comanda: Comanda;
}

export const ComandaCard: React.FC<ComandaCardProps> = ({ comanda }) => {
  const statusConfig = {
    active: { bg: 'bg-emerald-500', text: 'text-emerald-700', label: 'Aberta' },
    closing: { bg: 'bg-amber-500', text: 'text-amber-700', label: 'Fechando' },
    delayed: { bg: 'bg-rose-500', text: 'text-rose-700', label: 'Atrasada' },
  };

  const currentStatus = statusConfig[comanda.status];

  return (
    <div className="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden hover:shadow-md transition-shadow flex flex-col cursor-pointer">
      <div className="p-4 flex-1">
        <div className="flex justify-between items-start mb-3">
          <div className="flex items-center gap-2">
            <span className="font-bold text-slate-800 text-lg">#{comanda.id}</span>
            <div className={`px-2 py-0.5 rounded-full text-[10px] font-bold uppercase tracking-wider ${currentStatus.bg} bg-opacity-15 ${currentStatus.text}`}>
              {currentStatus.label}
            </div>
          </div>
          <div className="text-right">
            <div className="text-xs font-semibold text-slate-400 uppercase tracking-wider">
              {comanda.section}
            </div>
            {comanda.tableNumber && (
              <div className="font-bold text-slate-700 text-sm">
                Mesa {comanda.tableNumber}
              </div>
            )}
          </div>
        </div>
        
        <div className="flex items-center gap-2 text-slate-600 mb-4">
          <User size={16} className="text-slate-400" />
          <span className="font-medium truncate" title={comanda.clientName}>
            {comanda.clientName}
          </span>
        </div>
      </div>
      
      <div className="bg-slate-50 px-4 py-3 border-t border-slate-100 flex items-center justify-between">
        <div className="flex items-center gap-1.5 text-slate-500 text-sm font-medium">
          <Clock size={16} />
          <span>{comanda.timeOpen}</span>
        </div>
        <div className="font-bold text-slate-900 text-lg">
          {new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(comanda.total)}
        </div>
      </div>
    </div>
  );
};
