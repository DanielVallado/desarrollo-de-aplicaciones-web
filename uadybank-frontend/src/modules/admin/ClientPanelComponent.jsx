import PropTypes from "prop-types";
import "./client-panel-component-style.css";

export const ClientPanelComponent = ({ client }) => {
  return (
    <article className="card client-panel">
      <h3 className="client-panel__name">{client.name}</h3>
      <output className="client-panel__data">{client.matricula}</output>
      <output className="client-panel__data">{client.email}</output>
      <div className="client-panel__icons">
        <img
          src="/src/assets/icons/modify-icon.svg"
          alt="Icono modificar"
          className="client-panel__icon"
        />
        <img
          src="/src/assets/icons/card-icon.svg"
          alt="Icono tarjeta"
          className="client-panel__icon"
        />
        <img
          src="/src/assets/icons/delete-icon.svg"
          alt="Icono eliminar"
          className="client-panel__icon"
        />
      </div>
      <img
        src="/src/assets/icons/drop-down-icon.svg"
        alt="Icono desplegable"
        className="client-panel__drop-down"
      />
    </article>
  );
};

ClientPanelComponent.propTypes = {
  client: PropTypes.object.isRequired,
};

export default ClientPanelComponent;
