import React, { useState, useEffect } from "react";
import { getReisen, createReise } from "../services/reiseService";
import { getKunden } from "../services/kundeService";
import { getLeistungen } from "../services/leistungService";
import { getHotels } from "../services/hotelService";
import './ReiseErstellenPage.css';

export default function ReiseErstellenPage() {
  const [reisen, setReisen] = useState([]);
  const [kunden, setKunden] = useState([]);
  const [leistungen, setLeistungen] = useState([]);
  const [hotels, setHotels] = useState([]);

  const [form, setForm] = useState({
    zielort: "",
    beschreibung: "",
    startdatum: "",
    enddatum: "",
    hauptkundeId: "",
    mitreisendeIds: [],
    hotelId: "",
    leistungenIds: [],
  });

  useEffect(() => {
    loadReisen();
    loadKunden();
    loadLeistungen();
    loadHotels();
  }, []);

  const loadReisen = async () => {
    try {
      const data = await getReisen();
      setReisen(data);
    } catch (error) {
      console.error("Fehler beim Laden der Reisen:", error);
    }
  };

  const loadKunden = async () => {
    try {
      const data = await getKunden();
      setKunden(data);
    } catch (error) {
      console.error("Fehler beim Laden der Kunden:", error);
    }
  };

  const loadLeistungen = async () => {
    try {
      const data = await getLeistungen();
      setLeistungen(data);
    } catch (error) {
      console.error("Fehler beim Laden der Leistungen:", error);
    }
  };

  const loadHotels = async () => {
    try {
      const data = await getHotels();
      setHotels(data);
    } catch (error) {
      console.error("Fehler beim Laden der Hotels:", error);
    }
  };

  const handleCreateReise = async (e) => {
    e.preventDefault();
    try {
      const reiseData = {
        ...form,
        hauptkundeId: parseInt(form.hauptkundeId),
        hotelId: parseInt(form.hotelId),
        leistungenIds: form.leistungenIds.map(id => parseInt(id)),
      };

      await createReise(reiseData);
      setForm({
        zielort: "",
        beschreibung: "",
        startdatum: "",
        enddatum: "",
        hauptkundeId: "",
        mitreisendeIds: [],
        hotelId: "",
        leistungenIds: [],
      });
      loadReisen();
    } catch (error) {
      console.error("Fehler beim Erstellen der Reise:", error);
    }
  };

  return (
    <div className="reise-erstellen-container">
      <h2>Reise Erstellen</h2>

      <form onSubmit={handleCreateReise}>
        <div className="section">
          <h3>Reise Informationen</h3>
          <label>Reiseziel</label>
          <input
            type="text"
            placeholder="Zielort"
            value={form.zielort}
            onChange={(e) => setForm({ ...form, zielort: e.target.value })}
            required
          />
          <label>Beschreibung</label>
          <input
            type="text"
            placeholder="Beschreibung"
            value={form.beschreibung}
            onChange={(e) => setForm({ ...form, beschreibung: e.target.value })}
            required
          />
          <label>Startdatum</label>
          <input
            type="date"
            value={form.startdatum}
            onChange={(e) => setForm({ ...form, startdatum: e.target.value })}
            required
          />
          <label>Enddatum</label>
          <input
            type="date"
            value={form.enddatum}
            onChange={(e) => setForm({ ...form, enddatum: e.target.value })}
            required
          />
        </div>

        <div className="section">
          <h3>Hauptkunde auswählen</h3>
          <select
            value={form.hauptkundeId}
            onChange={(e) => setForm({ ...form, hauptkundeId: e.target.value })}
            required
          >
            <option value="">Hauptkunde auswählen</option>
            {kunden.map((kunde) => (
              <option key={kunde.kundeId} value={kunde.kundeId}>
                {kunde.vorname} {kunde.nachname}
              </option>
            ))}
          </select>
        </div>

        <div className="section">
          <h3>Hotel auswählen</h3>
          <select
            value={form.hotelId}
            onChange={(e) => setForm({ ...form, hotelId: e.target.value })}
            required
          >
            <option value="">Hotel auswählen</option>
            {hotels.map((hotel) => (
              <option key={hotel.hotelId} value={hotel.hotelId}>
                {hotel.name} – {hotel.ort} ({hotel.preis} CHF)
              </option>
            ))}
          </select>
        </div>

        <div className="section">
          <h3>Leistungen auswählen</h3>
          <select
            multiple
            value={form.leistungenIds}
            onChange={(e) =>
              setForm({
                ...form,
                leistungenIds: Array.from(e.target.selectedOptions, (option) => option.value)
              })
            }
            required
          >
            {leistungen.map((leistung) => (
              <option key={leistung.leistungId} value={leistung.leistungId}>
                {leistung.name} – {leistung.beschreibung} ({leistung.preis} CHF)
              </option>
            ))}
          </select>
        </div>

        <button type="submit">Reise erstellen</button>
      </form>
    </div>
  );
}
