import "./footer-style.css";
import uadybankLogo from "../../assets/uadybank-white.svg";

export const Footer = () => {
  function getYear() {
    const year = new Date().getFullYear();
    return year;
  }

  return (
    <footer className="footer">
      <div className="footer__logo">
        <a href="#">
          <img src={uadybankLogo} alt="Logo UADYBank" />
          <span className="footer__logo--barra"></span>
          <h2>UADYBANK</h2>
        </a>
      </div>

      <div className="">
        <div className="footer__info">
          <ul>
            <li>
              <a href="">Sobre nosotros</a>
            </li>
            <span className="link__bar"></span>
            <li>
              <a href="">Contacto</a>
            </li>
            <span className="link__bar"></span>
            <li>
              <a href="">Términos y condiciones</a>
            </li>
          </ul>
        </div>

        <div className="footer__bottom">
          <p>Todos los derechos reservados UADYBANK {getYear()} ©</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
