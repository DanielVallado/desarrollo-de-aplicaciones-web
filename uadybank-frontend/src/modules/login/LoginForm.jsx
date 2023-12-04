import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login-forms-style.css";
import LoginService from "/src/services/LoginService";

export const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleInputChange = (e) => {
    setError("");
    const { name, value } = e.target;
    switch (name) {
      case "email":
        setEmail(value);
        break;
      case "password":
        setPassword(value);
        break;
      default:
        break;
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (email.trim() !== "" && password.trim() !== "") {
      LoginService.login(email, password)
        .then((response) => {
          if (response.role === "client") {
            navigate("/client");
          } else if (response.role === "administrator") {
            navigate("/administrator");
          } else {
            setError("Usuario o contraseña incorrectos");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    }
  };

  const handleBlur = (e) => {
    const { value } = e.target;
    if (value.trim() === "") {
      setError(`Todos los campos son requeridos`);
    }
  };

  return (
    <div className={`login-section`}>
      <form className="login-form" onSubmit={handleSubmit}>
        <h2 className="form__title">Iniciar sesión</h2>

        <p className="form__error">{error}</p>

        <input
          type="text"
          placeholder="Correo electrónico"
          className="field field-icon field-icon--email"
          name="email"
          value={email}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />
        <input
          type="password"
          placeholder="Contraseña"
          className="field field-icon field-icon--password"
          name="password"
          value={password}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />
        <a href="/">¿Olvidaste tu contraseña?</a>
        <input
          value="Iniciar sesión"
          type="submit"
          className="button button--login"
        />
      </form>
    </div>
  );
};

export default LoginForm;
