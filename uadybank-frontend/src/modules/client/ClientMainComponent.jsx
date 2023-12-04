import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import CardComponent from "/src/modules/client/CardComponent";
import PanelCardInfo from "/src/modules/client/PanelCardInfo";
import AccountService from "/src/services/AccountService";
import "./client-main-component-style.css";

export const ClientMainComponent = () => {
  const navigate = useNavigate();

  const [client, setClient] = useState({});
  const [cards, setCards] = useState([]);
  const [selectedCard, setSelectedCard] = useState(null);

  useEffect(() => {
    const cookieValue = Cookies.get("client");
    if (!cookieValue) {
      navigate("/login");
    } else {
      const fetchCards = async () => {
        const account = await AccountService.getAccountByMatricula();
        setClient(account.client);
        setCards(account.cards);
        setSelectedCard(account.cards[0]);
      };

      fetchCards();
    }
  }, [navigate]);

  const handleCardClick = (card) => {
    setSelectedCard(card);
  };

  const handleDoubleClick = (card) => {
    navigate(`/client/transfer/${card.idCard}`);
  };

  return (
    <section className="card client-main-view">
      <h2 className="saludo">Bienvenido, {client.name}</h2>
      <PanelCardInfo card={selectedCard} />

      <section className="card-list">
        {cards.map((card, index) => (
          <CardComponent
            key={index}
            card={card}
            onClick={() => handleCardClick(card)}
            onDoubleClick={() => handleDoubleClick(card)}
          />
        ))}
      </section>
    </section>
  );
};

export default ClientMainComponent;
