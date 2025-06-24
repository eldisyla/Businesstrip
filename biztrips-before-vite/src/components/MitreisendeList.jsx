import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MitreisendeList.css";

function MitreisendeList() {
  const [kunden, setKunden] = useState([]);
  const [hauptkundeId, setHauptkundeId] = useState("");
  const [mitreisenderId, setMitreisenderId] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    loadKunden();
  }, []);

  const loadKunden = () => {
    axios.get("http://localhost:8080/api/kunden")
      .then(res => {
        setKunden(res.data);
        setErrorMessage("");
      })
      .catch(err => {
        console.error("Fehler beim Laden der Kunden:", err);
        setErrorMessage("Kunden konnten nicht geladen werden.");
      });
  };

  const handleHinzufuegen = () => {
    if (!hauptkundeId || !mitreisenderId) {
      setErrorMessage("Bitte Hauptkunde und Mitreisenden auswählen.");
      return;
    }
    if (hauptkundeId === mitreisenderId) {
      setErrorMessage("Hauptkunde kann nicht sich selbst als Mitreisenden hinzufügen.");
      return;
    }

    axios.post(`http://localhost:8080/api/kunden/${hauptkundeId}/mitreisende`, {
      mitreisenderId: parseInt(mitreisenderId, 10)
    })
    .then(() => {
      setErrorMessage("");
      setMitreisenderId("");
      loadKunden();
    })
    .catch(err => {
      console.error("Fehler beim Hinzufügen von Mitreisenden:", err);
      setErrorMessage("Mitreisenden konnte nicht hinzugefügt werden.");
    });
  };

  const handleLoeschen = (hauptId, mitId) => {
    axios.delete(`http://localhost:8080/api/kunden/${hauptId}/mitreisende/${mitId}`)
      .then(() => {
        setErrorMessage("");
        loadKunden();
      })
      .catch(err => {
        console.error("Fehler beim Entfernen von Mitreisenden:", err);
        setErrorMessage("Mitreisenden konnte nicht entfernt werden.");
      });
  };

  return (
    <div className="mitreisende-container">
      <h2>🤝 Mitreisende verwalten</h2>

      {errorMessage && <p className="error-message">{errorMessage}</p>}

      <div className="form-row">
        <select value={hauptkundeId} onChange={e => setHauptkundeId(e.target.value)}>
          <option value="">Hauptkunde wählen</option>
          {kunden.map(k => (
            <option key={k.kundeId} value={k.kundeId}>
              {k.vorname} {k.nachname}
            </option>
          ))}
        </select>

        <select value={mitreisenderId} onChange={e => setMitreisenderId(e.target.value)}>
          <option value="">Mitreisenden wählen</option>
          {kunden
            .filter(k => k.kundeId !== parseInt(hauptkundeId, 10))
            .map(k => (
              <option key={k.kundeId} value={k.kundeId}>
                {k.vorname} {k.nachname}
              </option>
            ))}
        </select>

        <button onClick={handleHinzufuegen} disabled={!hauptkundeId || !mitreisenderId}>
          Mitreisenden hinzufügen
        </button>
      </div>

      <div className="kundenliste">
        {kunden.length === 0 ? (
          <p>Keine Kunden vorhanden.</p>
        ) : (
          kunden.map(k => (
            <div key={k.kundeId} className="kunde-card">
              <h3>👤 {k.vorname} {k.nachname}</h3>
              {k.mitreisende && k.mitreisende.length > 0 ? (
                <ul>
                  {k.mitreisende.map(m => (
                    <li key={m.kundeId}>
                      🤝 {m.vorname} {m.nachname}
                      <button onClick={() => handleLoeschen(k.kundeId, m.kundeId)}>Löschen</button>
                    </li>
                  ))}
                </ul>
              ) : (
                <p className="leer">Keine Mitreisenden</p>
              )}
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default MitreisendeList;
