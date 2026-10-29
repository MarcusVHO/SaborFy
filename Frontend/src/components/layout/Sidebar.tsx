import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { 
  ClipboardList, 
  ChefHat,
  Bike,
  Utensils, 
  Users, 
  Layers,
  CreditCard,
  Settings,
  LogOut,
  Coffee,
  X,
  ShoppingBag
} from 'lucide-react';
import { AuthService } from '../../modules/auth/services/auth.service';

interface SidebarProps {
  isCollapsed: boolean;
  isMobileOpen: boolean;
  setIsMobileOpen: (isOpen: boolean) => void;
}

export const Sidebar: React.FC<SidebarProps> = ({ isCollapsed, isMobileOpen, setIsMobileOpen }) => {
  const navigate = useNavigate();

  const token = AuthService.getAccessToken();
  const payload = token ? AuthService.parseJwt(token) : null;
  const userRole = payload?.role || '';

  const canSeeManagement = ['ADMIN', 'OWNER', 'SYSTEM_ADMIN'].includes(userRole);

  const navItems = [
    { icon: ClipboardList, label: 'Comandas', path: '/' },
    { icon: ShoppingBag, label: 'Pedidos', path: '/orders' },
    { icon: ChefHat, label: 'Cozinha', path: '/kitchen' },
    { icon: Bike, label: 'Delivery', path: '/delivery' },
  ];

  if (canSeeManagement) {
    navItems.push(
      { icon: Utensils, label: 'Cardápio', path: '/menu' },
      { icon: Users, label: 'Equipe', path: '/staff' },
      { icon: Layers, label: 'Seções', path: '/sections' },
      { icon: CreditCard, label: 'Pagamentos', path: '/payments' },
      { icon: Settings, label: 'Configurações', path: '/settings' }
    );
  }

  const handleLogout = async () => {
    await AuthService.logout();
    navigate('/login');
  };

  return (
    <>
      {/* Mobile Overlay */}
      {isMobileOpen && (
        <div 
          className="fixed inset-0 bg-slate-900/50 z-40 lg:hidden transition-opacity"
          onClick={() => setIsMobileOpen(false)}
        />
      )}

      {/* Sidebar */}
      <aside className={`
        fixed lg:static inset-y-0 left-0 z-50
        bg-slate-900 h-screen flex flex-col text-slate-300 transition-all duration-300 ease-in-out
        ${isCollapsed ? 'lg:w-20' : 'lg:w-64'}
        ${isMobileOpen ? 'translate-x-0 w-64' : '-translate-x-full lg:translate-x-0'}
      `}>
        <div className="h-16 flex items-center justify-between px-6 border-b border-slate-800">
          <div className="flex items-center gap-2 text-white overflow-hidden">
            <div className="bg-indigo-500 p-1.5 rounded-lg shrink-0">
              <Coffee size={20} className="text-white" />
            </div>
            <span className={`font-bold text-xl tracking-tight whitespace-nowrap transition-opacity duration-300 ${isCollapsed && !isMobileOpen ? 'opacity-0 lg:hidden' : 'opacity-100'}`}>
              SaborFy
            </span>
          </div>
          {/* Mobile Close Button */}
          <button 
            className="lg:hidden p-1 text-slate-400 hover:text-white"
            onClick={() => setIsMobileOpen(false)}
          >
            <X size={20} />
          </button>
        </div>

        <div className="flex-1 overflow-y-auto py-6 px-4 flex flex-col gap-2">
          <div className={`text-xs font-semibold text-slate-500 uppercase tracking-wider mb-2 px-2 whitespace-nowrap overflow-hidden transition-opacity duration-300 ${isCollapsed && !isMobileOpen ? 'opacity-0 lg:hidden' : 'opacity-100'}`}>
            Gestão
          </div>
          
          {navItems.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
              title={isCollapsed && !isMobileOpen ? item.label : undefined}
              onClick={() => setIsMobileOpen(false)}
              className={({ isActive }) => 
                `flex items-center gap-3 px-3 py-2.5 rounded-lg transition-colors overflow-hidden ${
                  isActive 
                    ? 'bg-indigo-600/10 text-indigo-400 font-medium' 
                    : 'hover:bg-slate-800 hover:text-white'
                } ${isCollapsed && !isMobileOpen ? 'lg:justify-center' : ''}`
              }
            >
              <item.icon size={20} className="shrink-0" />
              <span className={`whitespace-nowrap transition-all duration-300 ${isCollapsed && !isMobileOpen ? 'opacity-0 w-0 lg:hidden' : 'opacity-100'}`}>
                {item.label}
              </span>
            </NavLink>
          ))}
        </div>

        <div className="p-4 border-t border-slate-800">
          <button 
            title={isCollapsed && !isMobileOpen ? 'Sair' : undefined}
            onClick={handleLogout}
            className={`flex items-center gap-3 px-3 py-2.5 w-full rounded-lg hover:bg-slate-800 hover:text-white transition-colors text-slate-400 overflow-hidden ${isCollapsed && !isMobileOpen ? 'lg:justify-center' : ''}`}
          >
            <LogOut size={20} className="shrink-0" />
            <span className={`whitespace-nowrap transition-all duration-300 ${isCollapsed && !isMobileOpen ? 'opacity-0 w-0 lg:hidden' : 'opacity-100'}`}>
              Sair
            </span>
          </button>
        </div>
      </aside>
    </>
  );
};
