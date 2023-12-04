import PropTypes from "prop-types";
import "./card-component-style.css";

export const CardComponent = ({ card, onClick, onDoubleClick }) => {
  return (
    <div
      className="card card-component"
      onClick={onClick}
      onDoubleClick={onDoubleClick}
    >
      <h3>Tarjeta {card.cardType}</h3>

      <picture>
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

      <div className="balance-section">
        <p>Saldo disponible</p>
        <output className="bold">${card.balance.toFixed(2)}</output>
      </div>
    </div>
  );
};

CardComponent.propTypes = {
  card: PropTypes.object.isRequired,
  onClick: PropTypes.func.isRequired,
  onDoubleClick: PropTypes.func.isRequired,
};

export default CardComponent;
