import React, { useEffect, useState } from "react";
import {
  getKunden,
  createKunde,
  deleteKunde,
} from "../services/kundeService";  // hier aus kundeService.js importieren
import "./KundeList.css"; // CSS-Datei importieren


export default function KundeList() {
  const [kunden, setKunden] = useState([]);
  const [form, setForm] = useState({
    vorname: "",
    nachname: "",
    email: "",
    telefon: "",
  });
  const [selectedKundeId, setSelectedKundeId] = useState(null);
  const [mitreisenderId, setMitreisenderId] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    loadKunden();
  }, []);

  const loadKunden = async () => {
    try {
      const data = await getKunden();
      setKunden(data);
      setErrorMessage("");
    } catch (error) {
      console.error("Fehler beim Laden der Kunden:", error);
      setErrorMessage("Kunden konnten nicht geladen werden.");
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createKunde(form);
      setForm({ vorname: "", nachname: "", email: "", telefon: "" });
      await loadKunden();
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
      setErrorMessage("Fehler beim Erstellen des Kunden.");
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteKunde(id);
      await loadKunden();
      if (selectedKundeId === id) setSelectedKundeId(null);
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
      setErrorMessage("Kunde konnte nicht gelöscht werden. Eventuell bestehen noch Buchungen.");
    }
  };

  const handleAddMitreisender = async () => {
    if (!mitreisenderId || !selectedKundeId) return;
    try {
      await addMitreisender(selectedKundeId, mitreisenderId);
      await loadKunden();
      setMitreisenderId("");
    } catch (error) {
      console.error("Fehler beim Hinzufügen von Mitreisenden:", error);
      setErrorMessage("Mitreisender konnte nicht hinzugefügt werden.");
    }
  };

  const handleRemoveMitreisender = async (hauptkundeId, mitreisenderId) => {
    try {
      await removeMitreisender(hauptkundeId, mitreisenderId);
      await loadKunden();
    } catch (error) {
      console.error("Fehler beim Entfernen von Mitreisenden:", error);
      setErrorMessage("Mitreisender konnte nicht entfernt werden.");
    }
  };

  return (
    <div className="kunden-container">
      <h2>Kunden</h2>
      {errorMessage && <p className="error-message">{errorMessage}</p>}

      <form className="kunden-form" onSubmit={handleCreate}>
        <input
          placeholder="Vorname"
          value={form.vorname}
          onChange={(e) => setForm({ ...form, vorname: e.target.value })}
          required
        />
        <input
          placeholder="Nachname"
          value={form.nachname}
          onChange={(e) => setForm({ ...form, nachname: e.target.value })}
          required
        />
        <input
          placeholder="Email"
          type="email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
          required
        />
        <input
          placeholder="Telefon"
          value={form.telefon}
          onChange={(e) => setForm({ ...form, telefon: e.target.value })}
        />
        <button type="submit">Hinzufügen</button>
      </form>

      <ul className="kunden-list">
        {Array.isArray(kunden) && kunden.length > 0 ? (
          kunden.map((k) => (
            <li
              key={k.kundeId}
              className={`kunden-item ${selectedKundeId === k.kundeId ? "selected" : ""}`}
              onClick={() => setSelectedKundeId(k.kundeId === selectedKundeId ? null : k.kundeId)}
            >
              <div>
                <strong>{k.vorname} {k.nachname}</strong><br />
                📧 {k.email}<br />
                📞 {k.telefon}
              </div>
              <button onClick={(e) => { e.stopPropagation(); handleDelete(k.kundeId); }}>Löschen</button>

              {/* Mitreisende anzeigen, wenn Kunde ausgewählt */}
              {selectedKundeId === k.kundeId && (
                <div className="mitreisende-section">
                  <h4>Mitreisende:</h4>
                  {k.mitreisende && k.mitreisende.length > 0 ? (
                    <ul>
                      {k.mitreisende.map((m) => (
                        <li key={m.kundeId}>
                          {m.vorname} {m.nachname}{" "}
                          <button onClick={() => handleRemoveMitreisender(k.kundeId, m.kundeId)}>Entfernen</button>
                        </li>
                      ))}
                    </ul>
                  ) : (
                    <p>Keine Mitreisenden vorhanden.</p>
                  )}

                  {/* Mitreisenden hinzufügen */}
                  <select
                    value={mitreisenderId}
                    onChange={(e) => setMitreisenderId(e.target.value)}
                  >
                    <option value="">Mitreisenden auswählen</option>
                    {kunden
                      .filter(c => c.kundeId !== k.kundeId && (!k.mitreisende || !k.mitreisende.some(m => m.kundeId === c.kundeId)))
                      .map((c) => (
                        <option key={c.kundeId} value={c.kundeId}>
                          {c.vorname} {c.nachname}
                        </option>
                      ))}
                  </select>
                  <button onClick={handleAddMitreisender} disabled={!mitreisenderId}>Hinzufügen</button>
                </div>
              )}
            </li>
          ))
        ) : (
          <li>Keine Kunden vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
