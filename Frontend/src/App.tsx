import React from 'react';
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom';
import { LoginPage } from './modules/auth/pages/LoginPage';
import { AppLayout } from './components/layout/AppLayout';
import { ComandasPage } from './modules/orders/pages/ComandasPage';
import { OrdersPage } from './modules/orders/pages/OrdersPage';
import { KitchenPage } from './modules/kitchen/pages/KitchenPage';
import { DeliveryPage } from './modules/delivery/pages/DeliveryPage';
import { MenuPage } from './modules/menu/pages/MenuPage';
import { StaffPage } from './modules/staff/pages/StaffPage';
import { SectionsPage } from './modules/sections/pages/SectionsPage';
import { PaymentsPage } from './modules/payments/pages/PaymentsPage';
import { SettingsPage } from './modules/settings/pages/SettingsPage';

const router = createBrowserRouter([
  {
    path: '/login',
    element: <LoginPage />,
  },
  {
    path: '/',
    element: <AppLayout />,
    children: [
      { index: true, element: <ComandasPage /> },
      { path: 'orders', element: <OrdersPage /> },
      { path: 'kitchen', element: <KitchenPage /> },
      { path: 'delivery', element: <DeliveryPage /> },
      { path: 'menu', element: <MenuPage /> },
      { path: 'staff', element: <StaffPage /> },
      { path: 'sections', element: <SectionsPage /> },
      { path: 'payments', element: <PaymentsPage /> },
      { path: 'settings', element: <SettingsPage /> },
      { path: '*', element: <Navigate to="/" replace /> }
    ]
  }
]);

export const App: React.FC = () => {
  return <RouterProvider router={router} />;
};

export default App;
