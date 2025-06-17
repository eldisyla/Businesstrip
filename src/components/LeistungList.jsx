import React, { useEffect, useState } from "react";
import {
  getLeistungen,
  createLeistung,
  deleteLeistung,
} from "../services/leistungService";

export default function LeistungList() {
  const [leistungen, setLeistungen] = useState([]);
  const [form, setForm] = useState({
    bezeichnung: "",
    preis: "",
  });

  useEffect(() => {
    loadLeistungen();
  }, []);

  const loadLeistungen = async () => {
    try {
      const data = await getLeistungen();
      setLeistungen(data);
    } catch (error) {
      console.error("Fehler beim Laden der Leistungen:", error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createLeistung(form);
      setForm({ bezeichnung: "", preis: "" });
      await loadLeistungen();
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteLeistung(id);
      await loadLeistungen();
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  return (
    <div>
      <h2>Leistungen</h2>
      <form onSubmit={handleCreate}>
        <input
          placeholder="Bezeichnung"
          value={form.bezeichnung}
          onChange={(e) =>
            setForm({ ...form, bezeichnung: e.target.value })
          }
          required
        />
        <input
          placeholder="Preis"
          type="number"
          value={form.preis}
          onChange={(e) =>
            setForm({ ...form, preis: e.target.value })
          }
          required
        />
        <button type="submit">Hinzufügen</button>
      </form>

      <ul>
        {Array.isArray(leistungen) && leistungen.length > 0 ? (
          leistungen.map((l) => (
            <li key={l.leistungId}>
              {l.bezeichnung} – {l.preis} CHF
              <button onClick={() => handleDelete(l.leistungId)}>
                Löschen
              </button>
            </li>
          ))
        ) : (
          <li>Keine Leistungen vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
