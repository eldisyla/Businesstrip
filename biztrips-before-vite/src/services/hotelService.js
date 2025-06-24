// src/services/hotelService.js

import axios from "axios";

const BASE_URL = "http://localhost:8080/api/hotels";

// Alle Hotels abrufen
export async function getHotels() {
  try {
    const response = await axios.get(BASE_URL);
    return response.data;
  } catch (error) {
    console.error("🚨 Fehler beim Laden der Hotels:", error.response?.data || error);
    throw new Error("Fehler beim Laden der Hotels.");
  }
}

// Neues Hotel erstellen
export async function createHotel(hotel) {
  try {
    const response = await axios.post(BASE_URL, hotel, {
      headers: {
        "Content-Type": "application/json" // Wichtig für Spring Boot
      }
    });
    return response.data;
  } catch (error) {
    console.error("Fehler beim Erstellen des Hotels:", error.response?.data || error);
    throw new Error("Fehler beim Erstellen des Hotels.");
  }
}
