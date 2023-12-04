import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import PropTypes from "prop-types";
import CardService from "/src/services/CardService";
import "./transfer-component-style.css";

export const TransferView = () => {
  const navigate = useNavigate();
  const { idCard } = useParams();
  const [card, setCard] = useState(null);

  useEffect(() => {
    const cookieValue = Cookies.get("client");
    if (!cookieValue) {
      navigate("/login");
    } else {
      CardService.getCardById(idCard).then((card) => {
        setCard(card);
      });
    }
  }, [idCard, navigate]);

  function formatCardNumber(cardNumber) {
    return cardNumber.toString().replace(/(\d{4})(?=\d)/g, "$1 - ");
  }

  return (
    <>
      {card ? (
        <div className="card transfer__container">
          <section className="tranfer__card-info">
            <picture className="tranfer__card-info__image">
              <source
                srcSet={`/src/assets/cards/${card.cardType}Card.webp`}
                type="image/webp"
              />
              <source
                srcSet={`/src/assets/cards/${card.cardType}Card.avif`}
                type="image/avif"
              />
              <img
                src={`/src/assets/cards/${card.cardType}Card.png`}
                alt={`Imagen tarjeta ${card.cardType}`}
              />
            </picture>

            <div className="card transfer__card-info__data">
              <h3>{card.cardType}</h3>
              <h4>Número de tarjeta</h4>
              <output className="card-info--number">
                {formatCardNumber(card.idCard)}
              </output>
              <h4>Saldo disponible</h4>
              <output className="card-info--balance">${card.balance}</output>
            </div>
          </section>
        </div>
      ) : (
        <p className="loading">Cargando...</p>
      )}
    </>
  );
};

TransferView.propTypes = {
  card: PropTypes.object,
};

export default TransferView;
