import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "/src/modules/shared/navbar";
import Header from "/src/modules/shared/header";
import Footer from "/src/modules/shared/footer";
import ClientMainComponent from "/src/modules/client/ClientMainComponent";
import TransferView from "/src/modules/client/TransferView";
import "./client-main-view-style.css";

export const ClientMainView = () => {
  return (
    <div>
      <div className="menu">
        <div className="menu__navbar">
          <Navbar />
        </div>

        <main>
          <Header />
          <Routes>
            <Route exact path="/" element={<ClientMainComponent />} />
            <Route path="/transfer/:cardId" element={<TransferView />} />
          </Routes>
        </main>
      </div>
      <Footer />
    </div>
  );
};

export default ClientMainView;
