import React, { useState } from 'react';
import { Outlet } from 'react-router-dom';
import { Sidebar } from './Sidebar';
import { Header } from './Header';

export const AppLayout: React.FC = () => {
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);
  const [isMobileSidebarOpen, setIsMobileSidebarOpen] = useState(false);

  return (
    <div className="flex h-screen w-full bg-slate-50 overflow-hidden relative">
      <Sidebar 
        isCollapsed={isSidebarCollapsed} 
        isMobileOpen={isMobileSidebarOpen}
        setIsMobileOpen={setIsMobileSidebarOpen}
      />
      <div className="flex-1 flex flex-col h-full overflow-hidden w-full relative">
        <Header 
          toggleSidebar={() => setIsSidebarCollapsed(!isSidebarCollapsed)} 
          toggleMobileSidebar={() => setIsMobileSidebarOpen(true)}
          isCollapsed={isSidebarCollapsed}
        />
        <main className="flex-1 overflow-y-auto p-4 md:p-6 w-full">
          <Outlet />
        </main>
      </div>
    </div>
  );
};
