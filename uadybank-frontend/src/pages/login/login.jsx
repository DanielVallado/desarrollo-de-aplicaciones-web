import { useState, useEffect } from "react";
import { CSSTransition } from "react-transition-group";
import HeaderLogin from "/src/modules/login/HeaderLogin";
import Footer from "/src/modules/shared/footer";
import LoginForm from "/src/modules/login/LoginForm";
import RegisterForm from "/src/modules/login/RegisterForm";
import LoginPanel from "/src/modules/login/LoginPanel";
import LoginService from "/src/services/loginService";
import "./login-style.css";

export const Login = () => {
  const [isRegister, setIsRegister] = useState(false);

  const handleButtonClick = (type) => {
    setIsRegister(type === "register");
  };

  useEffect(() => {
    LoginService.logout();
  }, []);

  return (
    <div>
      <HeaderLogin />

      <main className="login">
        <section className="login__container card">
          <div className={`front-container ${isRegister ? "active" : ""}`}>
            <LoginPanel
              type="register"
              onButtonClick={handleButtonClick}
              isActive={!isRegister}
            />
            <LoginPanel
              type="login"
              onButtonClick={handleButtonClick}
              isActive={isRegister}
            />
          </div>

          <CSSTransition
            in={isRegister}
            timeout={{ enter: 1000, exit: 0 }}
            classNames="fade"
          >
            <div
              className={`back-container--register ${
                isRegister ? "active" : ""
              }`}
            >
              <RegisterForm />
            </div>
          </CSSTransition>
          <CSSTransition
            in={!isRegister}
            timeout={{ enter: 1000, exit: 0 }}
            classNames="fade"
          >
            <div
              className={`back-container--login ${!isRegister ? "active" : ""}`}
            >
              <LoginForm />
            </div>
          </CSSTransition>
        </section>
      </main>

      <Footer />
    </div>
  );
};

export default Login;
