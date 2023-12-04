import uadybankLogo from "/src/assets/uadybank-black.svg";
import "./header-login-style.css";

export const HeaderLogin = () => {
  return (
    <div className="header--login">
      <div className="header__container--login">
        <div className="header__logo--login">
          <a href="#">
            <img src={uadybankLogo} alt="Logo UADYBank" />
            <h1>UADYBANK</h1>
          </a>
        </div>
      </div>
    </div>
  );
};

export default HeaderLogin;
