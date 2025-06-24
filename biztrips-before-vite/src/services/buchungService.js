const BASE_URL = "http://localhost:8080/api/buchungen";

// Alle Buchungen abrufen
export async function getBuchungen() {
  const res = await fetch(BASE_URL);
  if (!res.ok) throw new Error("Fehler beim Laden der Buchungen.");
  return res.json();
}

// Neue Buchung erstellen
export async function createBuchung(buchung) {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(buchung),
  });

  if (!res.ok) {
    const error = await res.json();
    console.error("🚨 Fehler beim Erstellen der Buchung:", error);
    throw new Error("Fehler beim Erstellen der Buchung.");
  }

  return res.json();
}

// Buchung löschen
export async function deleteBuchung(id) {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    throw new Error(`Fehler beim Löschen der Buchung mit ID ${id}.`);
  }
}
