import React from "react";
import uadybankLogo from "/src/assets/uadybank-white.svg";
import Navbar from "/src/modules/shared/navbar";
import Header from "/src/modules/shared/header";
import Footer from "/src/modules/shared/footer";
import "./admin-main-view-style.css";

export const AdminMainView = () => {
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

export default AdminMainView;
