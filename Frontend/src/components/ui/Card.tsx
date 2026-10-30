import React, { type HTMLAttributes } from 'react';

export interface CardProps extends HTMLAttributes<HTMLDivElement> {
  glass?: boolean;
}

export const Card: React.FC<CardProps> = ({ children, glass = true, className = '', ...props }) => {
  const cardClasses = glass
    ? 'bg-dark-800/75 backdrop-blur-xl border border-white/10 shadow-2xl shadow-black/50 rounded-3xl p-7'
    : 'bg-dark-800 border border-white/5 rounded-3xl p-7 shadow-xl';

  return (
    <div className={`${cardClasses} ${className}`} {...props}>
      {children}
    </div>
  );
};
