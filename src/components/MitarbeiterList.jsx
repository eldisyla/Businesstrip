import React, { useEffect, useState } from "react";
import {
  getMitarbeiter,
  createMitarbeiter,
  deleteMitarbeiter,
} from "../services/mitarbeiterService";

export default function MitarbeiterList() {
  const [mitarbeiter, setMitarbeiter] = useState([]);
  const [form, setForm] = useState({
    name: "",
    rolle: "",
  });

  useEffect(() => {
    loadMitarbeiter();
  }, []);

  const loadMitarbeiter = async () => {
    try {
      const data = await getMitarbeiter();
      setMitarbeiter(data);
    } catch (error) {
      console.error("Fehler beim Laden:", error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createMitarbeiter(form);
      setForm({ name: "", rolle: "" });
      await loadMitarbeiter();
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteMitarbeiter(id);
      await loadMitarbeiter();
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  return (
    <div>
      <h2>Mitarbeiter</h2>
      <form onSubmit={handleCreate}>
        <input
          placeholder="Name"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
          required
        />
        <input
          placeholder="Rolle"
          value={form.rolle}
          onChange={(e) => setForm({ ...form, rolle: e.target.value })}
          required
        />
        <button type="submit">Hinzufügen</button>
      </form>

      <ul>
        {Array.isArray(mitarbeiter) && mitarbeiter.length > 0 ? (
          mitarbeiter.map((m) => (
            <li key={m.mitarbeiterId}>
              {m.name} – {m.rolle}
              <button onClick={() => handleDelete(m.mitarbeiterId)}>Löschen</button>
            </li>
          ))
        ) : (
          <li>Keine Mitarbeiter vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
