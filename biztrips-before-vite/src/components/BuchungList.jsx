import React, { useEffect, useState } from "react";
import { getReisen } from "../services/reiseService";
import "./BuchungList.module.css";

export default function BuchungList() {
  const [reisen, setReisen] = useState([]);
  const [selectedReise, setSelectedReise] = useState(null);

  useEffect(() => {
    loadReisen();
  }, []);

  const loadReisen = async () => {
    try {
      const data = await getReisen();
      setReisen(data);
    } catch (error) {
      console.error("Fehler beim Laden der Reisen:", error);
    }
  };

  const handleReiseSelect = (reiseId) => {
    const reise = reisen.find((r) => r.reiseId === parseInt(reiseId));
    setSelectedReise(reise);
  };

  const formatPreis = (preis) =>
    preis ? `${preis.toFixed(2)} CHF` : "Keine Angabe";

  return (
    <div className="buchungList">
      <h2>Reisebuchung Übersicht</h2>

      <label>Reise auswählen:</label>
      <select onChange={(e) => handleReiseSelect(e.target.value)}>
        <option value="">Bitte wählen</option>
        {reisen.map((r) => (
          <option key={r.reiseId} value={r.reiseId}>
            {r.zielort} ({r.startdatum} – {r.enddatum})
          </option>
        ))}
      </select>

      {selectedReise && (
        <div className="reise-details">
          <h3>Details zur gewählten Reise</h3>

          <p><strong>Zielort:</strong> {selectedReise.zielort}</p>
          <p><strong>Beschreibung:</strong> {selectedReise.beschreibung}</p>
          <p><strong>Zeitraum:</strong> {selectedReise.startdatum} – {selectedReise.enddatum}</p>

          <p><strong>Hotel:</strong>
            {selectedReise.hotel
              ? `${selectedReise.hotel.name} – ${formatPreis(selectedReise.hotel.preis)}`
              : "Kein Hotel gewählt"}
          </p>

          <p><strong>Leistungen:</strong></p>
          {selectedReise.leistungen?.length > 0 ? (
            <ul>
              {selectedReise.leistungen.map((l) => (
                <li key={l.leistungId}>
                  {l.name} – {formatPreis(l.preis)}
                </li>
              ))}
            </ul>
          ) : (
            <p>Keine Leistungen</p>
          )}

          <p><strong>Hauptkunde:</strong>
            {selectedReise.hauptkunde
              ? `${selectedReise.hauptkunde.vorname} ${selectedReise.hauptkunde.nachname}`
              : "Nicht gesetzt"}
          </p>

          <p><strong>Mitreisende:</strong></p>
          {selectedReise.mitreisende?.length > 0 ? (
            <ul>
              {selectedReise.mitreisende.map((m) => (
                <li key={m.kundeId}>
                  {m.vorname} {m.nachname}
                </li>
              ))}
            </ul>
          ) : (
            <p>Keine Mitreisenden</p>
          )}

          <p><strong>Gesamtpreis:</strong> {formatPreis(selectedReise.gesamtpreis)}</p>
        </div>
      )}
    </div>
  );
}
