import PropTypes from "prop-types";
import "./burguer-button-style.css";

export const BurguerButton = ({ onClick, isOpen }) => {
  const buttonClasses = `burguer-icon ${isOpen ? "open" : ""}`;

  return (
    <div>
      <div onClick={onClick} className={buttonClasses}>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>
  );
};

BurguerButton.propTypes = {
  onClick: PropTypes.func.isRequired,
  isOpen: PropTypes.bool.isRequired,
};

export default BurguerButton;
