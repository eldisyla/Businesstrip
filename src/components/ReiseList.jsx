import React, { useEffect, useState } from "react";
import {
  getReisen,
  createReise,
  deleteReise,
} from "../services/reiseService";

export default function ReiseList() {
  const [reisen, setReisen] = useState([]);
  const [form, setForm] = useState({
    ziel: "",
    startdatum: "",
    enddatum: "",
  });

  useEffect(() => {
    loadReisen();
  }, []);

  const loadReisen = async () => {
    try {
      const data = await getReisen();
      setReisen(data);
    } catch (error) {
      console.error("Fehler beim Laden:", error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createReise(form);
      setForm({ ziel: "", startdatum: "", enddatum: "" });
      await loadReisen();
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteReise(id);
      await loadReisen();
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  return (
    <div>
      <h2>Reisen</h2>
      <form onSubmit={handleCreate}>
        <input
          placeholder="Ziel"
          value={form.ziel}
          onChange={(e) => setForm({ ...form, ziel: e.target.value })}
          required
        />
        <input
          type="date"
          value={form.startdatum}
          onChange={(e) => setForm({ ...form, startdatum: e.target.value })}
          required
        />
        <input
          type="date"
          value={form.enddatum}
          onChange={(e) => setForm({ ...form, enddatum: e.target.value })}
          required
        />
        <button type="submit">Hinzufügen</button>
      </form>

      <ul>
        {Array.isArray(reisen) && reisen.length > 0 ? (
          reisen.map((r) => (
            <li key={r.reiseId}>
              {r.ziel} ({r.startdatum} – {r.enddatum})
              <button onClick={() => handleDelete(r.reiseId)}>Löschen</button>
            </li>
          ))
        ) : (
          <li>Keine Reisen vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
