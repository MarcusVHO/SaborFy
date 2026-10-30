import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { Bell, ChevronDown, Menu, PanelLeftClose, PanelLeftOpen } from 'lucide-react';

interface HeaderProps {
  toggleSidebar: () => void;
  toggleMobileSidebar: () => void;
  isCollapsed: boolean;
}

const routeTitles: Record<string, string> = {
  '/': 'Comandas',
  '/kitchen': 'Cozinha',
  '/delivery': 'Delivery',
  '/menu': 'Cardápio',
  '/staff': 'Equipe',
  '/sections': 'Seções',
  '/payments': 'Pagamentos',
  '/settings': 'Configurações'
};

export const Header: React.FC<HeaderProps> = ({ toggleSidebar, toggleMobileSidebar, isCollapsed }) => {
  const [time, setTime] = useState(new Date());
  const location = useLocation();

  const currentTitle = routeTitles[location.pathname] || '';

  useEffect(() => {
    const intervalId = setInterval(() => {
      setTime(new Date());
    }, 1000);
    return () => clearInterval(intervalId);
  }, []);

  const formattedTime = time.toLocaleTimeString('pt-BR', {
    hour: '2-digit',
    minute: '2-digit',
  });
  
  const formattedDate = time.toLocaleDateString('pt-BR', {
    weekday: 'short',
    day: 'numeric',
    month: 'short',
  });

  return (
    <header className="h-16 flex items-center justify-between px-4 md:px-6 bg-white border-b border-gray-200 shrink-0">
      <div className="flex items-center gap-4">
        {/* Mobile menu button */}
        <button 
          onClick={toggleMobileSidebar}
          className="lg:hidden p-2 -ml-2 rounded-lg text-slate-500 hover:bg-slate-100 transition-colors"
        >
          <Menu size={24} />
        </button>
        
        {/* Desktop collapse toggle */}
        <button 
          onClick={toggleSidebar}
          className="hidden lg:flex p-2 -ml-2 rounded-lg text-slate-400 hover:bg-slate-100 hover:text-slate-600 transition-colors"
          title={isCollapsed ? 'Expandir menu' : 'Recolher menu'}
        >
          {isCollapsed ? <PanelLeftOpen size={20} /> : <PanelLeftClose size={20} />}
        </button>

        {/* Page Title */}
        <h1 className="text-xl font-bold text-slate-900 tracking-tight hidden sm:block">
          {currentTitle}
        </h1>
      </div>
      
      <div className="flex items-center gap-2 md:gap-4">
        {/* Clock & Date (moved to right) */}
        <div className="flex flex-col items-end mr-2">
          <span className="text-sm font-medium text-gray-800">{formattedTime}</span>
          <span className="text-xs text-gray-500 capitalize hidden sm:block">{formattedDate}</span>
        </div>

        <button className="p-2 rounded-full hover:bg-gray-100 transition-colors relative text-gray-500 hover:text-gray-900">
          <Bell size={20} />
          <span className="absolute top-2 right-2 w-2 h-2 bg-red-500 rounded-full border-2 border-white"></span>
        </button>
        
        <div className="h-8 w-px bg-gray-200 hidden sm:block"></div>
        
        <button className="flex items-center gap-2 md:gap-3 hover:bg-gray-50 p-1.5 rounded-lg transition-colors">
          <div className="w-8 h-8 rounded-full bg-indigo-600 flex items-center justify-center text-white font-medium text-sm">
            M
          </div>
          <div className="flex flex-col items-start hidden sm:flex">
            <span className="text-sm font-semibold text-gray-800 leading-tight">Marcus</span>
            <span className="text-xs text-gray-500">Gerente</span>
          </div>
          <ChevronDown size={16} className="text-gray-400 hidden sm:block" />
        </button>
      </div>
    </header>
  );
};
