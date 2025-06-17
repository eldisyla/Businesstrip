import React, { useEffect, useState } from "react";
import {
  getKundenMitMitreisenden,
  addMitreisender,
  removeMitreisender,
} from "../services/mitreisendeService";

export default function MitreisendeList() {
  const [kunden, setKunden] = useState([]);
  const [form, setForm] = useState({ hauptkundeId: "", mitreisenderId: "" });

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    const data = await getKundenMitMitreisenden();
    setKunden(data);
  };

  const handleAdd = async (e) => {
    e.preventDefault();
    await addMitreisender(form.hauptkundeId, form.mitreisenderId);
    setForm({ hauptkundeId: "", mitreisenderId: "" });
    await loadData();
  };

  const handleRemove = async (hauptkundeId, mitreisenderId) => {
    await removeMitreisender(hauptkundeId, mitreisenderId);
    await loadData();
  };

  return (
    <div>
      <h2>Mitreisende verwalten</h2>

      <form onSubmit={handleAdd}>
        <input
          placeholder="Hauptkunde ID"
          value={form.hauptkundeId}
          onChange={(e) => setForm({ ...form, hauptkundeId: e.target.value })}
          required
        />
        <input
          placeholder="Mitreisender ID"
          value={form.mitreisenderId}
          onChange={(e) => setForm({ ...form, mitreisenderId: e.target.value })}
          required
        />
        <button type="submit">Mitreisenden hinzufügen</button>
      </form>

      <ul>
        {kunden.map((k) => (
          <li key={k.kundeId}>
            <strong>{k.vorname} {k.nachname}</strong>
            <ul>
              {k.mitreisende?.map((m) => (
                <li key={m.kundeId}>
                  {m.vorname} {m.nachname}
                  <button onClick={() => handleRemove(k.kundeId, m.kundeId)}>Entfernen</button>
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
}
