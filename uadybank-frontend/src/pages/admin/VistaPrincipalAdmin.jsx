import React from "react";
import uadybankLogo from "../../assets/uadybank-white.svg";
import Navbar from "/src/modules/shared/navbar";
import Header from "/src/modules/shared/header";
import Footer from "/src/modules/shared/footer";
import "./vista-principal-admin.css";

export const VistaPrincipalAdmin = () => {
  return (
    <div>
      <div className="menu">
        <div className="menu__navbar">
          <Navbar />
        </div>

        <main>
          <section className="menu__header">
            <Header />
          </section>
        </main>
      </div>
      <Footer />
    </div>
  );
};

export default VistaPrincipalAdmin;
