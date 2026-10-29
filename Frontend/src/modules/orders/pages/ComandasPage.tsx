import React, { useState } from 'react';
import { Search, Filter, Plus, FileText, LayoutGrid, Layers } from 'lucide-react';
import { ComandaCard } from '../components/ComandaCard';
import type { Comanda } from '../components/ComandaCard';

export const ComandasPage: React.FC = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedSection, setSelectedSection] = useState<string>('all');
  const [viewMode, setViewMode] = useState<'comandas' | 'sections'>('comandas');
  
  // Lista de comandas inicia vazia, sem dados fictícios
  const [comandas] = useState<Comanda[]>([]);

  const filteredComandas = comandas.filter(comanda => {
    const matchesSearch = comanda.clientName.toLowerCase().includes(searchTerm.toLowerCase()) || 
                          comanda.id.includes(searchTerm);
    const matchesSection = selectedSection === 'all' || comanda.section === selectedSection;
    
    return matchesSearch && matchesSection;
  });

  return (
    <div className="h-full flex flex-col">
      <div className="bg-white p-3 md:p-4 rounded-xl shadow-sm border border-slate-200 mb-6 flex flex-col lg:flex-row gap-3 items-stretch lg:items-center">
        <div className="relative flex-1">
          <Search size={18} className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" />
          <input 
            type="text" 
            placeholder="Buscar por cliente ou número (#)..." 
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full pl-9 pr-4 py-2.5 bg-slate-50 border border-slate-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all text-sm"
          />
        </div>
        
        <div className="flex flex-col sm:flex-row gap-3 items-center">
          <div className="relative w-full sm:w-auto sm:min-w-[160px] md:min-w-[180px]">
            <Filter size={18} className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" />
            <select 
              value={selectedSection}
              onChange={(e) => setSelectedSection(e.target.value)}
              className="w-full pl-9 pr-8 py-2.5 bg-slate-50 border border-slate-200 rounded-lg appearance-none focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all text-sm text-slate-700 cursor-pointer"
            >
              <option value="all">Todas as Seções</option>
              <option value="Salão">Salão</option>
              <option value="Deck">Deck</option>
              <option value="Balcão">Balcão</option>
              <option value="Delivery">Delivery</option>
            </select>
          </div>

          <div className="flex bg-slate-100 p-1 rounded-lg border border-slate-200 shrink-0 w-full sm:w-auto">
            <button 
              onClick={() => setViewMode('comandas')}
              className={`flex-1 sm:flex-none px-4 py-2 sm:py-1.5 rounded-md text-sm font-medium transition-colors flex items-center justify-center gap-2 ${viewMode === 'comandas' ? 'bg-white shadow-sm text-indigo-700' : 'text-slate-500 hover:text-slate-700'}`}
            >
              <LayoutGrid size={16} />
              <span className="hidden sm:inline">Comandas</span>
            </button>
            <button 
              onClick={() => setViewMode('sections')}
              className={`flex-1 sm:flex-none px-4 py-2 sm:py-1.5 rounded-md text-sm font-medium transition-colors flex items-center justify-center gap-2 ${viewMode === 'sections' ? 'bg-white shadow-sm text-indigo-700' : 'text-slate-500 hover:text-slate-700'}`}
            >
              <Layers size={16} />
              <span className="hidden sm:inline">Seções</span>
            </button>
          </div>

          <button className="flex w-full sm:w-auto items-center justify-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-5 py-2.5 rounded-lg font-medium transition-colors shadow-sm shrink-0 text-sm">
            <Plus size={18} />
            <span>Nova Comanda</span>
          </button>
        </div>
      </div>

      {viewMode === 'comandas' ? (
        filteredComandas.length > 0 ? (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 pb-6">
            {filteredComandas.map(comanda => (
              <ComandaCard key={comanda.id} comanda={comanda} />
            ))}
          </div>
        ) : (
          <div className="flex-1 flex flex-col items-center justify-center text-center p-8 bg-white border border-slate-200 border-dashed rounded-xl">
            <div className="w-16 h-16 bg-slate-50 rounded-full flex items-center justify-center mb-4">
              <FileText size={32} className="text-slate-300" />
            </div>
            <h3 className="text-lg font-semibold text-slate-900 mb-1">Nenhuma comanda aberta</h3>
            <p className="text-slate-500 max-w-sm mb-6">
              Atualmente não há comandas ativas na base de dados. Clique no botão abaixo para registrar a primeira.
            </p>
            <button className="flex items-center justify-center gap-2 bg-indigo-50 hover:bg-indigo-100 text-indigo-700 px-4 py-2 rounded-lg font-medium transition-colors border border-indigo-200">
              <Plus size={18} />
              <span>Criar Primeira Comanda</span>
            </button>
          </div>
        )
      ) : (
        <div className="flex-1 flex flex-col items-center justify-center text-center p-8 bg-white border border-slate-200 rounded-xl">
          <div className="w-16 h-16 bg-indigo-50 rounded-full flex items-center justify-center mb-4">
            <Layers size={32} className="text-indigo-300" />
          </div>
          <h3 className="text-lg font-semibold text-slate-900 mb-1">Visualização por Seções</h3>
          <p className="text-slate-500 max-w-sm">
            Aqui você poderá visualizar e gerenciar suas comandas agrupadas pelas seções e mesas do restaurante (Salão, Deck, Delivery).
          </p>
        </div>
      )}
    </div>
  );
};
