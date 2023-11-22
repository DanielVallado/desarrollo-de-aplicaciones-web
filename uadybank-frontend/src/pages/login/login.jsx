import React from "react";
import uadybankLogo from "../../assets/uadybank-white.svg";
import Footer from "../../modules/shared/footer";
import "./login-style.css";
import HeaderLogin from "./HeaderLogin";
import LoginForm from "./login_components/LoginForm";
import RegisterForm from "./login_components/RegisterForm";
import LoginPanel from "./login_components/LoginPanel";

export const Login = () => {
  const handleButtonClick = (type) => {
    //! TODO Lógica para manejar el clic en el botón
    console.log(`${type} button clicked`);
  };

  return (
    <div>
      <HeaderLogin />

      <main className="login">
        <section className="login__container card">
          <div className="front-container">
            <LoginPanel type="register" onButtonClick={handleButtonClick} />
            {/* <LoginPanel type="login" onButtonClick={handleButtonClick} /> */}
          </div>

          <div className="back-container">
            <LoginForm />
            {/* <RegisterForm /> */}
          </div>
        </section>
      </main>

      <Footer />
    </div>
  );
};

export default Login;
