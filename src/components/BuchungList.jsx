import React, { useEffect, useState } from "react";
import {
  getBuchungen,
  createBuchung,
  deleteBuchung,
} from "../services/buchungService";

export default function BuchungList() {
  const [buchungen, setBuchungen] = useState([]);
  const [form, setForm] = useState({
    buchungsdatum: "",
    gesamtpreis: "",
    kundeId: "",
    reiseId: "",
  });

  useEffect(() => {
    loadBuchungen();
  }, []);

  const loadBuchungen = async () => {
    try {
      const data = await getBuchungen();
      setBuchungen(data);
    } catch (error) {
      console.error("Fehler beim Laden:", error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createBuchung(form);
      setForm({
        buchungsdatum: "",
        gesamtpreis: "",
        kundeId: "",
        reiseId: "",
      });
      await loadBuchungen();
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteBuchung(id);
      await loadBuchungen();
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  return (
    <div>
      <h2>Buchungen</h2>
      <form onSubmit={handleCreate}>
        <input
          type="date"
          value={form.buchungsdatum}
          onChange={(e) =>
            setForm({ ...form, buchungsdatum: e.target.value })
          }
          required
        />
        <input
          placeholder="Gesamtpreis"
          type="number"
          value={form.gesamtpreis}
          onChange={(e) =>
            setForm({ ...form, gesamtpreis: e.target.value })
          }
          required
        />
        <input
          placeholder="Kunde-ID"
          value={form.kundeId}
          onChange={(e) =>
            setForm({ ...form, kundeId: e.target.value })
          }
          required
        />
        <input
          placeholder="Reise-ID"
          value={form.reiseId}
          onChange={(e) =>
            setForm({ ...form, reiseId: e.target.value })
          }
          required
        />
        <button type="submit">Hinzufügen</button>
      </form>

      <ul>
        {Array.isArray(buchungen) && buchungen.length > 0 ? (
          buchungen.map((b) => (
            <li key={b.buchungId}>
              Buchung #{b.buchungId} – {b.buchungsdatum} – {b.gesamtpreis} CHF
              <button onClick={() => handleDelete(b.buchungId)}>
                Löschen
              </button>
            </li>
          ))
        ) : (
          <li>Keine Buchungen vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
