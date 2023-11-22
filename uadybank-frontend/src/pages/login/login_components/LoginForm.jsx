import React from "react";
import "./login-forms-style.css";

export const LoginForm = () => {
  return (
    <div className="login-section">
      <form className="login-form">
        <h2 className="form__title">Iniciar sesión</h2>

        <input type="text" placeholder="Correo electrónico" />
        <input type="password" placeholder="Contraseña" />
        <a href="/">¿Olvidaste tu contraseña?</a>
        <input value="Iniciar sesión" type="submit" />
      </form>
    </div>
  );
};

export default LoginForm;
