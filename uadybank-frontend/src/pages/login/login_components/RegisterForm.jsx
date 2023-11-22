import React from "react";
import "./login-forms-style.css";

export const RegisterForm = () => {
  return (
    <div className="register-section">
      <form className="register-form">
        <h2 className="form__title">Registro</h2>

        <input type="text" placeholder="Nombre completo" />
        <input type="email" placeholder="Correo electrónico" />
        <input type="email" placeholder="Confirmar correo electrónico" />
        <input type="text" placeholder="Matrícula" />
        <input type="tel" placeholder="Número celular" />
        <input type="text" placeholder="Dirección" />
        <input type="password" placeholder="Contraseña" />
        <input type="password" placeholder="Confirmar ontraseña" />
      </form>
    </div>
  );
};

export default RegisterForm;
