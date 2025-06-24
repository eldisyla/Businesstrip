import React from "react";
import { NavLink } from "react-router-dom";
import "./Navigation.css";

export default function Navigation() {
  return (
    <nav className="main-nav">
      <span className="nav-group-label">Allgemein</span>
      <NavLink to="/" end className={({ isActive }) => (isActive ? "active" : "")}>Home</NavLink>

      <span className="nav-group-label">Personen</span>
      <NavLink to="/kunden" className={({ isActive }) => (isActive ? "active" : "")}>Kunden</NavLink>
      <NavLink to="/mitarbeiter" className={({ isActive }) => (isActive ? "active" : "")}>Mitarbeiter</NavLink>
      <NavLink to="/mitreisende" className={({ isActive }) => (isActive ? "active" : "")}>Mitreisende</NavLink>

      <span className="nav-group-label">Reiseverwaltung</span>
      <NavLink to="/reisen" className={({ isActive }) => (isActive ? "active" : "")}>Reisen</NavLink>
      <NavLink to="/buchungen" className={({ isActive }) => (isActive ? "active" : "")}>Buchungen</NavLink>
      <NavLink to="/leistungen" className={({ isActive }) => (isActive ? "active" : "")}>Leistungen</NavLink>
      <NavLink to="/hotels" className={({ isActive }) => (isActive ? "active" : "")}>Hotels</NavLink>
    </nav>
  );
}
