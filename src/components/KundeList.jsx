import React, { useEffect, useState } from "react";
import {
  getKunden,
  createKunde,
  deleteKunde,
} from "../services/kundeService";

export default function KundeList() {
  const [kunden, setKunden] = useState([]);
  const [form, setForm] = useState({
    vorname: "",
    nachname: "",
    email: "",
    telefon: "",
  });

  useEffect(() => {
    loadKunden();
  }, []);

  const loadKunden = async () => {
    try {
      const data = await getKunden();
      setKunden(data);
    } catch (error) {
      console.error("Fehler beim Laden der Kunden:", error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createKunde(form);
      setForm({ vorname: "", nachname: "", email: "", telefon: "" });
      await loadKunden(); // nach erfolgreichem Speichern neu laden
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteKunde(id);
      await loadKunden(); // nach Löschen aktualisieren
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  return (
    <div>
      <h2>Kunden</h2>

      <form onSubmit={handleCreate}>
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

      <ul>
        {Array.isArray(kunden) && kunden.length > 0 ? (
          kunden.map((k) => (
            <li key={k.kundeId}>
              {k.vorname} {k.nachname} – {k.email}
              <button onClick={() => handleDelete(k.kundeId)}>Löschen</button>
            </li>
          ))
        ) : (
          <li>Keine Kunden vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
