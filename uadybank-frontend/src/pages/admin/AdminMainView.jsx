import { Routes, Route } from "react-router-dom";
import Navbar from "/src/modules/shared/navbar";
import Header from "/src/modules/shared/header";
import Footer from "/src/modules/shared/footer";
import NotFound from "../../modules/shared/NotFound";
import AdminMainComponent from "../../modules/admin/AdminMainComponent";
import "./admin-main-view-style.css";

export const AdminMainView = () => {
  return (
    <div>
      <div className="menu">
        <div className="menu__navbar">
          <Navbar />
        </div>

        <main>
          <Header />
          <Routes>
            <Route exact path="/" element={<AdminMainComponent />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </main>
      </div>
      <Footer />
    </div>
  );
};

export default AdminMainView;
