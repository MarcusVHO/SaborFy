import React from 'react';
import { AuthHero } from '../components/AuthHero';
import { LoginForm } from '../components/LoginForm';

export const LoginPage: React.FC = () => {
  const handleLoginSuccess = () => {
    // Callback executado ao autenticar com sucesso (ex: redirecionamento de rota)
  };

  return (
    <main className="min-h-screen w-screen flex items-center justify-center p-4 sm:p-6 bg-dark-900 bg-[radial-gradient(ellipse_80%_80%_at_50%_-20%,rgba(249,115,22,0.15),rgba(255,255,255,0))]">
      <div className="grid grid-cols-1 lg:grid-cols-2 w-full max-w-6xl min-h-[640px] bg-dark-800/60 border border-white/10 rounded-3xl shadow-2xl shadow-black/80 overflow-hidden backdrop-blur-xl">
        {/* Left Side: Brand Visual & Features */}
        <div className="hidden lg:block">
          <AuthHero />
        </div>

        {/* Right Side: Login Form */}
        <section className="flex items-center justify-center p-6 sm:p-12">
          <LoginForm onSuccess={handleLoginSuccess} />
        </section>
      </div>
    </main>
  );
};
