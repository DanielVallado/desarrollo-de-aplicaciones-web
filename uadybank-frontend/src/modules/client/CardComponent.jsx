import React from "react";
import PropTypes from "prop-types";
import "./card-component-style.css";

export const CardComponent = ({ card, onClick }) => {
  function getCardImage(card) {
    switch (card.cardType) {
      case "Classic":
        return "/src/assets/cards/ClassicCard.png";
      case "Silver":
        return "/src/assets/cards/SilverCard.png";
      case "Black":
        return "/src/assets/cards/BlackCard.png";
      default:
        return "/src/assets/cards/ClassicCard.png";
    }
  }

  return (
    <div className="card card-component" onClick={onClick}>
      <h3>Tarjeta {card.cardType}</h3>
      <img
        src={getCardImage(card.cardType)}
        alt={`Imagen tarjeta ${card.cardType}`}
      />
      <div className="balance-section">
        <p>Saldo disponible</p>
        <p className="bold">${card.balance.toFixed(2)}</p>
      </div>
    </div>
  );
};

CardComponent.propTypes = {
  card: PropTypes.object.isRequired,
  onClick: PropTypes.func.isRequired,
};

export default CardComponent;
