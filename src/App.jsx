import React, { useState } from "react";
import KundeList from "./components/KundeList";
import LeistungList from "./components/LeistungList";
import MitarbeiterList from "./components/MitarbeiterList";
import ReiseList from "./components/ReiseList";
import BuchungList from "./components/BuchungList";
import MitreisendeList from "./components/MitreisendeList";
import HotelList from "./components/HotelList";

function App() {
  const [view, setView] = useState("kunden");

  return (
    <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
      <h1>BusinessTrips App</h1>

      <nav style={{ marginBottom: "20px", display: "flex", gap: "10px", flexWrap: "wrap" }}>
        <button onClick={() => setView("kunden")}>Kunden</button>
        <button onClick={() => setView("leistungen")}>Leistungen</button>
        <button onClick={() => setView("mitarbeiter")}>Mitarbeiter</button>
        <button onClick={() => setView("reisen")}>Reisen</button>
        <button onClick={() => setView("buchungen")}>Buchungen</button>
        <button onClick={() => setView("mitreisende")}>Mitreisende</button>
        <button onClick={() => setView("hotels")}>Hotels</button>
      </nav>

      {view === "kunden" && <KundeList />}
      {view === "leistungen" && <LeistungList />}
      {view === "mitarbeiter" && <MitarbeiterList />}
      {view === "reisen" && <ReiseList />}
      {view === "buchungen" && <BuchungList />}
      {view === "mitreisende" && <MitreisendeList />}
      {view === "hotels" && <HotelList />}
    </div>
  );
}

export default App;
