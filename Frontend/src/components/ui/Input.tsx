import React, { type InputHTMLAttributes, forwardRef } from 'react';

export interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  label?: string;
  error?: string;
  hint?: string;
  leftIcon?: React.ReactNode;
  rightIcon?: React.ReactNode;
}

export const Input = forwardRef<HTMLInputElement, InputProps>(
  ({ label, error, hint, leftIcon, rightIcon, className = '', id, ...props }, ref) => {
    const inputId = id || (label ? label.toLowerCase().replace(/\s+/g, '-') : undefined);

    return (
      <div className="flex flex-col gap-1.5 w-full">
        {label && (
          <label htmlFor={inputId} className="text-sm font-medium text-gray-200">
            {label}
          </label>
        )}
        <div className="relative flex items-center w-full">
          {leftIcon && (
            <span className="absolute left-3.5 text-gray-400 pointer-events-none flex items-center justify-center">
              {leftIcon}
            </span>
          )}
          <input
            ref={ref}
            id={inputId}
            className={`w-full py-3 px-4 text-sm text-gray-100 bg-dark-800/80 border border-white/10 rounded-xl placeholder-gray-500 backdrop-blur-md transition-all duration-200 focus:bg-dark-800 focus:border-sabor-500 focus:ring-2 focus:ring-sabor-500/20 focus:outline-none ${
              error ? 'border-red-500/80 focus:border-red-500 focus:ring-red-500/20' : ''
            } ${leftIcon ? 'pl-11' : ''} ${rightIcon ? 'pr-11' : ''} ${className}`}
            {...props}
          />
          {rightIcon && (
            <span className="absolute right-3.5 text-gray-400 flex items-center justify-center">
              {rightIcon}
            </span>
          )}
        </div>
        {error && <span className="text-xs text-red-400 mt-0.5">{error}</span>}
        {hint && !error && <span className="text-xs text-gray-500 mt-0.5">{hint}</span>}
      </div>
    );
  }
);

Input.displayName = 'Input';
