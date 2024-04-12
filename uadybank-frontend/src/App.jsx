import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import "./styles/normalize.css";
import "./styles/general-styles.css";
import Login from "/src/pages/login/login";
import AdminMainView from "/src/pages/admin/AdminMainView";
import ClientMainView from "/src/pages/client/ClientMainView";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<Login />} />
          <Route exact path="/login" element={<Login />} />
          <Route path="/client/*" element={<ClientMainView />} />
          <Route path="/administrator/*" element={<AdminMainView />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
