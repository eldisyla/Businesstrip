import React, { useEffect, useState } from "react";
import {
  getHotels,
  createHotel,
  deleteHotel,
} from "../services/hotelService";

export default function HotelList() {
  const [hotels, setHotels] = useState([]);
  const [form, setForm] = useState({
    name: "",
    ort: "",
    sterne: "",
  });

  useEffect(() => {
    loadHotels();
  }, []);

  const loadHotels = async () => {
    try {
      const data = await getHotels();
      setHotels(data);
    } catch (error) {
      console.error("Fehler beim Laden der Hotels:", error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await createHotel(form);
      setForm({ name: "", ort: "", sterne: "" });
      await loadHotels();
    } catch (error) {
      console.error("Fehler beim Erstellen:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteHotel(id);
      await loadHotels();
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  return (
    <div>
      <h2>Hotels</h2>

      <form onSubmit={handleCreate}>
        <input
          placeholder="Hotelname"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
          required
        />
        <input
          placeholder="Ort"
          value={form.ort}
          onChange={(e) => setForm({ ...form, ort: e.target.value })}
          required
        />
        <input
          type="number"
          placeholder="Sterne (1–5)"
          value={form.sterne}
          onChange={(e) => setForm({ ...form, sterne: e.target.value })}
          required
        />
        <button type="submit">Hinzufügen</button>
      </form>

      <ul>
        {Array.isArray(hotels) && hotels.length > 0 ? (
          hotels.map((hotel) => (
            <li key={hotel.hotelId}>
              {hotel.name}, {hotel.ort} – {hotel.sterne}★
              <button onClick={() => handleDelete(hotel.hotelId)}>Löschen</button>
            </li>
          ))
        ) : (
          <li>Keine Hotels vorhanden.</li>
        )}
      </ul>
    </div>
  );
}
