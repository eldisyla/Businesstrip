import React from "react";
import { BrowserRouter as Router, Routes, Route, NavLink } from "react-router-dom";

import Home from "./components/Home";
import KundeList from "./components/KundeList";
import ReiseErstellenPage from "./components/ReiseErstellenPage";
import BuchungList from "./components/BuchungList";
import MitreisendeList from "./components/MitreisendeList";

import "./App.css";

function App() {
  return (
    <Router>
      <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
        <h1>BusinessTrips App</h1>

        <nav className="main-nav">
          <div className="nav-group">
            <div className="nav-group-title">🏠 Dashboard / Start</div>
            <NavLink to="/" end className={({ isActive }) => (isActive ? "active" : "")}>Home</NavLink>
          </div>

          <div className="nav-group">
            <div className="nav-group-title">🔹 Personenverwaltung</div>
            <NavLink to="/kunden" className={({ isActive }) => (isActive ? "active" : "")}>👤 Kunden</NavLink>
            <NavLink to="/mitreisende" className={({ isActive }) => (isActive ? "active" : "")}>🤝 Mitreisende</NavLink>
          </div>

          <div className="nav-group">
            <div className="nav-group-title">🔹 Reiseplanung & Verwaltung</div>
            <NavLink to="/reisen" className={({ isActive }) => (isActive ? "active" : "")}>🌍 Reisen</NavLink>
            <NavLink to="/buchungen" className={({ isActive }) => (isActive ? "active" : "")}>🧾 Buchungen</NavLink>
          </div>
        </nav>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/kunden" element={<KundeList />} />
          <Route path="/reisen" element={<ReiseErstellenPage />} />
          <Route path="/buchungen" element={<BuchungList />} />
          <Route path="/mitreisende" element={<MitreisendeList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
